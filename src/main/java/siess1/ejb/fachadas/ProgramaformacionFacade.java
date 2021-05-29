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
import siess1.jpa.entidades.Programaformacion;

/**
 *
 * @author USUARIO
 */
@Stateless
public class ProgramaformacionFacade extends AbstractFacade<Programaformacion> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramaformacionFacade() {
        super(Programaformacion.class);
    }

    public Programaformacion obtenerProgramaPorFicha(String codigo) {
        StringBuilder sbsql = new StringBuilder("select pf.nombre from ficha f inner join ");
        sbsql.append("programaformacion pf on f.fk_programaformacion=pf.pk_programaformacion ");
        sbsql.append("where f.codigo='");
        sbsql.append(codigo);
        sbsql.append("'");
        
        Query sql = em.createNativeQuery(sbsql.toString(), Programaformacion.class);
        
        Programaformacion oPrograma;
        
        try{
            oPrograma=(Programaformacion)sql.getSingleResult();
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
        return oPrograma;
    }

     public List<Programaformacion> obtenerPrograma(String valorBusqueda) {
        StringBuilder sbsql = new StringBuilder("SELECT *  FROM PROGRAMAFORMACION ");
        sbsql.append(" WHERE  lower(NOMBRE) LIKE '%");
        sbsql.append(valorBusqueda);
        sbsql.append("%'");
        sbsql.append(" or  lower(sigla) LIKE '%");
        sbsql.append(valorBusqueda);
        sbsql.append("%'");
        
        Query sql = em.createNativeQuery(sbsql.toString(), Programaformacion.class);
        
        List<Programaformacion> lstPrograma;
        
        try{
            lstPrograma=(List<Programaformacion>)sql.getResultList();
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
        return lstPrograma;
    }
       public Programaformacion getProgramaByCodigo(String codigo) {
        Query consulta = em.createNamedQuery("Programaformacion.findByCodigo");
        consulta.setParameter("codigo", codigo);
        Programaformacion oFabricante;
        try {
            oFabricante = (Programaformacion) consulta.getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return oFabricante;
    }
}
