package siess1.jsf.controladores.util;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import siess1.jpa.entidades.Departamento;
import siess1.jpa.entidades.Municipio;
import siess1.jpa.entidades.Pais;

/**
 *
 * @author ACER
 */
@Named("ubicacionGeograficaController")
@ViewScoped
public class UbicacionGeograficaController implements Serializable {
    @EJB
    private siess1.ejb.fachadas.PaisFacade oPaisFacade;
    @EJB
    private siess1.ejb.fachadas.DepartamentoFacade oDepartamentoFacade;
    @EJB
    private siess1.ejb.fachadas.MunicipioFacade oMunicipioFacade;

    private List<Pais> paises;
    private List<Departamento> departamentos;
    private List<Municipio> municipios;

     public List<Pais> getPaises() {
        if (paises == null) {
            paises = oPaisFacade.findAll();
        }
        return paises;
    }

    public List<Departamento> getDepartamento() {
        return departamentos;
    }

    public void onPaisChange(Pais pais) {
        if (pais != null) {
            departamentos = oDepartamentoFacade.obtenerDepartamentosPorPais(pais.getCodigo());
        } else {
            departamentos= null;
            

        }
    }
    public List<Departamento> getDepartamentos() {
        if (departamentos == null) {
            departamentos = oDepartamentoFacade.findAll();
        }
        return departamentos;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void onDepartamentChange(Departamento depto) {
        if (depto != null) {
            municipios = oMunicipioFacade.obtenerMunicipiosPorDepartamento(depto.getCodigo());
        } else {
            municipios = null;

        }
    }
}
