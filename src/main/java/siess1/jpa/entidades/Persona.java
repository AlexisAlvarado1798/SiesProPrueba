/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "PERSONA", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CORREO"}),
    @UniqueConstraint(columnNames = {"IDENTIFICACION"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByPkPersona", query = "SELECT p FROM Persona p WHERE p.pkPersona = :pkPersona"),
    @NamedQuery(name = "Persona.findByIdentificacion", query = "SELECT p FROM Persona p WHERE p.identificacion = :identificacion"),
    @NamedQuery(name = "Persona.findByNombres", query = "SELECT p FROM Persona p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Persona.findByApellidos", query = "SELECT p FROM Persona p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Persona.findByDireccion", query = "SELECT p FROM Persona p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Persona.findByCorreo", query = "SELECT p FROM Persona p WHERE p.correo = :correo")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
     @SequenceGenerator(name = "SQPERSONA", sequenceName = "SQPERSONA", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQPERSONA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PERSONA", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDENTIFICACION", nullable = false, length = 20)
    private String identificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NOMBRES", nullable = false, length = 500)
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "APELLIDOS", nullable = false, length = 500)
    private String apellidos;
    @Size(max = 500)
    @Column(name = "DIRECCION", length = 500)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CORREO", nullable = false, length = 100)
    private String correo;
    @JoinColumn(name = "FK_MUNICIPIOORIGEN", referencedColumnName = "PK_MUNICIPIO", nullable = false)
    @ManyToOne(optional = false)
    private Municipio fkMunicipioorigen;
    @JoinColumn(name = "FK_TIPOIDENTIFICACION", referencedColumnName = "PK_TIPOIDENTIFICACION", nullable = false)
    @ManyToOne(optional = false)
    private Tipoidentificacion fkTipoidentificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkVocero")
    private List<Ficha> fichaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkInstructor")
    private List<Ficha> fichaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkResponsable")
    private List<Prestamo> prestamoList;
    @OneToMany(mappedBy = "fkPersona")
    private List<Mantenimiento> mantenimientoList;
    @OneToMany(mappedBy = "fkPersona")
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPersona")
    private List<Telefono> telefonoList;

    public Persona() {
    }

    public Persona(BigDecimal pkPersona) {
        this.pkPersona = pkPersona;
    }

    public Persona(BigDecimal pkPersona, String identificacion, String nombres, String apellidos, String correo) {
        this.pkPersona = pkPersona;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    public BigDecimal getPkPersona() {
        return pkPersona;
    }

    public void setPkPersona(BigDecimal pkPersona) {
        this.pkPersona = pkPersona;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Municipio getFkMunicipioorigen() {
        return fkMunicipioorigen;
    }

    public void setFkMunicipioorigen(Municipio fkMunicipioorigen) {
        this.fkMunicipioorigen = fkMunicipioorigen;
    }

    public Tipoidentificacion getFkTipoidentificacion() {
        return fkTipoidentificacion;
    }

    public void setFkTipoidentificacion(Tipoidentificacion fkTipoidentificacion) {
        this.fkTipoidentificacion = fkTipoidentificacion;
    }

    @XmlTransient
    public List<Ficha> getFichaList() {
        return fichaList;
    }

    public void setFichaList(List<Ficha> fichaList) {
        this.fichaList = fichaList;
    }

    @XmlTransient
    public List<Ficha> getFichaList1() {
        return fichaList1;
    }

    public void setFichaList1(List<Ficha> fichaList1) {
        this.fichaList1 = fichaList1;
    }

    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    @XmlTransient
    public List<Mantenimiento> getMantenimientoList() {
        return mantenimientoList;
    }

    public void setMantenimientoList(List<Mantenimiento> mantenimientoList) {
        this.mantenimientoList = mantenimientoList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPersona != null ? pkPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.pkPersona == null && other.pkPersona != null) || (this.pkPersona != null && !this.pkPersona.equals(other.pkPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return identificacion + "-" + nombres + " " + apellidos;
    }
    
}
