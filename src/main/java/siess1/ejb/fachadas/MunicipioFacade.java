/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.ejb.fachadas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import siess1.jpa.entidades.Municipio;

/**
 *
 * @author Aprendiz
 */
@Stateless
public class MunicipioFacade extends AbstractFacade<Municipio> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }
    public ArrayList<Municipio> obtenerMunicipioPorDepartamento(String codDepto){
        Query consulta = em.createNamedQuery("Municipio.findByDepartamento");
        consulta.setParameter("codDepartamento", codDepto);
        ArrayList<Municipio> lstMunicipios =new ArrayList();
        try{
            lstMunicipios = (ArrayList <Municipio> ) consulta.getResultList();
            
        }catch (NoResultException e ){
            System.err.println(e.getMessage());
        }
        return lstMunicipios;
        
    }
    public List<Municipio>obtenerMunicipiosPorDepartamento(String codDepartamento)
    {
        StringBuilder sbSQL = new StringBuilder();
        sbSQL.append("select * from municipio M");
        sbSQL.append(" INNER JOIN DEPARTAMENTO D ON (M.FK_DEPARTAMENTO = D.PK_DEPARTAMENTO) ");
        sbSQL.append(" where D.CODIGO = '").append(codDepartamento);
        sbSQL.append("' ORDER BY M.nombre");
        
        Query consulta = em.createNativeQuery(sbSQL.toString(), Municipio.class);
        List<Municipio> lstMunicipios= new ArrayList<>();
        try{
        lstMunicipios= consulta.getResultList();
        }catch(NoResultException e){
        System.err.println(e.getMessage());
        }
        return lstMunicipios;
    }
}
