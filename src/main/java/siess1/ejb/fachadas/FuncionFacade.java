/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.ejb.fachadas;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import siess1.jpa.entidades.Funcion;

/**
 *
 * @author USUARIO
 */
@Stateless
public class FuncionFacade extends AbstractFacade<Funcion> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuncionFacade() {
        super(Funcion.class);
    }
         public List<Funcion> obtenerFuncion(String valorBusqueda) {
        StringBuilder sbsql = new StringBuilder("SELECT *  FROM FUNCION ");
        sbsql.append(" WHERE  lower(NOMBRE) LIKE '%");
        sbsql.append(valorBusqueda);
        sbsql.append("%'");
        
        Query sql = em.createNativeQuery(sbsql.toString(), Funcion.class);
        
        List<Funcion> lstFunciones;
        
        try{
            lstFunciones=(List<Funcion>)sql.getResultList();
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
        return lstFunciones;
    }
           public Funcion getFuncion(String nombre) {
        Query consulta = em.createNamedQuery("Funcion.findByNombre");
        consulta.setParameter("nombre", nombre);
        Funcion oFuncion;
        try {
            oFuncion = (Funcion) consulta.getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return oFuncion;
    }
    
}
