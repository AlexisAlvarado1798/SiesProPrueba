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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "TIPOMANTENIMIENTO", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomantenimiento.findAll", query = "SELECT t FROM Tipomantenimiento t"),
    @NamedQuery(name = "Tipomantenimiento.findByPkTipomantenimiento", query = "SELECT t FROM Tipomantenimiento t WHERE t.pkTipomantenimiento = :pkTipomantenimiento"),
    @NamedQuery(name = "Tipomantenimiento.findByCodigo", query = "SELECT t FROM Tipomantenimiento t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Tipomantenimiento.findByNombre", query = "SELECT t FROM Tipomantenimiento t WHERE t.nombre = :nombre")})
public class Tipomantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
       @SequenceGenerator(name = "SQTIPOMANTENIMIENTO", sequenceName = "SQTIPOMANTENIMIENTO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQTIPOMANTENIMIENTO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TIPOMANTENIMIENTO", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkTipomantenimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO", nullable = false, length = 20)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTipomantenimiento")
    private List<Mantenimiento> mantenimientoList;

    public Tipomantenimiento() {
    }

    public Tipomantenimiento(BigDecimal pkTipomantenimiento) {
        this.pkTipomantenimiento = pkTipomantenimiento;
    }

    public Tipomantenimiento(BigDecimal pkTipomantenimiento, String codigo, String nombre) {
        this.pkTipomantenimiento = pkTipomantenimiento;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public BigDecimal getPkTipomantenimiento() {
        return pkTipomantenimiento;
    }

    public void setPkTipomantenimiento(BigDecimal pkTipomantenimiento) {
        this.pkTipomantenimiento = pkTipomantenimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Mantenimiento> getMantenimientoList() {
        return mantenimientoList;
    }

    public void setMantenimientoList(List<Mantenimiento> mantenimientoList) {
        this.mantenimientoList = mantenimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTipomantenimiento != null ? pkTipomantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomantenimiento)) {
            return false;
        }
        Tipomantenimiento other = (Tipomantenimiento) object;
        if ((this.pkTipomantenimiento == null && other.pkTipomantenimiento != null) || (this.pkTipomantenimiento != null && !this.pkTipomantenimiento.equals(other.pkTipomantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
