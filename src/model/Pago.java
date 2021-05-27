/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author mipc
 */
public class Pago {
     private Integer idPago;
     private Integer idBoleta;
     private Date fechaPago;
     private int monto;
     private boolean estado;
     private String nombre;

    public Pago(int idBoleta, Date fechaPago, int monto) {
        this.idBoleta = idBoleta;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.estado = true;
       }
        public Pago(int idPago, int idBoleta, Date fechaPago, int monto, boolean estado) {
        this.idPago = idPago;    
        this.idBoleta = idBoleta;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.estado = estado;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    } 
        
    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }   

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
                           
        
}

