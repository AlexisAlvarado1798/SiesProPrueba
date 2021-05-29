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
import siess1.jpa.entidades.Persona;

/**
 *
 * @author Aprendiz
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    public void eliminar(BigDecimal pkPersona) {
        em.createNativeQuery("DELETE FROM PERSONA WHERE PK_PERSONA = " + pkPersona).executeUpdate();
    }

    public List<Persona> obtenerPersonasSinUsuarioAsociado(String valorBusqueda) {

        StringBuilder sbSQL = new StringBuilder("SELECT P.* FROM PERSONA P LEFT JOIN USUARIO U ");
        sbSQL.append("ON P.PK_PERSONA = U.FK_PERSONA WHERE U.FK_PERSONA IS NULL AND ");
        sbSQL.append(" (p.identificacion LIKE '% ");
        sbSQL.append(valorBusqueda.toLowerCase());
        sbSQL.append("%' OR LOWER (P.NOMBRES) LIKE '%");
        sbSQL.append(valorBusqueda.toLowerCase());
        sbSQL.append("%' OR LOWER (P.APELLIDOS) LIKE '%");
        sbSQL.append(valorBusqueda.toLowerCase());
        sbSQL.append("%')");

        Query SQL = em.createNativeQuery(sbSQL.toString(), Persona.class);
        List<Persona> lstPersonas;
        try {
            lstPersonas = (List<Persona>) SQL.getResultList();
        } catch (NoResultException e) {
            return null;
        }
        return lstPersonas;
    }

    public Persona getPersonaByIdentificacion(String identificacion) {
        Query consulta = em.createNamedQuery("Persona.findByIdentificacion");
        consulta.setParameter("identificacion", identificacion);
        Persona oPersona;
        try {
            oPersona = (Persona) consulta.getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return oPersona;
    }

    public List<Persona> getPersonas(String valorBusqueda) {
        StringBuilder sbSQL = new StringBuilder("SELECT P.* FROM PERSONA P ");
        sbSQL.append(" WHERE ");
        sbSQL.append(" (p.identificacion LIKE '%");
        sbSQL.append(valorBusqueda.toLowerCase());
        sbSQL.append("%' OR LOWER (P.NOMBRES) LIKE '%");
        sbSQL.append(valorBusqueda.toLowerCase());
        sbSQL.append("%' OR LOWER (P.APELLIDOS) LIKE '%");
        sbSQL.append(valorBusqueda.toLowerCase());
        sbSQL.append("%')");

        String squ = sbSQL.toString();
        
        Query SQL = em.createNativeQuery(sbSQL.toString(), Persona.class);
        List<Persona> lstPersonas;
        try {
            lstPersonas = (List<Persona>) SQL.getResultList();
        } catch (NoResultException e) {
            return null;
        }
        return lstPersonas;
    }
}
