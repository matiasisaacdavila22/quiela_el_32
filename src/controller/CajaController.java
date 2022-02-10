/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Caja;
import utils.Conexion;
import model.Jugada;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.control.Alert;

/**
 *
 * @author mipc
 */
public class CajaController {

  private Conexion mysql=new Conexion();
  private Connection cn = mysql.conectar();
  private String sSQL="";
  public Integer totalregistros; 

 public boolean eliminarCajas(){
      java.util.Date fecha = new java.util.Date();
     java.sql.Date fechaSql=new java.sql.Date(fecha.getTime()-15 * 24 * 60 * 60 * 1000);
     sSQL = "DELETE FROM caja where CAST(caja.fecha AS DATE)<='"+fechaSql+"'";
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
    
 public boolean checarCaja(Date fecha) {
        try {
          
            Statement orden=cn.createStatement();
            String sSQL="SELECT * from caja where CAST(caja.fecha AS DATE)='"+fecha+"'";
            ResultSet r =orden.executeQuery(sSQL);  
            
              while(r.next()){
                    int id =r.getInt("id");
                    Date fechaSql= r.getDate("fecha");
                    int caja_inicial = r.getInt("caja_inicial");
                    int caja_final = r.getInt("caja_final");
                    boolean estado=r.getBoolean("estado");
                     orden.close();
                     r.close();
                    return true;
                  }
              
               } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return false;
    }    
    
  
 public Caja buscarCaja(Date fecha) {
     
        try {

            Statement orden=cn.createStatement();
            sSQL="SELECT * from caja where CAST(caja.fecha AS DATE)='"+fecha+"'";
            ResultSet r =orden.executeQuery(sSQL);  
            
              while(r.next()){
                    int id =r.getInt("id");
                    Date fechaSql= r.getDate("fecha");
                    Double caja_inicial = r.getDouble("caja_inicial");
                    Double caja_final = r.getDouble("caja_final");
                    boolean estado=r.getBoolean("estado");
                    
                   Caja caja=new Caja(id, fechaSql, caja_inicial, caja_final, estado);
                   orden.close();
                   r.close();
                   return caja;
                  }
  
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return null;
    }
    
 public Date fechaActual(){
     java.util.Date fecha = new java.util.Date();
     Date fechaSql=new Date(fecha.getTime());
     return fechaSql;
 } 
 
public boolean createCaja(Caja caja) {
      sSQL = "insert into caja (caja_inicial, caja_final)"
                + "values (?,?)";
                 
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setDouble(1, caja.getCaja_inicial());
            pst.setDouble(2, caja.getCaja_final());           
            int n = pst.executeUpdate();
    
            if (n != 0) {
                               Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                               alerta.setHeaderText(null);
                               alerta.setTitle("informacion");
                               alerta.setContentText("la caja se inicio correctamente");
                               alerta.showAndWait();
                               pst.close();
                               return true;
            }else{   
                            
                  Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                               alerta.setHeaderText(null);
                               alerta.setTitle("informacion");
                               alerta.setContentText("la caja no pudo iniciarce..REINTENTE.....");
                               alerta.showAndWait();
                               pst.close();
                               return false;
            }


        } catch (Exception e) {
           System.out.println("Error : "+e);
           
        }return false;
    }

public void editarCaja(Caja caja) {

    String sSQL = "update caja set caja_inicial=?,caja_final=? where id=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setDouble(1, caja.getCaja_inicial());
            pst.setDouble(2, caja.getCaja_final());            
            pst.setInt(3, caja.getId());
            int n = pst.executeUpdate();

            if (n != 0) {
               pst.close();
              } else {
                  Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                               alerta.setHeaderText(null);
                               alerta.setTitle("informacion");
                               alerta.setContentText("ERROR AL ACTUALIZAR EL CAJA");
                               alerta.showAndWait();
                               pst.close();
         }
            
            
        }catch (Exception e) {
            System.out.println("error :"+e);
            
        }
} 

//void setCaja() {
//               String horaActual = this.hora();
//             Caja caja=this.buscarCaja(this.fechaActual());
//        try {
//                DateFormat dateFormat = new SimpleDateFormat("HH:mm");
//                  java.util.Date horaManiana =dateFormat.parse("14:00");
//                  java.util.Date horaNocturna= dateFormat.parse("21:00");
//                  java.util.Date hora= dateFormat.parse(horaActual);
//                         
//                if (hora.after(horaManiana)){
//                    caja.setEstadoManiana(false);
//                } else {caja.setEstadoManiana(true);}
//                if (hora.after(horaManiana) && hora.before(horaNocturna)){
//                    caja.setEstadoTarde(true);
//                }else { caja.setEstadoTarde(false);}
//                
//                this.editarCaja(caja);
//                
//        } catch (ParseException ex) {
//            System.out.println("Posee errores");
//        }               
//}

     public String fecha() {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormato = formatoFecha.format(fecha);
        return fechaFormato;
    }
   public java.util.Date fechaSql() {
        java.util.Date fecha = new java.util.Date();
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
        return fechaSql;
    }

    public String hora() {
        java.util.Date hora = new java.util.Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        String horaFormato = formatoHora.format(hora);
        return horaFormato;

    }
}    


