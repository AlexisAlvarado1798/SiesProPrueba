/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "DETALLEENTREGA", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleentrega.findAll", query = "SELECT d FROM Detalleentrega d"),
    @NamedQuery(name = "Detalleentrega.findByPkDetalleentrega", query = "SELECT d FROM Detalleentrega d WHERE d.pkDetalleentrega = :pkDetalleentrega"),
    @NamedQuery(name = "Detalleentrega.findByObservacion", query = "SELECT d FROM Detalleentrega d WHERE d.observacion = :observacion")})
public class Detalleentrega implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "SQDETALLEENTREGA", sequenceName = "SQDETALLEENTREGA", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQDETALLEENTREGA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_DETALLEENTREGA", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkDetalleentrega;
    @Size(max = 500)
    @Column(name = "OBSERVACION", length = 500)
    private String observacion;
    @JoinColumn(name = "FK_ENTREGA", referencedColumnName = "PK_ENTREGA", nullable = false)
    @ManyToOne(optional = false)
    private Entrega fkEntrega;
    @JoinColumn(name = "FK_EQUIPO", referencedColumnName = "PK_EQUIPO", nullable = false)
    @ManyToOne(optional = false)
    private Equipo fkEquipo;

    public Detalleentrega() {
    }

    public Detalleentrega(BigDecimal pkDetalleentrega) {
        this.pkDetalleentrega = pkDetalleentrega;
    }

    public BigDecimal getPkDetalleentrega() {
        return pkDetalleentrega;
    }

    public void setPkDetalleentrega(BigDecimal pkDetalleentrega) {
        this.pkDetalleentrega = pkDetalleentrega;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Entrega getFkEntrega() {
        return fkEntrega;
    }

    public void setFkEntrega(Entrega fkEntrega) {
        this.fkEntrega = fkEntrega;
    }

    public Equipo getFkEquipo() {
        return fkEquipo;
    }

    public void setFkEquipo(Equipo fkEquipo) {
        this.fkEquipo = fkEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkDetalleentrega != null ? pkDetalleentrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleentrega)) {
            return false;
        }
        Detalleentrega other = (Detalleentrega) object;
        if ((this.pkDetalleentrega == null && other.pkDetalleentrega != null) || (this.pkDetalleentrega != null && !this.pkDetalleentrega.equals(other.pkDetalleentrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return observacion;
    }
    
}
