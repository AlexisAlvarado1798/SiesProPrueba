/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.ejb.fachadas;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import siess1.jpa.entidades.Equipo;
import siess1.jpa.entidades.Proveedor;

/**
 *
 * @author USUARIO
 */
@Stateless
public class ProveedorFacade extends AbstractFacade<Proveedor> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorFacade() {
        super(Proveedor.class);
    }

    public List<Proveedor> getProveedores(String valorBusqueda) {
        StringBuilder sbSQL = new StringBuilder("SELECT *  FROM PROVEEDOR ");
        sbSQL.append(" WHERE  lower(NOMBRRE) LIKE '%");
        sbSQL.append(valorBusqueda.toLowerCase());
        sbSQL.append("%'");
        sbSQL.append(" OR  lower(NIT) LIKE '%");
        sbSQL.append(valorBusqueda.toLowerCase());
        sbSQL.append("%'");

        String squ = sbSQL.toString();

        Query SQL = em.createNativeQuery(sbSQL.toString(), Proveedor.class);
        List<Proveedor> lstProveedores;
        try {
            lstProveedores = (List<Proveedor>) SQL.getResultList();
        } catch (Exception e) {
            return null;
        }
        return lstProveedores;
    }
        public Proveedor getProveedor(String nit) {
        Query consulta = em.createNamedQuery("Proveedor.findByNit");
        consulta.setParameter("nit", new BigInteger(nit));
        Proveedor oProveedor = new Proveedor();
        try {
            oProveedor = (Proveedor) consulta.getSingleResult();
            
        } catch (NoResultException e) {
            System.err.println(e.getMessage());
        }
        return oProveedor;

    }
//
//    public List<Proveedor> obtenerProveedores(String filtro) {
//        Query consulta = em.createNamedQuery("Proveedor.findFiltro");
//        consulta.setParameter("nombrre", new BigInteger(filtro));
//        List<Proveedor> lstPro = new ArrayList<>();
//        try {
//            lstPro = consulta.getResultList();
//
//        } catch (NoResultException e) {
//            System.err.println(e.getMessage());
//        }
//        return lstPro;
//
//    }
}
