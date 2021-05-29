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
import siess1.jpa.entidades.Fabricante;
import siess1.jpa.entidades.Programaformacion;

/**
 *
 * @author USUARIO
 */
@Stateless
public class FabricanteFacade extends AbstractFacade<Fabricante> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FabricanteFacade() {
        super(Fabricante.class);
    }
      public List<Fabricante> obtenerFabricantes(String valorBusqueda) {
        StringBuilder sbsql = new StringBuilder("SELECT *  FROM FABRICANTE ");
        sbsql.append(" WHERE  lower(NOMBRE) LIKE '%");
        sbsql.append(valorBusqueda);
        sbsql.append("%'");
        
        Query sql = em.createNativeQuery(sbsql.toString(), Fabricante.class);
        
        List<Fabricante> lstFabricantes;
        
        try{
            lstFabricantes=(List<Fabricante>)sql.getResultList();
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
        return lstFabricantes;
    }
        public Fabricante getFabricante(String nombre) {
        Query consulta = em.createNamedQuery("Fabricante.findByNombre");
        consulta.setParameter("nombre", nombre);
        Fabricante oFabricante;
        try {
            oFabricante = (Fabricante) consulta.getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return oFabricante;
    }
}
