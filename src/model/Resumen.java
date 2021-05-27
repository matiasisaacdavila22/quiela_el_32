/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

/**
 *
 * @author juamn
 */
public class Resumen {
     private int id;
    
    private String numero;
   
    private String turno;
   
    private String quiniela;
    
    private double monto;
    
    private Date fecha;
    
    private Date fechaPago;
  
    private boolean gano;
       
    private String posiciones;
    
    private String nombre;
    
    private int idResumen;
    
    private String idJugadas;  
    
    private int tipo;
    
    private static int idR =1;
    
    private int premio;

    public Resumen(int id, String numero, String turno, String quiniela, double monto, Date fecha,Date fechaPago, boolean gano, String posiciones ,String nombre, String idsJugadas, int tipo,int premio) {
        this.id = id;
        this.numero = numero;
        this.turno = turno;
        this.quiniela = quiniela;
        this.monto = monto;
        this.fecha = fecha;
        this.fechaPago = fechaPago;
        this.gano = gano;
        this.posiciones = posiciones;
        this.nombre=nombre;
        this.idJugadas=idsJugadas;
        this.idResumen=this.idR;
        this.tipo=tipo;
        this.premio= premio;
        this.idR++;
    }

       public Resumen(int id, String numero, String turno, String quiniela, double monto, Date fecha, boolean gano, String posiciones ,String nombre, String idsJugadas, int tipo) {
        this.id = id;
        this.numero = numero;
        this.turno = turno;
        this.quiniela = quiniela;
        this.monto = monto;
        this.fecha = fecha;
        this.gano = gano;
       this.posiciones = posiciones;
        this.nombre=nombre;
        this.idJugadas=idsJugadas;
        this.idResumen=this.idR;
        this.tipo=tipo;
        this.idR++;
    }
    public int getPremio() {
        if (gano){
             return premio; 
        }
          return 0;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }
    
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
 
    public String getIdJugadas() {
        return idJugadas;
    }

    public void setIdJugadas(String idsJugadas) {
        this.idJugadas = idsJugadas;
    }  
    
    public int getIdResumen() {
        return idResumen;
    }

    public void setIdResumen(int idResumen) {
        this.idResumen = idResumen;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getQuiniela() {
        return quiniela;
    }

    public void setQuiniela(String quiniela) {
        this.quiniela = quiniela;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    
    public boolean isGano() {
        return gano;
    }

    public void setGano(boolean gano) {
        this.gano = gano;
    }

    public String getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(String posiciones) {
        this.posiciones = posiciones;
    }
}