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
import siess1.jpa.entidades.Entrega;
import siess1.jpa.entidades.Equipo;

/**
 *
 * @author USUARIO
 */
@Stateless
public class EntregaFacade extends AbstractFacade<Entrega> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntregaFacade() {
        super(Entrega.class);
    }
    public List<Equipo> obtenerEquiposNoEntregados(){
       StringBuilder sbSQL = new StringBuilder("select * from equipo where pk_equipo not in ");
        sbSQL.append("(select e.pk_equipo from equipo e inner join definitiva d on e.pk_equipo=d.fk_equipo) ");

        Query SQL = em.createNativeQuery(sbSQL.toString(), Equipo.class);
        List<Equipo> lstEquipos;
        try {
            lstEquipos = (List<Equipo>) SQL.getResultList();
        } catch (NoResultException e) {
            return null;
        }
        return lstEquipos;
    }
    
}
