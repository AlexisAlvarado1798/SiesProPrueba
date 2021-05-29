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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "TIPOIDENTIFICACION", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoidentificacion.findAll", query = "SELECT t FROM Tipoidentificacion t"),
    @NamedQuery(name = "Tipoidentificacion.findByPkTipoidentificacion", query = "SELECT t FROM Tipoidentificacion t WHERE t.pkTipoidentificacion = :pkTipoidentificacion"),
    @NamedQuery(name = "Tipoidentificacion.findByCodigo", query = "SELECT t FROM Tipoidentificacion t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Tipoidentificacion.findByDescripcion", query = "SELECT t FROM Tipoidentificacion t WHERE t.descripcion = :descripcion")})
public class Tipoidentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   @Id
    @SequenceGenerator(name = "SQTIPOIDENTIFICACION", sequenceName = "SQTIPOIDENTIFICACION", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQTIPOIDENTIFICACION")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TIPOIDENTIFICACION", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkTipoidentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO", nullable = false, length = 20)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTipoidentificacion")
    private List<Persona> personaList;

    public Tipoidentificacion() {
    }

    public Tipoidentificacion(BigDecimal pkTipoidentificacion) {
        this.pkTipoidentificacion = pkTipoidentificacion;
    }

    public Tipoidentificacion(BigDecimal pkTipoidentificacion, String codigo, String descripcion) {
        this.pkTipoidentificacion = pkTipoidentificacion;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public BigDecimal getPkTipoidentificacion() {
        return pkTipoidentificacion;
    }

    public void setPkTipoidentificacion(BigDecimal pkTipoidentificacion) {
        this.pkTipoidentificacion = pkTipoidentificacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTipoidentificacion != null ? pkTipoidentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoidentificacion)) {
            return false;
        }
        Tipoidentificacion other = (Tipoidentificacion) object;
        if ((this.pkTipoidentificacion == null && other.pkTipoidentificacion != null) || (this.pkTipoidentificacion != null && !this.pkTipoidentificacion.equals(other.pkTipoidentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo + "-"+ descripcion;
    }
    
}
