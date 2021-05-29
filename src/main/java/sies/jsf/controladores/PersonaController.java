package sies.jsf.controladores;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import siess1.jpa.entidades.Persona;
import siess1.jsf.controladores.util.JsfUtil;
import siess1.jsf.controladores.util.PaginationHelper;
import siess1.ejb.fachadas.PersonaFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.jboss.logging.Logger;
import siess1.jpa.entidades.Departamento;
import siess1.jpa.entidades.Pais;

@Named("personaController")
@SessionScoped
public class PersonaController implements Serializable {

    private Persona current;
    private DataModel items = null;
    @EJB
    private siess1.ejb.fachadas.PersonaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    @Resource
    protected UserTransaction oTransaction;
    private Departamento departamento;
    private Pais pais;

    public Persona getCurrent() {
        return current;
    }

    public void setCurrent(Persona current) {
        this.current = current;
    }

    public PersonaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PersonaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public UserTransaction getoTransaction() {
        return oTransaction;
    }

    public void setoTransaction(UserTransaction oTransaction) {
        this.oTransaction = oTransaction;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public PersonaController() {
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Persona getSelected() {
        if (current == null) {
            current = new Persona();
            selectedItemIndex = -1;
        }
        return current;
    }

    private PersonaFacade getFacade() {
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
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(),
                        getPageFirstItem() + getPageSize()}));
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
        current = (Persona) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Persona();
        pais = null;
        departamento = null;
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            oTransaction.begin();
            getFacade().create(current);
            oTransaction.commit();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("PersonaCreated"));
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
        current = (Persona) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("PersonaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Persona) getItems().getRowData();
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
            //getFacade().remove(current);
            getFacade().eliminar(current.getPkPersona());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("PersonaDeleted"));
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

    public Persona getPersona(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    public Persona getPersona(String identificacion) {
        return ejbFacade.getPersonaByIdentificacion(identificacion);
    }

    @FacesConverter(forClass = Persona.class)
    public static class PersonaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonaController controller = (PersonaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personaController");
            String identificacion = value.split("-")[0].trim();
            return controller.getPersona(identificacion);
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
            if (object instanceof Persona) {
                Persona o = (Persona) object;
                return getStringKey(o.getPkPersona());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Persona.class.getName());
            }
        }

    }

    public List<Persona> completarText(String valorBusqueda) {
        return ejbFacade.obtenerPersonasSinUsuarioAsociado(valorBusqueda);
    }

    public List<Persona> completeTexto(String valorBusqueda) {
        List<Persona> personas = ejbFacade.getPersonas(valorBusqueda);
        return personas;
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "assets" + File.separator + "Koala.png";

        Paragraph titulo = new Paragraph("Centro de Comercio y servicios Regional-Cauca", new Font(Font.HELVETICA, 25));
        titulo.setAlignment("center");
        titulo.setSpacingAfter(10);
        pdf.add(titulo);

        Paragraph subtitulo = new Paragraph("Centro de Simulación en Salud", new Font(Font.HELVETICA, 15));
        subtitulo.setAlignment("center");
        subtitulo.setSpacingAfter(10);
        pdf.add(subtitulo);

        pdf.addAuthor("SIESS");

        pdf.add(Image.getInstance(logo));
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.AQUA.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        
        for (int j = 0; j < header.getRowNum(); j++) {
            for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {

              HSSFCell cell = header.getCell(i);
                 cell.setCellStyle(cellStyle);
            }
        }
    }

    public void descargarFomato(Object document) {

        HSSFWorkbook libro = (HSSFWorkbook) document;
        HSSFSheet hoja = libro.getSheetAt(0);
        HSSFRow fila = hoja.getRow(0);
        //    HSSFCell celda= fila.createCell(0);

        String texto = getDepartamento().getNombre();

        HSSFRichTextString text = new HSSFRichTextString(texto);

        for (int i = 0; i < fila.getPhysicalNumberOfCells(); i++) {
            HSSFCell celda = fila.getCell(i);
            celda.setCellValue(text);
        }

//        try{
//            FileOutputStream arch = new FileOutputStream("prueba.xls");
//            libro.write(arch);
//            arch.close();
//        }catch(Exception e){
//            System.err.println(e.getMessage());
//        }
    }

}
