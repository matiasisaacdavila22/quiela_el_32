/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import utils.Conexion;
import model.Jugada;
import model.Boleta;
import model.Ganada;
import model.Resumen;
import model.ResumenJugada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
////////////////////////////////////////////////////////////////
import static utils.Config.*;


/**
 *
 * @author mipc
 */
public class JugadaController {
  
  private GanadaController ganadaController;
  private static double matriz[][]={ {7, 70, 600,3500}, {3.5 , 35, 300, 1750}, {2.33, 23.33, 200, 1166},//1-3
                                   {1.75, 17.5, 150, 875},{1.4, 14, 120, 700},{1.16, 11.66, 100, 583},//4-6
                                   {1, 10, 85,71, 500},{0.87, 8.75, 75, 438,5},{0.77, 7.77, 66.66, 388.88},//7-9
                                   {0.7, 7, 60, 350},{0.63, 6.36, 54.54, 318},{0.58, 5.83, 50, 291.66},//10-12
                                   {0.54, 5.38, 46.15, 269.23},{0.50, 5, 42.85, 250},{0.46, 4.66, 33.33, 233.33},//13-15
                                   {0.43, 4.37, 40, 218.75},{0.41, 4.12, 35.29, 205.88},{0.38, 3.88, 33.33, 194.44},//16-18
                                   {0.37, 3.68, 31.58, 184.21},{0.35, 3.5, 30, 175}};
  
 private static double matrizRedoblon[][]={ {1280, 640, 336}, {256, 128, 64}, {64, 32, 16},{36, 32, 16}};



   private Conexion mysql=new Conexion();
   private Connection cn = mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   
   
  public boolean eliminarJugadas(){
      java.util.Date fecha = new java.util.Date();
     java.sql.Date fechaSql=new java.sql.Date(fecha.getTime()-15 * 24 * 60 * 60 * 1000);
     sSQL = "DELETE FROM jugada where CAST(jugada.fecha AS DATE)<='"+fechaSql+"'";
        try(PreparedStatement pst = cn.prepareStatement(sSQL)){
            int n = pst.executeUpdate();
            if (n != 0) {
                pst.close();
                return true;
            } else {
                pst.close();
                return false;
            }
        } catch (Exception e) {
            
            System.out.println("error : " + e);
            return false;
        }
    }
   
   public ArrayList<Jugada> generarJugada(ArrayList<Integer> posiciones, ArrayList<String> quinielas, ArrayList<String> turnos, String nombre,int tipo, String numero, int cifras, int monto) {
    ArrayList<Jugada> jugadas = new ArrayList();

    try {
        String horaActual = this.hora();
        DateFormat dateFormat = new SimpleDateFormat(FH);
        Date horaVespertinaM = dateFormat.parse(FH_SEGUNDA_M);
        Date  hora = dateFormat.parse(horaActual); 
        Date horaTardeM = dateFormat.parse(FH_TERCERA_M);

//conso
        for (String turno : turnos) {
            for (String quiniela: quinielas) {
                 for (Integer posicion : posiciones) {
                   if(!(((turno.equals(primera))||(turno.equals(tercera))) && (quiniela.equals("O")))){
                    if(!(quiniela.equals(mendoza) && ((turno.equals(segunda) && (hora.after(horaVespertinaM))) || (turno.equals(tercera) && (hora.after(horaTardeM)))))){
                    Jugada j = new Jugada(tipo, numero, cifras, posicion, monto, quiniela, turno, false);
                    jugadas.add(j);                           
                     }
                    }
                }    
            }
        }
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       return jugadas;

    } 
   
public ResumenJugada generarResumenJugadas(ArrayList<Integer> posiciones, ArrayList<String> quinielas, ArrayList<String> turnos, String nombre,int tipo, String numero, int cifras, int monto) {
        
        StringBuilder jugada = new StringBuilder();
        jugada.append(numero);
        jugada.append("  ");
           for (String quiniela : quinielas) {
            jugada.append(quiniela);
             }jugada.append("  ");
            for (String turno : turnos) {
                jugada.append(turno);
                jugada.append("/");}
                 jugada.append("  ");
                for (Integer posicion : posiciones) {
                    jugada.append(String.valueOf(posicion));
                      jugada.append("-");      
                  } jugada.append("  ");
                  jugada.append("$");
                  jugada.append(monto);
                ResumenJugada resumen=new ResumenJugada(numero,jugada);
              return resumen;
    }

    public String fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormato = formatoFecha.format(fecha);
        return fechaFormato;
    }
   public Date fechaSql() {
        Date fecha = new Date();
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
        return fechaSql;
    }

    public String hora() {
        Date hora = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        String horaFormato = formatoHora.format(hora);
        return horaFormato;

    }

    public int contarCifras(String numero) {  
        return numero.length();
    }

    public boolean insertar(Jugada jugada) {
  
       sSQL = "insert into jugada (tipo, numero, cifras, posicion, monto, quiniela, turno,idRedoblona,idBoleta)"
                + "values (?,?,?,?,?,?,?,?,(select idboleta from boleta WHERE idboleta = (SELECT MAX(idboleta)from boleta)))";
        try {
            PreparedStatement pst2 = cn.prepareStatement(sSQL);
            pst2.setInt(1, jugada.getTipo());
            pst2.setString(2, jugada.getNumero());
            pst2.setInt(3, jugada.getCifras());
            pst2.setInt(4, jugada.getPosicion());
            pst2.setInt(5, jugada.getMonto());
            pst2.setString(6, jugada.getQuiniela());
            pst2.setString(7, jugada.getTurno());
            pst2.setInt(8, jugada.getIdRedoblona());
         
            int n = pst2.executeUpdate();
            if (n != 0) {
                 pst2.close();
                return true;
            } else {
                pst2.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
            return false;
        }
    }
    public ArrayList<Jugada> buscarJugadas(int idBoleta) {
        ArrayList<Jugada> jugadas = new ArrayList();
        try {
        
            ResultSet r;
            try (Statement orden = cn.createStatement()) {
                sSQL = "SELECT * from jugada where jugada.idBoleta="+idBoleta;
                r = orden.executeQuery(sSQL);
                while (r.next()) {
                    
                    int idJugada = r.getInt("idJugada");
                    int tipo =r.getInt("tipo");
                    String numero = r.getString("numero");
                    int cifras = r.getInt("cifras");
                    int posicion = r.getInt("posicion");
                    int monto = r.getInt("monto");
                    
                    String quiniela = r.getString("quiniela");
                    String turno = r.getString("turno");
                    
                    Date fecha = r.getDate("fecha");
                    boolean gano = r.getBoolean("gano");
                    int idRedoblona = r.getInt("idRedoblona");
                    
                    
                    Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);
                    jugadas.add(jugada);
                    
                }
                 orden.close();
            }
            r.close();
          
            return jugadas;

        } catch (SQLException ex) {
            System.out.println("error :" + ex);
            return jugadas;
        }
    }

    public boolean eliminarJugada(Jugada j) {
       
        String sSQL = "delete from jugada where idJugada=?";
        try {
                  
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, j.getIdJugada());
            int n = pst.executeUpdate();
            if (n != 0) {
                pst.close();
                return true;
            } else {
                pst.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("error : " + e);
            return false;
        }
    } 
       
public boolean eliminarJugada(int idBoleta) {
       
       sSQL = "delete from jugada where idboleta=?";
        try {
     
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, idBoleta);
            int n = pst.executeUpdate();
            if (n != 0) {
                pst.close();
                return true;
            } else {
                pst.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("error : " + e);
            return false;
        }
    }
    

public void actualizarJugadas(Ganada ganada){
    ArrayList<Jugada> jugadas = new ArrayList();
    ArrayList<String> numeros = new ArrayList();
    numeros = this.desarmarNumero(ganada.getNumero());
    for(String numero : numeros){
        jugadas = this.getJugadas(ganada, numero);

    }
    
    
}
    public ArrayList<Jugada> getJugadas(Ganada ganada, String num) {
         ArrayList<Jugada> jugadas = new ArrayList();
    
        try {
             Statement orden=cn.createStatement();
             sSQL = "SELECT * from jugada where jugada.fecha='" + ganada.getFecha() + "' and jugada.quiniela='" + ganada.getQuiniela() + "' and jugada.turno='" + ganada.getTurno() + "' and jugada.numero=" +num+ "' and jugada.posicion >='" + ganada.getPosicion();//\n"
             ResultSet r = orden.executeQuery(sSQL);
             while (r.next()) {

                int idJugada = r.getInt("idJugada");
                int tipo = r.getInt("tipo");
                String numero = r.getString("numero");
                int cifras = r.getInt("cifras");
                int posicion = r.getInt("posicion");
                int monto = r.getInt("monto");
                String quiniela = r.getString("quiniela");
                String turno = r.getString("turno");
                boolean gano = r.getBoolean("gano");
                 int idBoleta = r.getInt("idBoleta");
                int idRedoblona = r.getInt("idRedoblona");
                Date fecha = r.getDate("fecha");
 
                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);
                jugadas.add(jugada);              
            }
            orden.close();
            r.close();
            return jugadas;

        } catch (SQLException ex) {
            System.out.println("error :" + ex);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setTitle("informacion");
            alerta.setContentText("FALLOLA BUSQUEDA");
            alerta.showAndWait();
        }
        return null;
    }

public boolean actualizarJugada(Jugada jugada) {
        String sSQL = "update Jugada set gano=? where idJugada=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setBoolean(1, jugada.isGano());                    
            pst.setInt(2, jugada.getIdJugada());

            int n = pst.executeUpdate();

            if (n != 0) {
                pst.close();
               return true;
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("informacion");
                alerta.setContentText("ERROR AL ACTUALIZAR EL REGISTRO");
                alerta.showAndWait();
                pst.close();
                return false;
            }

        } catch (Exception e) {
            System.out.println("error :" + e);

        }return false;
    }


    public ArrayList<String> desarmarNumero(String numero) {

        ArrayList<String> lista = new ArrayList();        
        String una = numero.substring(3);
         String dos = numero.substring(2);
          String tres = numero.substring(1);
           String cuatro = numero.substring(0);
        lista.add(una);lista.add(dos);lista.add(tres);lista.add(cuatro);
        return lista;
    }


    public boolean jugadaGano(Jugada j) {
        if (j.isGano() && j.getTipo()==1) {
            return true;
        } else if (j.isGano() && j.getTipo()==2) {     
            Jugada jugadaPareja = this.buscarPareja(j);
            if (jugadaPareja.isGano()) {
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

public Jugada buscarPareja(Jugada jugada) {

        try {
              
            Statement orden = cn.createStatement();
           sSQL = "SELECT * from jugada where jugada.idboleta=" + jugada.getIdBoleta() + " and jugada.idRedoblona=" + jugada.getIdRedoblona() + " and jugada.idJugada !=" +jugada.getIdJugada() ;
            ResultSet r = orden.executeQuery(sSQL);
            while (r.next()) {
                int idJugada = r.getInt("idJugada");
                int tipo = r.getInt("tipo");
                String numero = r.getString ("numero");
                int cifras = r.getInt("cifras");
                int posicion = r.getInt("posicion");
                int monto = r.getInt("monto");
                String quiniela = r.getString("quiniela");
                String turno = r.getString("turno");
                //String nombre = r.getString("nombre");
                boolean gano = r.getBoolean("gano");
                int idBoleta = r.getInt("idBoleta");
                int idRedoblona = r.getInt("idRedoblona");
                Date fecha = r.getDate("fecha");               
              
                Jugada j = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);
               
                    orden.close();
                    r.close();
                    return j;
               }
              orden.close();
                     r.close();

        } catch (SQLException ex) {
            System.out.println("error :" + ex);

        }
        return null;
    }


public int calcularPremio(Jugada j) {
            if(j.isGano() && j.getTipo()==1){
              int total = this.premio(j);
              return total;
            }else {
                System.out.println("entro a calcular premio redoblona ***************");
                 if(j.isGano() && j.getTipo()==2){
                     System.out.println("j is gano y es de tipo 2***************");
                    Jugada pareja=this.buscarPareja(j);                 
                    int total = this.premio(j,pareja);  
                    return total;
                      }
            return 0;
         }
    }
    
public int premio(Jugada j){
   int fila=j.getPosicion()-1;
   int columna=j.getCifras()-1;
   int monto=j.getMonto();
   double multiplicador=(double) this.matriz[fila][columna];
   int premio=(int) (monto*multiplicador); 
   return premio;
    }
 
private int premio(Jugada j, Jugada g){
    System.out.println("entro por fina calular el premio******");
    Jugada uno;Jugada dos;
             if(this.sonPareja(j, g)){
                if(j.isGano() && g.isGano()){
                    if(j.getPosicion()<g.getPosicion()){
                       uno=j; dos=g;}
                    else{
                         uno=g; dos=j;
                    }
                      int columna;
                      if(dos.getPosicion()==5)columna=0;
                      else if(dos.getPosicion()==10)columna=1;
                      else columna=2;
                   int fila;
                     if(uno.getPosicion()==1)fila=0; 
                     else if(uno.getPosicion()==5)fila=0;
                     else if(uno.getPosicion()==10)fila=1;
                     else fila=2;
                     int monto=uno.getMonto();
  
                     int multiplicador=(int) this.matrizRedoblon[fila][columna];
                     int premio=monto*multiplicador; 
                     return premio;
                    }
                   
        }return 0;
  
}
public boolean sonPareja(Jugada uno, Jugada dos){
   if(uno.getIdRedoblona()==dos.getIdRedoblona()){
      if(uno.getIdBoleta()== dos.getIdBoleta()){
        if(uno.getTipo() == 2 && dos.getTipo() == 2){
                    return true;
            }
        }
    }
   return false;
}


public void actualizarJugada(int id) {
   
   sSQL = "update Jugada set gano=?,idGanada=? where idGanada=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setBoolean(1, false);
            pst.setInt(2, 0);
   
            pst.setInt(3, id);
            int n = pst.executeUpdate();

            if (n != 0) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("informacion");
                alerta.setContentText("SE ACTUALIZO EL REGISTRO JUGADAS");
                alerta.showAndWait();
                pst.close();
                
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("informacion");
                alerta.setContentText("ERROR AL ACTUALIZAR EL REGISTRO");
                alerta.showAndWait();
               pst.close();
            }

        } catch (Exception e) {
            System.out.println("error :" + e);

        }
    }

ArrayList<Jugada> consultar(Date fecha, String quiniela, String turno) {

     ArrayList<Jugada> jugadas = new ArrayList();
    
        try {

            Statement orden=cn.createStatement();
            sSQL="SELECT * from Jugada where CAST(Jugada.fecha AS DATE)='"+fecha+"' and Jugada.quiniela='"+quiniela+"' and Jugada.turno='"+turno+"'";
            ResultSet r =orden.executeQuery(sSQL);  
           while (r.next()) {
                int idJugada = r.getInt("idJugada");
                int tipo =r.getInt("tipo");
                String  numero = r.getString ("numero");
                int cifras = r.getInt("cifras");
                int posicion = r.getInt("posicion");
                int monto = r.getInt("monto");
                int idBoleta = r.getInt("idBoleta");
                boolean gano = r.getBoolean("gano");
                int idRedoblona = r.getInt("idRedoblona");
                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);
                jugadas.add(jugada);
           }
         orden.close();
            r.close();    
           return jugadas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return jugadas;
    }

public ResumenJugada generarResumenRedoblona(ArrayList<Integer> Nposiciones, ArrayList<String> quinielas, ArrayList<String> turnos, String numero1, String numero2, int monto) {
        StringBuilder jugada = new StringBuilder();
        jugada.append("R:");
        jugada.append(numero1);
        jugada.append("_");
        jugada.append(numero2);
        jugada.append("  ");
        for (String quiniela : quinielas) {
            jugada.append(quiniela);
                  }jugada.append("  ");
            for (String turno : turnos) {
                jugada.append(turno);
                jugada.append("/");}
                 jugada.append("  ");
                for (Integer posicion : Nposiciones) {
                    jugada.append(String.valueOf(posicion));
                      jugada.append("-");      
                  }jugada.append("  ");
                  jugada.append("$");
                  jugada.append(monto);
                ResumenJugada resumen=new ResumenJugada(numero1,jugada);
              return resumen;
  
    }

public ArrayList<Jugada> consultarFecha(Date fecha) {
             ArrayList<Jugada> jugadas = new ArrayList();
    
        try {
                
            Statement orden=cn.createStatement();
            String sSQL3="SELECT * from Jugada where Jugada.fecha='"+fecha+"'";
            ResultSet r =orden.executeQuery(sSQL3);  
           while (r.next()) {
                int idJugada = r.getInt("idJugada");
                int tipo =r.getInt("tipo");
                String  numero = r.getString ("numero");
                int cifras = r.getInt("cifras");
                int posicion = r.getInt("posicion");
                int monto = r.getInt("monto");
                String quiniela = r.getString("quiniela");
                String turno = r.getString("turno");
                int idBoleta = r.getInt("idBoleta");
                boolean gano = r.getBoolean("gano");
                int idRedoblona = r.getInt("idRedoblona");
                 int idPago = r.getInt("idPago");
                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);             
                jugadas.add(jugada);
           }
             orden.close();
            r.close();  
       return jugadas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return jugadas;

    }

public ArrayList<Jugada> consultarEntreFechas(java.sql.Date fecha1, java.sql.Date fecha2) {
                  ArrayList<Jugada> jugadas = new ArrayList();
        try {

            Statement orden=cn.createStatement();
          String sSQL3="SELECT * from Jugada where fecha BETWEEN '"+(java.sql.Date)fecha1+"' AND '"+(java.sql.Date)fecha2+"'";
            ResultSet r =orden.executeQuery(sSQL3);  
           while (r.next()) {
                int idJugada = r.getInt("idJugada");
                int tipo =r.getInt("tipo");
               String  numero = r.getString ("numero");
                int cifras = r.getInt("cifras");
                int posicion = r.getInt("posicion");
                int monto = r.getInt("monto");
                String quiniela = r.getString("quiniela");
                  String turno = r.getString("turno");
                int idBoleta = r.getInt("idBoleta");
                Date fecha = r.getDate("fecha");
                boolean gano = r.getBoolean("gano");
                int idRedoblona = r.getInt("idRedoblona");

                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);
                jugadas.add(jugada);
           }
            orden.close();
            r.close();   
       return jugadas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return jugadas;

    }
 public Jugada buscarPareja(Jugada jugada, ArrayList<Jugada> jugadas){
     for(Jugada j : jugadas){
         if(j.getIdRedoblona() == jugada.getIdRedoblona() && j.getIdJugada() != jugada.getIdJugada()){
             return j;
         }
     }
     return null;
 }
    
    }

    





  
    


