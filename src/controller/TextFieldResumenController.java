/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.TextFieldResumen;


/**
 *
 * @author yo
 */
public class TextFieldResumenController {
    
  public void setTextField(ArrayList<TextFieldResumen> textResumenSimple, int id){
        for(TextFieldResumen tf : textResumenSimple){ 
            if(tf.getIdTextField() != id){
                tf.setStado(false);
             }else{
                tf.setStado(true);
             }
        }
      
    }
  
    public void setTextField(ArrayList<TextFieldResumen> textResumen){
        for(TextFieldResumen tf : textResumen){ 
          
             System.out.println("la posicion del testfield es :"+tf.getIdTextField());
          
        }
    }
    
      public int searchTextField(ArrayList<TextFieldResumen> textResumen, int id){
      for(int i = 0; i < textResumen.size(); i++){
          if(textResumen.get(i).getIdTextField() == id){
              return i;
             }
          }
        return 100;
      }  
}
