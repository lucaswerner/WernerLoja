/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.teste;

import br.com.fatec.loja.ConnectionFactory;
import br.com.fatec.loja.dao.CarrinhoDao;
import br.com.fatec.loja.dao.ItemDao;
import br.com.fatec.loja.modelo.Carrinho;
import br.com.fatec.loja.modelo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Lucas
 */
public class testeCarrinho {



    public static void main(String[] args) {
        
        
         String Str = new String("Welcome to Tutorialspoint.com");

      System.out.print("Return Value :" );
      System.out.println(Str.substring(10) );

      System.out.print("Return Value :" );
      System.out.println(Str.substring(10, 15) );
}
/*
         Connection connection = new ConnectionFactory().getConnection();
        
         User user = new User();
        String sql="select * from usuarios where login='lucas'";
        
        try{
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
         user.setId(rs.getLong("id"));
        }
        } catch (SQLException e) {
        throw new RuntimeException(e);
        
        }
        
        Carrinho a = new Carrinho();
        Date data = new Date(System.currentTimeMillis());
        a.setData_expiracao(data);
        
        a.setUsuarios_id(user.getId());
        
        ItemDao itens = new ItemDao();
        a.setItens(itens.getLista(user));
        
        CarrinhoDao dao = new CarrinhoDao();
        dao.finalizarCompra(a);
        
    }*/
    
}
