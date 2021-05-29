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
 * @author USUARIO
 */
@Entity
@Table(name = "TIPOTELEFONO", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipotelefono.findAll", query = "SELECT t FROM Tipotelefono t"),
    @NamedQuery(name = "Tipotelefono.findByPkTipotelefono", query = "SELECT t FROM Tipotelefono t WHERE t.pkTipotelefono = :pkTipotelefono"),
    @NamedQuery(name = "Tipotelefono.findByCodigo", query = "SELECT t FROM Tipotelefono t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Tipotelefono.findByDescripcion", query = "SELECT t FROM Tipotelefono t WHERE t.descripcion = :descripcion")})
public class Tipotelefono implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
     @SequenceGenerator(name = "SQTIPOTELEFONO", sequenceName = "SQTIPOTELEFONO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQTIPOTELEFONO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TIPOTELEFONO", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkTipotelefono;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTipotelefono")
    private List<Telefono> telefonoList;

    public Tipotelefono() {
    }

    public Tipotelefono(BigDecimal pkTipotelefono) {
        this.pkTipotelefono = pkTipotelefono;
    }

    public Tipotelefono(BigDecimal pkTipotelefono, String codigo, String descripcion) {
        this.pkTipotelefono = pkTipotelefono;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public BigDecimal getPkTipotelefono() {
        return pkTipotelefono;
    }

    public void setPkTipotelefono(BigDecimal pkTipotelefono) {
        this.pkTipotelefono = pkTipotelefono;
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
    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTipotelefono != null ? pkTipotelefono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipotelefono)) {
            return false;
        }
        Tipotelefono other = (Tipotelefono) object;
        if ((this.pkTipotelefono == null && other.pkTipotelefono != null) || (this.pkTipotelefono != null && !this.pkTipotelefono.equals(other.pkTipotelefono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
 return descripcion;
    }
    
}
