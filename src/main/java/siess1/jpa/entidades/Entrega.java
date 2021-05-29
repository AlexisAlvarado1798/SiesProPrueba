/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "ENTREGA", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrega.findAll", query = "SELECT e FROM Entrega e"),
    @NamedQuery(name = "Entrega.findByPkEntrega", query = "SELECT e FROM Entrega e WHERE e.pkEntrega = :pkEntrega"),
    @NamedQuery(name = "Entrega.findByFechaentregado", query = "SELECT e FROM Entrega e WHERE e.fechaentregado = :fechaentregado")})
public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
     @SequenceGenerator(name = "SQENTREGA", sequenceName = "SQENTREGA", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQENTREGA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ENTREGA", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAENTREGADO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaentregado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEntrega")
    private List<Detalleentrega> detalleentregaList;
    @JoinColumn(name = "FK_PRESTAMO", referencedColumnName = "PK_PRESTAMO", nullable = false)
    @ManyToOne(optional = false)
    private Prestamo fkPrestamo;

    public Entrega() {
    }

    public Entrega(BigDecimal pkEntrega) {
        this.pkEntrega = pkEntrega;
    }

    public Entrega(BigDecimal pkEntrega, Date fechaentregado) {
        this.pkEntrega = pkEntrega;
        this.fechaentregado = fechaentregado;
    }

    public BigDecimal getPkEntrega() {
        return pkEntrega;
    }

    public void setPkEntrega(BigDecimal pkEntrega) {
        this.pkEntrega = pkEntrega;
    }

    public Date getFechaentregado() {
        return fechaentregado;
    }

    public void setFechaentregado(Date fechaentregado) {
        this.fechaentregado = fechaentregado;
    }

    @XmlTransient
    public List<Detalleentrega> getDetalleentregaList() {
        return detalleentregaList;
    }

    public void setDetalleentregaList(List<Detalleentrega> detalleentregaList) {
        this.detalleentregaList = detalleentregaList;
    }

    public Prestamo getFkPrestamo() {
        return fkPrestamo;
    }

    public void setFkPrestamo(Prestamo fkPrestamo) {
        this.fkPrestamo = fkPrestamo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkEntrega != null ? pkEntrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrega)) {
            return false;
        }
        Entrega other = (Entrega) object;
        if ((this.pkEntrega == null && other.pkEntrega != null) || (this.pkEntrega != null && !this.pkEntrega.equals(other.pkEntrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
 return " "+fechaentregado;
    }
    
}
