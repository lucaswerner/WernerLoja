/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.controller;

import br.com.fatec.loja.Email;
import br.com.fatec.loja.dao.Mensagem;
import br.com.fatec.loja.dao.UserDao;
import br.com.fatec.loja.modelo.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private UserDao dao;

    @Autowired
    public UserController(UserDao dao) {
        this.dao = dao;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, User user) {
        session.setAttribute("user", null);
        return "redirect:" + user.getPagina();
    }

    @RequestMapping("/loginForm")
    public ModelAndView formulario(HttpServletRequest request) {
        System.out.println(request.getAttribute("pagina"));
        ModelAndView mv = new ModelAndView("user/login");

        mv.addObject("msg", request.getAttribute("msg"));
        mv.addObject("pagina", request.getAttribute("pagina"));

        return mv;
    }

    @RequestMapping("/efetuaLogin")
    public String efetuaLogin(User usuario, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("pagina", usuario.getPagina());
        usuario = dao.validaUser(usuario);
        if (usuario == null) {

            request.getRequestDispatcher("loginForm").forward(request, response);
        } else {
            session.setAttribute("user", usuario);
            return ("redirect:" + usuario.getPagina());
        }

        //mv.addObject("pagina", usuario.getPagina());
        return "loginForm";
    }

    @RequestMapping("/recuperarSenha")
    public ModelAndView recuperarSenha(User usuario, HttpServletRequest request, HttpServletResponse response, Mensagem msg) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView("user/login");
        if (dao.existeUser(usuario)) {
            String senha = dao.novaSenha(usuario);
            msg.setTexto("Sua nova senha foi enviada pelo email!");
            Email email = new Email();
            email.setDestinatario(usuario.getLogin());
            email.setAssunto("WernerLoja");
            email.setTitulo("Reset de senha");
            email.setTexto("<p>Olá, você solicitou o reset de senha? Segue a nova: <strong>" + senha + "</strong></p>");
            email.enviar(email);

            mv.addObject("msg", msg);
            return mv;

        } else {
            msg.setTexto("Conta não registrada");
            mv.addObject("msg", msg);
            return mv;
        }

    }

    @RequestMapping("/cadastraUser")
    public String cadastrar(User usuario, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (dao.existeUser(usuario)) {
            Mensagem msg = new Mensagem();
            msg.setTipo("warnning");
            msg.setTexto("Email já registrado");
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("loginForm").forward(request, response);
            return "redirect:loginForm";

        } else {
            dao.registra(usuario);
            usuario.setSenha(null);
            session.setAttribute("user", usuario);
            return "redirect:principal";

        }

    }

    @RequestMapping("/alteraUser")
    public String alterar(User usuario, String novaSenha, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!dao.verificaLoginId(usuario)) {
            return "redirect:loginForm?erro=1";
        } else if (!dao.senhasIguais(usuario)) {
            return "redirect:loginForm?erro=2";
        } else {
            usuario.setSenha(novaSenha);
            dao.altera(usuario);
            session.setAttribute("user", usuario);
            return "redirect:loginForm";
        }

    }

}
