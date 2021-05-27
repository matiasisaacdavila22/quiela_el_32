/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author holasur
 */
public class Movimiento {
    
    private int id;
    private int idCaja;
    private Double monto;
    private String descripcion;
    private boolean estado;
    private Date fecha;

    public Movimiento(int id, int idCaja, Double monto, String descripcion, boolean estado, Date fecha) {
        this.id = id;
        this.idCaja = idCaja;
        this.monto = monto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Movimiento(int idCaja, Double monto, String descripcion, boolean estado) {
        this.idCaja = idCaja;
        this.monto = monto;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
