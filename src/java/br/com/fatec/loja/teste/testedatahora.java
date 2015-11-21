/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.teste;

import br.com.fatec.loja.dao.UserDao;
import br.com.fatec.loja.modelo.User;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Lucas
 */
public class testedatahora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  
SimpleDateFormat horaformatada = new SimpleDateFormat("HH:mm:ss");  
SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");    
Calendar cal = Calendar.getInstance();

String grava = horaformatada.format(cal.getTime()) + data.format(cal.getTime());  
    System.out.println(grava);
    
    User usuario = new User();
    UserDao dao = new UserDao();
    dao.novaSenha(usuario);
    }
    
}
