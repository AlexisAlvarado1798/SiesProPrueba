/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "ESTADOUSUARIO", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadousuario.findAll", query = "SELECT e FROM Estadousuario e"),
    @NamedQuery(name = "Estadousuario.findByPkEstadousuario", query = "SELECT e FROM Estadousuario e WHERE e.pkEstadousuario = :pkEstadousuario"),
    @NamedQuery(name = "Estadousuario.findByFechainicio", query = "SELECT e FROM Estadousuario e WHERE e.fechainicio = :fechainicio"),
    @NamedQuery(name = "Estadousuario.findByFechafin", query = "SELECT e FROM Estadousuario e WHERE e.fechafin = :fechafin")})
public class Estadousuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
     @SequenceGenerator(name = "SQESTADOUSUARIO", sequenceName = "SQESTADOUSUARIO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQESTADOUSUARIO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ESTADOUSUARIO", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkEstadousuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAINICIO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicio;
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @JoinColumn(name = "FK_ESTADO", referencedColumnName = "PK_ESTADO", nullable = false)
    @ManyToOne(optional = false)
    private Estado fkEstado;
    @JoinColumn(name = "FK_USUARIO", referencedColumnName = "PK_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private Usuario fkUsuario;

    public Estadousuario() {
    }

    public Estadousuario(BigDecimal pkEstadousuario) {
        this.pkEstadousuario = pkEstadousuario;
    }

    public Estadousuario(BigDecimal pkEstadousuario, Date fechainicio) {
        this.pkEstadousuario = pkEstadousuario;
        this.fechainicio = fechainicio;
    }

    public BigDecimal getPkEstadousuario() {
        return pkEstadousuario;
    }

    public void setPkEstadousuario(BigDecimal pkEstadousuario) {
        this.pkEstadousuario = pkEstadousuario;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Estado getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(Estado fkEstado) {
        this.fkEstado = fkEstado;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkEstadousuario != null ? pkEstadousuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadousuario)) {
            return false;
        }
        Estadousuario other = (Estadousuario) object;
        if ((this.pkEstadousuario == null && other.pkEstadousuario != null) || (this.pkEstadousuario != null && !this.pkEstadousuario.equals(other.pkEstadousuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return fechainicio + "-" +fechafin;
    }
    
}
