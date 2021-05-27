/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author mipc
 */
public class Redoblona {
  Jugada uno;
  Jugada dos;
 private int idRedoblona;
 private static int idSiguiente=1;
 
  
    public Redoblona(Jugada uno, Jugada dos) {
        idRedoblona=idSiguiente;
        uno.setIdRedoblona(idRedoblona);
        dos.setIdRedoblona(idRedoblona);
        this.uno = uno;
        this.dos = dos;
        idSiguiente++;
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
  
  
 }
