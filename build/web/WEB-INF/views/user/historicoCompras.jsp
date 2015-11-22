<%@page import="br.com.fatec.loja.modelo.User"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:import url="../cabecalho.jsp"/>


<div class="page-header">
    <p class="lead">Histórico de compras :</p>
</div>

<c:forEach var="carrinho" items="${compras}" varStatus="id">
    <table class="table">
        <caption>${carrinho.data_expiracao}</caption>
        <thead>
            <tr>
                <th>Item</th>
                <th>Quantidade</th>
                <th>Preço</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${carrinho.itens}" varStatus="id">
                <tr>
                    <td><p class="form-control-static"><a class="infos" href="produto?id=${item.id}">${item.nome}</a></p></td>
                    <td><p class="form-control-static">1x</p></td>
                    <td><p class="form-control-static" style="font-size:18px;"><strong>R$ ${item.valor}</strong></p></td>
                </tr>
            </c:forEach>
        <td></td>
        <td></td>
        <td></td>
        <td><strong>Total: </strong>R$${carrinho.total}</td>


    </table>
</c:forEach>

<c:import url="../rodape.jsp"/>