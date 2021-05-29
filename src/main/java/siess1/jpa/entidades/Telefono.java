/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "TELEFONO", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefono.findAll", query = "SELECT t FROM Telefono t"),
    @NamedQuery(name = "Telefono.findByPkTelefono", query = "SELECT t FROM Telefono t WHERE t.pkTelefono = :pkTelefono"),
    @NamedQuery(name = "Telefono.findByNumero", query = "SELECT t FROM Telefono t WHERE t.numero = :numero")})
public class Telefono implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
      @Id
    @SequenceGenerator(name = "SQTELEFONO", sequenceName = "SQTELEFONO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQTELEFONO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TELEFONO", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkTelefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO", nullable = false)
    private BigInteger numero;
    @JoinColumn(name = "FK_PERSONA", referencedColumnName = "PK_PERSONA", nullable = false)
    @ManyToOne(optional = false)
    private Persona fkPersona;
    @JoinColumn(name = "FK_TIPOTELEFONO", referencedColumnName = "PK_TIPOTELEFONO", nullable = false)
    @ManyToOne(optional = false)
    private Tipotelefono fkTipotelefono;

    public Telefono() {
    }

    public Telefono(BigDecimal pkTelefono) {
        this.pkTelefono = pkTelefono;
    }

    public Telefono(BigDecimal pkTelefono, BigInteger numero) {
        this.pkTelefono = pkTelefono;
        this.numero = numero;
    }

    public BigDecimal getPkTelefono() {
        return pkTelefono;
    }

    public void setPkTelefono(BigDecimal pkTelefono) {
        this.pkTelefono = pkTelefono;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    public Persona getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(Persona fkPersona) {
        this.fkPersona = fkPersona;
    }

    public Tipotelefono getFkTipotelefono() {
        return fkTipotelefono;
    }

    public void setFkTipotelefono(Tipotelefono fkTipotelefono) {
        this.fkTipotelefono = fkTipotelefono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTelefono != null ? pkTelefono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefono)) {
            return false;
        }
        Telefono other = (Telefono) object;
        if ((this.pkTelefono == null && other.pkTelefono != null) || (this.pkTelefono != null && !this.pkTelefono.equals(other.pkTelefono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "-"+numero;
    }
    
}
