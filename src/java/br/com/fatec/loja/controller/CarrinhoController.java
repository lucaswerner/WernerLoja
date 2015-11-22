/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.controller;

import br.com.fatec.loja.Email;
import br.com.fatec.loja.dao.CarrinhoDao;
import br.com.fatec.loja.dao.ItemDao;
import br.com.fatec.loja.dao.Mensagem;
import br.com.fatec.loja.modelo.Carrinho;
import br.com.fatec.loja.modelo.Item;
import br.com.fatec.loja.modelo.User;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView finalizar(HttpSession session) {

        User user = (User) session.getAttribute("user");

        Date data = new Date(System.currentTimeMillis());
        carrinho.setData_expiracao(data);
        carrinho.setUsuarios_id(user.getId());
        System.out.println(user.getId());

        dao.finalizarCompra(this.carrinho);
        
        Email email = new Email();
            email.setDestinatario(user.getLogin());
            email.setAssunto("WernerLoja");
            email.setTitulo("Compra realizada com sucesso");
            String texto="<p>Olá "+user.getLogin()+", você finalizou uma compra no meu site.</p>"
                    + "<p>Segue abaixo o(s) produto(s) escolhido:</p>";
            
            String itens = "<ul>";
            for(Item a : this.carrinho.getItens()){
                itens += "<li>"+a.getNome()+"</li>";
            }
            itens +="</ul> <p>Bom, todos os produtos são ficticios, mas fico feliz que tenha usado meu site =D.</p>";
            
             email.setTexto(texto+itens);
            
            email.enviar(email);

            Mensagem msg = new Mensagem();
            msg.setTexto("Compra realizada com sucesso!!!!!11!!!1!");
            ModelAndView mv = new ModelAndView("item/revisaoCompra");
            mv.addObject("msg", msg);
        
        
        session.setAttribute("carrinho", null);
        carrinho = new Carrinho();
        return mv;
        
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
    
    @RequestMapping("/historicoCompras")
    public ModelAndView historicoCompras(HttpSession session) throws ParseException {
        User usuario = new User();
        usuario = (User) session.getAttribute("user");
        Carrinho carrito = new Carrinho();
        carrito.setUsuarios_id(usuario.getId());
        
        List<Carrinho> compras;
        compras = dao.historicoCompras(carrito);

        for(Carrinho a : compras){
            System.out.println("\nCarrinho ");
            for(Item itens : a.getItens()){
             System.out.println("\nItem:" + itens.getNome());
            }
             System.out.println("\nTotal:" + a.getTotal());
        }
        
        
        
        ModelAndView mv = new ModelAndView("user/historicoCompras");
        mv.addObject("compras", compras);
        
        return mv;
    }
    

}
