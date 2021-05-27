/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.HeadlessException;
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
import utils.Conexion;

/**
 *
 * @author oXCToo
 */
public class User {

    private int id;

    private String name;

    private String password;

    private int role;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public int getRole ()
    {
        return role;
    }

    public void setRole(int role)
    {
        this.role = role;
    }
    
    public User(String name, String password, int role) {
        this.name = name;
        this.password = password;
        this.role = role;
       
    }
    
     public User(int id, String name, String password, int role) {
        this.id =id;
        this.name = name;
        this.password = password;
        this.role = role;
       
    }     
     
  }
   