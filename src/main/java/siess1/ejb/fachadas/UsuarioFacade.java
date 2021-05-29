/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.ejb.fachadas;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import siess1.jpa.entidades.Usuario;

/**
 *
 * @author Aprendiz
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario buscarUsuario(String login, String pass) {
        Query consulta = em.createNamedQuery("Usuario.findByNombreusuario");
        consulta.setParameter("nombreusuario", login);
        consulta.setParameter("contrasena", pass);

        try {
            Usuario oUsuario = (Usuario) consulta.getSingleResult();
            em.refresh(oUsuario);
            return oUsuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    public String obtenerNombreCompletoUsuarioPorId(BigDecimal UsuarioId) {
        StringBuilder sbSQL = new StringBuilder(" Select login from U where pk_Usuario = ");
        sbSQL.append(UsuarioId);
        Query consulta = em.createNamedQuery(sbSQL.toString());
        String strNombreCompleto = "";

        try {
            strNombreCompleto = consulta.getSingleResult().toString();
        } catch (NoResultException e) {
            return strNombreCompleto;
        }
        return strNombreCompleto;

    }

    public Usuario autenticar(String login, String password,
            siess1.ejb.fachadas.EstadousuarioFacade fachadaEstadoUsuario) {

        FacesMessage msg = null;
        Usuario oUsuario = buscarUsuario(login, password);
        if (oUsuario != null) {
            boolean blnEstadoUsuario
                    = fachadaEstadoUsuario.estadoUsuario(oUsuario.getPkUsuario());
            if (blnEstadoUsuario) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, " Bienvenido", login);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return oUsuario;
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario Inactivo", login);

            }

        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Credenciales Inválidas");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }
     public Usuario encontrarUsuario(String nombre, String pass){
        StringBuilder sbSQL = new StringBuilder("select * from usuario where  ");
        sbSQL.append(" nombreusuario='");
        sbSQL.append(nombre);
        sbSQL.append("' and contrasena=(select upper(md5hash('");
        sbSQL.append(pass);
        sbSQL.append("' )) from dual)");

       
        Query SQL = em.createNativeQuery(sbSQL.toString(), Usuario.class);
       Usuario oUsuario = new Usuario();
        try {
            oUsuario = (Usuario) SQL.getSingleResult();
           
        } catch (NoResultException e) {
            System.err.println(e.getMessage());
        }
        return oUsuario;

    }

}
