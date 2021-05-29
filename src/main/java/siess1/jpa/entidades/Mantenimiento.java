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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "MANTENIMIENTO", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m"),
    @NamedQuery(name = "Mantenimiento.findByPkMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.pkMantenimiento = :pkMantenimiento"),
    @NamedQuery(name = "Mantenimiento.findByCodigo", query = "SELECT m FROM Mantenimiento m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Mantenimiento.findByFechainspeccion", query = "SELECT m FROM Mantenimiento m WHERE m.fechainspeccion = :fechainspeccion"),
    @NamedQuery(name = "Mantenimiento.findByObservacion", query = "SELECT m FROM Mantenimiento m WHERE m.observacion = :observacion")})
public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   @Id
     @SequenceGenerator(name = "SQMANTENIMIENTO", sequenceName = "SQMANTENIMIENTO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQMANTENIMIENTO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_MANTENIMIENTO", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkMantenimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO", nullable = false, length = 20)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAINSPECCION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainspeccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "OBSERVACION", nullable = false, length = 500)
    private String observacion;
    @JoinColumn(name = "FK_EQUIPO", referencedColumnName = "PK_EQUIPO", nullable = false)
    @ManyToOne(optional = false)
    private Equipo fkEquipo;
    @JoinColumn(name = "FK_PERSONA", referencedColumnName = "PK_PERSONA")
    @ManyToOne
    private Persona fkPersona;
    @JoinColumn(name = "FK_TIPOMANTENIMIENTO", referencedColumnName = "PK_TIPOMANTENIMIENTO", nullable = false)
    @ManyToOne(optional = false)
    private Tipomantenimiento fkTipomantenimiento;

    public Mantenimiento() {
    }

    public Mantenimiento(BigDecimal pkMantenimiento) {
        this.pkMantenimiento = pkMantenimiento;
    }

    public Mantenimiento(BigDecimal pkMantenimiento, String codigo, Date fechainspeccion, String observacion) {
        this.pkMantenimiento = pkMantenimiento;
        this.codigo = codigo;
        this.fechainspeccion = fechainspeccion;
        this.observacion = observacion;
    }

    public BigDecimal getPkMantenimiento() {
        return pkMantenimiento;
    }

    public void setPkMantenimiento(BigDecimal pkMantenimiento) {
        this.pkMantenimiento = pkMantenimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechainspeccion() {
        return fechainspeccion;
    }

    public void setFechainspeccion(Date fechainspeccion) {
        this.fechainspeccion = fechainspeccion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Equipo getFkEquipo() {
        return fkEquipo;
    }

    public void setFkEquipo(Equipo fkEquipo) {
        this.fkEquipo = fkEquipo;
    }

    public Persona getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(Persona fkPersona) {
        this.fkPersona = fkPersona;
    }

    public Tipomantenimiento getFkTipomantenimiento() {
        return fkTipomantenimiento;
    }

    public void setFkTipomantenimiento(Tipomantenimiento fkTipomantenimiento) {
        this.fkTipomantenimiento = fkTipomantenimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkMantenimiento != null ? pkMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.pkMantenimiento == null && other.pkMantenimiento != null) || (this.pkMantenimiento != null && !this.pkMantenimiento.equals(other.pkMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo +"-"+ fechainspeccion +"-"+ observacion;
    }
    
}
