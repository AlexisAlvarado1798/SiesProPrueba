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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "SUMINISTRA", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suministra.findAll", query = "SELECT s FROM Suministra s"),
    @NamedQuery(name = "Suministra.findByPkSuministra", query = "SELECT s FROM Suministra s WHERE s.pkSuministra = :pkSuministra")})
public class Suministra implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
       @SequenceGenerator(name = "SQSUMINISTRA", sequenceName = "SQSUMINISTRA", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQSUMINISTRA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_SUMINISTRA", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkSuministra;
    @JoinColumn(name = "FK_EQUIPO", referencedColumnName = "PK_EQUIPO", nullable = false)
    @ManyToOne(optional = false)
    private Equipo fkEquipo;
    @JoinColumn(name = "FK_PROVEEDOR", referencedColumnName = "PK_PROVEEDOR", nullable = false)
    @ManyToOne(optional = false)
    private Proveedor fkProveedor;

    public Suministra() {
    }

    public Suministra(BigDecimal pkSuministra) {
        this.pkSuministra = pkSuministra;
    }

    public BigDecimal getPkSuministra() {
        return pkSuministra;
    }

    public void setPkSuministra(BigDecimal pkSuministra) {
        this.pkSuministra = pkSuministra;
    }

    public Equipo getFkEquipo() {
        return fkEquipo;
    }

    public void setFkEquipo(Equipo fkEquipo) {
        this.fkEquipo = fkEquipo;
    }

    public Proveedor getFkProveedor() {
        return fkProveedor;
    }

    public void setFkProveedor(Proveedor fkProveedor) {
        this.fkProveedor = fkProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkSuministra != null ? pkSuministra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suministra)) {
            return false;
        }
        Suministra other = (Suministra) object;
        if ((this.pkSuministra == null && other.pkSuministra != null) || (this.pkSuministra != null && !this.pkSuministra.equals(other.pkSuministra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  pkSuministra +"";
    }
    
}
