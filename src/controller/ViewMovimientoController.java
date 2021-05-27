/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Movimiento;
import model.Caja;
/**
 * FXML Controller class
 *
 * @author holasur
 */
public class ViewMovimientoController implements Initializable {

    @FXML
    private TextField txtMonto;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnRetirar;
    @FXML
    private TextArea txtaMotivo;
    @FXML
    private AnchorPane panel;
    private boolean estado;
    public Caja caja;
    private MovimientoController moviController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            this.moviController = new MovimientoController();
            
        this.txtMonto.setOnKeyPressed((KeyEvent e)->{
         switch(e.getCode()){
          case ENTER:this.txtaMotivo.requestFocus();break;}
               });
    }    

    @FXML
    private void agregarMonto(MouseEvent event) {
        this.estado = true;
        this.btnRetirar.setVisible(false);
        this.createMovimiento();
    }

    @FXML
    private void retirarMonto(MouseEvent event) {
        this.estado = false;
         this.btnAgregar.setVisible(false);
         this.createMovimiento();
    }

    @FXML
    private void createMovimiento() {
    
        Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("ALERTA");
                                alerta.setContentText("LOS CAMBIOS MODIFICARAN LA CAJA!!");
                       
               if (alerta.showAndWait().get() == ButtonType.OK){
                        
        if(this.isNumerico(this.txtMonto.getText())){
           Double monto = Double.parseDouble(this.txtMonto.getText()); 
           if(monto > 0){
           Movimiento movi = new Movimiento(caja.getId(), monto, this.txtaMotivo.getText(), this.estado);
           this.moviController.createMovimiento(movi);
           this.cerrarVentana();
        }else if(monto <= 0){
            Alert alerta2=new Alert(Alert.AlertType.CONFIRMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("ALERTA");
                                alerta.setContentText("El numero ingresado no es valido");
                                this.txtMonto.requestFocus();
                                         this.btnAgregar.setVisible(true);   
                                         this.btnRetirar.setVisible(true); 
        }
      }else{
             Alert alerta2=new Alert(Alert.AlertType.CONFIRMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("ALERTA");
                                alerta.setContentText("El numero ingresado no es valido");
                                this.txtMonto.requestFocus();
                                         this.btnAgregar.setVisible(true);   
                                         this.btnRetirar.setVisible(true); 
        }
     }else{
         this.btnAgregar.setVisible(true);   
         this.btnRetirar.setVisible(true); 
               }      
    } 
private static boolean isNumerico(String cadena){
	try {
		Double.parseDouble(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}

    private void cerrarVentana() {
    Node source = (Node) panel;
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
}
}
