/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.controller;

import br.com.fatec.loja.dao.ItemDao;
import br.com.fatec.loja.modelo.Item;
import br.com.fatec.loja.modelo.User;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController implements ServletContextAware {

    private ServletContext servletContext;
    private User user = new User();
    private ItemDao dao;

    @Autowired
    public ItemController(ItemDao dao) {
        this.dao = dao;
    }

    @RequestMapping("/listaItens")
    public ModelAndView listaItens(HttpSession session) {
        User us = (User) session.getAttribute("user");

        List<Item> itens = this.dao.getLista(us);

        ModelAndView mv = new ModelAndView("item/lista");
        mv.addObject("itens", itens);
        return mv;
    }

    @RequestMapping("/principal")
    public ModelAndView principal(String id) {

        List<Item> itens;
        if (id == null) {
            itens = this.dao.tudo();
        } else {
            itens = this.dao.listaCategoria(Integer.parseInt(id));
        }

        ModelAndView mv = new ModelAndView("item/principal");
        mv.addObject("itens", itens);
        return mv;
    }

    @RequestMapping("/produto")
    public ModelAndView produto(Item a) {

        Item item = this.dao.carregaItem(a);

        ModelAndView mv = new ModelAndView("item/produto");
        mv.addObject("item", item);
        return mv;
    }

    @RequestMapping(value = "/itemForm", method = RequestMethod.GET)
    public String itemForm(Map<String, Object> model) {
        Item item = new Item();
        model.put("itemForm", item);
        return "item/itemForm";
    }

    @RequestMapping(value = "/adicionaItem")
    public String adiciona(@Valid @ModelAttribute("itemForm") Item itemForm,
            BindingResult result, HttpSession session,
            Map<String, Object> model) throws IOException {
        if (result.hasErrors()) {
            return "item/itemForm";
        }

        this.user = (User) session.getAttribute("user");

        if (!itemForm.getImage().isEmpty()) {

            try {
                this.validateImage(itemForm.getImage());

            } catch (RuntimeException re) {
                result.reject(re.getMessage());
                return "item/itemForm";
            }

            itemForm.setImg(user.md5(momento()));

            try {
                saveImage(itemForm.getImg(), itemForm.getImage());
            } catch (IOException e) {
                result.reject(e.getMessage());
                return "item/itemForm";
            }
        } else {
            itemForm.setImg("box");
        }

        this.dao.adiciona(itemForm, user);

        return "redirect:listaItens";
    }

    private void saveImage(String filename, MultipartFile image)
            throws RuntimeException, IOException {
        try {
            File file = new File("C:/Users/Lucas/Documents/NetBeansProjects/WernerLoja/web/resources/images/"
                    + filename + ".jpg");

            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            throw e;
        }
    }

    private void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/png") && !image.getContentType().equals("image/jpeg") && !image.getContentType().equals("image/jpg") && !image.getContentType().equals("image/jpe") && !image.getContentType().equals("image/gif") && !image.getContentType().equals("image/bmp")) {
            throw new RuntimeException("Only JPG images are accepted");
        }
    }

    @RequestMapping("/removeItem")
    public String remover(Item item) {

        dao.remove(item);
        return "redirect:listaItens";
    }

    @RequestMapping("/itemAltera")
    public ModelAndView itemAltera(Item item, HttpSession session, Map<String, Object> model) {
        User us = (User) session.getAttribute("user");

        item = dao.carregaItem(item, us);

        /*System.out.println("ID carregado: "+ item.getId()+
         "\n nome : "+ item.getNome()+
         "\n descricao: "+ item.getDescricao()+
         "\n quantidade: "+ item.getQuant());
         */
        if (item == null) {
            return new ModelAndView("redirect:listaItens");
        } else {
            ModelAndView mv = new ModelAndView("item/itemAltera");
            mv.addObject("item", item);
            model.put("itemForm", item);

            /* System.out.println("ID carregado: "+ item.getId()+
             "\n nome : "+ item.getNome()+
             "\n descricao: "+ item.getDescricao()+
             "\n quantidade: "+ item.getQuant());
             */
            return mv;
        }

    }

    @RequestMapping(value = "/alterando")
    public String altera(@Valid @ModelAttribute("itemForm") Item itemForm,
            BindingResult result,
            Map<String, Object> model) {

        if (result.hasErrors()) {

            return "redirect:itemAltera?id=" + itemForm.getId();

        }

        if (!itemForm.getImage().isEmpty()) {

            try {
                this.validateImage(itemForm.getImage());

            } catch (RuntimeException re) {

                result.reject(re.getMessage());
                return "redirect:itemAltera?id=" + itemForm.getId();
            }

            itemForm.setImg(user.md5(momento()));

            try {
                saveImage(itemForm.getImg(), itemForm.getImage());
            } catch (IOException e) {
                return "redirect:itemAltera?id=" + itemForm.getId();
            }
        }

        dao.altera(itemForm);
        return "redirect:listaItens";
    }

    public String momento() {
        SimpleDateFormat horaformatada = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        return horaformatada.format(cal.getTime()) + data.format(cal.getTime());
    }

    @RequestMapping("/revisaoCompra")
    public String revisarCompra() {
        return "item/revisaoCompra";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

}
