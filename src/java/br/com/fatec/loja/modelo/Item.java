/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.modelo;

import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Lucas
 */
public class Item {
    private Long id;
    
    
    @NotEmpty
    private String nome;

    @Min(value=1)
    private double valor;
    
    @Min(value=1)
    private short quant;
    
    @NotEmpty
    private String descricao;
    
    
    private String img;
    
    
    private Long usuario_id;
    
    private Long categoria_id;    

    private MultipartFile image;
    
    private String categoria;

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }

    public Long getCategoria_id() {
        return categoria_id;
    }
    
    public void setImage(MultipartFile image) {
        this.image = image;
    }
    
    public MultipartFile getImage() {
        return image;
    }
    
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

   

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setQuant(short quant) {
        this.quant = quant;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public short getQuant() {
        return quant;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
