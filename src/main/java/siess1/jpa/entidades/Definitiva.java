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
@Table(name = "DEFINITIVA", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Definitiva.findAll", query = "SELECT d FROM Definitiva d"),
    @NamedQuery(name = "Definitiva.findByPkDefinitiva", query = "SELECT d FROM Definitiva d WHERE d.pkDefinitiva = :pkDefinitiva"),
    @NamedQuery(name = "Definitiva.findByCodigo", query = "SELECT d FROM Definitiva d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "Definitiva.findByDescripcion", query = "SELECT d FROM Definitiva d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Definitiva.findByFecha", query = "SELECT d FROM Definitiva d WHERE d.fecha = :fecha")})
public class Definitiva implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
     @SequenceGenerator(name = "SQDEFINITIVA", sequenceName = "SQDEFINITIVA", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQDEFINITIVA")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_DEFINITIVA", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkDefinitiva;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO", nullable = false, length = 20)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "FK_EQUIPO", referencedColumnName = "PK_EQUIPO")
    @ManyToOne
    private Equipo fkEquipo;

    public Definitiva() {
    }

    public Definitiva(BigDecimal pkDefinitiva) {
        this.pkDefinitiva = pkDefinitiva;
    }

    public Definitiva(BigDecimal pkDefinitiva, String codigo, String descripcion, Date fecha) {
        this.pkDefinitiva = pkDefinitiva;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public BigDecimal getPkDefinitiva() {
        return pkDefinitiva;
    }

    public void setPkDefinitiva(BigDecimal pkDefinitiva) {
        this.pkDefinitiva = pkDefinitiva;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash += (pkDefinitiva != null ? pkDefinitiva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Definitiva)) {
            return false;
        }
        Definitiva other = (Definitiva) object;
        if ((this.pkDefinitiva == null && other.pkDefinitiva != null) || (this.pkDefinitiva != null && !this.pkDefinitiva.equals(other.pkDefinitiva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  descripcion ; 
    }
    
}
