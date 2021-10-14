/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Boleta;
import utils.Conexion;
import model.Ganada;
import model.Jugada;
import model.Pago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author mipc
 */
public class PagoController {
   private Conexion mysql=new Conexion();
   private Connection cn = mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   private ArrayList<Pago>listPagos; 
   private JugadaController jugadaController;
   private ArrayList<Jugada>jugadasGanadoras;
  

 public void insertar(Pago pago) {
        System.out.println("entro en insertar  PAGO en base de datos");
        String sSQL = "insert into Pago (idBoleta, monto, estado)"
                + "values (?,?,?)";
                    
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            System.out.println("entro en pst inser PAGO");
            
            pst.setInt(1, pago.getIdBoleta());
            pst.setInt(2, pago.getMonto());
            pst.setBoolean(3, pago.isEstado());             
            int n = pst.executeUpdate();
    
            if (n != 0) {
                               Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                               alerta.setHeaderText(null);
                               alerta.setTitle("informacion");
                               alerta.setContentText("Exito al insertar el PAGO");
                               alerta.showAndWait();
            pst.close();
            }else{   
                            
                  Alert alerta=new Alert(Alert.AlertType.WARNING);
                               alerta.setHeaderText(null);
                               alerta.setTitle("informacion");
                               alerta.setContentText("error al ingrsar el PAGO..REINTENTE.....");
                               alerta.showAndWait();
            }
            pst.close();

        } catch (Exception e) {
           System.out.println("Error : "+e);
           
        }
    }
    public ArrayList<Pago> consultarFecha(Date fecha) {
             ArrayList<Pago> pagos = new ArrayList();
    
        try {            
            Statement orden=cn.createStatement();
            String sSQL3="SELECT * from Pago where Pago.fecha='"+fecha+"'";
            ResultSet r =orden.executeQuery(sSQL3);  
           while (r.next()) {
                int idPago = r.getInt("idPago");
                int idBoleta = r.getInt("idBoleta");
                int idJugada = r.getInt("idJugada");
                int monto = r.getInt("monto");
                Date fechaPago = r.getDate("fechaPago");
                boolean estado = r.getBoolean("estado");
                Pago pago = new Pago( idPago, idBoleta,(java.sql.Date) fechaPago, monto, estado);    
                pagos.add(pago);
           }
             orden.close();
            r.close();  
       return pagos;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
       }
    return pagos;

    }
    
    public ObservableList<Boleta> checarPagos(ObservableList<Boleta>boletas){
        for(Boleta boleta : boletas){
            Pago pago = this.buscarPagoIdBoleta(boleta.getId());
            if(pago != null){
                Date fecha = pago.getFechaPago();
                boleta.setPagado(true);
                boleta.setFechaPago((java.sql.Date) fecha);
                boleta.setTotalPago(pago.getMonto());
              }else if(pago == null){
                 boleta.setPagado(false);
                 if(boleta.isEstado()){
                     
                 }
            }
        }        
        return boletas;
    }
        
    public Pago buscarPagoIdBoleta(int idBoleta) {
        System.out.println("la id de boleta is :"+idBoleta);
        try {
            Statement orden=cn.createStatement();
            String sSQL = "SELECT * from pago where pago.idboleta="+idBoleta;
            ResultSet r = orden.executeQuery(sSQL);

            System.out.println("entrar a la consulta");
            while (r.next()) {
                int idPago = r.getInt("idpago");
                int monto = r.getInt("monto");
                Date fechaPago = r.getDate("fechaPago");
                boolean estado = r.getBoolean("estado");
                Pago pago = new Pago( idPago, idBoleta,(java.sql.Date) fechaPago, monto, estado);     
    
            orden.close();
            r.close();
            return pago;
            }

        } catch (SQLException ex) {
            System.out.println("error :" + ex);
            
        }
        return null;
    } 
    
      public Pago consultarUltimoPago() {
       System.out.println("entro a consultar laultima PAGO");
        try {
            Statement orden = cn.createStatement();
            // String sSQL3="SELECT * from Caja where Caja.fecha='"+fecha+"'";
            String sSQL = "SELECT * from Pagos  WHERE Pagos.idPago = (SELECT MAX(idPago)from Pagos)";

            ResultSet r = orden.executeQuery(sSQL);

            while (r.next()) {
                int idPago = r.getInt("idPago");
                int idBoleta = r.getInt("idBoleta");
                int idJugada = r.getInt("idJugada");
                int monto = r.getInt("monto");
                Date fechaPago = r.getDate("fecha");
                boolean estado = r.getBoolean("estado");
                Pago pago = new Pago( idPago, idBoleta,(java.sql.Date) fechaPago, monto, estado);      
    
            orden.close();
            r.close();
            return pago;
                    }
            return null;
        } catch (SQLException ex) {
            System.out.println("error :" + ex);
            return null;
        }
    }
     // tiene en cuenta si esta pagada la Boleta 
   public int calcularPago(Boleta boleta){
       int total = 0;
       jugadaController = new JugadaController();
       ArrayList idRedoblona = new ArrayList();
       if(boleta.isEstado()){
           if(!boleta.isPagado()){
              ArrayList<Jugada> jugadas = new ArrayList();
              jugadas = jugadaController.buscarJugadas(boleta.getId());
              for(Jugada jugada : jugadas){                    
                  if(jugadaController.jugadaGano(jugada)){
                      if(jugada.getTipo()== 1){
                          total = total + jugadaController.calcularPremio(jugada);
                      }else if(jugada.getTipo() == 2){
                          if(!idRedoblona.contains(jugada.getIdRedoblona())){
                              idRedoblona.add(jugada.getIdRedoblona());
                              total = total + jugadaController.calcularPremio(jugada);
                          }
                      }
                  }               
           }
              return total;
   }
  return total;      
   }
  return total;         
}
   // calcula el pago independientemente si se pago o no la boleta 
      public int calcularPago2(Boleta boleta){
       int total = 0;
       jugadaController = new JugadaController();
       ArrayList idRedoblona = new ArrayList();
       if(boleta.isEstado()){
              ArrayList<Jugada> jugadas = new ArrayList();
              jugadas = jugadaController.buscarJugadas(boleta.getId());
              for(Jugada jugada : jugadas){
                  if(jugadaController.jugadaGano(jugada)){
                      if(jugada.getTipo()== 1){
                          total = total + jugadaController.calcularPremio(jugada);
                      }else if(jugada.getTipo() == 2){
                          if(!idRedoblona.contains(jugada.idRedoblona)){
                              idRedoblona.add(jugada.idRedoblona);
                              total = total + jugadaController.calcularPremio(jugada);
                          }
                      }
                  }               
           }
              return total;   
   }
  return total;         
}
}       
       
       
      
       
  