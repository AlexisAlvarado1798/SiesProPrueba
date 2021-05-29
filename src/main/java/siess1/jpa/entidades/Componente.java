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
@Table(name = "COMPONENTE", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Componente.findAll", query = "SELECT c FROM Componente c"),
    @NamedQuery(name = "Componente.findByPkComponete", query = "SELECT c FROM Componente c WHERE c.pkComponete = :pkComponete"),
    @NamedQuery(name = "Componente.findByNombre", query = "SELECT c FROM Componente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Componente.findByDescrppion", query = "SELECT c FROM Componente c WHERE c.descrppion = :descrppion")})
public class Componente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
     @SequenceGenerator(name = "SQCOMPONENTE", sequenceName = "SQCOMPONENTE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQCOMPONENTE")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_COMPONETE", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkComponete;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Size(max = 500)
    @Column(name = "DESCRPPION", length = 500)
    private String descrppion;
    @JoinColumn(name = "FK_EQUIPO", referencedColumnName = "PK_EQUIPO", nullable = false)
    @ManyToOne(optional = false)
    private Equipo fkEquipo;

    public Componente() {
    }

    public Componente(BigDecimal pkComponete) {
        this.pkComponete = pkComponete;
    }

    public Componente(BigDecimal pkComponete, String nombre) {
        this.pkComponete = pkComponete;
        this.nombre = nombre;
    }

    public BigDecimal getPkComponete() {
        return pkComponete;
    }

    public void setPkComponete(BigDecimal pkComponete) {
        this.pkComponete = pkComponete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrppion() {
        return descrppion;
    }

    public void setDescrppion(String descrppion) {
        this.descrppion = descrppion;
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
        hash += (pkComponete != null ? pkComponete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Componente)) {
            return false;
        }
        Componente other = (Componente) object;
        if ((this.pkComponete == null && other.pkComponete != null) || (this.pkComponete != null && !this.pkComponete.equals(other.pkComponete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "siess1.jpa.entidades.Componente[ pkComponete=" + pkComponete + " ]";
    }
    
}
