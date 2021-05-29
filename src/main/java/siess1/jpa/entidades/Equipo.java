/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * @author Aprendiz
 */
@Entity
@Table(name = "EQUIPO", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PLACASENA"}),
    @UniqueConstraint(columnNames = {"SERIAL"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findByPkEquipo", query = "SELECT e FROM Equipo e WHERE e.pkEquipo = :pkEquipo"),
    @NamedQuery(name = "Equipo.findByNombre", query = "SELECT e FROM Equipo e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Equipo.findBySerial", query = "SELECT e FROM Equipo e WHERE e.serial = :serial"),
    @NamedQuery(name = "Equipo.findByModelo", query = "SELECT e FROM Equipo e WHERE e.modelo = :modelo"),
    @NamedQuery(name = "Equipo.findByPlacasena", query = "SELECT e FROM Equipo e WHERE e.placasena = :placasena"),
    @NamedQuery(name = "Equipo.findByNumerocertificacion", query = "SELECT e FROM Equipo e WHERE e.numerocertificacion = :numerocertificacion"),
    @NamedQuery(name = "Equipo.findByVidautilprevista", query = "SELECT e FROM Equipo e WHERE e.vidautilprevista = :vidautilprevista"),
    @NamedQuery(name = "Equipo.findByAniofabricacion", query = "SELECT e FROM Equipo e WHERE e.aniofabricacion = :aniofabricacion"),
    @NamedQuery(name = "Equipo.findByFechacompra", query = "SELECT e FROM Equipo e WHERE e.fechacompra = :fechacompra"),
    @NamedQuery(name = "Equipo.findByFechainiciooperacion", query = "SELECT e FROM Equipo e WHERE e.fechainiciooperacion = :fechainiciooperacion"),
    @NamedQuery(name = "Equipo.findByFecharetiro", query = "SELECT e FROM Equipo e WHERE e.fecharetiro = :fecharetiro")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
     @SequenceGenerator(name = "SQEQUIPO", sequenceName = "SQEQUIPO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQEQUIPO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_EQUIPO", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkEquipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERIAL", nullable = false)
    private BigInteger serial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MODELO", nullable = false, length = 100)
    private String modelo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLACASENA", nullable = false)
    private BigInteger placasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMEROCERTIFICACION", nullable = false)
    private BigInteger numerocertificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "VIDAUTILPREVISTA", nullable = false, length = 100)
    private String vidautilprevista;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANIOFABRICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date aniofabricacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHACOMPRA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAINICIOOPERACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainiciooperacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHARETIRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharetiro;
    @JoinColumn(name = "FK_FABRICANTE", referencedColumnName = "PK_FABRICANTE", nullable = false)
    @ManyToOne(optional = false)
    private Fabricante fkFabricante;
    @JoinColumn(name = "FK_FUNCION", referencedColumnName = "PK_FUNCION")
    @ManyToOne
    private Funcion fkFuncion;

    public Equipo() {
    }

    public Equipo(BigDecimal pkEquipo) {
        this.pkEquipo = pkEquipo;
    }

    public Equipo(BigDecimal pkEquipo, String nombre, BigInteger serial, String modelo, BigInteger placasena, BigInteger numerocertificacion, String vidautilprevista, Date aniofabricacion, Date fechacompra, Date fechainiciooperacion, Date fecharetiro) {
        this.pkEquipo = pkEquipo;
        this.nombre = nombre;
        this.serial = serial;
        this.modelo = modelo;
        this.placasena = placasena;
        this.numerocertificacion = numerocertificacion;
        this.vidautilprevista = vidautilprevista;
        this.aniofabricacion = aniofabricacion;
        this.fechacompra = fechacompra;
        this.fechainiciooperacion = fechainiciooperacion;
        this.fecharetiro = fecharetiro;
    }

    public BigDecimal getPkEquipo() {
        return pkEquipo;
    }

    public void setPkEquipo(BigDecimal pkEquipo) {
        this.pkEquipo = pkEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getSerial() {
        return serial;
    }

    public void setSerial(BigInteger serial) {
        this.serial = serial;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public BigInteger getPlacasena() {
        return placasena;
    }

    public void setPlacasena(BigInteger placasena) {
        this.placasena = placasena;
    }

    public BigInteger getNumerocertificacion() {
        return numerocertificacion;
    }

    public void setNumerocertificacion(BigInteger numerocertificacion) {
        this.numerocertificacion = numerocertificacion;
    }

    public String getVidautilprevista() {
        return vidautilprevista;
    }

    public void setVidautilprevista(String vidautilprevista) {
        this.vidautilprevista = vidautilprevista;
    }

    public Date getAniofabricacion() {
        return aniofabricacion;
    }

    public void setAniofabricacion(Date aniofabricacion) {
        this.aniofabricacion = aniofabricacion;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public Date getFechainiciooperacion() {
        return fechainiciooperacion;
    }

    public void setFechainiciooperacion(Date fechainiciooperacion) {
        this.fechainiciooperacion = fechainiciooperacion;
    }

    public Date getFecharetiro() {
        return fecharetiro;
    }

    public void setFecharetiro(Date fecharetiro) {
        this.fecharetiro = fecharetiro;
    }

    public Fabricante getFkFabricante() {
        return fkFabricante;
    }

    public void setFkFabricante(Fabricante fkFabricante) {
        this.fkFabricante = fkFabricante;
    }

    public Funcion getFkFuncion() {
        return fkFuncion;
    }

    public void setFkFuncion(Funcion fkFuncion) {
        this.fkFuncion = fkFuncion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkEquipo != null ? pkEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.pkEquipo == null && other.pkEquipo != null) || (this.pkEquipo != null && !this.pkEquipo.equals(other.pkEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return placasena+"-"+nombre;
    }
    
}
