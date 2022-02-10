/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

 //
import utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import model.Boleta;
import model.Jugada;
import model.Resumen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static org.apache.poi.hwpf.model.FileInformationBlock.logger;


/**
 *
 * @author mipc
 */
public class BoletaController {
   private Conexion mysql=new Conexion();
   private Connection cn = mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   private JugadaController jugadaController; 

public boolean generarBoleta(int total, String nombre){
    Date fechaActual= new Date();
    java.sql.Date fechaActualSQL = new java.sql.Date(fechaActual.getTime());
    System.out.println(fechaActualSQL.getTime());
    Boleta boletaNueva= new Boleta(nombre, total, fechaActualSQL, false );
    return insertar(boletaNueva);
}

public boolean insertar(Boleta boleta) {
    System.out.println("entro en insertar boleta");    
     sSQL = "Insert Into boleta(total,nombre,estado)"
                + "values (?,?,?)";
        try {           
            PreparedStatement pst = cn.prepareStatement(sSQL);
            System.out.println("insertando boleta");
  
           // pst.setDate(1, boleta.getFecha());
            pst.setInt(1, boleta.getTotal());
            pst.setString(2, boleta.getNombre());
            pst.setBoolean(3, boleta.isEstado());
                       
            int n = pst.executeUpdate();
            if (n != 0) {
                System.out.println("boleta insertada");
                pst.close();
               return true;
             
           } else {
                pst.close();
                return false;
            }
     } catch (Exception e) {
            System.out.println("Error : "+e);
            return false;
        }
    }

   public Boleta consultarUltimaBoleta() {
       System.out.println("entro a consultar laultima boleta");
        try {

            Statement orden = cn.createStatement();
            // String sSQL3="SELECT * from Caja where Caja.fecha='"+fecha+"'";
          sSQL = "SELECT * from boleta  WHERE boleta.idboleta = (SELECT MAX(idboleta)from boleta)";

            ResultSet r = orden.executeQuery(sSQL);

            while (r.next()) {

                int idBoleta = r.getInt("idboleta");
                String nombre = r.getString("nombre");
                Date fecha = r.getDate("fecha");
                 int total = r.getInt("total");
                 boolean estado = r.getBoolean("estado");
                                           
                Boleta boleta = new Boleta(idBoleta, nombre, total,(java.sql.Date) fecha, estado);
                System.out.println(""+boleta.getId()+" "+boleta.getFecha()+" "+boleta.getTotal());
                 orden.close();
                 r.close();
                return boleta;
                    }
            return null;

        } catch (SQLException ex) {
            System.out.println("error :" + ex);
            return null;
        }
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

  public ObservableList<Boleta> consultar() {
             ObservableList<Boleta> b;
        b = FXCollections.observableArrayList();
          try {
            Statement orden = cn.createStatement();
           sSQL = "SELECT * from Boletas order by id desc";

            ResultSet r = orden.executeQuery(sSQL);

            while (r.next()) {

                int idBoleta = r.getInt("idboleta");
                String nombre = r.getString("nombre");
                Date fecha = r.getDate("fecha");
                int total = r.getInt("total");
                boolean estado = r.getBoolean("estado");
               Boleta boleta = new Boleta(idBoleta, nombre, total,(java.sql.Date) fecha, estado);
                b.add(boleta);
                
            }
               orden.close();
                 r.close();
             
            return b;

        } catch (SQLException ex) {
            System.out.println("error :" + ex);
            return null;
        }    }
 
 public ObservableList<Boleta> consultarxNombre(String buscar, Date fecha) {
             ObservableList<Boleta> b;
        b = FXCollections.observableArrayList();

        try {
    
            Statement orden = cn.createStatement(); //Jugadas.turno='"+turno+"'";
             sSQL="SELECT * from boleta where boleta.nombre='"+buscar+"' and CAST(Boleta.fecha AS DATE) ='"+fecha+"'";
            ResultSet r = orden.executeQuery(sSQL);

            while (r.next()) {

                int idBoleta = r.getInt("idboleta");
                String nombre = r.getString("nombre");             
                int total = r.getInt("total");
                boolean estado = r.getBoolean("estado");
               Boleta boleta = new Boleta(idBoleta, nombre, total,(java.sql.Date) fecha, estado);
                b.add(boleta);
                
            }
               orden.close();
                 r.close();
             
            return b;

        } catch (SQLException ex) {
            System.out.println("error :" + ex);
            return null;
        }    }  
  
 public ObservableList<Boleta> consultarXId(int id) {
             ObservableList<Boleta> b;
        b = FXCollections.observableArrayList();
        try {         
            Statement orden = cn.createStatement();
            sSQL="SELECT * from boleta where boleta.idboleta='"+id+"' order by idboleta desc";
        
            ResultSet r = orden.executeQuery(sSQL);

            while (r.next()) {

                int idBoleta = r.getInt("idboleta");
                 String nombre = r.getString("nombre");
                Date fecha = r.getDate("fecha");
                int total = r.getInt("total");
                boolean estado = r.getBoolean("estado");
               Boleta boleta = new Boleta(idBoleta, nombre, total,(java.sql.Date) fecha, estado);
                b.add(boleta);
                
            }
               orden.close();
                 r.close();
             
            return b;

        } catch (SQLException ex) {
            System.out.println("error :" + ex);
            return null;
        }    
 }
 
  public Boleta consultarBoletaById(int id) {
     
        try {         
            Statement orden = cn.createStatement();
            sSQL="SELECT * from boleta where boleta.idboleta='"+id+"' order by idboleta desc";
        
            ResultSet r = orden.executeQuery(sSQL);

            while (r.next()) {

                int idBoleta = r.getInt("idboleta");
                 String nombre = r.getString("nombre");
                Date fecha = r.getDate("fecha");
                int total = r.getInt("total");
                boolean estado = r.getBoolean("estado");
               Boleta boleta = new Boleta(idBoleta, nombre, total,(java.sql.Date) fecha, estado);
             
                return boleta;
            }
               orden.close();
                 r.close();
             
            return null;

        } catch (SQLException ex) {
              System.out.println("error :" + ex);
            return null;
        }    
 }

public ArrayList<Boleta> consultarFecha(Date fecha) {
                 ArrayList<Boleta> boletas = new ArrayList();
    
        try {
            Statement orden=cn.createStatement();
            sSQL="SELECT * from boleta where CAST(boleta.fecha AS DATE)='"+fecha+"'";
            ResultSet r =orden.executeQuery(sSQL);  
           while (r.next()) {
                 int idBoleta = r.getInt("idboleta");
                 String nombre = r.getString("nombre");
                Date fecha2 = r.getDate("fecha");
              
                int total = r.getInt("total");
                boolean estado = r.getBoolean("estado");
               Boleta boleta = new Boleta(idBoleta, nombre, total,(java.sql.Date) fecha, estado);
                boletas.add(boleta);
           }
             orden.close();
                 r.close();
                 
       return boletas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return boletas;

    }
public ObservableList<Boleta> consultarxFecha(Date fecha) {
    ObservableList<Boleta> boletas = FXCollections.observableArrayList();   
        try { 
//           SELECT * FROM wp_posts
//WHERE CAST(post_date AS DATE) = '2014-03-19'
//AND post_status = 'publish'
//ORDER BY post_date ASC
           Statement orden=cn.createStatement();
           sSQL="SELECT * from boleta where CAST(Boleta.fecha AS DATE)='"+fecha+"' order by idBoleta desc";
            ResultSet r =orden.executeQuery(sSQL);  
           while (r.next()) {
                 int idBoleta = r.getInt("idBoleta");
                 String nombre = r.getString("nombre");
                Date fecha2 = r.getDate("fecha");
              java.sql.Timestamp fecha3 = r.getTimestamp("fecha");
                int total = r.getInt("total");
                boolean estado = r.getBoolean("estado");
               Boleta boleta = new Boleta(idBoleta, nombre, total,(java.sql.Date) fecha, estado);
             
                boletas.add(boleta);
                 }
             orden.close();
                 r.close();
             
       return boletas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return boletas;

    }
public ArrayList<Boleta> consultarFecha(Date fecha, String hora_inicio, String hora_final) {
    ArrayList<Boleta> boletas = new ArrayList();   
        try { 

           Statement orden=cn.createStatement();//='"+fecha+"'
sSQL="SELECT * FROM boleta WHERE boleta.fecha >= '"+fecha+" "+hora_inicio+"' AND boleta.fecha <= '"+fecha+" "+hora_final+"'";
            ResultSet r =orden.executeQuery(sSQL);  
           while (r.next()) {
                 int idBoleta = r.getInt("idBoleta");
                 String nombre = r.getString("nombre");
                Date fecha2 = r.getDate("fecha");
              
                int total = r.getInt("total");
                boolean estado = r.getBoolean("estado");
               Boleta boleta = new Boleta(idBoleta, nombre, total,(java.sql.Date) fecha, estado);
                boletas.add(boleta);
                 }
             orden.close();
                 r.close();
            System.out.println("las boletas son :"+boletas.size());    
       return boletas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return boletas;

    }
public ObservableList<Boleta> consultarEntreFechas(Date fecha1, Date fecha2) {
          ObservableList<Boleta> boletas = FXCollections.observableArrayList();  
          System.out.println("entro a consultar entre fechas, la fecha1 es : "+fecha1+" la fecha2 es : "+fecha2);
        try {           
            Statement orden=cn.createStatement();
     
     sSQL="SELECT * from boleta where CAST(boleta.fecha AS DATE) BETWEEN '"+fecha1+"' AND '"+fecha2+"'";
            ResultSet r =orden.executeQuery(sSQL);  
           while (r.next()) {
                 int idBoleta = r.getInt("idBoleta");
                 String nombre = r.getString("nombre");
                 Date fecha = r.getDate("fecha");
              
                 int total = r.getInt("total");
                 boolean estado = r.getBoolean("estado");
                 Boleta boleta = new Boleta(idBoleta, nombre, total,(java.sql.Date) fecha, estado);
                 boletas.add(boleta);
           }
             orden.close();
                 r.close();  
       return boletas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return boletas;

    }
    
public ObservableList<Boleta> consultarxState(boolean state, Date fecha) {
    ObservableList<Boleta> boletas = FXCollections.observableArrayList();   
        try {            
           Statement orden=cn.createStatement();
           sSQL="SELECT * from Boleta where CAST(Boleta.fecha AS DATE)='"+fecha+"' order by idBoleta desc";
            ResultSet r =orden.executeQuery(sSQL);  
           while (r.next()) {
                 int idBoleta = r.getInt("idboleta");
                 String nombre = r.getString("nombre");            
                int total = r.getInt("total");
                boolean estado = r.getBoolean("estado");
              if(estado){
               Boleta boleta = new Boleta(idBoleta, nombre, total,(java.sql.Date) fecha, estado);
                boletas.add(boleta);
                 }
           }
             orden.close();
                 r.close();  
       return boletas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return boletas;

    }

 public boolean eliminarBoleta(int idBoleta){
   sSQL = "delete from boleta where idBoleta=?";
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
 
  public boolean eliminarBoletas(){
      java.util.Date fecha = new java.util.Date();
     java.sql.Date fechaSql=new java.sql.Date(fecha.getTime()-15 * 24 * 60 * 60 * 1000);
     sSQL = "DELETE FROM boleta where CAST(boleta.fecha AS DATE)<='"+fechaSql+"'";
        try {
           PreparedStatement pst = cn.prepareStatement(sSQL);
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

    public int tiempoBoleta(Boleta j) {
        Date fechaInicial = j.getFecha();
        Date fechaFinal = new Date();
        int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
        return dias;
    }

    public void actualizarXfecha(ObservableList<Boleta> boletas) {
        boletas.stream().filter((n) -> tiempoBoleta(n) > 7).forEach((n) -> eliminarBoleta(n.getId()));
    }

public ObservableList<Boleta> consultarxId(ObservableList<Boleta> obsBoleta, int id) {
         ObservableList<Boleta> boletas = FXCollections.observableArrayList();
        
        for(Boleta b : obsBoleta){
            if(b.getId()== id)boletas.add(b);
        }
        return boletas;
    }

 public ObservableList<Boleta> consultarxNombre(ObservableList<Boleta> obsBoleta, String buscar) {
         ObservableList<Boleta> boletas = FXCollections.observableArrayList();
        
        for(Boleta b : obsBoleta){
            if(b.getNombre().contains(buscar))boletas.add(b);
        }
        return boletas;
    }

 public void actualizarBoletas(Date fecha) {
             ArrayList<Boleta> boletas;
             boletas = this.consultarFecha(fecha);
             for(Boleta boleta : boletas){
                 this.actualizarBoleta(boleta);
       }
 }
    private void actualizarBoleta(Boleta boleta) {
                 ArrayList<Jugada> jugadas;
                 this.jugadaController = new JugadaController();
                 jugadas = this.jugadaController.buscarJugadas(boleta.getId());
                 for(Jugada jugada : jugadas){
                     if(jugada.isGano()){
                         if(jugada.getTipo() == 1){
                       this.cambiarEstado(boleta.getId(),true);
                       return;
                    }else if(jugada.getTipo() == 2){
                        Jugada jugada2 = this.jugadaController.buscarPareja(jugada, jugadas);
                        if(jugada2 != null){
                            if(jugada2.isGano()){
                            this.cambiarEstado(boleta.getId(),true);
                            return;                                
                            }
                        }
                    }
                 }
             }
                       this.cambiarEstado(boleta.getId(),false);
                       return;                 
    }
     public boolean cambiarEstado(int id,boolean estado){
     System.out.println("entro a cambiar estado de boleta id :"+id);
    String sSQL = "update boleta set estado=? where idboleta=?";
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
             pst.setBoolean(1, estado);
               pst.setInt(2, id);   
              int n = pst.executeUpdate();

            if (n != 0) {
                pst.close();
                  return true;
                  
                } else {
                pst.close();
                 return false;
              }
            
        }catch (Exception e) {
            System.out.println("error :"+e);
            
        }return false;
 }

    private void actualizarBoletasTipo2(ArrayList<Jugada> jugadasTipo2) {
    }
    }

  

