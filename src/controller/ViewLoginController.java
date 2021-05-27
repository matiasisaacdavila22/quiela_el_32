/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.control.Button;

import com.sun.glass.ui.Window.EventHandler;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import model.User;



/**
 * FXML Controller class
 *
 * @author holasur
 */
public class ViewLoginController implements Initializable {

    @FXML
    private Button btnEntrar;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.txtUser.requestFocus();
        
        btnEntrar.setOnKeyPressed(e->{
            switch(e.getCode()){
                case ENTER:this.login(e) ;break;
                           }
               });
        txtPassword.setOnKeyPressed(e->{
            switch(e.getCode()){
                case ENTER:this.login(e) ;break;
                           }
               });
         txtUser.setOnKeyPressed(e->{
            switch(e.getCode()){
                case ENTER:txtPassword.requestFocus();break;
                           }
               });  
          
    }
    
    private void eventKey(KeyEvent event){
        
        Object evt = event.getSource();
        
        if(evt.equals(txtUser)){
           if(event.getCharacter().equals(" ")){
              event.consume();
            }
        
        }else if(evt.equals(txtPassword)){
            if(event.getCharacter().equals(" ")){
                event.consume();
            }            
        }    
    }
    
    
     private void login(Event event) {
        loguear(event);
    }
        
        private void loguear(Event event){
        String name = txtUser.getText();
        String password = txtPassword.getText();       
        
        UserController userController = new UserController();
       if(userController.login(name, password)){
          // JOptionPane.showMessageDialog(null, name, "Bienvenido", 2);
           loadStage("/view/viewPanel.fxml", event);
       }else{
           JOptionPane.showMessageDialog(null, "error en nombre o password", "ERROR!!", 0);
       }
   }
    
    private void loadStage(String url, Event event){
                    
         try {
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
            FXMLLoader loader =new FXMLLoader(getClass().getResource(url));
            Parent root = loader.load();
            ViewPanelController controlador = loader.getController();
                       
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage stage=new Stage();
          
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.showAndWait();             
                       
                } catch (IOException ex) {
            Logger.getLogger(ViewPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void entrar(MouseEvent event) {
        this.login(event);
    }
}
