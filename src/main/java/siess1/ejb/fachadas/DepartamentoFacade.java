/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.ejb.fachadas;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import siess1.jpa.entidades.Departamento;
import siess1.jpa.entidades.Municipio;

/**
 *
 * @author Aprendiz
 */
@Stateless
public class DepartamentoFacade extends AbstractFacade<Departamento> {

    @PersistenceContext(unitName = "1613314_siess1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }
       
     public ArrayList<Departamento> obtenerDepartamentoPorPais(String codPais){
        Query consulta = em.createNamedQuery("Departamento.findByPais");
        consulta.setParameter("codPais", codPais);
        ArrayList<Departamento> lstDepartamento =new ArrayList();
        try{
            lstDepartamento = (ArrayList <Departamento> ) consulta.getResultList();
            
        }catch (NoResultException e ){
            System.err.println(e.getMessage());
        }
        return lstDepartamento;
        
    }
    public List<Departamento>obtenerDepartamentosPorPais(String codPais)
    {
        StringBuilder sbSQL = new StringBuilder();
        sbSQL.append("select * from departamento D");
        sbSQL.append(" INNER JOIN Pais P ON (D.FK_PAIS = P.PK_PAIS) ");
        sbSQL.append(" where P.CODIGO = '").append(codPais);
        sbSQL.append("' ORDER BY D.nombre");
        
        Query consulta = em.createNativeQuery(sbSQL.toString(), Departamento.class);
        List<Departamento> lstDepartamento= new ArrayList<>();
        try{
        lstDepartamento= consulta.getResultList();
        }catch(NoResultException e){
        System.err.println(e.getMessage());
        }
        return lstDepartamento;
    }
    
}
