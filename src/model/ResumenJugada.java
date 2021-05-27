/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mipc
 */
public class ResumenJugada {
   
    private String numero;
    private StringBuilder resumen;

    public ResumenJugada(String numero, StringBuilder resumen) {
      
        this.numero = numero;
        this.resumen = resumen;
              
    }
 
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public StringBuilder getResumen() {
        return resumen;
    }

    public void setResumen(StringBuilder resumen) {
        this.resumen = resumen;
    }
    
    
}
