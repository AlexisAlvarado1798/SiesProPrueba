/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.ejb.fachadas;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import siess1.jpa.entidades.Estadousuario;

/**
 *
 * @author Aprendiz
 */
@Stateless
public class EstadousuarioFacade extends AbstractFacade<Estadousuario> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadousuarioFacade() {
        super(Estadousuario.class);
    }
    
    public boolean estadoUsuario(BigDecimal UsuarioId){
     Query consulta = em.createNativeQuery("SELECT CODIGO FROM ESTADO E INNER JOIN ESTADOUSUARIO ESU "+
             "ON E.PK_ESTADO = ESU.FK_ESTADO "+
             "WHERE ESU.PK_ESTADOUSUARIO = (SELECT MAX"+
             "(ESTADOUSUARIO.PK_ESTADOUSUARIO)"+
             "FROM ESTADOUSUARIO WHERE ESTADOUSUARIO.FK_USUARIO ="+ UsuarioId
             +"AND (SYSDATE -1 BETWEEN ESTADOUSUARIO.FECHAINICIO AND ESTADOUSUARIO.FECHAFIN))");
     
     try {
         String strResult = (String) consulta.getSingleResult();
         return strResult.equals("1");
     }catch (NoResultException e){
         return false;
     }
    }
}
