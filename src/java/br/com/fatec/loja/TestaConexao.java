/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lab3aluno
 */
public class TestaConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();
        String a = "Instrumentos";
        String sql = "insert into categoria" + "(nome)" + " values (?)";
        try{
            
           
               PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1,a);
                

                stmt.execute();
                stmt.close();
            
     
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
}
