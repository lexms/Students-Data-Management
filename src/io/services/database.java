/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author AlexanderMS
 */
public class database {
    private static final String link = "jdbc:mysql://localhost/kemahasiswaan_10116347_10116370";
    private static final String user = "root";
    private static final String password = "";
    private static Connection conn;
    
    private database(){    
    }
    
    public static Connection getConnection(){
        try {
            conn = DriverManager.getConnection(link, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR : " + ex.getMessage() );
        }
        
        return conn;
    }
    
    
}
