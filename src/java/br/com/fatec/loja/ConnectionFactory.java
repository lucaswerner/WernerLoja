/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            String url = "jdbc:mysql://localhost:3306/wernerloja";
            String usuario = "root";
            String senha = "senha";
            try{
            return DriverManager.getConnection(url,usuario,senha);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        
    }
}

