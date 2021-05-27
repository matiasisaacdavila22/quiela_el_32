/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

                                            // shift+0 quita el puntero negro
package model;


import javafx.scene.control.TextField;


public class TextFieldResumen extends TextField {
    private boolean stado;
    private int idTextField;


    public TextFieldResumen(int id, String text) {
        super(text);
        this.idTextField = id;
      }   

    public int getIdTextField() {
        return idTextField;
    }

    public void setIdTextField(int idTextField) {
       this.idTextField = idTextField;
    }


    
    public void setStado(){
        if(this.stado){
          setStado(false);      
             this.setStyle("-fx-background-color: #FFFFFF;-fx-font-size: 16pt;");
        }else{
            setStado(true);    
        this.setStyle("-fx-background-color: #BAFF9B; -fx-font-size: 16pt;");
        }
    }

 

    public boolean isStado() {
        return stado;
    }

    public void setStado(boolean stado) {
        this.stado = stado;
        if(stado){
            this.setStyle("-fx-background-color: #BAFF9B; -fx-font-size: 16pt;");
        }else{
            this.setStyle("-fx-background-color: #FFFFFF;-fx-font-size: 16pt;");
        }
    }
    
    
}