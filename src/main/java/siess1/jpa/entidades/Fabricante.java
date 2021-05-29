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
import javax.persistence.CascadeType;
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
@Table(name = "FABRICANTE", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fabricante.findAll", query = "SELECT f FROM Fabricante f"),
    @NamedQuery(name = "Fabricante.findByPkFabricante", query = "SELECT f FROM Fabricante f WHERE f.pkFabricante = :pkFabricante"),
    @NamedQuery(name = "Fabricante.findByNombre", query = "SELECT f FROM Fabricante f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Fabricante.findByDireccion", query = "SELECT f FROM Fabricante f WHERE f.direccion = :direccion")})
public class Fabricante implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
     @SequenceGenerator(name = "SQFABRICANTE", sequenceName = "SQFABRICANTE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQFABRICANTE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FABRICANTE", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkFabricante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIRECCION", nullable = false, length = 100)
    private String direccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFabricante")
    private List<Equipo> equipoList;

    public Fabricante() {
    }

    public Fabricante(BigDecimal pkFabricante) {
        this.pkFabricante = pkFabricante;
    }

    public Fabricante(BigDecimal pkFabricante, String nombre, String direccion) {
        this.pkFabricante = pkFabricante;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public BigDecimal getPkFabricante() {
        return pkFabricante;
    }

    public void setPkFabricante(BigDecimal pkFabricante) {
        this.pkFabricante = pkFabricante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
        hash += (pkFabricante != null ? pkFabricante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fabricante)) {
            return false;
        }
        Fabricante other = (Fabricante) object;
        if ((this.pkFabricante == null && other.pkFabricante != null) || (this.pkFabricante != null && !this.pkFabricante.equals(other.pkFabricante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
