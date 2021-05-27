 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import model.User;
import utils.Conexion;

/**
 *
 * @author yo
 */
public class UserController {
    
   private Conexion mysql=new Conexion();
   private Connection cn = mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
  
   
        public ObservableList<User>getUser(){
         ObservableList<User> obs = FXCollections.observableArrayList();
          sSQL="select * from user";
        try {
               Statement st= cn.createStatement();
               ResultSet rs=st.executeQuery(sSQL);
               
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int role = rs.getInt("rol_id");
                
                User user = new User(id, name, password, role);
                obs.add(user);
            }
             cn.close();
             return obs;
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
           }
        return obs;
     }
      
     
public boolean  login (String user, String password){
        
         ObservableList<User> obs = FXCollections.observableArrayList();
         sSQL = "SELECT * FROM user WHERE BINARY name='"+user+"' AND password='"+password+"'";       
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               String role =rs.getString("rol_id");

              System. out. println(role);
               cn.close();
               return true;
           }
           cn.close();
           return false;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
   
   public boolean insertar (User user){
       sSQL="insert into user (name, password, rol_id)" +
               "values (?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, user.getName());
           pst.setString(2, user.getPassword());
           pst.setInt(3, user.getRole());        
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }           
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
   
   public boolean editar (User user){
       sSQL="update user set name=?,password=?,rol_id=?"+
               " where id=?";
                 
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, user.getName());
           pst.setString(2, user.getPassword());
           pst.setInt(3, user.getRole());
                     
           pst.setInt(4, user.getId());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
  
   public boolean eliminar (User user){
       sSQL="delete from user where id=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, user.getId());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   } 
}
