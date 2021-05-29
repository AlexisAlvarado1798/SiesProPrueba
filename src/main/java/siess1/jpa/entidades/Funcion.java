/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "FUNCION", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcion.findAll", query = "SELECT f FROM Funcion f"),
    @NamedQuery(name = "Funcion.findByPkFuncion", query = "SELECT f FROM Funcion f WHERE f.pkFuncion = :pkFuncion"),
    @NamedQuery(name = "Funcion.findByNombre", query = "SELECT f FROM Funcion f WHERE f.nombre = :nombre")})
public class Funcion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
     @SequenceGenerator(name = "SQFUNCION", sequenceName = "SQFUNCION", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQFUNCION")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FUNCION", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkFuncion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @OneToMany(mappedBy = "fkFuncion")
    private List<Equipo> equipoList;

    public Funcion() {
    }

    public Funcion(BigDecimal pkFuncion) {
        this.pkFuncion = pkFuncion;
    }

    public Funcion(BigDecimal pkFuncion, String nombre) {
        this.pkFuncion = pkFuncion;
        this.nombre = nombre;
    }

    public BigDecimal getPkFuncion() {
        return pkFuncion;
    }

    public void setPkFuncion(BigDecimal pkFuncion) {
        this.pkFuncion = pkFuncion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkFuncion != null ? pkFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcion)) {
            return false;
        }
        Funcion other = (Funcion) object;
        if ((this.pkFuncion == null && other.pkFuncion != null) || (this.pkFuncion != null && !this.pkFuncion.equals(other.pkFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
