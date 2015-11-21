/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package br.com.fatec.loja.teste;

import br.com.fatec.loja.ConnectionFactory;
import br.com.fatec.loja.dao.ItemDao;
import br.com.fatec.loja.modelo.Item;
import br.com.fatec.loja.modelo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class testaItem {
}
    /*
     
    public static void main(String[] args) {
        Item item = new Item();
        User user = new User();
       
        item.setDescricao("Ruim sujo e caro");
        
        Short numero = 40;
        item.setQuant(numero);
        item.setValor(120.5);
        item.setNome("Tênis");
        Connection connection = new ConnectionFactory().getConnection();
        String sql="select * from usuarios where login='lucas'";
        try{
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
         user.setId(rs.getLong("id"));
        }
        System.out.println(user.getId());
        ItemDao dao = new ItemDao();
        item = dao.carregaItem(item, user);
        item.setNome("Livro do Eric");
        dao.altera(item);
        
        
        } catch (SQLException e) {
        throw new RuntimeException(e);
        
        }
        
        
        
    }
    
}
   
/* for(Item opa : itens){
           System.out.println("nome: "+opa.getNome());
           System.out.println("descricao: "+opa.getDescricao());
           System.out.println("valor: "+opa.getValor());
           System.out.println("Quantidade: "+opa.getQuant());
        }

*/