package sies.jsf.controladores;

import siess1.jpa.entidades.Usuario;
import siess1.jsf.controladores.util.JsfUtil;
import siess1.jsf.controladores.util.PaginationHelper;
import siess1.jsf.controladores.util.AlgoritmoMD5;
import siess1.ejb.fachadas.UsuarioFacade;
import siess1.jsf.controladores.util.Session;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
import org.jboss.logging.Logger;
import siess1.jpa.entidades.Estado;
import siess1.jpa.entidades.Estadousuario;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario current;
    private DataModel items = null;
    @EJB
    private siess1.ejb.fachadas.UsuarioFacade ejbFacade;
    @EJB
    private siess1.ejb.fachadas.EstadousuarioFacade oEstadoUsuarioFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Session oSession;
    private Boolean administrador = null;
    private boolean adminChecked;
    private Estado estado;
    private Date fechaInicioEstado;
    private Date fechaFinEstado;

    @Resource
    UserTransaction oTransaction;

//    public Boolean getAdministrador() {
//        
//        if (administrador == null) {
//            administrador=oSession.esAdminitrador();
//        }
//        
//        return administrador;
//    }
    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isAdminChecked() {
        return adminChecked;
    }

    public void setAdminChecked(boolean adminChecked) {
        this.adminChecked = adminChecked;
    }

    
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFechaInicioEstado() {
        return fechaInicioEstado;
    }

    public void setFechaInicioEstado(Date fechaInicioEstado) {
        this.fechaInicioEstado = fechaInicioEstado;
    }

    public Date getFechaFinEstado() {
        return fechaFinEstado;
    }

    public void setFechaFinEstado(Date fechaFinEstado) {
        this.fechaFinEstado = fechaFinEstado;
    }

    public Session getOSession() {
        return oSession;
    }

    public void setoSession(Session oSession) {
        this.oSession = oSession;
    }

    public UsuarioController() {
        oSession = new Session();
    }

    public Usuario getSelected() {
        if (current == null) {
            current = new Usuario();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UsuarioFacade getFacade() {
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
        current = (Usuario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Usuario();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            oTransaction.begin();

            String contrasenamd5 = AlgoritmoMD5.cifrarMD5(current.getContrasena());
            current.setContrasena(contrasenamd5);

            BigInteger val = adminChecked ? new BigInteger("0") : new BigInteger("1");
            current.setTipousuarioenum(val);
            getFacade().create(current);

            Estadousuario oEU = new Estadousuario();
            oEU.setFkUsuario(current);
            oEU.setFkEstado(estado);
            oEU.setFechainicio(fechaInicioEstado);
            oEU.setFechafin(fechaFinEstado);
            oEstadoUsuarioFacade.create(oEU);
            oTransaction.commit();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("UsuarioCreated"));
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
        current = (Usuario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("UsuarioUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Usuario) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("UsuarioDeleted"));
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
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public Usuario getUsuario(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getPkUsuario());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuario.class.getName());
            }
        }

    }

    public void autenticarUsuario() {

        String strContrasena = current.getContrasena();
        String strContrasenaMD5 = AlgoritmoMD5.cifrarMD5(strContrasena);
        Usuario oUsuario = ejbFacade.autenticar(current.getNombreusuario(),
                strContrasenaMD5, oEstadoUsuarioFacade);
        String strUrlMenu = null;

        if (oUsuario != null) {
            strUrlMenu = obtenerRutaMenuPrincipal(oUsuario);
            if (oSession != null) {
                oSession.crearSesion_JSF_HTTP(true);
                oSession.getoSession().setAttribute("oSesionUsuario", oUsuario);
                oSession.redireccionarUrl(strUrlMenu);
            }
        }
    }

    public void cerrarSesion() throws IOException {
        if (oSession != null) {
            oSession.crearSesion_JSF_HTTP(false);
            oSession.redireccionarUrl("/faces/index.xhtml");
        }
    }

    public String obtenerRutaMenuPrincipal() {

        String strRutaActual = "/faces/index.xhtml";
        if (oSession.getoSession() != null) {
            Usuario oU = (Usuario) oSession.getoSession().getAttribute("oSesionUsuario");
            int intTipoUsuario = oU.getTipousuarioenum().intValue();
            switch (intTipoUsuario) {
                case 0:
                    // Administrador

                    strRutaActual
                            = "/faces/catalogos/menuAdmin.xhtml?faces-redirect=true";
                    break;
                case 1:
                    // Usuario

                    strRutaActual = "/faces/menuUsuario.xhtml?faces-redirect=true";
                    break;
                default:
                    break;
            }
        }
        return strRutaActual;
    }

    public String obtenerRutaMenuPrincipal(Usuario oU) {

        String strRutaActual = "/faces/index.xhtml";
        int intTipoUsuario = oU.getTipousuarioenum().intValue();
        switch (intTipoUsuario) {
            case 0:
                // Administrador

                strRutaActual
                        = "/faces/catalogos/menuAdmin.xhtml?faces-redirect=true";
                break;
            case 1:
                // Usuario

                strRutaActual = "/faces/menuUsuario.xhtml?faces-redirect=true";
                break;
            default:
                break;
        }

        return strRutaActual;
    }

    public String obtenerNombreCompletoUsuarioPorId(BigDecimal UsuarioId) {
        return ejbFacade.obtenerNombreCompletoUsuarioPorId(UsuarioId);
    }

    public Usuario obtenerUsuarioLogueado() {
        return oSession.obtenerUsuarioLogueado();
    }

}
