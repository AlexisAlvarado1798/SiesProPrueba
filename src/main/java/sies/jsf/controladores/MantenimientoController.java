package sies.jsf.controladores;

import siess1.jpa.entidades.Mantenimiento;
import siess1.jsf.controladores.util.JsfUtil;
import siess1.jsf.controladores.util.PaginationHelper;
import siess1.ejb.fachadas.MantenimientoFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import siess1.jpa.entidades.Detalleprestamo;
import siess1.jpa.entidades.Equipo;

@Named("mantenimientoController")
@SessionScoped
public class MantenimientoController implements Serializable {

    private Mantenimiento current;
    private DataModel items = null;
    @EJB
    private siess1.ejb.fachadas.MantenimientoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    @Resource
    UserTransaction oTransaction;

    public MantenimientoController() {
    }

    public Mantenimiento getSelected() {
        if (current == null) {
            current = new Mantenimiento();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MantenimientoFacade getFacade() {
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
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
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
        current = (Mantenimiento) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Mantenimiento();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {

        try {
            oTransaction.begin();

            String codigo = java.util.UUID.randomUUID().toString();
            codigo = codigo.replaceAll("-", "");
            codigo = codigo.substring(0, 19);
            current.setCodigo(codigo);

            getFacade().create(current);

            oTransaction.commit();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("MantenimientoCreated"));
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
        current = (Mantenimiento) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("MantenimientoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Mantenimiento) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("MantenimientoDeleted"));
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

    public Mantenimiento getMantenimiento(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Mantenimiento.class)
    public static class MantenimientoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MantenimientoController controller = (MantenimientoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mantenimientoController");
            return controller.getMantenimiento(getKey(value));
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
            if (object instanceof Mantenimiento) {
                Mantenimiento o = (Mantenimiento) object;
                return getStringKey(o.getPkMantenimiento());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Mantenimiento.class.getName());
            }
        }

    }

}
