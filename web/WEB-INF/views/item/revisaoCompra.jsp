<%@page import="br.com.fatec.loja.modelo.Carrinho"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../cabecalho.jsp"/>

<style>
    .colunaImagem{
        max-width: 20px;
        height: 50px;
    }

    .imagemOcupa{
        height:98%;
        width:98%;

    }

</style>



<body>
    <% Carrinho carrinho = new Carrinho();
        carrinho = (Carrinho) session.getAttribute("carrinho");

        if (carrinho != null) {
    %>

    <div class="page-header">
        <h1>Meu carrinho: revisão</h1>
        <p class="lead">Confirme para finalizar sua compra.</p>
        <p class="lead">${msg}</p>
    </div>
    <a href="principal">
        <img data-src='<c:url value="/resources/images/voltar.png"/>'  width="24" height="24"><strong>Continuar comprando</strong>
    </a>
    <table class="table table-striped">
        <thead>
            <tr>
                <th></th>
                <th>Item</th>
                <th>Quantidade</th>
                <th>Preço</th>
                <th>Opção</th>
            </tr>
        </thead>
        <tbody>

            <c:forEach var="item" items="${carrinho.itens}" varStatus="id">
                <tr>
                    <th scope="row" class="colunaImagem">
                        <a href="produto?id=${item.id}">
                            <img data-src='<c:url value="/resources/images/${item.img}.jpg"/>'  class="img-rounded imagemOcupa" style="margin:5px;">
                        </a>
                    </th>
                    <td><p class="form-control-static"><a class="infos" href="produto?id=${item.id}">${item.nome}</a></p></td>
                    <td><p class="form-control-static">${item.quant}x</p></td>
                    <td><p class="form-control-static" style="font-size:18px;"><strong>R$ ${item.valor}</strong></p></td>
                    <td><form action="removeItemCarrinho" />
                        <input type="hidden" name="pagina" value="<%=request.getAttribute("pagina")%>" />
                        <input type="hidden" name="id" value="${id.count-1}" />
                        <button type="submit" class="btn btn-default" 
                                style="width: 90px;
                                font-size: 10px;
                                height: 30px;
                                border: none;
                                padding-top: 0px;
                                padding-bottom: 0px;
                                padding-left: 7px;">
                            <img src='<c:url value="/resources/images/delete.png"/>' width="24" height="24">
                            Remover
                        </button>
                        </form>
                    </td> 

                </tr>
            </c:forEach>
        <td></td>
        <td></td>
        <td></td>
        <td><strong>Total: </strong>R$${carrinho.total}</td>
        <td>
            <form action="finalizarCompra">
                <button type="submit" class="btn btn-primary" 
                        style="
                        height: 30px;
                        border: none;
                        padding-top: 0px;
                        padding-bottom: 0px;
                        padding-left: 7px;
                        background-color: rgb(230, 135, 0);
                        border-color: rgb(230, 135, 0);
                        ">
                    Finalizar Compra
                </button>
            </form>
        </td>
    </tbody>
</table>
<%} else {%>
<div class="page-header">
    <%if(request.getAttribute("msg")!=null){%>
    <p class="lead">${msg.getTexto()}</p>
    <%}else{%>
    <p class="lead">Seu carrinho está vazio.</p>
    <%}%>
</div>
<%}%>

<c:import url="../rodape.jsp"/>