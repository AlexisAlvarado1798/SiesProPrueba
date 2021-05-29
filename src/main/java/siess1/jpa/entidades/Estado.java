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
@Table(name = "ESTADO", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
    @NamedQuery(name = "Estado.findByPkEstado", query = "SELECT e FROM Estado e WHERE e.pkEstado = :pkEstado"),
    @NamedQuery(name = "Estado.findByCodigo", query = "SELECT e FROM Estado e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Estado.findByDescripcion", query = "SELECT e FROM Estado e WHERE e.descripcion = :descripcion")})
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
       @Id
    @SequenceGenerator(name = "SQESTADO", sequenceName = "SQESTADO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQESTADO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ESTADO", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkEstado;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstado")
    private List<Estadousuario> estadousuarioList;

    public Estado() {
    }

    public Estado(BigDecimal pkEstado) {
        this.pkEstado = pkEstado;
    }

    public Estado(BigDecimal pkEstado, String codigo, String descripcion) {
        this.pkEstado = pkEstado;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public BigDecimal getPkEstado() {
        return pkEstado;
    }

    public void setPkEstado(BigDecimal pkEstado) {
        this.pkEstado = pkEstado;
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
    public List<Estadousuario> getEstadousuarioList() {
        return estadousuarioList;
    }

    public void setEstadousuarioList(List<Estadousuario> estadousuarioList) {
        this.estadousuarioList = estadousuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkEstado != null ? pkEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.pkEstado == null && other.pkEstado != null) || (this.pkEstado != null && !this.pkEstado.equals(other.pkEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo + "-" + descripcion;
    }

}
