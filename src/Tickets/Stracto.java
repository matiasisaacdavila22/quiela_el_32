/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;

import controller.JugadaController;
import model.Jugada;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Ganada;

public class Stracto {
private JugadaController jugadaController;
    
   private ObservableList<Ganada> provincia;
    private ObservableList<Ganada> nacional;
    private ObservableList<Ganada> santafe;
    private ObservableList<Ganada> entrerios;
    private ObservableList<Ganada> cordoba;
   private ObservableList<Ganada> oros;
    private String turno;

    public Stracto(ObservableList<Ganada> provincia, ObservableList<Ganada> nacional, ObservableList<Ganada> santafe, ObservableList<Ganada> entrerios, ObservableList<Ganada> cordoba, ObservableList<Ganada> oros, String turno) {
       
        this.provincia = provincia;
        this.nacional = nacional;
        this.santafe = santafe;
        this.entrerios = entrerios;
        this.cordoba = cordoba;
        this.oros = oros;
        this.turno = turno;
    }

    

public  void iprimirStracto(){
 jugadaController=new JugadaController();     
Date date=new Date();

SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
String hora=jugadaController.hora();

Ticket ticket = new Ticket();

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(""+fecha.format(date) + " " + hora);//.format(date));

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("** EL 33 **");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("JUEGUE GANEs Y COBRE YA");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(ticket.DibujarLinea(29));
/////////////////////////////////////////////////////////////////////////////////////
ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera("           ESTRACTO    ");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("TURNO:"+this.turno);
ticket.AddSubCabecera(ticket.DarEspacio());
int max= nacional.size();
ticket.AddSubCabecera("  NACIONAL PR0VICIA SANTAFE ");
ticket.AddSubCabecera(ticket.DarEspacio()); 
            if(provincia.size()>max)max=provincia.size();
            if(santafe.size()>max)max=santafe.size();
            
for(int i=0; i<max;i++){
     StringBuilder sb = new StringBuilder("                        ");
    sb.replace(0, 1, String.valueOf(i+1));
   if(nacional.size()>i)sb.replace(3, 6, nacional.get(i).getNumero());
   if(provincia.size()>i)sb.replace(13, 16, provincia.get(i).getNumero());
   if(santafe.size()>i)sb.replace(21, 24, santafe.get(i).getNumero());
   ticket.AddSubCabecera(sb.toString()); 
   ticket.AddSubCabecera(ticket.DarEspacio());
  }  
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("  ENTRERIOS CORDOBA  OROS ");  
ticket.AddSubCabecera(ticket.DarEspacio());  
            max=entrerios.size();
            if(cordoba.size()>max)max=cordoba.size();
            if(oros.size()>max)max=oros.size();
for(int i=0; i<max;i++){
     StringBuilder sb = new StringBuilder("                        ");
    sb.replace(0, 1, String.valueOf(i+1));
   if(entrerios.size()>i)sb.replace(3, 6, entrerios.get(i).getNumero());
   if(cordoba.size()>i)sb.replace(13, 16, cordoba.get(i).getNumero());
   if(oros.size()>i)sb.replace(21, 24, oros.get(i).getNumero());
   ticket.AddSubCabecera(sb.toString()); 
   ticket.AddSubCabecera(ticket.DarEspacio());
  } 

///////////////////////////////////////////////////////////////////////////////////////////////
ticket.AddTotal("",ticket.DibujarLinea(29));


ticket.AddTotal("",ticket.DarEspacio());

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
ticket.AddPieLinea(ticket.DibujarLinea(29));

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("(RECLAMOS DENTRO DE LAS 48HS)");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("Gracias por su visita");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea(ticket.DibujarLinea(29));

ticket.AddPieLinea(ticket.DarEspacio());
ticket.AddPieLinea(ticket.DarEspacio());
ticket.AddPieLinea(ticket.DibujarLinea(29));

/////////////////////////////////////////////////////////////////////////////////
ticket.ImprimirDocumento();
 }  

    public JugadaController getJugadaController() {
        return jugadaController;
    }

    public void setJugadaController(JugadaController jugadaController) {
        this.jugadaController = jugadaController;
    }

    public ObservableList<Ganada> getProvincia() {
        return provincia;
    }

    public void setProvincia(ObservableList<Ganada> provincia) {
        this.provincia = provincia;
    }

    public ObservableList<Ganada> getNacional() {
        return nacional;
    }

    public void setNacional(ObservableList<Ganada> nacional) {
        this.nacional = nacional;
    }

    public ObservableList<Ganada> getSantafe() {
        return santafe;
    }

    public void setSantafe(ObservableList<Ganada> santafe) {
        this.santafe = santafe;
    }

    public ObservableList<Ganada> getEntrerios() {
        return entrerios;
    }

    public void setEntrerios(ObservableList<Ganada> entrerios) {
        this.entrerios = entrerios;
    }

    public ObservableList<Ganada> getCordoba() {
        return cordoba;
    }

    public void setCordoba(ObservableList<Ganada> cordoba) {
        this.cordoba = cordoba;
    }

    public ObservableList<Ganada> getOros() {
        return oros;
    }

    public void setOros(ObservableList<Ganada> oros) {
        this.oros = oros;
    }
    


    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
        
    
}