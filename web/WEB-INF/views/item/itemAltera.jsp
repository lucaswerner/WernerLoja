<%@page import="br.com.fatec.loja.modelo.Item"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:import url="../cabecalho.jsp"/>

<div class="page-header">
    <h1>Alterando item</h1>
    <p class="lead">Item:<strong> ${item.nome}</strong></p>
</div>

<form:form method="POST" class="form-horizontal" modelAttribute="itemForm" action="alterando" enctype="multipart/form-data">
    <form:hidden path="id" />
    <form:hidden path="img" />

    <div class="form-group">
        <label for="uploadBtn" class="col-sm-2 control-label">Imagem</label>
        <div class="col-sm-3">
            <img data-src='<c:url value="/resources/images/${item.img}.jpg"/>'  class="img-rounded" style="
                 width: 100%;
                 height: 100%;
                 ">
        </div>
        <div class="col-sm-7">
            <input id="uploadFile" placeholder="png,jpeg,jpg,jpe,gif ou bmp" disabled="disabled" />
            <div class="fileUpload btn btn-default">
                <span>Upload</span>
                <form:input path="image" id="uploadBtn" type="file" class="upload" />
            </div>
        </div>

    </div>

    <div class="form-group">
        <label for="nomeItem" class="col-sm-2 control-label">Nome</label>
        <div class="col-sm-7">
            <form:input class="form-control" path="nome" id="nomeItem"/>
        </div>
        <div class="col-sm-3">
            <form:errors path="nome" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <label for="descricaoItem" class="col-sm-2 control-label">Descrição</label>
        <div class="col-sm-7">
            <form:textarea class="form-control" path="descricao" id="descricaoItem" ></form:textarea>
            </div>
            <div class="col-sm-3">
            <form:errors path="descricao" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <label for="valorItem" class="col-sm-2 control-label">Valor</label>
        <div class="col-sm-7">
            <form:input class="form-control" path="valor" type="number" min="1" id="valorItem" />
        </div>
        <div class="col-sm-3">
            <form:errors path="valor" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <label for="quantItem" class="col-sm-2 control-label">Quantidade</label>
        <div class="col-sm-7">
            <form:input class="form-control" path="quant" min="1" type="number" id="quantItem" />
        </div>
        <div class="col-sm-3">
            <form:errors path="quant" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-2 control-label">
            <label for="cboSelect">Categoria</label>
        </div>
        <fieldset>


            <div class="col-sm-7">
                <select id='cboSelect' class="form-control" name="categoria_id">
                    <% String valores[] = {"Eletrônicos", "Utilidades", "Têxtil", "Decoração", "Instrumentos"};
                        Item a = (Item) request.getAttribute("item");
                        for (int i = 1; i <= valores.length; i++) {
                            if (i == a.getCategoria_id()) {%>
                    <option value="<%=i%>" selected="selected"><%=valores[i - 1]%></option>   
                    <% } else {%>
                    <option value="<%=i%>"><%=valores[i - 1]%></option> 
                    <%}
                        }%>

                </select>
            </div>
        </fieldset>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Alterar</button>
        </div>
    </div>



</form:form>


<c:import url="../rodape.jsp"/>


