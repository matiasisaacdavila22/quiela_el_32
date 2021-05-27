/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Boleta;
import model.Jugada;
import model.Pago;
import model.Resumen;
import model.TextFieldResumen;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import Tickets.Ticket;
import Tickets.TicketBorratina;
import Tickets.TicketJugada;
import java.io.IOException;
import java.net.URL;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 *
 * @author juamn
 */
public class ResumenController {
    private JugadaController jugadaController;
    private ArrayList<Resumen>resumenes;
 
//   public ArrayList<Resumen> crearResumen(ArrayList<Jugada>jugadas){
//        ArrayList<Integer> boletas = new ArrayList();
//        
//        ObservableList<Jugada> obsJugadas;
//        obsJugadas = FXCollections.observableArrayList();
//        
//        ArrayList<Resumen> resumenp;
//        resumenp = new ArrayList();
//        
//         ArrayList<Resumen> resumenf;
//        resumenf = new ArrayList();
//        
//       for(Jugada j : jugadas){
//           
//         if(!boletas.contains(j.getIdBoleta())){
//             boletas.add(j.getIdBoleta());
//             
//             for(Jugada j2 : jugadas){
//               if(j2.getIdBoleta()==j.getIdBoleta()){
//                   obsJugadas.add(j2);
//               }
//             }
//            System.out.println("las boletas son :"+obsJugadas.get(0).getIdBoleta());
//             System.out.println("las jugadas encontradas para esa boleta son "+obsJugadas.size());
//            resumenp = this.crearResumenBoleta(obsJugadas);
//            resumenf.addAll(resumenp);
//            obsJugadas.clear();
//         } 
//       }
//      resumenp = null;
//      obsJugadas = null;
//      boletas = null;
//   return resumenf;
//   }    
    
   public ObservableList<Resumen> crearResumen(int idboleta){
       jugadaController = new JugadaController(); 
       ArrayList<Jugada> jugadas = jugadaController.buscarJugadas(idboleta);
        
       ObservableList<Resumen> resumen = FXCollections.observableArrayList();
             
       resumen = this.crearResumenBoleta(jugadas);           
       return resumen;
   }
               
 public ObservableList<Resumen> crearResumenBoleta(ArrayList<Jugada> jugadas){
        this.resumenes = new ArrayList();
        ObservableList<Resumen> resumenes = FXCollections.observableArrayList();
                          
        ArrayList<String> numeros = new ArrayList();
        ArrayList<Jugada> aux = new ArrayList();
        ArrayList<Jugada> js = new ArrayList();
        ArrayList<Jugada> jr = new ArrayList();
        ArrayList<Jugada> jb = new ArrayList();
        ArrayList<Jugada> jb5 = new ArrayList();
        ArrayList<Jugada> jb8 = new ArrayList();
        ArrayList<String> turnos = new ArrayList();
        ArrayList<String> quinielas = new ArrayList();
        ArrayList<String> posiciones = new ArrayList();
        boolean g=false;
       
        
       for(Jugada j : jugadas){
           if(j.getTipo()==1)js.add(j);
           if(j.getTipo()==2)jr.add(j);
           if(j.getTipo()==3)jb.add(j);
           if(j.getTipo()==5)jb.add(j); // por el momento no distinguiremos entre las booratins
           if(j.getTipo()==8)jb8.add(j);
           }
       
if(!js.isEmpty()){
ArrayList<Resumen>r;
 r = this.generarResumenSimple(js);
 r.stream().forEach((n)->resumenes.add(n));
        }

if(!jr.isEmpty()){ 
 ArrayList<Resumen>d;
 d = this.generarResumenDoble(jr);
 d.stream().forEach((n)->resumenes.add(n));
    
 }

//if(!jb.isEmpty()){  
// ArrayList<Resumen>r;
// r = this.generarResumenBorratina(jb);
// r.stream().forEach((n)->resumenes2.add(n));
//    
// }
//if(!jb8.isEmpty()){  
// ArrayList<Resumen>r;
// r = this.generarResumenBorratina8(jb8);
// r.stream().forEach((n)->resumenes2.add(n));
//    
// }
return resumenes;
 }
 
public  ArrayList<Resumen> generarResumenSimple( ArrayList<Jugada>js){
   ArrayList<String>numeros = new ArrayList();
   ArrayList<String>turnos = new ArrayList();
   ArrayList<String>quinielas = new ArrayList();
   ArrayList<Integer>posiciones = new ArrayList();
   ArrayList<Integer>ids = new ArrayList();
   ArrayList<Resumen>resumenes3 = new ArrayList();
          boolean g=false;
           
   for(Jugada i : js){
           if(!numeros.contains(i.getNumero())){
               numeros.add(i.getNumero());
               ids.add(i.getIdJugada());
               turnos.add(i.getTurno());
               quinielas.add(i.getQuiniela());
               if(!posiciones.contains(i.getPosicion()))posiciones.add(i.getPosicion());
               for(Jugada j2 : js){
                   if(j2.getNumero().equals(i.getNumero())){
                       if(!ids.contains(j2.getIdJugada()))ids.add(j2.getIdJugada());
                       if(!turnos.contains(j2.getTurno()))turnos.add(j2.getTurno());
                       if(!quinielas.contains(j2.getQuiniela()))quinielas.add(j2.getQuiniela());
                       if(!posiciones.contains(j2.getPosicion()))posiciones.add(j2.getPosicion());
                       g=g || j2.isGano();
                     }
               }
               int id=i.getIdBoleta();
               String numero =i.getNumero();
                StringBuilder sb = new StringBuilder();
               for(String t : turnos){
                   sb.append(t);
                   sb.append(" ");
               }
               String turno=sb.toString();
              
               StringBuilder sb2 = new StringBuilder();
               for(String q : quinielas){
                   sb2.append(q);
                   sb2.append(" ");
               }
               String quiniela=sb2.toString();
               double monto=i.getMonto();
               Date fecha= i.getFecha();
               boolean gano = g;
               
               StringBuilder sb3 = new StringBuilder();
               for(Integer p : posiciones){
                   sb3.append(""+p);
                   sb3.append("-");
               }
               String posicion = sb3.toString();
               String nombre = i.getNombre();
               StringBuilder idsj = new StringBuilder();
               for(Integer nn : ids){
                   idsj.append(String.valueOf(nn));
                   idsj.append("-");
                   }
               int tipo = i.getTipo();
               String ss=idsj.toString();
               Resumen resumen = new Resumen (id,  numero,  turno, quiniela,  monto,  fecha,  gano, posicion, nombre,ss, tipo); 
               resumenes3.add(resumen);
               ids.clear();
        turnos.clear();
        quinielas.clear();
        g=false;
        }

       }
   return resumenes3;
}
public ArrayList<Resumen> generarResumenDoble( ArrayList<Jugada>jr){
   ArrayList<String>numeros = new ArrayList();
    ArrayList<String>turnos = new ArrayList();
     ArrayList<String>quinielas = new ArrayList();
       ArrayList<Integer>idj = new ArrayList();
         ArrayList<Resumen>resumenes = new ArrayList();
              
   ArrayList<Integer>idr = new ArrayList();
          boolean g=false;
          boolean p=false;
      for(Jugada i : jr){
         
          if(!idr.contains(i.getIdRedoblona())){
            
               Jugada k=this.buscarPareja(i, jr);
       
               idr.add(i.getIdRedoblona()); 
               numeros.add(i.getNumero());
               numeros.add(k.getNumero());
               idj.add(i.getIdJugada());
               idj.add(k.getIdJugada());
               turnos.add(i.getTurno());
               quinielas.add(i.getQuiniela());
                g = (g || (i.isGano() && k.isGano()));
               
                for(Jugada j2 : jr){
                   if(!idr.contains(j2.getIdRedoblona())){
                       
                   if(this.sePuedenSolapar(j2, i, jr)){
                   idr.add(j2.getIdRedoblona());
                   Jugada k2 = this.buscarPareja(j2,jr);
                     idj.add(j2.getIdJugada());
                     idj.add(k2.getIdJugada());
                       if(!turnos.contains(j2.getTurno()))turnos.add(j2.getTurno());
                       if(!quinielas.contains(j2.getQuiniela()))quinielas.add(j2.getQuiniela());
                       g = (g || (j2.isGano() && k2.isGano()));
                        }
               }
               }
               int id= i.getIdBoleta();
                StringBuilder sbn = new StringBuilder();
                sbn.append(i.getNumero());
                 sbn.append(" ");
                 sbn.append(k.getNumero());
                 
               String numero = sbn.toString();
                StringBuilder sb = new StringBuilder();
               for(String t : turnos){
                   sb.append(t);
                   sb.append(" ");
               }
               String turno=sb.toString();
              
               StringBuilder sb2 = new StringBuilder();
               for(String q : quinielas){
                   sb2.append(q);
                   sb2.append(" ");
               }
               String quiniela=sb2.toString();
               double monto=i.getMonto();
               Date fecha= i.getFecha();
               boolean gano = g;
               boolean pago = p;
               StringBuilder sbp = new StringBuilder();
                 sbp.append(i.getPosicion());
                 sbp.append(" ");
                 sbn.append(k.getPosicion());
              String posicion = sbp.toString();
               String nombre = i.getNombre();
                 StringBuilder idsj = new StringBuilder();
               for(Integer nn : idj){
                   idsj.append(String.valueOf(nn));
                   idsj.append("-");
                   }
               int tipo = i.getTipo();
               String ss=idsj.toString();
               Resumen resumen = new Resumen (id,  numero,  turno, quiniela,  monto,  fecha,  gano, posicion, nombre, ss, tipo); 
               resumenes.add(resumen);
                 g=false;
               
           }
        idj.clear();
        turnos.clear();
        quinielas.clear();
      
       }           
           
      return resumenes;  
}
//private  ArrayList<Resumen> generarResumenBorratina(ArrayList<Jugada>jb){
//  JugadaController jugadaC=new JugadaController();
//   ArrayList<String>numeros = new ArrayList();
//    ArrayList<String>turnos = new ArrayList();
//     ArrayList<String>quinielas = new ArrayList();
//       ArrayList<Jugada>borratina = new ArrayList();
//         ArrayList<Resumen>resumenes = new ArrayList();
//          boolean g=false;
//          boolean p=false;
//          ArrayList<Integer>idr = new ArrayList();
//          ArrayList<Integer>idj = new ArrayList();
//          
//             for( Jugada x : jb){
//                  if(!idr.contains(x.getIdRedoblona())){
//                    jb.stream().filter((n)->n.getIdRedoblona()== x.getIdRedoblona())
//                                .forEach((n)->borratina.add(n));
//                   
//                      idr.add(x.getIdRedoblona()); 
//                       for(Jugada j : borratina){
//                           numeros.add(j.getNumero());
//                           idj.add(j.getIdJugada());
//                       }
//                       turnos.add(x.getTurno());
//                       quinielas.add(x.getQuiniela());
//                       
//                      g =g || jugadaC.isTienePremio(borratina);
//                          
//               for(Jugada j2 : jb){
//                   
//                if(!idr.contains(j2.getIdRedoblona())){
//                     ArrayList<Jugada>  auxj2 = new ArrayList();
//                    jb.stream().filter((n)->n.getIdRedoblona()== j2.getIdRedoblona())
//                                .forEach((n)->auxj2.add(n));
//                 if(this.sePuedenSolaparB(auxj2, borratina)){
//                     idr.add(j2.getIdRedoblona());
//                     auxj2.stream().forEach((n)->idj.add(n.getIdJugada()));
//                    g=g || jugadaC.isTienePremio(auxj2);
//                       
//                       if(!turnos.contains(j2.getTurno()))turnos.add(j2.getTurno());
//                       if(!quinielas.contains(j2.getQuiniela()))quinielas.add(j2.getQuiniela());
//                 }  
//                }
//              }
//               int id=borratina.get(0).getIdBoleta();
//                StringBuilder sbn = new StringBuilder();
//                for(String num : numeros){
//                 sbn.append(num);
//                 sbn.append(" ");
//                }
//                String numero = sbn.toString();
//                
//                StringBuilder sb = new StringBuilder();
//               for(String t : turnos){
//                   sb.append(t);
//                   sb.append(" ");
//               }
//               String turno=sb.toString();
//             
//               StringBuilder sb2 = new StringBuilder();
//               for(String q : quinielas){
//                   sb2.append(q);
//                   sb2.append(" ");
//               }
//               
//               String quiniela=sb2.toString();
//               int monto=5;
//               Date fecha= borratina.get(0).getFecha();
//               boolean gano = g;
//               boolean pago = x.isPago();
//               StringBuilder sbp = new StringBuilder();
//                 sbp.append(borratina.get(0).getPosicion());
//                 sbp.append(" ");
//                 sbn.append(borratina.get(1).getPosicion());
//              String posicion = sbp.toString();
//               String nombre =borratina.get(0).getNombre();
//                 StringBuilder idsj = new StringBuilder();
//               for(Integer nn : idj){
//                   idsj.append(String.valueOf(nn));
//                   idsj.append("-");
//                   }
//               int tipo = x.getTipo();
//               String ss=idsj.toString();
//               Resumen resumen = new Resumen (id,  numero,  turno, quiniela,  monto,  fecha,  gano, pago, posicion, nombre,ss, tipo); 
//               resumenes.add(resumen);
//                      System.out.println("la cantidad de resumens que se geneneraron son :"+resumenes.size());
//                  
//        turnos.clear();
//        quinielas.clear();
//        numeros.clear();
//        borratina.clear();
//        idj.clear();
//        g=false;
//        p=false;
//                  }
//             }
//      return resumenes;      
// }
//private  ArrayList<Resumen> generarResumenBorratina8(ArrayList<Jugada>jb){
//   ArrayList<String>numeros = new ArrayList();
//  JugadaController jugadaC = new JugadaController();
//       ArrayList<Jugada>borratina = new ArrayList();
//         ArrayList<Resumen>resumenes = new ArrayList();
//                ArrayList<String>turnos = new ArrayList();
//          boolean g=false;
//          boolean p=false;
//          
//          String quinielas="N P";
//          ArrayList<Integer>idr = new ArrayList();
//           ArrayList<Integer>idj = new ArrayList();
//             for( Jugada x : jb){
//                  if(!idr.contains(x.getIdRedoblona())){
//                    jb.stream().filter((n)->n.getIdRedoblona()== x.getIdRedoblona())
//                                .forEach((n)->borratina.add(n));
//                   
//                      idr.add(x.getIdRedoblona()); 
//                       for(Jugada j : borratina){
//                           numeros.add(j.getNumero());
//                           idj.add(j.getIdJugada());
//                       }
//                        turnos.add(x.getTurno());
//                      
//                       g = g || jugadaC.isTienePremio(borratina);
//           
//              for(Jugada j2 : jb){
//                   
//                if(!idr.contains(j2.getIdRedoblona())){
//                     ArrayList<Jugada>  auxj2 = new ArrayList();
//                    jb.stream().filter((n)->n.getIdRedoblona()== j2.getIdRedoblona())
//                                .forEach((n)->auxj2.add(n));
//                 if(this.sePuedenSolaparB(auxj2, borratina)){
//                     idr.add(j2.getIdRedoblona());
//                     auxj2.stream().forEach((n)->idj.add(n.getIdJugada()));
//                     if(!turnos.contains(j2.getTurno()))turnos.add(j2.getTurno());
//                    g=g || jugadaC.isTienePremio(auxj2);
//                      }  
//                  }
//                 }
//                System.out.println("las redoblonas aderidas son   :"+idr.size());
//               int id=borratina.get(0).getIdBoleta();
//                StringBuilder sbn = new StringBuilder();
//                for(String num : numeros){
//                 sbn.append(num);
//                 sbn.append(" ");
//                }
//                String numero = sbn.toString();
//        
//                 StringBuilder sb = new StringBuilder();
//               for(String t : turnos){
//                   sb.append(t);
//                   sb.append(" ");
//               }
//               String turno = sb.toString();
//               int monto=5;
//               Date fecha= borratina.get(0).getFecha();
//               boolean gano = g;
//               boolean pago = x.isPago();
//               StringBuilder sbp = new StringBuilder();
//                 sbp.append(borratina.get(0).getPosicion());
//                 sbp.append(" ");
//                 sbn.append(borratina.get(1).getPosicion());
//              String posicion = sbp.toString();
//               String nombre =borratina.get(0).getNombre();
//                 StringBuilder idsj = new StringBuilder();
//               for(Integer nn : idj){
//                   idsj.append(String.valueOf(nn));
//                   idsj.append("-");
//                   }
//               int tipo = x.getTipo();
//               String ss=idsj.toString();
//               Resumen resumen = new Resumen (id,  numero,  turno, quinielas,  monto,  fecha,  gano, pago, posicion, nombre, ss, tipo); 
//               resumenes.add(resumen);          
//      
//        numeros.clear();
//        borratina.clear();
//        idj.clear();
//        turnos.clear();
//        g=false;
//        p=false;
//                  }
//                 
//             }
//      return resumenes;      
// } 
//public ArrayList<Resumen> crearResumenJugada(ArrayList<Jugada>jugadas){
//    
//    System.out.println("entro a crear resumenes para resumens por jugadas   ");
//        this.resumenes = new ArrayList();
//       JugadaController jugadaController = new JugadaController();
//        ArrayList<Resumen> resumenes2;
//        resumenes2 = new ArrayList();
//                   
//        ArrayList<String> numeros = new ArrayList();
//        ArrayList<Jugada> aux = new ArrayList();
//         ArrayList<Jugada> js = new ArrayList();
//           ArrayList<Jugada> jr = new ArrayList();
//             ArrayList<Jugada> jb = new ArrayList();
//               ArrayList<Jugada> jb5 = new ArrayList();
//                 ArrayList<Jugada> jb8 = new ArrayList();
//        ArrayList<String> turnos = new ArrayList();
//        ArrayList<String> quinielas = new ArrayList();
//        boolean g=false;
//        boolean p=false;
//        
//       for(Jugada j : jugadas){
//           if(j.getTipo()==1)js.add(j);
//           if(j.getTipo()==2)jr.add(j);
//           if(j.getTipo()==3)jb.add(j);
//           if(j.getTipo()==5)jb.add(j); // por el momento no distinguiremos entre las booratins
//           if(j.getTipo()==8)jb8.add(j);
//           }
//       
//if(!js.isEmpty()){
//ArrayList<Resumen>r;
// r = this.generarResumenSimpleJugada(js);
// r.stream().forEach((n)->resumenes2.add(n));
//        }
//
//if(!jr.isEmpty()){ 
// ArrayList<Resumen>d;
// d = this.generarResumenDobleJugada(jr);
// d.stream().forEach((n)->resumenes2.add(n));    
//   }

//if(!jb.isEmpty()){  
// ArrayList<Resumen>r;
//    System.out.println("encontro que es una borratina de 3 o de 5 para realizar el resumen por jugada");
// r = this.generarResumenBorratinaJugada(jb);
// r.stream().forEach((n)->resumenes2.add(n));
//    
// }
//if(!jb8.isEmpty()){  
// ArrayList<Resumen>r;
// r = this.generarResumenBorratina8Jugada(jb8);
// r.stream().forEach((n)->resumenes2.add(n));
//    
// }
//return resumenes2;
// }

//public  ArrayList<Resumen> generarResumenSimpleJugada( ArrayList<Jugada>js){
//         ArrayList<Resumen>resumenes3 = new ArrayList();
//          JugadaController jugadaController = new JugadaController();
//          PagoController pagoController = new PagoController();
//   for(Jugada i : js){
//           int id = i.getIdBoleta();
//           String numero = i.getNumero();
//           String turno = i.getTurno();
//           String quiniela = i.getQuiniela();
//           int monto = i.getMonto();
//           Date fecha = i.getFecha();
//           Date fechaPago=null; 
//           int idPago = i.getIdPago();
//           System.out.println("el id dela jugada es para generarr el resumen y optenter la fecha de pago es :"+idPago);
//           if(idPago!=0){
//               Pago pago=pagoController.buscarJugadasxId(idPago);
//               fechaPago = pago.getFechaPago();
//           }
//           boolean gano = i.isGano();
//            int premio=0; 
//           if(gano){premio=jugadaController.calcularPremio(i);}
//           boolean pago = i.isPago();
//           String posicion = String.valueOf(i.getPosicion());
//           String nombre = i.getNombre();
//         String idJugada = String.valueOf(i.getIdJugada());
//         int tipo = i.getTipo();
//               Resumen resumen = new Resumen (id,  numero,  turno, quiniela,  monto,  fecha, fechaPago, gano, pago, posicion, nombre, idJugada, tipo, premio); 
//               resumenes3.add(resumen);       
//       }
//   return resumenes3;
//} 
//public ArrayList<Resumen> generarResumenDobleJugada( ArrayList<Jugada>jr){
//   ArrayList<String>numeros = new ArrayList();
//    ArrayList<String>turnos = new ArrayList();
//     ArrayList<String>quinielas = new ArrayList();
//       ArrayList<Integer>idj = new ArrayList();
//         ArrayList<Resumen>resumenes = new ArrayList();
//          JugadaController jugadaController = new JugadaController();   
//            PagoController pagoController = new PagoController();
//   ArrayList<Integer>idr = new ArrayList();
//          boolean g=false;
//          boolean p=false;
//      for(Jugada i : jr){
//         
//           if(!idr.contains(i.getIdRedoblona())){
//               Jugada k=this.buscarPareja(i, jr);
//       
//               idr.add(i.getIdRedoblona()); 
//               String idJugada1 = String.valueOf(i.getIdJugada());
//               String idJugada2 = String.valueOf(k.getIdJugada());
//               String turno = i.getTurno();
//               String quiniela = i.getQuiniela();
//              
//                g = (g || (i.isGano() && k.isGano()));
//               
//                int id= i.getIdBoleta();
//                StringBuilder sbn = new StringBuilder();
//                sbn.append(i.getNumero());
//                 sbn.append(" ");
//                 sbn.append(k.getNumero());
//                 
//               String numero = sbn.toString();
//                StringBuilder sb = new StringBuilder();
//                            
//               StringBuilder sb2 = new StringBuilder();
//              
//               int monto=i.getMonto();
//               int idPago = i.getIdJugada();
//               System.out.println("el IDPAGO para este resumene es :"+idPago);
//               Date fecha= i.getFecha();
//               Date fechaPago = null;
//                if(idPago!=0){
//               Pago pago=pagoController.buscarJugadasxId(idPago);
//               fechaPago = pago.getFechaPago();
//           }
//               boolean gano = g;
//               boolean pago = p;
//               
//               StringBuilder sbp = new StringBuilder();
//                 sbp.append(i.getPosicion());
//                 sbp.append(" ");
//                 sbn.append(k.getPosicion());
//              String posicion = sbp.toString();
//               String nombre = i.getNombre();
//               
//               StringBuilder idsj = new StringBuilder();
//                   idsj.append(idJugada1);
//                   idsj.append("-");
//                   idsj.append(idJugada2);
//               String ss=idsj.toString();
//               int tipo = i.getTipo();
//                int premio=0; 
//           if(gano){premio=jugadaController.calcularPremio(i);}
//               Resumen resumen = new Resumen (id,  numero,  turno, quiniela,  monto,  fecha,fechaPago, gano, pago, posicion, nombre, ss, tipo,premio); 
//               resumenes.add(resumen);
//             p=false;
//             g=false;
//               
//           }
// }           
//           
// return resumenes;  
//}

//private  ArrayList<Resumen> generarResumenBorratinaJugada(ArrayList<Jugada>jb){
//    System.out.println("estamos entrando en resumen para booorauna de 3 y borratinad de 5");
//  JugadaController jugadaC=new JugadaController();
//   ArrayList<String>numeros = new ArrayList();
//       ArrayList<Jugada>borratina = new ArrayList();
//         ArrayList<Resumen>resumenes = new ArrayList();
//          boolean g=false;
//          boolean p=false;
//          ArrayList<Integer>idr = new ArrayList();
//          ArrayList<Integer>idj = new ArrayList();
//            PagoController pagoController = new PagoController();
//          
//             for( Jugada x : jb){
//                  if(!idr.contains(x.getIdRedoblona())){
//                    jb.stream().filter((n)->n.getIdRedoblona()== x.getIdRedoblona())
//                                .forEach((n)->borratina.add(n));
//                   
//                      idr.add(x.getIdRedoblona()); 
//                       for(Jugada j : borratina){
//                           numeros.add(j.getNumero());
//                           idj.add(j.getIdJugada());
//                       }
//                 g =g || jugadaC.isTienePremio(borratina);
//
//               int id=borratina.get(0).getIdBoleta();
//                StringBuilder sbn = new StringBuilder();
//                for(String num : numeros){
//                 sbn.append(num);
//                 sbn.append(" ");
//                }
//                String numero = sbn.toString();
//               String turno = x.getTurno();
//               String quiniela=x.getQuiniela();
//               
//               int monto=5;
//               Date fecha = x.getFecha();
//               Date fechaPago = null;
//               int idPago = x.getIdPago();
//                if(idPago!=0){
//               Pago pago=pagoController.buscarJugadasxId(idPago);
//               fechaPago = pago.getFechaPago();
//                }
//               boolean gano = g;
//               boolean pago = x.isPago();
//               StringBuilder sbp = new StringBuilder();
//                 sbp.append(borratina.get(0).getPosicion());
//                 sbp.append(" ");
//                 sbn.append(borratina.get(1).getPosicion());
//                 String posicion = sbp.toString();
//             
//               String nombre = x.getNombre();
//                 StringBuilder idsj = new StringBuilder();
//               for(Integer nn : idj){
//                   idsj.append(String.valueOf(nn));
//                   idsj.append("-");
//                   }
//               String ss=idsj.toString();
//               int tipo = x.getTipo();
//                int premio=0; 
//           if(gano){
//               System.out.println("detecto que gano biennnnn");
//               premio=jugadaC.calcularPremioBorratina(borratina);}
//               Resumen resumen = new Resumen (id,  numero,  turno, quiniela,  monto,  fecha,fechaPago,  gano, pago, posicion, nombre,ss, tipo, premio); 
//               resumenes.add(resumen);
//                      System.out.println("la cantidad de resumens que se geneneraron son :"+resumenes.size());
//        numeros.clear();
//        borratina.clear();
//        idj.clear();
//        g=false;
//        p=false;
//                  }
//             }
//      return resumenes;      
// }

//private  ArrayList<Resumen> generarResumenBorratina8Jugada(ArrayList<Jugada>jb){
//  ArrayList<String>numeros = new ArrayList();
//  JugadaController jugadaC = new JugadaController();
//       ArrayList<Jugada>borratina = new ArrayList();
//         ArrayList<Resumen>resumenes = new ArrayList();
//                ArrayList<String>turnos = new ArrayList();
//          boolean g=false;
//          boolean p=false;
//          
//          String quinielas="N P";
//          ArrayList<Integer>idr = new ArrayList();
//           ArrayList<Integer>idj = new ArrayList();
//            PagoController pagoController = new PagoController();
//           
//             for( Jugada x : jb){
//                  if(!idr.contains(x.getIdRedoblona())){
//                    jb.stream().filter((n)->n.getIdRedoblona()== x.getIdRedoblona())
//                                .forEach((n)->borratina.add(n));
//                   
//                      idr.add(x.getIdRedoblona()); 
//                       for(Jugada j : borratina){
//                           numeros.add(j.getNumero());
//                           idj.add(j.getIdJugada());
//                       }
//                       g = g || jugadaC.isTienePremio(borratina);
//           
//              for(Jugada j2 : jb){
//                   
//                if(!idr.contains(j2.getIdRedoblona())){
//                    if(x.getTurno().equals(j2.getTurno())){
//                     ArrayList<Jugada>  auxj2 = new ArrayList();
//                    jb.stream().filter((n)->n.getIdRedoblona()== j2.getIdRedoblona())
//                                .forEach((n)->auxj2.add(n));
//                 if(this.sePuedenSolaparB(auxj2, borratina)){
//                     idr.add(j2.getIdRedoblona());
//                     auxj2.stream().forEach((n)->idj.add(n.getIdJugada()));
//                     g=g || jugadaC.isTienePremio(auxj2);
//                      }  
//                  }
//                 }
//              }
//                System.out.println("las redoblonas aderidas son   :"+idr.size());
//               int id=borratina.get(0).getIdBoleta();
//                StringBuilder sbn = new StringBuilder();
//                for(String num : numeros){
//                 sbn.append(num);
//                 sbn.append(" ");
//                }
//                String numero = sbn.toString();
//              
//              String turno = x.getTurno();
//               int monto=5;
//               Date fecha= borratina.get(0).getFecha();
//                Date fechaPago = null;
//               int idPago = x.getIdPago();
//                if(idPago!=0){
//               Pago pago=pagoController.buscarJugadasxId(idPago);
//               fechaPago = pago.getFechaPago();
//                }
//               boolean gano = g;
//               boolean pago = x.isPago();
//               StringBuilder sbp = new StringBuilder();
//                 sbp.append(borratina.get(0).getPosicion());
//                 sbp.append(" ");
//                 sbn.append(borratina.get(1).getPosicion());
//              String posicion = sbp.toString();
//               String nombre =borratina.get(0).getNombre();
//                 StringBuilder idsj = new StringBuilder();
//               for(Integer nn : idj){
//                   idsj.append(String.valueOf(nn));
//                   idsj.append("-");
//                   }
//               int tipo = x.getTipo();
//                   int premio=0; 
//           if(gano){premio=jugadaC.calcularPremioBorratina(borratina);}
//               String ss=idsj.toString();
//               Resumen resumen = new Resumen (id,  numero,  turno, quinielas,  monto,  fecha,fechaPago, gano, pago, posicion, nombre, ss, tipo, premio); 
//               resumenes.add(resumen);          
//      
//        numeros.clear();
//        borratina.clear();
//        idj.clear();
//       g = false;
//       p = false;
//                  }
//                 
//             }
//      return resumenes;      
// }    
  

public Jugada buscarPareja(Jugada jugada , ArrayList<Jugada>jugadas){
     for(Jugada j : jugadas){
         if(jugada.getIdRedoblona()==j.getIdRedoblona() && !jugada.getNumero().equals(j.getNumero())){
             Jugada k=j;
             return k;
         }
     } return null;
}
//public void imprimirResumen(ObservableList<Resumen> resumens, int total){
//     
//this.resetCacheImpresora();
//
//this.resetCacheImpresora();
//}
// public ArrayList<Resumen> mostrarResumen(ObservableList<Jugada> jugadasFiltroBoleta) {
//     
//        ArrayList<Resumen> resumenes;
//        resumenes = new ArrayList();
//       
//        resumenes = this.crearResumen(jugadasFiltroBoleta);
//        
//        return resumenes;
//        
//    }

//public ObservableList<Resumen> consultarxTexto(ObservableList<Resumen>obs, String nombre) {
//          ObservableList<Resumen> filtroJugadas;
//        filtroJugadas = FXCollections.observableArrayList();
//   
//      String filtroNombre = nombre;
//    
//        
//                if(esNumero(filtroNombre)){ 
//                    
//                obs.stream().filter((n)->n.getId()==Integer.parseInt(filtroNombre))
//                                .forEach((n)->filtroJugadas.add(n));
//                }  
//                 if(esNumero(filtroNombre)){ 
//              
//                obs.stream().filter((n)->n.getNumero().contains(filtroNombre))
//                                .forEach((n)->filtroJugadas.add(n));
//                }
//                 if(!esNumero(filtroNombre)){
//                     
//               obs.stream().filter((n)->n.getFecha().equals(filtroNombre)).forEach((n)->filtroJugadas.add(n));
//               // obsJugada.stream().forEach((n)->System.out.println(n.getFecha()));  
//                 }
//                if(!esNumero(filtroNombre)){ 
//              obs.stream().filter((n)->n.getNombre().toLowerCase().contains(filtroNombre.toLowerCase()))
//                        .forEach((n)->filtroJugadas.add(n));
//                }
//                 if(!esNumero(filtroNombre)){
//                obs.stream().filter((n)->n.getQuiniela().toLowerCase().contains(filtroNombre.toLowerCase()))
//                        .forEach((n)->filtroJugadas.add(n));
//                 }
//                  if(esNumero(filtroNombre)){
//              obs.stream().filter((n)->n.getTurno().toLowerCase().contains(filtroNombre.toLowerCase()))
//                        .forEach((n)->filtroJugadas.add(n));
//                 }
//                   if( filtroNombre.toLowerCase().contains("gano".toLowerCase())){
//                  for(Resumen jugada : obs){
//                      if(jugada.isGano())filtroJugadas.add(jugada);
//                      }
//                   }
//                 
//                return filtroJugadas;
//                 } 

public boolean esNumero(String val){
        try{
            Integer.parseInt(val);
            return true;
        }catch(Exception e){
            return false;
        }
 }

private boolean sePuedenSolapar(Jugada j, Jugada k, ArrayList<Jugada>jugadas){
    Jugada j2=this.buscarPareja(j,jugadas);
    Jugada k2 =this.buscarPareja(k, jugadas);
    Jugada unoj; Jugada dosj;
    Jugada unok; Jugada dosk;
    boolean uno=false;
    boolean dos=false;
    if(j.getPosicion()<j2.getPosicion()){
        unoj=j;
        dosj=j2;
    }else{
        unoj=j2;
        dosj=j;
    }
     if(k.getPosicion()<k2.getPosicion()){
        unok=k;
        dosk=k2;
    }else{
        unok=k2;
        dosk=k;
    }
     
     if((unoj.getNumero().equals(unok.getNumero())) && unoj.getPosicion()== unok.getPosicion())uno=true;
     if((dosj.getNumero().equals(dosk.getNumero())) && dosj.getPosicion()== dosk.getPosicion())dos=true;
     if(uno && dos)return true;
     else return false;
}

private boolean sePuedenSolaparB( ArrayList<Jugada>borra2, ArrayList<Jugada> borra1){
   
if(borra2.size()==borra1.size()){
  
    boolean uno=true; 
    for(int i=0;i<borra1.size();i++){
       
        for(int j = 0; j<borra2.size(); j++){
            if(borra1.get(i).getPosicion()==borra2.get(j).getPosicion()){
                uno = uno && (borra1.get(i).getNumero().equals(borra2.get(j).getNumero())); 
            }
        }
        
    } 
   
   return uno;
   
    }
     
  else return false;
}



//public void resetCacheImpresora() {
//
//Ticket.resetCabezalineas();
//Ticket.resetItems();
//Ticket.resetLineasPie();
//Ticket.resetSubCabezalineas();
//Ticket.resetTotals();
//
//    } 

}
  