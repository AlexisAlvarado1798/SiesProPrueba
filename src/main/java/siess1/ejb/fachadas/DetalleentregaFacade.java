/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.ejb.fachadas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import siess1.jpa.entidades.Detalleentrega;

/**
 *
 * @author USUARIO
 */
@Stateless
public class DetalleentregaFacade extends AbstractFacade<Detalleentrega> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleentregaFacade() {
        super(Detalleentrega.class);
    }
    
}
