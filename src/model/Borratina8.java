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
public class Borratina8 {
    
    private Jugada uno;
     private Jugada dos;
     private Jugada tres;
     private Jugada cuatro;
     private Jugada cinco;
     private Jugada seis;
     private Jugada siete;
     private Jugada ocho;
  
  private static int idRedoblona =400;
  
public Borratina8(Jugada uno, Jugada dos, Jugada tres,Jugada cuatro,Jugada cinco,Jugada seis,Jugada siete,Jugada ocho) {
       this.uno = uno; this.uno.setPosicion(1);
        this.dos = dos; this.dos.setPosicion(2);
        this.tres=tres; this.tres.setPosicion(3);
        this.cuatro=cuatro; this.cuatro.setPosicion(4);
        this.cinco=cinco; this.cinco.setPosicion(5);
        this.seis=seis; this.seis.setPosicion(6);
        this.siete=siete; this.siete.setPosicion(7);
        this.ocho=ocho; this.ocho.setPosicion(8);
        
        uno.setIdRedoblona(idRedoblona);
        dos.setIdRedoblona(idRedoblona);
        tres.setIdRedoblona(idRedoblona);
        cuatro.setIdRedoblona(idRedoblona);
        cinco.setIdRedoblona(idRedoblona);
        seis.setIdRedoblona(idRedoblona);
        siete.setIdRedoblona(idRedoblona);
        ocho.setIdRedoblona(idRedoblona);
        
       idRedoblona++;
    }

    public Jugada getUno() {
        return uno;
    }

    public void setUno(Jugada uno) {
        this.uno = uno;
    }

    public Jugada getDos() {
        return dos;
    }

    public void setDos(Jugada dos) {
        this.dos = dos;
    }

    public Jugada getTres() {
        return tres;
    }

    public void setTres(Jugada tres) {
        this.tres = tres;
    }

    public Jugada getCuatro() {
        return cuatro;
    }

    public void setCuatro(Jugada cuatro) {
        this.cuatro = cuatro;
    }

    public Jugada getCinco() {
        return cinco;
    }

    public void setCinco(Jugada cinco) {
        this.cinco = cinco;
    }

    public Jugada getSeis() {
        return seis;
    }

    public void setSeis(Jugada seis) {
        this.seis = seis;
    }

    public Jugada getSiete() {
        return siete;
    }

    public void setSiete(Jugada siete) {
        this.siete = siete;
    }

    public Jugada getOcho() {
        return ocho;
    }

    public void setOcho(Jugada ocho) {
        this.ocho = ocho;
    }

    public static int getIdRedoblona() {
        return idRedoblona;
    }

    public static void setIdRedoblona(int idRedoblona) {
        Borratina8.idRedoblona = idRedoblona;
    }
    
}
