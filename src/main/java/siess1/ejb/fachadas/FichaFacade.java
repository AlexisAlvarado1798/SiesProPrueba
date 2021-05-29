/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.ejb.fachadas;

import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import siess1.jpa.entidades.Ficha;

/**
 *
 * @author USUARIO
 */
@Stateless
public class FichaFacade extends AbstractFacade<Ficha> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FichaFacade() {
        super(Ficha.class);
    }

    public List<Ficha> obtenerFichas(String codigo) {
        StringBuilder sbSQL = new StringBuilder("SELECT F.* FROM FICHA F ");
        sbSQL.append(" WHERE ");
        sbSQL.append(" F.CODIGO LIKE '%");
        sbSQL.append(codigo);
        sbSQL.append("%' ");

        String squ = sbSQL.toString();
        
        Query SQL = em.createNativeQuery(sbSQL.toString(), Ficha.class);
        List<Ficha> lstFichas;
        try {
            lstFichas = (List<Ficha>) SQL.getResultList();
        } catch (NoResultException e) {
            return null;
        }
        return lstFichas;

    }  public Ficha getFabricante(String codigo) {
        Query consulta = em.createNamedQuery("Ficha.findByCodigo");
        consulta.setParameter("codigo", codigo);
        Ficha oFicha;
        try {
            oFicha = (Ficha) consulta.getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return oFicha;
    }

}
