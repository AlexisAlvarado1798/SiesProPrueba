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
@Table(name = "FICHA", catalog = "", schema = "SIESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ficha.findAll", query = "SELECT f FROM Ficha f"),
    @NamedQuery(name = "Ficha.findByPkFicha", query = "SELECT f FROM Ficha f WHERE f.pkFicha = :pkFicha"),
    @NamedQuery(name = "Ficha.findByCodigo", query = "SELECT f FROM Ficha f WHERE f.codigo = :codigo")})
public class Ficha implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
       @Id
     @SequenceGenerator(name = "SQFICHA", sequenceName = "SQFICHA", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQFICHA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FICHA", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkFicha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO", nullable = false, length = 20)
    private String codigo;
    @JoinColumn(name = "FK_VOCERO", referencedColumnName = "PK_PERSONA", nullable = false)
    @ManyToOne(optional = false)
    private Persona fkVocero;
    @JoinColumn(name = "FK_INSTRUCTOR", referencedColumnName = "PK_PERSONA", nullable = false)
    @ManyToOne(optional = false)
    private Persona fkInstructor;
    @JoinColumn(name = "FK_PROGRAMAFORMACION", referencedColumnName = "PK_PROGRAMAFORMACION", nullable = false)
    @ManyToOne(optional = false)
    private Programaformacion fkProgramaformacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFicha")
    private List<Prestamo> prestamoList;

    public Ficha() {
    }

    public Ficha(BigDecimal pkFicha) {
        this.pkFicha = pkFicha;
    }

    public Ficha(BigDecimal pkFicha, String codigo) {
        this.pkFicha = pkFicha;
        this.codigo = codigo;
    }

    public BigDecimal getPkFicha() {
        return pkFicha;
    }

    public void setPkFicha(BigDecimal pkFicha) {
        this.pkFicha = pkFicha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Persona getFkVocero() {
        return fkVocero;
    }

    public void setFkVocero(Persona fkVocero) {
        this.fkVocero = fkVocero;
    }

    public Persona getFkInstructor() {
        return fkInstructor;
    }

    public void setFkInstructor(Persona fkInstructor) {
        this.fkInstructor = fkInstructor;
    }

    public Programaformacion getFkProgramaformacion() {
        return fkProgramaformacion;
    }

    public void setFkProgramaformacion(Programaformacion fkProgramaformacion) {
        this.fkProgramaformacion = fkProgramaformacion;
    }

    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkFicha != null ? pkFicha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ficha)) {
            return false;
        }
        Ficha other = (Ficha) object;
        if ((this.pkFicha == null && other.pkFicha != null) || (this.pkFicha != null && !this.pkFicha.equals(other.pkFicha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo;
    }
    
}
