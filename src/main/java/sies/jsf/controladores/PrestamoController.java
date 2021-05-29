package sies.jsf.controladores;

import java.io.IOException;
import siess1.jpa.entidades.Prestamo;
import siess1.jsf.controladores.util.JsfUtil;
import siess1.jsf.controladores.util.PaginationHelper;
import siess1.ejb.fachadas.PrestamoFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.model.DualListModel;
import siess1.jpa.entidades.Detalleprestamo;
import siess1.jpa.entidades.Equipo;
import siess1.jpa.entidades.Usuario;
import siess1.jsf.controladores.util.Session;

@Named("prestamoController")
@SessionScoped
public class PrestamoController implements Serializable {

    private Prestamo current;
    private DataModel items = null;
    @EJB
    private siess1.ejb.fachadas.PrestamoFacade ejbFacade;

    @EJB
    private siess1.ejb.fachadas.EquipoFacade ejbEquipoFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @EJB
    private siess1.ejb.fachadas.DetalleprestamoFacade oDetallePrestamoFacade;

    List<Equipo> equiposSource = new ArrayList<>();
    List<Equipo> equiposTarget = new ArrayList<>();

    List<Equipo> eqprestados = new ArrayList<>();
    List<Prestamo> lstPrestamos = new ArrayList<>();

    Usuario oUsuario = new Usuario();
    Session oSession = new Session();

    @Resource
    UserTransaction oTransaction;
    private DualListModel<Equipo> equipos;

    @PostConstruct
    public void init() {

        equiposSource = ejbEquipoFacade.obtenerEquiposDisponibles();
        equipos = new DualListModel<>(equiposSource, equiposTarget);

    }

    public DualListModel<Equipo> getEquipos() {
        return equipos;
    }

    public DualListModel<Equipo> getEquiposPrestamo() {
        if (current != null) {
            eqprestados = getEquiposPres();
            equipos = new DualListModel<>(eqprestados, new ArrayList<Equipo>());
        }
        return equipos;
    }

    public List<Equipo> getEquiposPres() {
        if (current != null) {
            eqprestados = ejbEquipoFacade.obtenerEquiposDelPrestamo(getSelected().getPkPrestamo());
        }
        return eqprestados;
    }

    public void setEquipos(DualListModel<Equipo> equipos) {
        this.equipos = equipos;
    }

    public PrestamoController() {
        oUsuario = oSession.obtenerUsuarioLogueado();
    }

    public Prestamo getSelected() {
        if (current == null) {
            current = new Prestamo();
            current.setFechasolicitud(new Date());
            selectedItemIndex = -1;
        }
        return current;
    }

    private PrestamoFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(100000) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {

                    if (oUsuario.getTipousuarioenum().intValue() == 0) {
                        return new ListDataModel(ejbFacade.findAll());
                    } else {
                        return new ListDataModel(ejbFacade.obtenerEquiposPorPrestamo(oUsuario.getPkUsuario()));
                    }
                    //return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Prestamo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Prestamo();
        equiposTarget = new ArrayList<>();
        selectedItemIndex = -1;
        return "Create";
    }

    public List<Prestamo> getLstPrestamos() {
        if (oUsuario.getTipousuarioenum().intValue() == 0) {
            lstPrestamos = ejbFacade.findAll();
        } else {
            lstPrestamos = ejbFacade.obtenerEquiposPorPrestamo(oUsuario.getPkUsuario());
        }
        return lstPrestamos;
    }

//     public List<Prestamo> getLstPrestamos() {
//        if (oUsuario.getTipousuarioenum().intValue() == 0) {
//            lstPrestamos = ejbFacade.findAll();
//        } else {
//            lstPrestamos = ejbFacade.obtenerEquiposPorPrestamo(oUsuario.getPkUsuario());
//        }
//        return lstPrestamos;
//    }
    public void setLstPrestamos(List<Prestamo> lstPrestamos) {
        this.lstPrestamos = lstPrestamos;
    }

    public String create() {

        try {
            oTransaction.begin();

            String codigo = java.util.UUID.randomUUID().toString();
            codigo = codigo.replaceAll("-", "");
            codigo = codigo.substring(0, 19);
            current.setCodigo(codigo);
            current.setCreadopor(oUsuario.getPkUsuario());

            getFacade().create(current);
            Object[] eq = equipos.getTarget().toArray();
            for (int i = 0; i < eq.length; i++) {
                String equipoSeleccionado = (String) eq[i];
                String placaSena = equipoSeleccionado.split("-")[0];
                equiposTarget.add(ejbEquipoFacade.obtenerEquipo(placaSena));
            }

            for (Equipo equipo : equiposTarget) {
                Detalleprestamo oDP = new Detalleprestamo();
                oDP.setFkPrestamo(current);
                oDP.setFkEquipo(equipo);
                oDetallePrestamoFacade.create(oDP);
            }

            oTransaction.commit();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("PrestamoCreated"));
            return prepareCreate();

        } catch (Exception e) {

            try {
                oTransaction.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex) {
                java.util.logging.Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }

            JsfUtil.addErrorMessage(e,
                    ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

    }

    public String prepareEdit() {
        current = (Prestamo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("PrestamoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Prestamo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("PrestamoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Prestamo getPrestamo(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Prestamo.class)
    public static class PrestamoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PrestamoController controller = (PrestamoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "prestamoController");
            return controller.getPrestamo(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Prestamo) {
                Prestamo o = (Prestamo) object;
                return getStringKey(o.getPkPrestamo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Prestamo.class.getName());
            }
        }

    }
    
    public void descargarFormato() throws IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(0.0);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"prestamo.xls\"");
        
        workbook.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }

}
