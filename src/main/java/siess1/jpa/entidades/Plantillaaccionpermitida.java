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
@Table(name = "PLANTILLAACCIONPERMITIDA", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plantillaaccionpermitida.findAll", query = "SELECT p FROM Plantillaaccionpermitida p"),
    @NamedQuery(name = "Plantillaaccionpermitida.findByPkPlantillaaccionpermitida", query = "SELECT p FROM Plantillaaccionpermitida p WHERE p.pkPlantillaaccionpermitida = :pkPlantillaaccionpermitida"),
    @NamedQuery(name = "Plantillaaccionpermitida.findByCodigo", query = "SELECT p FROM Plantillaaccionpermitida p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Plantillaaccionpermitida.findByDescripcion", query = "SELECT p FROM Plantillaaccionpermitida p WHERE p.descripcion = :descripcion")})
public class Plantillaaccionpermitida implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
     @SequenceGenerator(name = "SQPLANTILLAACCIONPERMITIDA", sequenceName = "SQPLANTILLAACCIONPERMITIDA", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQPLANTILLAACCIONPERMITIDA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PLANTILLAACCIONPERMITIDA", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkPlantillaaccionpermitida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO", nullable = false, length = 20)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPlantillaaccionpermitida")
    private List<Accionpermitidaplantilla> accionpermitidaplantillaList;

    public Plantillaaccionpermitida() {
    }

    public Plantillaaccionpermitida(BigDecimal pkPlantillaaccionpermitida) {
        this.pkPlantillaaccionpermitida = pkPlantillaaccionpermitida;
    }

    public Plantillaaccionpermitida(BigDecimal pkPlantillaaccionpermitida, String codigo, String descripcion) {
        this.pkPlantillaaccionpermitida = pkPlantillaaccionpermitida;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public BigDecimal getPkPlantillaaccionpermitida() {
        return pkPlantillaaccionpermitida;
    }

    public void setPkPlantillaaccionpermitida(BigDecimal pkPlantillaaccionpermitida) {
        this.pkPlantillaaccionpermitida = pkPlantillaaccionpermitida;
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
        hash += (pkPlantillaaccionpermitida != null ? pkPlantillaaccionpermitida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantillaaccionpermitida)) {
            return false;
        }
        Plantillaaccionpermitida other = (Plantillaaccionpermitida) object;
        if ((this.pkPlantillaaccionpermitida == null && other.pkPlantillaaccionpermitida != null) || (this.pkPlantillaaccionpermitida != null && !this.pkPlantillaaccionpermitida.equals(other.pkPlantillaaccionpermitida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
   
        return codigo +"-"+ descripcion;
    }
    
}
