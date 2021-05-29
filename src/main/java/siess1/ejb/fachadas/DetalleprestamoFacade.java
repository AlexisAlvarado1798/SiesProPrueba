/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.ejb.fachadas;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import siess1.jpa.entidades.Detalleprestamo;

/**
 *
 * @author USUARIO
 */
@Stateless
public class DetalleprestamoFacade extends AbstractFacade<Detalleprestamo> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleprestamoFacade() {
        super(Detalleprestamo.class);
    }
    
    public List<Detalleprestamo> obtenerDetalleprestamoPorPrestamo(BigDecimal idPrestamo) 
    {
        StringBuilder consulta = new StringBuilder("select * from Detalleprestamo dp ");
        consulta.append("where dp.fk_prestamo =");
        consulta.append(idPrestamo);
        
        Query sql = em.createNativeQuery(consulta.toString(), Detalleprestamo.class);
        
        List<Detalleprestamo> lstDetalleprestamo;
        try { 
            lstDetalleprestamo = (List<Detalleprestamo>) sql.getResultList();
        } catch (NoResultException e) { return null; } return lstDetalleprestamo; 
    }
    
}
