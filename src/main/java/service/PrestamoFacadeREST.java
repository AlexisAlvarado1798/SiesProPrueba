/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import siess.dto.menu.DtoPrestamo;
import siess1.jpa.entidades.Prestamo;
import siess1.ejb.fachadas.PrestamoFacade;

/**
 *
 * @author Aprendiz
 */
@Stateless
@Path("siess1.jpa.entidades.prestamo")
public class PrestamoFacadeREST extends AbstractFacade<Prestamo> {
    
    
    @EJB
    private siess1.ejb.fachadas.PrestamoFacade facade;

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PrestamoFacadeREST() {
        super(Prestamo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public void create(Prestamo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") BigDecimal id, Prestamo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigDecimal id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public Prestamo find(@PathParam("id") BigDecimal id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Prestamo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Prestamo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     @GET
    @Path("prestamos")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<DtoPrestamo> lstPrestamo() {
        List <DtoPrestamo> prestamos = facade.obtenerPrestamoyEquipo();
        return prestamos;
    }
    
}
