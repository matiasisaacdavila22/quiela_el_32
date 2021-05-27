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
public class Ganada {
    
    private Integer idGanada ;
    private String numero;                       
    private int posicion;                       
    private String quiniela;                       
    private String turno; 
    private Date fecha;
   

      public Ganada(int idGanada ,String numero, int posicion, String quiniela, String turno, Date fecha) {
        this.idGanada=idGanada;
        this.numero = numero;
        this.posicion = posicion;
        this.quiniela = quiniela;
        this.turno = turno;
        this.fecha=fecha;
        }
     
     
    public Ganada(String numero, int posicion, String quiniela, String turno, Date fecha) {
        this.numero = numero;
        this.posicion = posicion;
        this.quiniela = quiniela;
        this.turno = turno;
        this.fecha = fecha;
   }
    
        public Ganada(String numero, int posicion, String quiniela, String turno) {
        
        this.numero = numero;
        this.posicion = posicion;
        this.quiniela = quiniela;
        this.turno = turno;
   }


    public int getIdGanada() {
        return idGanada;
    }

    public void setIdGanada(int idGanada) {
        this.idGanada = idGanada;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
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
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }                         
    
}
