/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Caja;
import model.Movimiento;
import utils.Conexion;

/**
 *
 * @author holasur
 */
public class MovimientoController {
    
    
  private Conexion mysql=new Conexion();
  private Connection cn = mysql.conectar();
  private String sSQL="";
  public Integer totalregistros; 
  
  
 public Date fechaActual(){
     java.util.Date fecha = new java.util.Date();
     Date fechaSql=new Date(fecha.getTime());
     return fechaSql;
 } 
 
public boolean createMovimiento(Movimiento movi) {
      sSQL = "insert into movimiento (idCaja, monto, descripcion, estado)"
                + "values (?,?,?,?)";
                 
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setInt(1, movi.getIdCaja());
            pst.setDouble(2, movi.getMonto());
            pst.setString(3, movi.getDescripcion());
            pst.setBoolean(4, movi.isEstado());
            int n = pst.executeUpdate();
    
            if (n != 0) {
                               Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                               alerta.setHeaderText(null);
                               alerta.setTitle("informacion");
                               alerta.setContentText("movimiento insertado correctamente");
                               alerta.showAndWait();
                               pst.close();
                               return true;
            }else{   
                            
                  Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                               alerta.setHeaderText(null);
                               alerta.setTitle("informacion");
                               alerta.setContentText("ERROR al incertar movimiento!!!!");
                               alerta.showAndWait();
                               pst.close();
                               return false;
            }


        } catch (Exception e) {
           System.out.println("Error : "+e);
           
        }return false;
    }

 public ObservableList<Movimiento> movimientos(Date fecha) {
     ObservableList<Movimiento> movimientos = FXCollections.observableArrayList();
        try {

            Statement orden=cn.createStatement();
            sSQL="SELECT * from movimiento where CAST(movimiento.fecha AS DATE)='"+fecha+"'";
            ResultSet r =orden.executeQuery(sSQL);  
            
              while(r.next()){
                    int id =r.getInt("id");
                    int idCaja =r.getInt("idCaja");
                    Double monto = r.getDouble("monto");
                    String descripcion = r.getString("descripcion");
                    java.sql.Time fechaSql= r.getTime("fecha");
                    boolean estado=r.getBoolean("estado");
                    
                   Movimiento movimiento=new Movimiento(id, idCaja, monto, descripcion, estado, fechaSql);
                   System.out.println("la fecha es :"+fechaSql);
                   movimientos.add(movimiento);
                  }
                   orden.close();
                   r.close();
                   return movimientos;  
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return null;
    }
}
