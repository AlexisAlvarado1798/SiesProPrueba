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
@Table(name = "PROGRAMAFORMACION", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programaformacion.findAll", query = "SELECT p FROM Programaformacion p"),
    @NamedQuery(name = "Programaformacion.findByPkProgramaformacion", query = "SELECT p FROM Programaformacion p WHERE p.pkProgramaformacion = :pkProgramaformacion"),
    @NamedQuery(name = "Programaformacion.findByNombre", query = "SELECT p FROM Programaformacion p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Programaformacion.findByCodigo", query = "SELECT p FROM Programaformacion p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Programaformacion.findBySigla", query = "SELECT p FROM Programaformacion p WHERE p.sigla = :sigla")})
public class Programaformacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
       @Id
    @SequenceGenerator(name = "SQPROGRAMAFORMACION", sequenceName = "SQPROGRAMAFORMACION", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQPROGRAMAFORMACION")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PROGRAMAFORMACION", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkProgramaformacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CODIGO", nullable = false, length = 100)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SIGLA", nullable = false, length = 100)
    private String sigla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProgramaformacion")
    private List<Ficha> fichaList;

    public Programaformacion() {
    }

    public Programaformacion(BigDecimal pkProgramaformacion) {
        this.pkProgramaformacion = pkProgramaformacion;
    }

    public Programaformacion(BigDecimal pkProgramaformacion, String nombre, String codigo, String sigla) {
        this.pkProgramaformacion = pkProgramaformacion;
        this.nombre = nombre;
        this.codigo = codigo;
        this.sigla = sigla;
    }

    public BigDecimal getPkProgramaformacion() {
        return pkProgramaformacion;
    }

    public void setPkProgramaformacion(BigDecimal pkProgramaformacion) {
        this.pkProgramaformacion = pkProgramaformacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @XmlTransient
    public List<Ficha> getFichaList() {
        return fichaList;
    }

    public void setFichaList(List<Ficha> fichaList) {
        this.fichaList = fichaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProgramaformacion != null ? pkProgramaformacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programaformacion)) {
            return false;
        }
        Programaformacion other = (Programaformacion) object;
        if ((this.pkProgramaformacion == null && other.pkProgramaformacion != null) || (this.pkProgramaformacion != null && !this.pkProgramaformacion.equals(other.pkProgramaformacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre +"-"+ codigo;
    }
    
}
