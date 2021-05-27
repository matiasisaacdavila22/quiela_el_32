/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Caja;
import utils.Conexion;
import model.Ganada;
import model.Jugada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author mipc
 */
public class GanadaController {
    
   private Conexion mysql=new Conexion();
   private Connection cn = mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   private ArrayList<Ganada>listGanadas; 
   private JugadaController jugadaController;
   private ArrayList<Jugada>jugadasGanadoras;
    

public void insertar(Ganada ganada) {
        System.out.println("entro en insertar numero ganado en base de datos agregamos la fecha");
         sSQL = "insert into ganada (numero, posicion, quiniela, turno, fecha)"
                + "values (?,?,?,?,?)";                   
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            System.out.println("entro en pst inser ganadas");
            
            pst.setString(1, ganada.getNumero());
            pst.setInt(2, ganada.getPosicion());
            pst.setString(3, ganada.getQuiniela());
            pst.setString(4, ganada.getTurno());
            pst.setDate(5, ganada.getFecha());
   
            int n = pst.executeUpdate();
    
            if (n != 0) {
                               
            }else{   
                            
                  Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                               alerta.setHeaderText(null);
                               alerta.setTitle("informacion");
                               alerta.setContentText("error al ingrsar el NUMERO..REINTENTE.....");
                               alerta.showAndWait();
            }


        } catch (Exception e) {
           System.out.println("Error : "+e);
           
        }
    }
 
 public static int contarCifras(int numero){
        String numeroS = String.valueOf(numero);
        return numeroS.length()+1;
}
 
 
 public static ArrayList<Integer> desarmarNumero(int numero){
    ArrayList<Integer> lista=new ArrayList();
    int cifras=contarCifras(numero);
    if(cifras>1){
        lista.add(numero);
         numero =(int) (numero%(Math.pow(10, (cifras-1))));
        return desarmarNumero(numero,lista);
         }
        lista.add(numero);
    return lista;
}
   
private static ArrayList<Integer> desarmarNumero(int numero, ArrayList<Integer> lista){
    int cifras=contarCifras(numero);
    if(cifras>1){
        lista.add(numero);
        numero =(int) (numero%(Math.pow(10, (cifras-1))));
        return desarmarNumero(numero,lista);
    }
    lista.add(numero);
    return lista;
}
  private void insertarGanadas(ArrayList<Ganada> listGanadas) {
     listGanadas.stream().forEach((n)->insertar(n));
        }

    void insertarNumero(String numero, int posicion, String quiniela, String turno) {
        System.out.println("entro a insertar ganada en ganada controller");
        listGanadas=new ArrayList();

       Ganada ganada=new Ganada(numero, posicion,quiniela,turno);
                     this.insertar(ganada);
                     listGanadas.add(ganada);
            }
        
 
public String fecha(){
       Date fecha=new Date();
       SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
       String fechaFormato=formatoFecha.format(fecha);
       return fechaFormato;
     }
 
 public String hora(){
     Date hora = new Date();
     SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
     String horaFormato=formatoHora.format(hora);
     return horaFormato;
      
    }

// public void buscarGandores() {
//  for(Ganada ganada : listGanadas){
//  ArrayList<Jugada>parecidos=new ArrayList();
//  parecidos=buscarCoinsidencias(ganada);
//   if(!parecidos.isEmpty())jugadasGanadoras=jugadaController.buscameGanadores(parecidos,ganada);
//
//            }
//    }
//private ArrayList<Jugada> buscarCoinsidencias(Ganada ganada) {
//    ArrayList<Jugada>listaAux=jugadaController.pedirJugadasBase(ganada);
//         return listaAux;
//        
//       
//        }

public ObservableList<Ganada> consultar(Date fecha,String quiniela, String turno){
     System.out.println("entro a consultar ganadas de la fecha :"+fecha+"   de la quiniela :"+quiniela+"   del el turno :"+turno);
     ObservableList<Ganada> ganadas = FXCollections.observableArrayList();
     Ganada ganada;

        try {

            Statement orden=cn.createStatement();
 //"SELECT * from Jugadas where Jugadas.idboleta="+jugada.getIdBoleta()+" and Jugadas.idRedoblona="+jugada.getIdRedoblona();ganada.fecha='"+fecha+"' and 
            sSQL="SELECT * from ganada where ganada.fecha='"+fecha+"' and ganada.quiniela='"+quiniela+"' and ganada.turno='"+turno+"'";
            ResultSet r =orden.executeQuery(sSQL);  
            
              while(r.next()){
                    int idGanada =r.getInt("idGanada");
                    String numero = r.getString("numero");
                    int posicion = r.getInt("posicion");
                    String quini = r.getString("quiniela");
                    String tur = r.getString("turno");
                    java.sql.Date fechaSql= r.getDate("fecha"); 
                    
                                       
                  ganada=new Ganada(idGanada, numero, posicion, quini, tur, fechaSql);
                  ganadas.add(ganada);
                  }
              System.out.println("salio d consultar ganadas con "+ganadas.size());
              
       return ganadas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return ganadas;
    }

public Ganada consultarUltima(){
          try {
            
            Statement orden=cn.createStatement();
      String sSQL="SELECT * from ganada where ganada.idganada=(SELECT MAX(idganada)from ganada)";
              ResultSet r =orden.executeQuery(sSQL);  
            
              while(r.next()){
                    int idGanada =r.getInt("idGanada");
                    String numero = r.getString("numero");
                    int posicion = r.getInt("posicion");
                    String quini = r.getString("quiniela");
                    String tur = r.getString("turno");
                    java.sql.Date fechaSql= r.getDate("fecha"); 
                                                         
                 Ganada ganada=new Ganada(idGanada, numero, posicion, quini, tur, fechaSql);
                  return ganada;
                  }
                   return null;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return null;
    }


public boolean actualizarGanada(Ganada ganada) {
           sSQL = "update ganada set numero=? where ganada.fecha='"+ganada.getFecha()+"' and ganada.turno='"+ganada.getTurno()+"' and ganada.quiniela='"+ganada.getQuiniela()+"' and ganada.posicion='"+ganada.getPosicion()+"'";
           try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
             pst.setString(1, ganada.getNumero());
                  
              int n = pst.executeUpdate();

            if (n != 0) {
        
              return true;
                } else {
                 return false;
         }
            
        }catch (Exception e) {
            System.out.println("error :"+e);
            
        }return false;
    }

public Ganada buscar(int id) {
       
        try {
            Statement orden = cn.createStatement();
            String sSQL2 = "SELECT * from ganada where ganada.idganada=" +id;
            ResultSet r = orden.executeQuery(sSQL2);
            while (r.next()) {
                    int idGanada =r.getInt("idGanada");
                   String numero = r.getString("numero");
                    int posicion = r.getInt("posicion");
                    String quini = r.getString("quiniela");
                    String tur = r.getString("turno");
                    java.sql.Date fechaSql= r.getDate("fecha"); ;
                    
               Ganada  ganada=new Ganada(idGanada, numero, posicion, quini, tur, fechaSql);
               return ganada;
                 }
  }catch (SQLException ex) {
            System.out.println("error :" + ex);
        }
        return null;
    }

    void actualizarGanadas(ObservableList<Ganada> quinielaActual) {
        if(quinielaActual.size() > 0){
        ObservableList<Ganada> listaGanadas;
        Date fecha = quinielaActual.get(0).getFecha();  
        String quiniela = quinielaActual.get(0).getQuiniela();
        String turno = quinielaActual.get(0).getTurno();
        
       listaGanadas = this.consultar(fecha,quiniela, turno);
       
      for(int i  = 0; i < listaGanadas.size(); i++){
                  this.actualizarGanada(listaGanadas.get(i));
          }
      for(int i = listaGanadas.size(); i < quinielaActual.size(); i++){
              this.insertar(quinielaActual.get(i));
        }
        }
    }
    

    }
 
   
    


