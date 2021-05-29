/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess.dto.menu;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Aprendiz
 */
public class DtoPrestamo {

    BigDecimal fk_ficha, fk_municipio, fk_responsable, fk_equipo;
    Date fechasolicitud, fechadevolucion;
    String  codigo, observacion;

    public BigDecimal getFk_ficha() {
        return fk_ficha;
    }

    public void setFk_ficha(BigDecimal fk_ficha) {
        this.fk_ficha = fk_ficha;
    }

    public BigDecimal getFk_municipio() {
        return fk_municipio;
    }

    public void setFk_municipio(BigDecimal fk_municipio) {
        this.fk_municipio = fk_municipio;
    }

    public BigDecimal getFk_responsable() {
        return fk_responsable;
    }

    public void setFk_responsable(BigDecimal fk_responsable) {
        this.fk_responsable = fk_responsable;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getFk_equipo() {
        return fk_equipo;
    }

    public void setFk_equipo(BigDecimal fk_equipo) {
        this.fk_equipo = fk_equipo;
    }

    public Date getFechasolicitud() {
        return fechasolicitud;
    }

    public void setFechasolicitud(Date fechasolicitud) {
        this.fechasolicitud = fechasolicitud;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
        @Override
    public String toString (){
        return this.codigo+"";
    }
}
