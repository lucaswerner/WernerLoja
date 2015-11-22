<%@page import="br.com.fatec.loja.modelo.Item"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../cabecalho.jsp"/>

<style>
      .imagemOcupa{
        height:98%;
        width:98%;

    }
</style>

<div class="page-header">
    <h1>Meus itens</h1>
</div>

<% List<Item> MeusItens = (List<Item>) request.getAttribute("itens");
    if (MeusItens.isEmpty()) { %>
<p>Você tem nenhum item anunciado</p>

<%} else {%>
<!-- percorre contatos montando as linhas da tabela -->
<c:forEach var="item" items="${itens}" varStatus="id">
    <form class="form-horizontal" >
        <div class="form-group">
            <div class="col-sm-3">
                <img data-src='<c:url value="/resources/images/${item.img}.jpg"/>'  class="img-rounded imagemOcupa">
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <div class="col-sm-3">
                        <p class="form-control-static"><strong>${item.nome}</strong></p>
                    </div>
                    <div class="col-sm-6"></div>
                    <div class="col-sm-3">
                        <p class="form-control-static">R$ ${item.valor}</p>
                    </div>
               
                
                    <div class="col-sm-9">
                        <p class="form-control-static">Categoria: ${item.categoria}</p>
                    </div>
                    <div class="col-sm-3">
                        <p class="form-control-static">Disponível: ${item.quant}</p>
                    </div>
                    <div class="col-sm-12">
                        <p class="form-control-static">Descrição:</p>
                    </div>
                    <div class="col-sm-12">
                        <p class="form-control-static">${item.descricao}</p>
                    </div>
                 </div>
            </div>
            
            <div class="col-sm-3">
                
                <div class="form-group">
                    <br>
                    
                    <div class="col-sm-6">
                        <a href="itemAltera?id=${item.id}" class="btn" role="button">
                            <img src='<c:url value="/resources/images/editMode.png"/>' width="24" height="24">Alterar
                        </a>
                    </div>
                    <div class="col-sm-6">
                        <a href="removeItem?id=${item.id}" class="btn" role="button">
                            <img src='<c:url value="/resources/images/delete.png"/>' width="24" height="24">Remover
                        </a>
                    </div>
                </div>
            </div>
           

        </div>


    </form>
    <hr>
</c:forEach>

<%}%>


<c:import url="../rodape.jsp"/>
