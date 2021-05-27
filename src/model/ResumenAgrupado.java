/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author holasur
 */
public class ResumenAgrupado {
    
    String numero;
    int cantidad;
    Double total;
    String quinielas;
    String turno;

    public ResumenAgrupado(String numero, int cantidad, Double total, String quinielas, String turno) {
        this.numero = numero;
        this.cantidad = cantidad;
        this.total = total;
        this.quinielas = quinielas;
        this.turno = turno;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getQuinielas() {
        return quinielas;
    }

    public void setQuinielas(String quinielas) {
        this.quinielas = quinielas;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
 public boolean equals(ResumenAgrupado ra){
     if(this.getNumero().equals(ra.getNumero()) && this.getTurno().equals(ra.getTurno())){
         if(this.getQuinielas().length() == ra.getQuinielas().length()){
             if(this.getQuinielas().length() > 1){
                 System.out.println("************************la longitud es mayor de 1 "+this.getQuinielas().length());
                 return true;
             }else{
                  System.out.println("*******************la longitud es == de 1 "+this.getQuinielas().length());
                 return this.getQuinielas().equals(ra.getQuinielas());
             }
         }
         return false;
     }
     return false;

 }
 
 public void sumarUno(){
     this.cantidad++;
 }
 public void sumarTotal(ResumenAgrupado ra){
     this.total = this.total + ra.getTotal();
 }   
}

