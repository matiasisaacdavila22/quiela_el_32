/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import static java.awt.Color.GREEN;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Caja;
import model.Boleta;
import model.Movimiento;

/**
 * FXML Controller class
 *
 * @author holasur
 */
public class ViewCajaController implements Initializable {

  
    @FXML
    private Button btnModificar;
    @FXML
    private Text lblFecha;
    private CajaController cajaController;
    private BoletaController boletaController;
    @FXML
    private Text lblCaja_inicial;
    @FXML
    private Text lblCaja_maniana;
    @FXML
    private Text lblCaja_tarde;
    @FXML
    private Text lblCaja_final;
    @FXML
    private AnchorPane panel;

    @FXML
    private TableView<Movimiento> tblMovimientos;
    @FXML
    private TableColumn<?, ?> colHora;
    @FXML
    private TableColumn<?, ?> colDescripcion;
    @FXML
    private TableColumn<?, ?> colMonto;
    @FXML
    private Text lblTotalMovimientos;
    public Caja caja;
    private MovimientoController movimientoController;
    private ObservableList<Movimiento>movimientos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cajaController = new CajaController();
        this.boletaController = new BoletaController();
        this.movimientoController = new MovimientoController();
        this.movimientos = FXCollections.observableArrayList();
        this.mostrarCaja();
        
       this.colHora.setCellValueFactory(new PropertyValueFactory("fecha"));
       this.colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
       this.colMonto.setCellValueFactory(new PropertyValueFactory("monto"));
              
     panel.setOnKeyReleased((javafx.scene.input.KeyEvent e) -> {
      switch(e.getCode()){  
      case ESCAPE : 
              this.cerrarVentana(); break;         
                    } 
           }
    );  
    }
    
   public java.sql.Date fechaActual(){
     java.util.Date fecha = new java.util.Date();
     java.sql.Date fechaSql=new java.sql.Date(fecha.getTime());
     return fechaSql;
 }

   public void mostrarCaja(){
       java.sql.Date fechaActual = this.fechaActual();
       this.lblFecha.setText(this.fecha());
       this.caja = this.cajaController.buscarCaja(fechaActual);
       Double caja_inicial = this.caja.getCaja_inicial();
       Double caja_maniana = this.caja(fechaActual,"00:00:00", "14:30:00");
       Double caja_tarde = this.caja(fechaActual,"14:30:00", "21:00:00");
       Double totalmovimientos = this.totalMovimientos();
       Double caja_final = caja_inicial + caja_maniana +caja_tarde + totalmovimientos;
       this.paintTotalMovimientos(totalmovimientos);
       this.lblCaja_inicial.setText(Double.toString(caja_inicial));
       this.lblCaja_maniana.setText(Double.toString(caja_maniana));
       this.lblCaja_tarde.setText(Double.toString(caja_tarde));
       this.lblCaja_final.setText(Double.toString(caja_final));
       
       this.mostrarMovimientos();
   }
//this.jugadas.stream().forEach((n)->jugadaController.insertar(n));
    private Double caja(Date fechaActual, String hora_inicio, String hora_final) {
        ArrayList<Boleta> boletas = new ArrayList();
        boletas = this.boletaController.consultarFecha(fechaActual, hora_inicio, hora_final);
        Double total = 0.0;
        for(Boleta boleta : boletas){
            total = total + boleta.getTotal();
        }
        System.out.println("el total es :"+total);
        return total;
        
    }
    public String fecha() {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormato = formatoFecha.format(fecha);
        return fechaFormato;
    }   
    
    private void cerrarVentana() {
    Node source = (Node) panel;
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
}

    @FXML
    private void modificarCaja(MouseEvent event) {
        this.openFormMovimiento();
    }

    private void openFormMovimiento(){
         try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/viewMovimiento.fxml")); 
            Parent root = loader.load();
            ViewMovimientoController controlador = loader.getController();
            controlador.caja = this.caja;
            
              Scene scene= new Scene(root);
              Stage stage= new Stage();
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
              this.mostrarCaja();                        
        } catch (IOException ex) {
            Logger.getLogger(ViewCajaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
private Double totalMovimientos(){
    Double total = 0.0;
   
    Date fecha = this.fechaActual();
    this.movimientos = movimientoController.movimientos(fecha);
    for(Movimiento movimiento : movimientos){
        if(movimiento.isEstado()){
        total = total + movimiento.getMonto();
    }else if (!movimiento.isEstado()){
        total = total - movimiento.getMonto();
    }
    }
    return total;
}

private void mostrarMovimientos(){
    this.tblMovimientos.setItems(this.movimientos);
    this.paintTable(); 
   
}
private void paintTable(){
              
    this.tblMovimientos.setRowFactory(row -> new TableRow<Movimiento>(){
   
    @Override
    public void updateItem(Movimiento item, boolean empty){
        super.updateItem(item, empty);

        if (!isEmpty()) {
            if(item.isEstado()){
               setStyle("-fx-background-color: #1CA261;");
            }else{
                   setStyle("-fx-background-color: #DC4E41");
                }
            }
            
        }
    });
  }
private void paintTotalMovimientos(Double totalMovimientos){
           if(totalMovimientos > 0){
           this.lblTotalMovimientos.setText(totalMovimientos.toString());
           this.lblTotalMovimientos.setStyle("-fx-text-fill: #DC4E41;-fx-background-color: green;");
           
       }else{
           this.lblTotalMovimientos.setText(totalMovimientos.toString());
           this.lblTotalMovimientos.setStyle("-fx-text-fill: #DC4E41;-fx-background-color: red;");
           
       }
} 
        
}
