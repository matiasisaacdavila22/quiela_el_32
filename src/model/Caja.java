/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;

/**
 *
 * @author mipc
 */
public class Caja {
    int id;
    Date fecha;
    Double caja_inicial;
    Double caja_final;
    boolean estado; 

    public Caja(int id, Date fecha, Double caja_inicial, Double caja_final, boolean estado) {
        this.id = id;
        this.fecha = fecha;
        this.caja_inicial = caja_inicial;
        this.caja_final = caja_final;
        this.estado = estado;
    }
    public Caja(Double caja_inicial) {
      
        this.caja_inicial = caja_inicial;
        this.caja_final = caja_inicial;
        this.estado = true;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getCaja_inicial() {
        return caja_inicial;
    }

    public void setCaja_inicial(Double caja_inicial) {
        this.caja_inicial = caja_inicial;
    }

    public Double getCaja_final() {
        return caja_final;
    }

    public void setCaja_final(Double caja_final) {
        this.caja_final = caja_final;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }  
    
    
}

