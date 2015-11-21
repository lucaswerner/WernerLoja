/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.teste;import br.com.fatec.loja.dao.UserDao;


import br.com.fatec.loja.modelo.User;

/**
 *
 * @author Lucas
 */
public class testeUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User usuario = new User();
        usuario.setLogin("lucas@lucas.com");
        
        
        UserDao dao = new UserDao();
        if(dao.existeUser(usuario)){
            System.out.println("FOI");
        }else{
            System.out.println("FOsfsfaI");
        }
    }
    
}
