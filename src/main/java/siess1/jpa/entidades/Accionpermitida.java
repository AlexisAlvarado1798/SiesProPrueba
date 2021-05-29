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
@Table(name = "ACCIONPERMITIDA", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accionpermitida.findAll", query = "SELECT a FROM Accionpermitida a"),
    @NamedQuery(name = "Accionpermitida.findByPkAccionpermitida", query = "SELECT a FROM Accionpermitida a WHERE a.pkAccionpermitida = :pkAccionpermitida"),
    @NamedQuery(name = "Accionpermitida.findByCodigo", query = "SELECT a FROM Accionpermitida a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Accionpermitida.findByDescripcion", query = "SELECT a FROM Accionpermitida a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Accionpermitida.findByRuta", query = "SELECT a FROM Accionpermitida a WHERE a.ruta = :ruta")})
public class Accionpermitida implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "SQACCIONPERMITIDA", sequenceName = "SQACCIONPERMITIDA", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQACCIONPERMITIDA")

    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ACCIONPERMITIDA", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkAccionpermitida;
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
    @Size(min = 1, max = 500)
    @Column(name = "RUTA", nullable = false, length = 500)
    private String ruta;
    @OneToMany(mappedBy = "fkAccionpermitida")
    private List<Accionpermitida> accionpermitidaList;
    @JoinColumn(name = "FK_ACCIONPERMITIDA", referencedColumnName = "PK_ACCIONPERMITIDA")
    @ManyToOne
    private Accionpermitida fkAccionpermitida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAccionpermitida")
    private List<Accionpermitidaporusuario> accionpermitidaporusuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAccionpermitida")
    private List<Accionpermitidaplantilla> accionpermitidaplantillaList;

    public Accionpermitida() {
    }

    public Accionpermitida(BigDecimal pkAccionpermitida) {
        this.pkAccionpermitida = pkAccionpermitida;
    }

    public Accionpermitida(BigDecimal pkAccionpermitida, String codigo, String descripcion, String ruta) {
        this.pkAccionpermitida = pkAccionpermitida;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.ruta = ruta;
    }

    public BigDecimal getPkAccionpermitida() {
        return pkAccionpermitida;
    }

    public void setPkAccionpermitida(BigDecimal pkAccionpermitida) {
        this.pkAccionpermitida = pkAccionpermitida;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @XmlTransient
    public List<Accionpermitida> getAccionpermitidaList() {
        return accionpermitidaList;
    }

    public void setAccionpermitidaList(List<Accionpermitida> accionpermitidaList) {
        this.accionpermitidaList = accionpermitidaList;
    }

    public Accionpermitida getFkAccionpermitida() {
        return fkAccionpermitida;
    }

    public void setFkAccionpermitida(Accionpermitida fkAccionpermitida) {
        this.fkAccionpermitida = fkAccionpermitida;
    }

    @XmlTransient
    public List<Accionpermitidaporusuario> getAccionpermitidaporusuarioList() {
        return accionpermitidaporusuarioList;
    }

    public void setAccionpermitidaporusuarioList(List<Accionpermitidaporusuario> accionpermitidaporusuarioList) {
        this.accionpermitidaporusuarioList = accionpermitidaporusuarioList;
    }

    @XmlTransient
    public List<Accionpermitidaplantilla> getAccionpermitidaplantillaList() {
        return accionpermitidaplantillaList;
    }

    public void setAccionpermitidaplantillaList(List<Accionpermitidaplantilla> accionpermitidaplantillaList) {
        this.accionpermitidaplantillaList = accionpermitidaplantillaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkAccionpermitida != null ? pkAccionpermitida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accionpermitida)) {
            return false;
        }
        Accionpermitida other = (Accionpermitida) object;
        if ((this.pkAccionpermitida == null && other.pkAccionpermitida != null) || (this.pkAccionpermitida != null && !this.pkAccionpermitida.equals(other.pkAccionpermitida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
return codigo + "-" + descripcion;
    }
    
}
