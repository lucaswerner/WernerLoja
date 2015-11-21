/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.dao;

import br.com.fatec.loja.ConnectionFactory;
import br.com.fatec.loja.modelo.Carrinho;
import br.com.fatec.loja.modelo.Item;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas
 */
@Repository
public class CarrinhoDao {
     private Connection connection;
    
    @Autowired    
    public CarrinhoDao(DataSource ds){
            try {
                this.connection = ds.getConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
    }
    
    public void finalizarCompra(Carrinho carrinho){
        String sql = "insert into carrinho" + " (data_expiracao, usuarios_id, total)" + " values (?,?,?)";
    
        try{
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, new Date(System.currentTimeMillis()));
            stmt.setLong(2, carrinho.getUsuarios_id());
            stmt.setDouble(3, carrinho.getTotal());
            stmt.execute();
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            rs.next();
            

            int idCarrinho = rs.getInt(1);
            
        
        
        sql = "insert into itens_carrinho" + " (item_id, carrinho_id)" + " values (?,?)";
        
         for(Item item : carrinho.getItens()){
            
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, item.getId());
            stmt.setLong(2, idCarrinho);
        
            stmt.execute();
         }
         
         stmt.close();
         
        
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        
    }
    
}
