/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.dao;

/**
 *
 * @author Lucas
 */
public class Mensagem {
    private String tipo;
    private String texto;

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTexto() {
        return texto;
    }
    
    
}
