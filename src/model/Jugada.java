/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.Formatter;
import utils.Conexion;


public class Jugada {
    
    
 Conexion conex=new Conexion();
 private Formatter obj = new Formatter();

    public int idJugada;
    public int tipo;
    public String numero;
    public int cifras;
    public int posicion;
    public int monto;
    public String quiniela;
    public String turno;
    public Date fecha;
    public String nombre;
    public boolean gano;
    public int idBoleta;
    public int idRedoblona;
    
    ///////////////////////////////////////////////
    public double TotalPago;
    
        
    public Jugada(int tipo, String numero, int cifras,int posicion, int monto, String quiniela, String turno,boolean gano){
       this.tipo=tipo;
       this.numero = numero;
       this.cifras=cifras;
       this.posicion=posicion;
       this.monto=monto;
       this.quiniela=quiniela;
       this.turno=turno;
       this.gano=gano;      
       }
    
        public Jugada(int idJugada,int tipo, String numero,int cifras,int posicion, int monto, String quiniela, String turno, Date fecha, boolean gano, int idBoleta, int idRedoblona) {
       this.idJugada=idJugada;
       this.tipo=tipo;
        this.numero = numero;
       this.cifras=cifras;
       this.posicion=posicion;
       this.monto=monto;
       this.quiniela=quiniela;
       this.turno=turno;
       this.fecha=fecha;
       this.nombre=nombre;
       this.gano=gano;
       this.idBoleta=idBoleta; 
       this.idRedoblona=idRedoblona;
       }

    public double getTotalPago() {
        return TotalPago;
    }

    public void setTotalPago(double TotalPago) {
        this.TotalPago = TotalPago;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
        
    public Conexion getConex() {
        return conex;
    }

    public void setConex(Conexion conex) {
        this.conex = conex;
    }

     public int getIdJugada() {
        return idJugada;
    }

    public void setIdJugada(int idJugada) {
        this.idJugada = idJugada;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
         this.numero = String.valueOf(obj.format("%04d", numero));
    }

    public int getCifras() {
        return cifras;
    }

    public void setCifras(int cifras) {
        this.cifras = cifras;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getQuiniela() {
        return quiniela;
    }

    public void setQuiniela(String quiniela) {
        this.quiniela = quiniela;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isGano() {
        return gano;
    }

    public void setGano(boolean gano) {
        this.gano = gano;
    }

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public int getIdRedoblona() {
        return idRedoblona;
    }

    public void setIdRedoblona(int idRedoblona) {
        this.idRedoblona = idRedoblona;
    }
            
}

  