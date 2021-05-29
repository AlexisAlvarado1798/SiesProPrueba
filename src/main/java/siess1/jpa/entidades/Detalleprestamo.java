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
@Table(name = "DETALLEPRESTAMO", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleprestamo.findAll", query = "SELECT d FROM Detalleprestamo d"),
    @NamedQuery(name = "Detalleprestamo.findByPkDetalleprestamo", query = "SELECT d FROM Detalleprestamo d WHERE d.pkDetalleprestamo = :pkDetalleprestamo")
    })
public class Detalleprestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
    @SequenceGenerator(name = "SQDETALLEPROCESO", sequenceName = "SQDETALLEPROCESO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQDETALLEPROCESO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_DETALLEPRESTAMO", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkDetalleprestamo;
   
    @JoinColumn(name = "FK_EQUIPO", referencedColumnName = "PK_EQUIPO", nullable = false)
    @ManyToOne(optional = false)
    private Equipo fkEquipo;
    @JoinColumn(name = "FK_PRESTAMO", referencedColumnName = "PK_PRESTAMO", nullable = false)
    @ManyToOne(optional = false)
    private Prestamo fkPrestamo;

    public Detalleprestamo() {
    }

    public Detalleprestamo(BigDecimal pkDetalleprestamo) {
        this.pkDetalleprestamo = pkDetalleprestamo;
    }

   

    public BigDecimal getPkDetalleprestamo() {
        return pkDetalleprestamo;
    }

    public void setPkDetalleprestamo(BigDecimal pkDetalleprestamo) {
        this.pkDetalleprestamo = pkDetalleprestamo;
    }


    public Equipo getFkEquipo() {
        return fkEquipo;
    }

    public void setFkEquipo(Equipo fkEquipo) {
        this.fkEquipo = fkEquipo;
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
        hash += (pkDetalleprestamo != null ? pkDetalleprestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleprestamo)) {
            return false;
        }
        Detalleprestamo other = (Detalleprestamo) object;
        if ((this.pkDetalleprestamo == null && other.pkDetalleprestamo != null) || (this.pkDetalleprestamo != null && !this.pkDetalleprestamo.equals(other.pkDetalleprestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         return ""+pkDetalleprestamo ;
    }
    
}
