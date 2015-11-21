/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.teste;


import br.com.fatec.loja.dao.UserDao;
import br.com.fatec.loja.modelo.User;
import java.sql.SQLException;

/**
 *
 * @author lab3aluno
 */
public class TestaAdiciona {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
     
        User user = new User();
        user.setLogin("teste@teste.com");
        user.setNome("teste");
        user.setSenha("teste");
        UserDao dao = new UserDao();
        dao.registra(user);
       
    }
    
}
