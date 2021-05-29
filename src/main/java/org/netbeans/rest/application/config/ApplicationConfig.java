/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Aprendiz
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.AccionpermitidaFacadeREST.class);
        resources.add(service.AccionpermitidaplantillaFacadeREST.class);
        resources.add(service.AccionpermitidaporusuarioFacadeREST.class);
        resources.add(service.ComponenteFacadeREST.class);
        resources.add(service.DefinitivaFacadeREST.class);
        resources.add(service.DepartamentoFacadeREST.class);
        resources.add(service.DetalleentregaFacadeREST.class);
        resources.add(service.DetalleprestamoFacadeREST.class);
        resources.add(service.EntregaFacadeREST.class);
        resources.add(service.EquipoFacadeREST.class);
        resources.add(service.EstadoFacadeREST.class);
        resources.add(service.EstadousuarioFacadeREST.class);
        resources.add(service.FabricanteFacadeREST.class);
        resources.add(service.FichaFacadeREST.class);
        resources.add(service.FuncionFacadeREST.class);
        resources.add(service.MantenimientoFacadeREST.class);
        resources.add(service.MunicipioFacadeREST.class);
        resources.add(service.PaisFacadeREST.class);
        resources.add(service.PersonaFacadeREST.class);
        resources.add(service.PlantillaaccionpermitidaFacadeREST.class);
        resources.add(service.PrestamoFacadeREST.class);
        resources.add(service.ProgramaformacionFacadeREST.class);
        resources.add(service.ProveedorFacadeREST.class);
        resources.add(service.SuministraFacadeREST.class);
        resources.add(service.TelefonoFacadeREST.class);
        resources.add(service.TipoidentificacionFacadeREST.class);
        resources.add(service.TipomantenimientoFacadeREST.class);
        resources.add(service.TipotelefonoFacadeREST.class);
        resources.add(service.UsuarioFacadeREST.class);
    }
    
}
