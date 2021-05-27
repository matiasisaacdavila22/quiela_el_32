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
public class Boleta {
    
   private int id;
   private String nombre;
   private int total;
   private Date fecha;
   private boolean estado;
   //////////////////////////////////////////////////////hasta aca es la base
   private boolean pagado;
   private Date fechaPago;
   private int totalPago;

    public Boleta(int id, String nombre, int total, Date fecha, boolean estado) {
        this.id = id;
        this.nombre=nombre;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.pagado = false;
        
    }
        public Boleta(String nombre, int total, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.total = total;
        this.estado = false;
        this.pagado = false;
    }
      public Boleta(String nombre, int total) {
        this.nombre = nombre;
        this.total = total;
        this.estado = false;
        this.pagado = false;
    }

    public Boleta(Date fecha) {
        this.fecha = fecha;
    }

    public Boleta(String nombre, int total, Date fecha, boolean estado ) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.pagado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(int totalPago) {
        this.totalPago = totalPago;
    }
    
    
    
}