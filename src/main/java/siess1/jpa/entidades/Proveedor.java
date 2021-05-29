/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "PROVEEDOR", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NIT"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findFiltro", query = "SELECT p FROM Proveedor p where p.nombrre like '%:filtro%' "),
    @NamedQuery(name = "Proveedor.findByPkProveedor", query = "SELECT p FROM Proveedor p WHERE p.pkProveedor = :pkProveedor"),
    @NamedQuery(name = "Proveedor.findByNit", query = "SELECT p FROM Proveedor p WHERE p.nit = :nit"),
    @NamedQuery(name = "Proveedor.findByNombrre", query = "SELECT p FROM Proveedor p WHERE p.nombrre = :nombrre"),
    @NamedQuery(name = "Proveedor.findByDireccion", query = "SELECT p FROM Proveedor p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Proveedor.findByTelefono", query = "SELECT p FROM Proveedor p WHERE p.telefono = :telefono")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
    @SequenceGenerator(name = "SQPROVEEDOR", sequenceName = "SQPROVEEDOR", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQPROVEEDOR")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PROVEEDOR", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIT", nullable = false)
    private BigInteger nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRRE", nullable = false, length = 100)
    private String nombrre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIRECCION", nullable = false, length = 100)
    private String direccion;
    @Column(name = "TELEFONO")
    private BigInteger telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProveedor")
    private List<Suministra> suministraList;

    public Proveedor() {
    }

    public Proveedor(BigDecimal pkProveedor) {
        this.pkProveedor = pkProveedor;
    }

    public Proveedor(BigDecimal pkProveedor, BigInteger nit, String nombrre, String direccion) {
        this.pkProveedor = pkProveedor;
        this.nit = nit;
        this.nombrre = nombrre;
        this.direccion = direccion;
    }

    public BigDecimal getPkProveedor() {
        return pkProveedor;
    }

    public void setPkProveedor(BigDecimal pkProveedor) {
        this.pkProveedor = pkProveedor;
    }

    public BigInteger getNit() {
        return nit;
    }

    public void setNit(BigInteger nit) {
        this.nit = nit;
    }

    public String getNombrre() {
        return nombrre;
    }

    public void setNombrre(String nombrre) {
        this.nombrre = nombrre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<Suministra> getSuministraList() {
        return suministraList;
    }

    public void setSuministraList(List<Suministra> suministraList) {
        this.suministraList = suministraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProveedor != null ? pkProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.pkProveedor == null && other.pkProveedor != null) || (this.pkProveedor != null && !this.pkProveedor.equals(other.pkProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nit+"-"+nombrre;
    }
    
}
