/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.controller;

import br.com.fatec.loja.dao.CarrinhoDao;
import br.com.fatec.loja.dao.ContatoDao;
import br.com.fatec.loja.dao.ItemDao;
import br.com.fatec.loja.modelo.Carrinho;
import br.com.fatec.loja.modelo.Item;
import br.com.fatec.loja.modelo.User;
import java.util.Date;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Lucas
 */
@Controller
public class CarrinhoController {

    private Carrinho carrinho = new Carrinho();
    private CarrinhoDao dao;
    private ItemDao itemdao;

    @Autowired
    public CarrinhoController(CarrinhoDao dao, ItemDao itemdao) {
        this.dao = dao;
        this.itemdao = itemdao;
    }

    @RequestMapping("/finalizarCompra")
    public String finalizar(HttpSession session) {

        User user = (User) session.getAttribute("user");

        Date data = new Date(System.currentTimeMillis());
        carrinho.setData_expiracao(data);
        carrinho.setUsuarios_id(user.getId());
        System.out.println(user.getId());

        dao.finalizarCompra(this.carrinho);
        session.setAttribute("carrinho", null);
        carrinho = new Carrinho();
        return "redirect:principal";
    }

    @RequestMapping("/adicionaItemCarrinho")
    public String adicionarItem(Item item, HttpSession session) {
        item = itemdao.carregaItem(item);
        if (item != null) {

            this.carrinho.getItens().add(item);
            this.carrinho.setTotal(this.carrinho.getTotal() + item.getValor());
            session.setAttribute("carrinho", this.carrinho);
        }
        return "redirect:revisaoCompra";
    }

    @RequestMapping("/removeItemCarrinho")
    public String removerItem(User usuario, int id, HttpSession session, HttpServletRequest request) {
        double valor = carrinho.getItens().get(id).getValor();
        carrinho.getItens().remove(id);
        carrinho.setTotal(carrinho.getTotal() - valor);
        
        String epa;
        String op[] = request.getHeader("Referer").split("/");
        if (op[op.length - 1].contains(".")) {

            epa = op[op.length - 1].substring(0, op[op.length - 1].length() - 4);
        } else {
            epa = op[op.length - 1];
        }
        if (carrinho.getItens().isEmpty()) {
            session.setAttribute("carrinho", null);
        } else {
            session.setAttribute("carrinho", this.carrinho);
        }
        return "redirect:" + epa;
    }

}
