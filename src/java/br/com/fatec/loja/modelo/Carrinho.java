/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.modelo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class Carrinho {
    private List<Item> itens = new ArrayList<Item>();
    
    private Long id;
    
    private double total;
    
    private boolean pago;
    
    private Date data_expiracao;
    
    private Long usuarios_id;

    public void setUsuarios_id(Long usuarios_id) {
        this.usuarios_id = usuarios_id;
    }

    public Long getUsuarios_id() {
        return usuarios_id;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public void setData_expiracao(Date data_expiracao) {
        this.data_expiracao = data_expiracao;
    }

    public Long getId() {
        return id;
    }

    public boolean isPago() {
        return pago;
    }

    public Date getData_expiracao() {
        return data_expiracao;
    }

    public List<Item> getItens() {
        return itens;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
    
}
