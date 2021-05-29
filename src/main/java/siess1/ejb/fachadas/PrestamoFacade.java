/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.ejb.fachadas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import siess.dto.menu.DtoPrestamo;
import siess1.jpa.entidades.Prestamo;
import siess1.jpa.entidades.Usuario;

/**
 *
 * @author USUARIO
 */
@Stateless
public class PrestamoFacade extends AbstractFacade<Prestamo> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestamoFacade() {
        super(Prestamo.class);
    }

    public List<Prestamo> obtenerEquiposPorPrestamo(BigDecimal idResponsable) {
        StringBuilder consulta = new StringBuilder("select * from prestamo p inner join usuario u ");
        consulta.append("on p.creadopor = u.pk_usuario where (creadopor =");
        consulta.append(idResponsable);
        consulta.append(" or p.fk_responsable = (select fk_persona from usuario usu where usu.pk_usuario =");
        consulta.append(idResponsable);
        consulta.append("))");
        consulta.append(" order by p.fechasolicitud desc ");

        Query sql = em.createNativeQuery(consulta.toString(), Prestamo.class);
        List<Prestamo> lstEquipos;

        try {
            lstEquipos = (List<Prestamo>) sql.getResultList();
        } catch (NoResultException e) {
            return null;
        }
        return lstEquipos;
    }

    public List<DtoPrestamo> obtenerPrestamoyEquipo() {
        StringBuilder consulta = new StringBuilder("select p.fk_ficha,p.fk_municipio, p.fk_responsable,p.codigo ");
        consulta.append(" ,p.fechasolicitud ,p.fechadevolucion , p.observacion, dp.fk_equipo ");
        consulta.append("from prestamo p inner join detalleprestamo");
        consulta.append(" dp on p.pk_prestamo=dp.fk_prestamo order by dp.fk_equipo");

        try {
            Query sql = em.createNativeQuery(consulta.toString());
            List<DtoPrestamo> lstPrestamos= new ArrayList<>();
            
            List<Object[]> listaAcciones = sql.getResultList();
            
            for (Object[] accion : listaAcciones) {
                DtoPrestamo pre = new DtoPrestamo();
                pre.setFk_ficha((BigDecimal) accion[0]);
                pre.setFk_municipio((BigDecimal) accion[1]);
                pre.setFk_responsable((BigDecimal) accion[2]);
                pre.setCodigo((String) accion[3]);
                pre.setFechasolicitud((Date) accion[4]);
                pre.setFechadevolucion((Date) accion[5]);
                pre.setObservacion((String) accion[6]);
                pre.setFk_equipo((BigDecimal) accion[7]);
                lstPrestamos.add(pre);
            }
            return lstPrestamos;
        } catch (NoResultException e) {
            return null;
        }
    }
}
