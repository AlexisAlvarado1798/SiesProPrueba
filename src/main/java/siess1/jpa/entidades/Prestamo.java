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
@Table(name = "PRESTAMO", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p"),
    @NamedQuery(name = "Prestamo.findByPkPrestamo", query = "SELECT p FROM Prestamo p WHERE p.pkPrestamo = :pkPrestamo"),
    @NamedQuery(name = "Prestamo.findByCodigo", query = "SELECT p FROM Prestamo p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Prestamo.findByFechasolicitud", query = "SELECT p FROM Prestamo p WHERE p.fechasolicitud = :fechasolicitud"),
    @NamedQuery(name = "Prestamo.findByFechasalida", query = "SELECT p FROM Prestamo p WHERE p.fechasalida = :fechasalida"),
    @NamedQuery(name = "Prestamo.findByFechadevolucion", query = "SELECT p FROM Prestamo p WHERE p.fechadevolucion = :fechadevolucion"),
    @NamedQuery(name = "Prestamo.findByEstado", query = "SELECT p FROM Prestamo p WHERE p.estado = :estado"),
    @NamedQuery(name = "Prestamo.findByObservacion", query = "SELECT p FROM Prestamo p WHERE p.observacion = :observacion")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "SQPROCESO", sequenceName = "SQPROCESO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQPROCESO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PRESTAMO", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkPrestamo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO", nullable = false, length = 20)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHASOLICITUD", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechasolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHASALIDA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechasalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHADEVOLUCION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadevolucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO", nullable = false)
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "OBSERVACION", nullable = false, length = 500)
    private String observacion;
    @JoinColumn(name = "FK_FICHA", referencedColumnName = "PK_FICHA", nullable = false)
    @ManyToOne(optional = false)
    private Ficha fkFicha;
    @JoinColumn(name = "FK_MUNICIPIO", referencedColumnName = "PK_MUNICIPIO", nullable = false)
    @ManyToOne(optional = false)
    private Municipio fkMunicipio;
    @JoinColumn(name = "FK_RESPONSABLE", referencedColumnName = "PK_PERSONA", nullable = false)
    @ManyToOne(optional = false)
    private Persona fkResponsable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPrestamo")
    private List<Entrega> entregaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPrestamo")
    private List<Detalleprestamo> detalleprestamoList;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREADOPOR", nullable = false)
    private BigDecimal creadopor;

    public Prestamo() {
    }

    public Prestamo(BigDecimal pkPrestamo) {
        this.pkPrestamo = pkPrestamo;
    }

    public Prestamo(BigDecimal pkPrestamo, String codigo, Date fechasolicitud, Date fechasalida, Date fechadevolucion, short estado, String observacion) {
        this.pkPrestamo = pkPrestamo;
        this.codigo = codigo;
        this.fechasolicitud = fechasolicitud;
        this.fechasalida = fechasalida;
        this.fechadevolucion = fechadevolucion;
        this.estado = estado;
        this.observacion = observacion;
    }

    public BigDecimal getPkPrestamo() {
        return pkPrestamo;
    }

    public void setPkPrestamo(BigDecimal pkPrestamo) {
        this.pkPrestamo = pkPrestamo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechasolicitud() {
        return fechasolicitud;
    }

    public void setFechasolicitud(Date fechasolicitud) {
        this.fechasolicitud = fechasolicitud;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Ficha getFkFicha() {
        return fkFicha;
    }

    public void setFkFicha(Ficha fkFicha) {
        this.fkFicha = fkFicha;
    }

    public Municipio getFkMunicipio() {
        return fkMunicipio;
    }

    public void setFkMunicipio(Municipio fkMunicipio) {
        this.fkMunicipio = fkMunicipio;
    }

    public Persona getFkResponsable() {
        return fkResponsable;
    }

    public void setFkResponsable(Persona fkResponsable) {
        this.fkResponsable = fkResponsable;
    }

    public BigDecimal getCreadopor() {
        return creadopor;
    }

    public void setCreadopor(BigDecimal creadopor) {
        this.creadopor = creadopor;
    }

    
    @XmlTransient
    public List<Entrega> getEntregaList() {
        return entregaList;
    }

    public void setEntregaList(List<Entrega> entregaList) {
        this.entregaList = entregaList;
    }

    @XmlTransient
    public List<Detalleprestamo> getDetalleprestamoList() {
        return detalleprestamoList;
    }

    public void setDetalleprestamoList(List<Detalleprestamo> detalleprestamoList) {
        this.detalleprestamoList = detalleprestamoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPrestamo != null ? pkPrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.pkPrestamo == null && other.pkPrestamo != null) || (this.pkPrestamo != null && !this.pkPrestamo.equals(other.pkPrestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return codigo+"-"+fechasolicitud;
    }

}
