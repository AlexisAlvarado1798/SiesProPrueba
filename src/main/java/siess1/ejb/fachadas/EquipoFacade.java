/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.ejb.fachadas;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import siess1.jpa.entidades.Detalleprestamo;
import siess1.jpa.entidades.Equipo;
import siess1.jpa.entidades.Municipio;
import siess1.jpa.entidades.Prestamo;

/**
 *
 * @author Aprendiz
 */
@Stateless
public class EquipoFacade extends AbstractFacade<Equipo> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoFacade() {
        super(Equipo.class);
    }

    public Equipo obtenerEquipo(String placaSena) {
        Query consulta = em.createNamedQuery("Equipo.findByPlacasena");
        consulta.setParameter("placasena", new BigInteger(placaSena));
        Equipo oEquipo = new Equipo();
        try {
            oEquipo = (Equipo) consulta.getSingleResult();
            
        } catch (NoResultException e) {
            System.err.println(e.getMessage());
        }
        return oEquipo;

    }
    
     public List<Equipo> obtenerEquiposDisponibles() {

        StringBuilder sbSQL = new StringBuilder("select * from equipo where pk_equipo not in  ");
        sbSQL.append("(select e.pk_equipo from equipo e inner join definitiva d on e.pk_equipo=d.fk_equipo) ");
        sbSQL.append(" and pk_equipo not in (select e.pk_equipo from equipo e inner join mantenimiento m on e.pk_equipo=m.fk_equipo) ");
        sbSQL.append(" and pk_equipo not in (select pk_equipo from equipo e inner join detalleentrega de on e.pk_equipo = de.fk_equipo ) ");
        sbSQL.append(" and  pk_equipo not in ( select pk_equipo from equipo e ");
        sbSQL.append("  inner join detalleprestamo dp on e.pk_equipo= dp.fk_equipo ");
        sbSQL.append(" inner join prestamo p on dp.fk_prestamo=p.pk_prestamo ");
        sbSQL.append("where p.fechasolicitud <= sysdate) ");

        Query SQL = em.createNativeQuery(sbSQL.toString(), Equipo.class);
        List<Equipo> lstEquipos;
        try {
            lstEquipos = (List<Equipo>) SQL.getResultList();
        } catch (NoResultException e) {
            return null;
        }
        return lstEquipos;
    }
     
     public List<Equipo> obtenerEquiposDelPrestamo(BigDecimal prestamo){
         StringBuilder consulta = new StringBuilder("select e.* from equipo e inner join detalleprestamo d ");
                 consulta.append("on e.pk_equipo = d.fk_equipo where ");
                 consulta.append(" d.fk_prestamo='");
                 consulta.append(prestamo);
                 consulta.append("'");
         
                 Query sql = em.createNativeQuery(consulta.toString(), Equipo.class);
                 List<Equipo> lstEquipos;
         
       try {
            lstEquipos = (List<Equipo>) sql.getResultList();
        } catch (NoResultException e) {
            return null;
        }
        return lstEquipos;
     }
     
      public List<Equipo> getEquipos(String valorBusqueda) {
        StringBuilder sbSQL = new StringBuilder("SELECT E.* FROM EQUIPO E ");
        sbSQL.append(" WHERE ");
        sbSQL.append("  lower(E.NOMBRE) LIKE '%");
        sbSQL.append(valorBusqueda.toLowerCase());
        sbSQL.append("%'");

        String squ = sbSQL.toString();
        
        Query SQL = em.createNativeQuery(sbSQL.toString(), Equipo.class);
        List<Equipo> lstEquipos;
        try {
            lstEquipos = (List<Equipo>) SQL.getResultList();
        } catch (Exception e) {
            return null;
        }
        return lstEquipos;
    }
         public List<Equipo> obtenerEquiposOcupados() {

        StringBuilder sbSQL = new StringBuilder("select * from equipo where pk_equipo  ");
        sbSQL.append(" in ( select pk_equipo from equipo e ");
        sbSQL.append("  inner join detalleprestamo dp on e.pk_equipo= dp.fk_equipo ");
        sbSQL.append(" inner join prestamo p on dp.fk_prestamo=p.pk_prestamo ");
        sbSQL.append("where p.fechasolicitud <= sysdate) ");

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
