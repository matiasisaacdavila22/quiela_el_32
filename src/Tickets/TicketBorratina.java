/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;

import controller.JugadaController;
import controller.ResumenController;
import model.Borratina3;
import model.Borratina5;
import model.Borratina8;
import model.Jugada;
import model.Resumen;
import model.TextFieldResumen;
//import com.itextpdf.text.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mipc
 */
public class TicketBorratina {
    private JugadaController jugadaController;
    private int idBoleta;
    private String nombre;
    private ArrayList<Borratina3>borratina3;
    private ArrayList<Borratina5>borratina5;
    private ArrayList<Borratina8>borratina8;
    private int totalBoleta;

    public TicketBorratina(ArrayList<Borratina3> borratina3, ArrayList<Borratina5> borratina5, ArrayList<Borratina8> borratina8, int idBoleta,String nombre, int totalBoleta) {
        this.borratina3 = borratina3;
        this.borratina5 = borratina5;
        this.borratina8 = borratina8;
        this.idBoleta=idBoleta;
        this.nombre=nombre;
        this.totalBoleta=totalBoleta;
    }
    public TicketBorratina(){
        
    }

public void imprimirTicketBorratina(ArrayList<TextFieldResumen> resumen) {
jugadaController=new JugadaController();     
Date date=new Date();
SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
String hora=jugadaController.hora();
String turno;
int total1=0;
int total2=0;
int total3=0;
Ticket ticket = new Ticket();

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(""+fecha.format(date) + " " + hora);//.format(date));

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("** EL 33 **");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("JUEGUE GANE Y COBRE YA ...");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(ticket.DibujarLinea(29));
/////////////////////////////////////////////////////////////////////////////////////
ticket.AddSubCabecera(ticket.DarEspacio());

//////////////////////////////////////////////////////////////////////////////////

ticket.AddSubCabecera("boleta :"+this.idBoleta);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("Nombre : "+this.nombre);

ticket.AddSubCabecera(ticket.DarEspacio());

ArrayList<TextFieldResumen> tres = new ArrayList();
ArrayList<TextFieldResumen> cinco = new ArrayList();
ArrayList<TextFieldResumen> ocho = new ArrayList();

for(TextFieldResumen r : resumen){  
   String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  
  String[] tipos = this.limpiarFormato(resultado[0]);
  String tipo = tipos[0];
   
  if(tipo.equals("3"))tres.add(r);
  if(tipo.equals("5"))cinco.add(r);
  if(tipo.equals("8"))ocho.add(r);

    }
if(!tres.isEmpty()){ 
ArrayList<TextFieldResumen> primera = new ArrayList();
ArrayList<TextFieldResumen> matutina = new ArrayList();
ArrayList<TextFieldResumen> tarde = new ArrayList();
ArrayList<TextFieldResumen> nocturna = new ArrayList();    
    for(TextFieldResumen tfr : tres){
        String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(tfr.getText());
  
  String[] turnos = this.limpiarFormato(resultado[1]);
    for(String t : turnos){
        if(t.equals("11"))primera.add(tfr);
       if(t.equals("14"))matutina.add(tfr);
       if(t.equals("17"))tarde.add(tfr);
       if(t.equals("21"))nocturna.add(tfr);
    }
    }
    
ticket.AddSubCabecera("       ***BORRARINA 3***    ");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("valor :$5 (x loteria)");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ArrayList<Borratina3>aux = new ArrayList();
ArrayList<Borratina3>aux2 = new ArrayList();
//borratina3.stream().forEach((n)->n.get);    
    
if(!primera.isEmpty()){

ticket.AddSubCabecera("PRIMERA");  
   
  String quinielaActual="n";  
 for(TextFieldResumen r : primera){ 
   
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
            
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString();
        
  String[] quini = limpiarFormato(resultado[2]);
  StringBuilder sbq = new StringBuilder();
        for(String q : quini){
            if(!q.equals("O")){
                sbq.append(q);
                sbq.append(" ");
            }
        }
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 

}
 
}
 ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio()); 
}     
///////////////////////////////////////////////////////////////////////////////////////////////
if(!matutina.isEmpty()){

             
 ticket.AddSubCabecera(" MATUTINA");  

     String quinielaActual="n";
    for(TextFieldResumen r : matutina){ 
   
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
            
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString();
 
  
  String[] quini = limpiarFormato(resultado[2]);
  StringBuilder sbq = new StringBuilder();
        for(String q : quini){
           
                sbq.append(q);
                sbq.append(" ");
            }
        
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
   if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 

}   
}
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}  
/////////////////////////////////////////////////////////////////////////////////////
if(!tarde.isEmpty()){
                
 ticket.AddSubCabecera(" TARDE");  

String quinielaActual="n";
    for(TextFieldResumen r : tarde){ 
   
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
                
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString();
 
  
  String[] quini = limpiarFormato(resultado[2]);
  StringBuilder sbq = new StringBuilder();
        for(String q : quini){
                if(!q.equals("O")){
                sbq.append(q);
                sbq.append(" ");
            }
        }
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
   if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 

}   
}
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}   
///////////////////////////////////////////////////////////////////////
if(!nocturna.isEmpty()){
 
     ticket.AddSubCabecera("NOCTURNA");  

    String quinielaActual="n";
    for(TextFieldResumen r : nocturna){ 
   
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
                
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString();
 
  
  String[] quini = limpiarFormato(resultado[2]);
  StringBuilder sbq = new StringBuilder();
        for(String q : quini){
                
                sbq.append(q);
                sbq.append(" ");
            }
        
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
  if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}   
} 
} 
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("*** BORRATINA DE 3 ***");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 3 en Orden  $35000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 3           $16000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 4           $2800");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 7           $1600");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 10          $650");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 15          $300");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 20          $120");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Valor de referencia  $5");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
   
}
if(!cinco.isEmpty()){ 
ArrayList<TextFieldResumen> primera = new ArrayList();
ArrayList<TextFieldResumen> matutina = new ArrayList();
ArrayList<TextFieldResumen> tarde = new ArrayList();
ArrayList<TextFieldResumen> nocturna = new ArrayList(); 

    for(TextFieldResumen tfr : cinco){
        String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(tfr.getText());
  
  String[] turnos = this.limpiarFormato(resultado[1]);
    for(String t : turnos){
        if(t.equals("11"))primera.add(tfr);
       if(t.equals("14"))matutina.add(tfr);
       if(t.equals("17"))tarde.add(tfr);
       if(t.equals("21"))nocturna.add(tfr);
    }
    }
    
ticket.AddSubCabecera("       ***BORRARINA 5***    ");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("valor :$5 (x loteria)");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
  
    
if(!primera.isEmpty()){

     ticket.AddSubCabecera("PRIMERA");  

    String quinielaActual = "n";
    for(TextFieldResumen r : primera){ 
   
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
            
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString();
 
  
  String[] quini = limpiarFormato(resultado[2]);
  StringBuilder sbq = new StringBuilder();
        for(String q : quini){
            if(!q.equals("O")){
                sbq.append(q);
                sbq.append("-");
            }
        }
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
 if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 

}   
}
 ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio()); 
}     
     
///////////////////////////////////////////////////////////////////////////////////////////////
if(!matutina.isEmpty()){

     ticket.AddSubCabecera("MATUTINA");  

  String quinielaActual = "n";  
    for(TextFieldResumen r : matutina){ 
   
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
            
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString();
 
  
  String[] quini = limpiarFormato(resultado[2]);
  StringBuilder sbq = new StringBuilder();
        for(String q : quini){
           
                sbq.append(q);
                sbq.append(" ");
            }
        
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
      
 if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 

}   
}
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}  
/////////////////////////////////////////////////////////////////////////////////////
if(!tarde.isEmpty()){

     ticket.AddSubCabecera("TARDE");  

    String quinielaActual = "n";
    for(TextFieldResumen r : tarde){ 
   
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
                
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString();
 
  
  String[] quini = limpiarFormato(resultado[2]);
  StringBuilder sbq = new StringBuilder();
        for(String q : quini){
                if(!q.equals("O")){
                sbq.append(q);
                sbq.append(" ");
            }
        }
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
      
 if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 

}   
}
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}   
///////////////////////////////////////////////////////////////////////
if(!nocturna.isEmpty()){

ticket.AddSubCabecera("\"NOCTURNA\"");  

    String quinielaActual = "n";
    for(TextFieldResumen r : nocturna){ 
   
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
                
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString(); 
  String[] quini = limpiarFormato(resultado[2]);
  StringBuilder sbq = new StringBuilder();
        for(String q : quini){
                
                sbq.append(q);
                sbq.append(" ");
            }       
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
 
 if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}   
}
 ticket.AddSubCabecera(ticket.DarEspacio()); 
} 
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("*** BORRATINA DE 5 ***");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 5 a los 18       $10000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 4 en 18          $500");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 18           $65");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Valor de referencia  $5");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));   
}
//////////////////////////////////////////////////////////////////////////
if(!ocho.isEmpty()){ 
ArrayList<TextFieldResumen> primera = new ArrayList();
ArrayList<TextFieldResumen> matutina = new ArrayList();
ArrayList<TextFieldResumen> tarde = new ArrayList();
ArrayList<TextFieldResumen> nocturna = new ArrayList(); 

for(TextFieldResumen tfr : ocho){
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(tfr.getText());
  
  String[] turnos = this.limpiarFormato(resultado[1]);
    for(String t : turnos){
        if(t.equals("11"))primera.add(tfr);
       if(t.equals("14"))matutina.add(tfr);
       if(t.equals("17"))tarde.add(tfr);
       if(t.equals("21"))nocturna.add(tfr);
    }
    }    
ticket.AddSubCabecera("       ***BORRARINA 8***    ");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("valor :$5 (x loteria)");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
    
if(!primera.isEmpty()){
     ticket.AddSubCabecera("PRIMERA");  
     ticket.AddSubCabecera(ticket.DarEspacio());
     ticket.AddSubCabecera("QUINIELA: "+" N-P");
     ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
for(TextFieldResumen r : primera){ 
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString(); 
 ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}
 ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}     
///////////////////////////////////////////////////////////////////////////////////////////////
if(!matutina.isEmpty()){

     ticket.AddSubCabecera("MATUTINA");  

   ticket.AddSubCabecera(ticket.DarEspacio());
     ticket.AddSubCabecera("QUINIELA: "+" N-P");
     ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
for(TextFieldResumen r : matutina){ 
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString(); 
 ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}   
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}  
/////////////////////////////////////////////////////////////////////////////////////
if(!tarde.isEmpty()){

     ticket.AddSubCabecera("TARDE");  
   ticket.AddSubCabecera(ticket.DarEspacio());
     ticket.AddSubCabecera("QUINIELA: "+" N-P");
     ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
for(TextFieldResumen r : tarde){ 
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString(); 
 ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
} 
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}   
///////////////////////////////////////////////////////////////////////
if(!nocturna.isEmpty()){

ticket.AddSubCabecera("\"NOCTURNA\"");  

   ticket.AddSubCabecera(ticket.DarEspacio());
     ticket.AddSubCabecera("QUINIELA: "+" N-P");
     ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
for(TextFieldResumen r : nocturna){ 
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getText());
  String[] numeros = limpiarFormato(resultado[3]);
  StringBuilder sbn = new StringBuilder();
        for(String n : numeros){
                sbn.append(n);
                sbn.append("  ");
            }
  String borratina = sbn.toString(); 
 ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
} 
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
}
ticket.AddSubCabecera("*** BORRATINA DE 8 ***");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Juega a los 40 premios ");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Loteria Nacional y Provincia");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("Con 8 aciertos       $8000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 7 aciertos       $320");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 6 aciertos       $35");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Valor de referencia  $5");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
}

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera("TOTAL:$"+this.totalBoleta);
ticket.AddSubCabecera(ticket.DarEspacio());
 
ticket.ImprimirDocumento();
}
public void reimprimir(int idBoleta, int total){
    jugadaController=new JugadaController(); 
   ArrayList<Jugada>jugadas = new ArrayList();   
   jugadas = jugadaController.buscarJugadas(idBoleta);
   Jugada j = jugadas.get(0);
if(!jugadas.isEmpty()){
    if(j.getTipo()<3){
        this.imprimirJugadas(jugadas, total);
    }
    else{
        this.imprimirBorratinas(jugadas, total);
    }
}
}
public void imprimirBorratinas(ArrayList<Jugada> jugadas,int total){
    this.resetCacheImpresora();
    Jugada j = jugadas.get(0);
   Date date = j.getFecha();

SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
String hora = "corregir hora";
String turno;

Ticket ticket = new Ticket();

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(""+fecha.format(date) + " " + hora);

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("** EL 33 **");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("JUEGUE GANE Y COBRE YA ...");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(ticket.DibujarLinea(29));
/////////////////////////////////////////////////////////////////////////////////////
ticket.AddSubCabecera(ticket.DarEspacio());

//////////////////////////////////////////////////////////////////////////////////

ticket.AddSubCabecera("boleta :"+j.getIdBoleta());
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("Nombre : "+j.getNombre());

ticket.AddSubCabecera(ticket.DarEspacio());

ResumenController resumenController = new ResumenController();
ObservableList<Resumen> resumenes = FXCollections.observableArrayList();
resumenes = resumenController.crearResumen(j.getIdBoleta());

ArrayList<Resumen>tres = new ArrayList();
ArrayList<Resumen>cinco = new ArrayList();
ArrayList<Resumen>ocho = new ArrayList();

for( Resumen rs : resumenes){
    if(rs.getTipo()==3)tres.add(rs);
    if(rs.getTipo()==5)cinco.add(rs);
    if(rs.getTipo()==8)ocho.add(rs);
}
if(!tres.isEmpty()){ 
ArrayList<Resumen> primera = new ArrayList();
ArrayList<Resumen> matutina = new ArrayList();
ArrayList<Resumen> tarde = new ArrayList();
ArrayList<Resumen> nocturna = new ArrayList();    
for(Resumen tfr : tres){
  String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] turnos= p1.split(tfr.getTurno());

    for(String t : turnos){
        if(t.equals("11")){Resumen rs = tfr; rs.setTurno("11");primera.add(rs);}
        if(t.equals("14")){Resumen rs = tfr; rs.setTurno("14");matutina.add(rs);}
        if(t.equals("17")){Resumen rs = tfr; rs.setTurno("17");tarde.add(rs);}
        if(t.equals("21")){Resumen rs = tfr; rs.setTurno("21");nocturna.add(rs);}
    }   
    }    
ticket.AddSubCabecera("       ***BORRARINA 3***    ");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("valor :$5 (x loteria)");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ArrayList<Borratina3>aux = new ArrayList();
ArrayList<Borratina3>aux2 = new ArrayList();
   
if(!primera.isEmpty()){

ticket.AddSubCabecera("PRIMERA");  
   
  String quinielaActual="n";  
 for(Resumen r : primera){ 
  String borratina = r.getNumero();
  String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] quini= p1.split(r.getQuiniela());
  StringBuilder sbq = new StringBuilder();
  for(String q : quini){ if(!q.contains("O"))sbq.append(q);sbq.append(" ");}
  String quinielas1 = sbq.toString();
  
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
} 
}
 ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio()); 
}     
///////////////////////////////////////////////////////////////////////////////////////////////
if(!matutina.isEmpty()){
    
 ticket.AddSubCabecera(" MATUTINA");  

     String quinielaActual="n";
 for(Resumen r : matutina){ 
  String borratina = r.getNumero();
  String quinielas1 = r.getQuiniela();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
   if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 

}   
}
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}  
/////////////////////////////////////////////////////////////////////////////////////
if(!tarde.isEmpty()){
                
 ticket.AddSubCabecera(" TARDE");  

String quinielaActual="n";
 for(Resumen r : tarde){ 
  String borratina = r.getNumero();
  String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] quini= p1.split(r.getQuiniela());
  StringBuilder sbq = new StringBuilder();
  for(String q : quini){ if(!q.contains("O"))sbq.append(q);sbq.append(" ");}
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
   if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}   
}
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}   
///////////////////////////////////////////////////////////////////////
if(!nocturna.isEmpty()){
    System.out.println("entro en reimprecion de borratina de tres entro en nocturna");
ticket.AddSubCabecera("NOCTURNA");  
    String quinielaActual="n";
 for(Resumen r : nocturna){ 
  String borratina = r.getNumero();
  String quinielas1 = r.getQuiniela();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
  if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}   
} 
} 
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("*** BORRATINA DE 3 ***");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 3 en Orden  $35000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 3           $16000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 4           $2800");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 7           $1600");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 10          $650");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 15          $300");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 20          $120");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Valor de referencia  $5");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));   
}
if(!cinco.isEmpty()){ 
ArrayList<Resumen> primera = new ArrayList();
ArrayList<Resumen> matutina = new ArrayList();
ArrayList<Resumen> tarde = new ArrayList();
ArrayList<Resumen> nocturna = new ArrayList();    
for(Resumen tfr : cinco){
    String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] turnos= p1.split(tfr.getTurno());
    for(String t : turnos){
        if(t.equals("11")){Resumen rs = tfr; rs.setTurno("11");primera.add(rs);}
        if(t.equals("14")){Resumen rs = tfr; rs.setTurno("14");matutina.add(rs);}
        if(t.equals("17")){Resumen rs = tfr; rs.setTurno("17");tarde.add(rs);}
        if(t.equals("21")){Resumen rs = tfr; rs.setTurno("21");nocturna.add(rs);}
    }   
    }  
ticket.AddSubCabecera(ticket.DarEspacio());    
ticket.AddSubCabecera("       ***BORRARINA 5***    ");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("valor :$5 (x loteria)");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
  
    
if(!primera.isEmpty()){

ticket.AddSubCabecera("PRIMERA");  
String quinielaActual="n";
 for(Resumen r : primera){ 
  String borratina = r.getNumero();
    String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] quini= p1.split(r.getQuiniela());
  StringBuilder sbq = new StringBuilder();
  for(String q : quini){ if(!q.contains("O"))sbq.append(q);sbq.append(" ");}
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
 if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 

}   
}
 ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio()); 
}     
     
///////////////////////////////////////////////////////////////////////////////////////////////
if(!matutina.isEmpty()){

     ticket.AddSubCabecera("MATUTINA");  
 
String quinielaActual="n";
 for(Resumen r : primera){ 
  String borratina = r.getNumero();
  String quinielas1 = r.getQuiniela();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
      
 if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 

}   
}
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}  
/////////////////////////////////////////////////////////////////////////////////////
if(!tarde.isEmpty()){

     ticket.AddSubCabecera("TARDE");  
String quinielaActual="n";
 for(Resumen r : tarde){ 
  String borratina = r.getNumero();
   String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] quini= p1.split(r.getQuiniela());
  StringBuilder sbq = new StringBuilder();
  for(String q : quini){ if(!q.contains("O"))sbq.append(q);sbq.append(" ");}
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
      
 if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 

}   
}
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}   
///////////////////////////////////////////////////////////////////////
if(!nocturna.isEmpty()){

ticket.AddSubCabecera("\"NOCTURNA\"");  
String quinielaActual="n";
 for(Resumen r : nocturna){ 
  String borratina = r.getNumero();
  String quinielas1 = r.getQuiniela();
  String quinielasT = "TODAS";
  String quinielas;  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
 
 if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}else {

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(""+borratina); 
}   
}
 ticket.AddSubCabecera(ticket.DarEspacio()); 
} 
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("*** BORRATINA DE 5 ***");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 5 a los 18       $10000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 4 en 18          $500");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 18           $65");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Valor de referencia  $5");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
}
//////////////////////////////////////////////////////////////////////////
if(!ocho.isEmpty()){ 
    
ArrayList<Resumen> primera = new ArrayList();
ArrayList<Resumen> matutina = new ArrayList();
ArrayList<Resumen> tarde = new ArrayList();
ArrayList<Resumen> nocturna = new ArrayList();    
for(Resumen tfr : ocho){
   String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] turnos= p1.split(tfr.getTurno());
  
    for(String t : turnos){
        System.out.println("el turno en reimprecion de borratina de 8 son : "+t);
        if(t.equals("11")){Resumen rs = tfr; rs.setTurno("11");primera.add(rs);}
        if(t.equals("14")){Resumen rs = tfr; rs.setTurno("14");matutina.add(rs);}
        if(t.equals("17")){Resumen rs = tfr; rs.setTurno("17");tarde.add(rs);}
        if(t.equals("21")){Resumen rs = tfr; rs.setTurno("21");nocturna.add(rs);}
    }   
    }      
ticket.AddSubCabecera(ticket.DarEspacio());   
ticket.AddSubCabecera("       ***BORRARINA 8***    ");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("valor :$5 (x loteria)");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
    
///////////////////////////////////////////////////////////////////////
if(!primera.isEmpty()){
ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera("PRIMERA");
 String quinielas="N_P";
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
for(Resumen r : primera){ 
 String borratina = r.getNumero();
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(""+borratina); 
}   
 ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio()); 
}   
if(!matutina.isEmpty()){
 ticket.AddSubCabecera("MATUTINA");
 String quinielas="N_P";
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
for(Resumen r : matutina){ 
 String borratina = r.getNumero();
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(""+borratina); 
}   
 ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio()); 
} 
if(!tarde.isEmpty()){
 ticket.AddSubCabecera("TARDE");
 String quinielas="N_P";
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
for(Resumen r : tarde){ 
 String borratina = r.getNumero();
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(""+borratina); 
}   
 ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio()); 
} 
if(!nocturna.isEmpty()){
 ticket.AddSubCabecera("NOCTURNA");
 String quinielas="N_P";
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
for(Resumen r : nocturna){ 
 String borratina = r.getNumero();
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(""+borratina); 
}   
 ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio()); 
} 
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("*** BORRATINA DE 8 ***");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Juega a los 40 premios ");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Loteria Nacional y Provincia");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("Con 8 aciertos       $8000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 7 aciertos       $320");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 6 aciertos       $35");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Valor de referencia  $5");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
}

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera("TOTAL:$"+total);
ticket.AddSubCabecera(ticket.DarEspacio());
 
ticket.ImprimirDocumento();
}
public void imprimirJugadas(ArrayList<Jugada> jugadas, int total){
     this.resetCacheImpresora();
    Jugada j = jugadas.get(0);
   Date date = j.getFecha();

SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
String hora = "corregir hora";
String turno;

Ticket ticket = new Ticket();

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(""+fecha.format(date) + " " + hora);

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("** EL 33 **");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("JUEGUE GANE Y COBRE YA ...");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(ticket.DibujarLinea(29));
/////////////////////////////////////////////////////////////////////////////////////
ticket.AddSubCabecera(ticket.DarEspacio());

//////////////////////////////////////////////////////////////////////////////////

ticket.AddSubCabecera("boleta :"+j.getIdBoleta());
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("Nombre : "+j.getNombre());

ticket.AddSubCabecera(ticket.DarEspacio());

ResumenController resumenController = new ResumenController();
ObservableList<Resumen> resumenes = FXCollections.observableArrayList();
resumenes = resumenController.crearResumen(j.getIdBoleta());

if(!resumenes.isEmpty()){ 
ArrayList<Resumen> primera = new ArrayList();
ArrayList<Resumen> matutina = new ArrayList();
ArrayList<Resumen> tarde = new ArrayList();
ArrayList<Resumen> nocturna = new ArrayList();    
for(Resumen tfr : resumenes){
  String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] turnos= p1.split(tfr.getTurno());

    for(String t : turnos){
        if(t.equals("11")){Resumen rs = tfr; rs.setTurno("11");primera.add(rs);}
        if(t.equals("14")){Resumen rs = tfr; rs.setTurno("14");matutina.add(rs);}
        if(t.equals("17")){Resumen rs = tfr; rs.setTurno("17");tarde.add(rs);}
        if(t.equals("21")){Resumen rs = tfr; rs.setTurno("21");nocturna.add(rs);}
    }   
    }    
   
if(!primera.isEmpty()){
  ticket.AddSubCabecera(ticket.DarEspacio());
  ticket.AddSubCabecera("PRIMERA:");
   
  String quinielaActual="n";  
 for(Resumen r : primera){ 
  String borratina = r.getNumero();
  String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] quini= p1.split(r.getQuiniela());
  StringBuilder sbq = new StringBuilder();
  for(String q : quini){ if(!q.contains("O"))sbq.append(q);sbq.append(" ");}
  String quinielas1 = sbq.toString();
  String posiciones = r.getPosiciones();
  
  String monto = String.valueOf(r.getMonto());
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS     POS       MONTO");
ticket.AddSubCabecera(ticket.DarEspacio());
StringBuilder sb = new StringBuilder("                           ");
  sb.replace(0,4,borratina);
  sb.replace(12,13,posiciones);
  sb.setLength(27);
  sb.replace(22,27, monto);

  ticket.AddSubCabecera(sb.toString());    
}else {
ticket.AddSubCabecera(ticket.DarEspacio());
StringBuilder sb = new StringBuilder("                           ");
  sb.replace(0,4,borratina);
  sb.replace(12,13,posiciones);
  sb.setLength(27);
  sb.replace(22,27, monto);
  ticket.AddSubCabecera(sb.toString());  
} 
}
 ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio()); 
}     
///////////////////////////////////////////////////////////////////////////////////////////////
if(!matutina.isEmpty()){

  ticket.AddSubCabecera("MATUTINA:");

 String quinielaActual="n";
 for(Resumen r : matutina){ 
  String borratina = r.getNumero();
  String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] quini= p1.split(r.getQuiniela());
  StringBuilder sbq = new StringBuilder();
  for(String q : quini){ if(!q.contains("O"))sbq.append(q);sbq.append(" ");}
  String quinielas1 = sbq.toString();
  String posiciones = r.getPosiciones();
  
  String monto = String.valueOf(r.getMonto());
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS     POS       MONTO");
ticket.AddSubCabecera(ticket.DarEspacio());
StringBuilder sb = new StringBuilder("                           ");
  sb.replace(0,4,borratina);
  sb.replace(12,13,posiciones);
  sb.setLength(27);
  sb.replace(22,27, monto);

  ticket.AddSubCabecera(sb.toString());    
}else {
ticket.AddSubCabecera(ticket.DarEspacio());
StringBuilder sb = new StringBuilder("                           ");
  sb.replace(0,4,borratina);
  sb.replace(12,13,posiciones);
  sb.setLength(27);
  sb.replace(22,27, monto);
  ticket.AddSubCabecera(sb.toString());  
}   
}
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}  
/////////////////////////////////////////////////////////////////////////////////////
if(!tarde.isEmpty()){
  ticket.AddSubCabecera("TARDE:");


String quinielaActual="n";
 for(Resumen r : tarde){ 
  String borratina = r.getNumero();
  String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] quini= p1.split(r.getQuiniela());
  StringBuilder sbq = new StringBuilder();
  for(String q : quini){ if(!q.contains("O"))sbq.append(q);sbq.append(" ");}
  String quinielas1 = sbq.toString();
  String posiciones = r.getPosiciones();
  
  String monto = String.valueOf(r.getMonto());
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS     POS       MONTO");
ticket.AddSubCabecera(ticket.DarEspacio());
StringBuilder sb = new StringBuilder("                           ");
  sb.replace(0,4,borratina);
  sb.replace(12,13,posiciones);
  sb.setLength(27);
  sb.replace(22,27, monto);

  ticket.AddSubCabecera(sb.toString());    
}else {
ticket.AddSubCabecera(ticket.DarEspacio());
StringBuilder sb = new StringBuilder("                           ");
  sb.replace(0,4,borratina);
  sb.replace(12,13,posiciones);
  sb.setLength(27);
  sb.replace(22,27, monto);
  ticket.AddSubCabecera(sb.toString());  
}   
}
  ticket.AddSubCabecera(ticket.DarEspacio()); 
 ticket.AddSubCabecera(ticket.DibujarLinea(29));
 ticket.AddSubCabecera(ticket.DarEspacio());
}   
///////////////////////////////////////////////////////////////////////
if(!nocturna.isEmpty()){
  ticket.AddSubCabecera("NOCTURNA:");

    String quinielaActual="n";
 for(Resumen r : nocturna){ 
  String borratina = r.getNumero();
  String patron = " ";
  Pattern p1 = Pattern.compile(patron);
  String[] quini= p1.split(r.getQuiniela());
  StringBuilder sbq = new StringBuilder();
  for(String q : quini){ if(!q.contains("O"))sbq.append(q);sbq.append(" ");}
  String quinielas1 = sbq.toString();
  String posiciones = r.getPosiciones();
  
  String monto = String.valueOf(r.getMonto());
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
if(!quinielas.equals(quinielaActual)){
    quinielaActual=quinielas;
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera(ticket.DarEspacio());
 ticket.AddSubCabecera("QUINIELA: "+quinielas);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS     POS       MONTO");
ticket.AddSubCabecera(ticket.DarEspacio());
StringBuilder sb = new StringBuilder("                           ");
  sb.replace(0,4,borratina);
  sb.replace(12,13,posiciones);
  sb.setLength(27);
  sb.replace(22,27, monto);

  ticket.AddSubCabecera(sb.toString());    
}else {
ticket.AddSubCabecera(ticket.DarEspacio());
StringBuilder sb = new StringBuilder("                           ");
  sb.replace(0,4,borratina);
  sb.replace(12,13,posiciones);
  sb.setLength(27);
  sb.replace(22,27, monto);
  ticket.AddSubCabecera(sb.toString());  
}    
} 
}    
}
//////////////////////////////////////////////////////////////////////////

ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());

   
ticket.AddSubCabecera("TOTAL:$"+total);
ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddPieLinea(ticket.DibujarLinea(29));

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("(RECLAMOS DENTRO DE LAS 48HS)");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("Gracias por su visita");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea(ticket.DarEspacio());

ticket.ImprimirDocumento();
    
}   
 public  void iprimirBorratia(){
 jugadaController=new JugadaController();     
Date date=new Date();
SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
String hora=jugadaController.hora();
String turno;
int total1=0;
int total2=0;
int total3=0;
Ticket ticket = new Ticket();

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(""+fecha.format(date) + " " + hora);//.format(date));

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("** EL 33 **");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("JUEGUE GANE Y COBRE YA ...");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(ticket.DibujarLinea(29));
/////////////////////////////////////////////////////////////////////////////////////
ticket.AddSubCabecera(ticket.DarEspacio());

//////////////////////////////////////////////////////////////////////////////////

ticket.AddSubCabecera("boleta :"+this.idBoleta);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("Nombre : "+this.nombre);

ticket.AddSubCabecera(ticket.DarEspacio());

if(!borratina3.isEmpty()){
ticket.AddSubCabecera("       ***BORRARINA 3***    ");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("valor :$5 (x loteria)");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ArrayList<Borratina3>aux = new ArrayList();
ArrayList<Borratina3>aux2 = new ArrayList();
//borratina3.stream().forEach((n)->n.get);

borratina3.stream().filter((n)->n.getUno().getTurno().equals("11")).forEach((n)->aux.add(n));
if(!aux.isEmpty()){


ticket.AddSubCabecera(" PRIMERA");  
ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera("QUINIELA:");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina3 b : aux){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"   "+b.getDos().getNumero()+"   "+b.getTres().getNumero()); 
total1=total1+5;
ticket.AddSubCabecera(ticket.DarEspacio()); 
}
}
aux.clear();
ticket.AddSubCabecera(ticket.DarEspacio()); 
borratina3.stream().filter((n)->n.getUno().getTurno().equals("14")).forEach((n)->aux.add(n));
  
if(!aux.isEmpty()){
    
turno="MATUTINA";
ticket.AddSubCabecera(""+turno);  
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina3 b : aux){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"   "+b.getDos().getNumero()+"   "+b.getTres().getNumero()+"    "+b.getUno().getQuiniela());  
ticket.AddSubCabecera(ticket.DarEspacio()); 
total1=total1+5;
}
}
aux.clear();
ticket.AddSubCabecera(ticket.DarEspacio()); 
borratina3.stream().filter((n)->n.getUno().getTurno().equals("17")).forEach((n)->aux.add(n));
if(!aux.isEmpty()){
turno="TARDE";
ticket.AddSubCabecera(""+turno);  
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina3 b : aux){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"   "+b.getDos().getNumero()+"   "+b.getTres().getNumero()+"    "+b.getUno().getQuiniela());  
ticket.AddSubCabecera(ticket.DarEspacio()); 
total1=total1+5;
}

}
aux.clear();
ticket.AddSubCabecera(ticket.DarEspacio()); 
borratina3.stream().filter((n)->n.getUno().getTurno().equals("21")).forEach((n)->aux.add(n));
if(!aux.isEmpty()){
turno="NOCTURNA";
ticket.AddSubCabecera(""+turno);  
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina3 b : aux){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"   "+b.getDos().getNumero()+"   "+b.getTres().getNumero()+"    "+b.getUno().getQuiniela());  
ticket.AddSubCabecera(ticket.DarEspacio()); 
total1=total1+5;
}
aux.clear();
ticket.AddSubCabecera(ticket.DarEspacio()); 
}

ticket.AddSubCabecera("SUBTOTAL : $"+total1);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("*** BORRATINA DE 3 ***");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 3 en Orden  $35000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 3           $16000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 4           $2800");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 7           $1600");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 10          $650");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 15          $300");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 20          $120");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Valor de referencia  $5");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));

}
////////////////////////////////////////////////////////////////////////////////////////////
if(!borratina5.isEmpty()){
ticket.AddSubCabecera(ticket.DarEspacio());    
ticket.AddSubCabecera("       ***BORRARINA 5***    ");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("valor :$5 (x loteria)");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));    
    
ArrayList<Borratina5>aux2 = new ArrayList();
borratina5.stream().filter((n)->n.getUno().getTurno().equals("11")).forEach((n)->aux2.add(n));
if(!aux2.isEmpty()){
turno="PRIMERA";
ticket.AddSubCabecera(""+turno);  
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina5 b : aux2){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"   "+b.getDos().getNumero()+"   "+b.getTres().getNumero()+"   "+b.getCuatro().getNumero()+"   "+b.getCinco().getNumero()+"    "+b.getUno().getQuiniela());  
ticket.AddSubCabecera(ticket.DarEspacio()); 
total2=total2+5;
}
}
aux2.clear();
ticket.AddSubCabecera(ticket.DarEspacio()); 
borratina5.stream().filter((n)->n.getUno().getTurno().equals("14")).forEach((n)->aux2.add(n));
if(!aux2.isEmpty()){
turno="MATUTINA";
ticket.AddSubCabecera(""+turno);  
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina5 b : aux2){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"   "+b.getDos().getNumero()+"   "+b.getTres().getNumero()+"   "+b.getCuatro().getNumero()+"   "+b.getCinco().getNumero()+"    "+b.getUno().getQuiniela());  
ticket.AddSubCabecera(ticket.DarEspacio()); 
total2=total2+5;
}
}
aux2.clear();
ticket.AddSubCabecera(ticket.DarEspacio()); 
borratina5.stream().filter((n)->n.getUno().getTurno().equals("17")).forEach((n)->aux2.add(n));
if(!aux2.isEmpty()){
turno="TARDE";
ticket.AddSubCabecera(""+turno);  
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina5 b : aux2){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"   "+b.getDos().getNumero()+"   "+b.getTres().getNumero()+"   "+b.getCuatro().getNumero()+"   "+b.getCinco().getNumero()+"    "+b.getUno().getQuiniela());  
ticket.AddSubCabecera(ticket.DarEspacio()); 
total2=total2+5;
}
}
aux2.clear();
ticket.AddSubCabecera(ticket.DarEspacio()); 
borratina5.stream().filter((n)->n.getUno().getTurno().equals("21")).forEach((n)->aux2.add(n));
if(!aux2.isEmpty()){
turno="NOCTURNA";
ticket.AddSubCabecera(""+turno);  
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina5 b : aux2){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"   "+b.getDos().getNumero()+"   "+b.getTres().getNumero()+"   "+b.getCuatro().getNumero()+"   "+b.getCinco().getNumero()+"    "+b.getUno().getQuiniela());  
ticket.AddSubCabecera(ticket.DarEspacio()); 
total2=total2+5;
}
aux2.clear();
ticket.AddSubCabecera(ticket.DarEspacio()); 
}
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("SUBTOTAL : $"+total2);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("*** BORRATINA DE 5 ***");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 5 a los 18       $10000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 4 en 18          $500");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 3 en 18           $65");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Valor de referencia  $5");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));

}
///////////////////////////////////////////////////////////////////////////////////////////////
if(!borratina8.isEmpty()){
ticket.AddSubCabecera(ticket.DarEspacio());    
ticket.AddSubCabecera("       ***BORRARINA 8***    ");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("valor :$5 (x loteria)");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));    
ArrayList<Borratina8>aux3 = new ArrayList();
borratina8.stream().filter((n)->n.getUno().getTurno().equals("11")).forEach((n)->aux3.add(n));
if(!aux3.isEmpty()){    
turno="PRIMERA";
ticket.AddSubCabecera(""+turno);  
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina8 b : aux3){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"  "+b.getDos().getNumero()+"  "+b.getTres().getNumero()+"  "+b.getCuatro().getNumero()+"  "+b.getCinco().getNumero()+"  "+b.getSeis().getNumero()+"  "+b.getSiete().getNumero()+"  "+b.getOcho().getNumero()+"  "+b.getUno().getQuiniela());  
ticket.AddSubCabecera(ticket.DarEspacio()); 
total3=total3+5;
}
}
aux3.clear();
ticket.AddSubCabecera(ticket.DarEspacio()); 
borratina8.stream().filter((n)->n.getUno().getTurno().equals("14")).forEach((n)->aux3.add(n));
if(!aux3.isEmpty()){
turno="MATUTINA";
ticket.AddSubCabecera(""+turno);  
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina8 b : aux3){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"  "+b.getDos().getNumero()+"  "+b.getTres().getNumero()+"  "+b.getCuatro().getNumero()+"  "+b.getCinco().getNumero()+"  "+b.getSeis().getNumero()+"  "+b.getSiete().getNumero()+"  "+b.getOcho().getNumero()+"  "+b.getUno().getQuiniela());  
ticket.AddSubCabecera(ticket.DarEspacio()); 
total3=total3+5;
}
}
aux3.clear();
ticket.AddSubCabecera(ticket.DarEspacio()); 
borratina8.stream().filter((n)->n.getUno().getTurno().equals("17")).forEach((n)->aux3.add(n));
if(!aux3.isEmpty()){
turno="TARDE";
ticket.AddSubCabecera(""+turno);  
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina8 b : aux3){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"  "+b.getDos().getNumero()+"  "+b.getTres().getNumero()+"  "+b.getCuatro().getNumero()+"  "+b.getCinco().getNumero()+"  "+b.getSeis().getNumero()+"  "+b.getSiete().getNumero()+"  "+b.getOcho().getNumero()+"  "+b.getUno().getQuiniela());  
ticket.AddSubCabecera(ticket.DarEspacio()); 
total3=total3+5;
}
}
aux3.clear();
ticket.AddSubCabecera(ticket.DarEspacio()); 
borratina8.stream().filter((n)->n.getUno().getTurno().equals("21")).forEach((n)->aux3.add(n));
if(!aux3.isEmpty()){
turno="NOCTURNA";
ticket.AddSubCabecera(""+turno);  
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("NUMEROS:");
ticket.AddSubCabecera(ticket.DarEspacio());
for(Borratina8 b : aux3){
ticket.AddSubCabecera(""+b.getUno().getNumero()+"  "+b.getDos().getNumero()+"  "+b.getTres().getNumero()+"  "+b.getCuatro().getNumero()+"  "+b.getCinco().getNumero()+"  "+b.getSeis().getNumero()+"  "+b.getSiete().getNumero()+"  "+b.getOcho().getNumero()+"  "+b.getUno().getQuiniela());  
ticket.AddSubCabecera(ticket.DarEspacio()); 
total3=total3+5;
}
}
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("SUBTOTAL : $"+total3);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("*** BORRATINA DE 8 ***");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Juega a los 40 premios ");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Loteria Nacional y Provincia");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("Con 8 aciertos       $8000");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 7 aciertos       $320");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Con 6 aciertos       $35");
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera(ticket.DarEspacio()); 
ticket.AddSubCabecera("Valor de referencia  $5");
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera(ticket.DibujarLinea(29));
}
ticket.AddSubCabecera(ticket.DarEspacio());
int tt=total1+total2+total3;
ticket.AddSubCabecera("TOTAL:$"+tt);
ticket.AddSubCabecera(ticket.DarEspacio());

///////////////////////////////////////////////////////////////////////////////////////////////
ticket.AddTotal("",ticket.DibujarLinea(29));


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("(RECLAMOS DENTRO DE LAS 48HS)");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("Gracias por su visita");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea(ticket.DibujarLinea(29));

ticket.AddPieLinea(ticket.DarEspacio());
ticket.AddPieLinea(ticket.DarEspacio());
ticket.AddPieLinea(ticket.DibujarLinea(29));
ticket.AddPieLinea(ticket.DibujarLinea(29));
/////////////////////////////////////////////////////////////////////////////////
ticket.ImprimirDocumento();
 } 
public Jugada buscarPareja(ArrayList<Jugada>jugadas, Jugada j){
    if(!jugadas.isEmpty()){
        if(j!=null){
           for(Jugada r : jugadas){
             if((r.getIdRedoblona()==j.getIdRedoblona()) && (!r.getNumero().equals(j.getNumero())) ){
               Jugada pareja=r;
               return pareja;}
    }return null;
}
}return null;
}
public void resetCacheImpresora() {

Ticket.resetCabezalineas();
Ticket.resetItems();
Ticket.resetLineasPie();
Ticket.resetSubCabezalineas();
Ticket.resetTotals();
    } 
public  String[] limpiarFormato(String quinielas) {
    
  String patron = "-";
  Pattern p1 = Pattern.compile(patron);
   String[] resultado= p1.split(quinielas);
   return resultado;
}
    public JugadaController getJugadaController() {
        return jugadaController;
    }

    public void setJugadaController(JugadaController jugadaController) {
        this.jugadaController = jugadaController;
    }

    public ArrayList<Borratina3> getBorratina3() {
        return borratina3;
    }

    public void setBorratina3(ArrayList<Borratina3> borratina3) {
        this.borratina3 = borratina3;
    }

    public ArrayList<Borratina5> getBorratina5() {
        return borratina5;
    }

    public void setBorratina5(ArrayList<Borratina5> borratina5) {
        this.borratina5 = borratina5;
    }

    public ArrayList<Borratina8> getBorratina8() {
        return borratina8;
    }

    public void setBorratina8(ArrayList<Borratina8> borratina8) {
        this.borratina8 = borratina8;
    }

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }
 
}

