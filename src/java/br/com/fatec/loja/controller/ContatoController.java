/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.controller;



import br.com.fatec.loja.dao.ContatoDao;
import br.com.fatec.loja.modelo.Contato;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatoController {
    
    private ContatoDao dao;
    
    @Autowired
    public ContatoController(ContatoDao dao){
        this.dao = dao;
    }
    
    @RequestMapping("/listaContatos")
    public ModelAndView listaContatos(){
        List<Contato> contatos = dao.getLista();
        
        ModelAndView mv = new ModelAndView("contato/lista");
        mv.addObject("contatos", contatos);
        
        return mv;
    }
    

    @RequestMapping(value="/contatoForm", method = RequestMethod.GET)
    public String contatoForm(Map<String, Object> model) {
        Contato contato = new Contato();
        model.put("contatoForm", contato);
        return"contato/contatoForm";
    }    
    
    
    @RequestMapping(value="/adicionaContato")
    public String adiciona(@Valid @ModelAttribute("contatoForm") Contato contatoForm,
            BindingResult result, Map<String, Object> model){
        if(result.hasErrors()){
            return "contato/contatoForm";
        }
        
        dao.adiciona(contatoForm);
        return"redirect:listaContatos";
    }
    
    
    @RequestMapping("/removeContato")
    public String remover(Contato contato){
        dao.remove(contato);
        return"redirect:listaContatos";
    }
    
}

