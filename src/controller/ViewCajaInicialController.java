/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Caja;

/**
 * FXML Controller class
 *
 * @author holasur
 */
public class ViewCajaInicialController implements Initializable {

    @FXML
    private Button btnAceptar;
    @FXML
    private TextField txtMonto;
    private CajaController cajaController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cajaController = new CajaController();
        this.txtMonto.setOnKeyPressed(e->{
            switch(e.getCode()){
                case ENTER:this.createCaja(this.txtMonto.getText(), e);break;
                           }
               });
    } 
    
    private void createCaja(String monto, Event event) {
        Double caja_inicial = Double.parseDouble(monto); 
        Caja caja = new Caja(caja_inicial);
        if(this.cajaController.createCaja(caja)){
             Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
             stage.close();
        }
    }
    
    @FXML
    private void createCaja(MouseEvent event) {
        if(!this.txtMonto.getText().isEmpty()){
            if(esDouble(this.txtMonto.getText())){
               this.createCaja(this.txtMonto.getText(), event);              
            }
        }
    }        
    
public boolean esDouble(String cad){
    try{
     Double.parseDouble(cad);
     return true;
    }
    catch(NumberFormatException nfe){
     return false;
    }
    }
}
