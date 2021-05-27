/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.JugadaController;
import Tickets.Ticket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mipc
 */
public class Borratina3 {
  JugadaController jugadaController;  
 private Jugada uno;
 private Jugada dos;
 private Jugada tres;
 private static int idRedoblona =1;
  
    public Borratina3(Jugada uno, Jugada dos, Jugada tres) {
        this.uno = uno; this.uno.setPosicion(1);
        this.dos = dos; this.dos.setPosicion(2);
        this.tres=tres; this.tres.setPosicion(3);
        
        uno.setIdRedoblona(idRedoblona);
        dos.setIdRedoblona(idRedoblona);
        tres.setIdRedoblona(idRedoblona);
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

    public static int getIdRedoblona() {
        return idRedoblona;
    }

    public static void setIdRedoblona(int idRedoblona) {
        Borratina3.idRedoblona = idRedoblona;
    }
    
}
