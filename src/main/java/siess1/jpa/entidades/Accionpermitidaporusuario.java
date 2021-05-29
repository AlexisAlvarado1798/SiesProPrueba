/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ACCIONPERMITIDAPORUSUARIO", catalog = "", schema = "SIESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accionpermitidaporusuario.findAll", query = "SELECT a FROM Accionpermitidaporusuario a"),
    @NamedQuery(name = "Accionpermitidaporusuario.findByPkAccperusu", query = "SELECT a FROM Accionpermitidaporusuario a WHERE a.pkAccperusu = :pkAccperusu")})
public class Accionpermitidaporusuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
     @SequenceGenerator(name = "SQACCIONPORUSUARIO", sequenceName = "SQACCIONPORUSUARIO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQACCIONPORUSUARIO")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ACCPERUSU", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkAccperusu;
    @JoinColumn(name = "FK_ACCIONPERMITIDA", referencedColumnName = "PK_ACCIONPERMITIDA", nullable = false)
    @ManyToOne(optional = false)
    private Accionpermitida fkAccionpermitida;
    @JoinColumn(name = "FK_USUARIO", referencedColumnName = "PK_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private Usuario fkUsuario;

    public Accionpermitidaporusuario() {
    }

    public Accionpermitidaporusuario(BigDecimal pkAccperusu) {
        this.pkAccperusu = pkAccperusu;
    }

    public BigDecimal getPkAccperusu() {
        return pkAccperusu;
    }

    public void setPkAccperusu(BigDecimal pkAccperusu) {
        this.pkAccperusu = pkAccperusu;
    }

    public Accionpermitida getFkAccionpermitida() {
        return fkAccionpermitida;
    }

    public void setFkAccionpermitida(Accionpermitida fkAccionpermitida) {
        this.fkAccionpermitida = fkAccionpermitida;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkAccperusu != null ? pkAccperusu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accionpermitidaporusuario)) {
            return false;
        }
        Accionpermitidaporusuario other = (Accionpermitidaporusuario) object;
        if ((this.pkAccperusu == null && other.pkAccperusu != null) || (this.pkAccperusu != null && !this.pkAccperusu.equals(other.pkAccperusu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "siess1.jpa.entidades.Accionpermitidaporusuario[ pkAccperusu=" + pkAccperusu + " ]";
    }
    
}
