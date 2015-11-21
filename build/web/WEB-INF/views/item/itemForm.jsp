<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:import url="../cabecalho.jsp"/>





<style>
    .fileUpload {
        position: relative;
        overflow: hidden;
        margin: 10px;
    }
    .fileUpload input.upload {
        position: absolute;
        top: 0;
        right: 0;
        margin: 0;
        padding: 0;
        font-size: 20px;
        cursor: pointer;
        opacity: 0;
        filter: alpha(opacity=0);
    }

    .error {
        color: red; font-weight: bold;
    }
</style>

<script>$("#cboSelect").change(function () {
        $("#cboSelect option:selected").text($("#cboSelect").val());
    });
</script>


<div class="page-header">
    <h1>Cadastrar item</h1>
    <p class="lead">Cadastre aqui novos itens para venda.</p>
</div>

<form:form class="form-horizontal" method="POST" modelAttribute="itemForm" action="adicionaItem" enctype="multipart/form-data">



    <div class="form-group">
        <label for="uploadBtn" class="col-sm-2 control-label">Imagem</label>
        <div class="col-sm-10">
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
                    <option value="1">Eletrônicos</option>
                    <option value="2">Utilidades</option>
                    <option value="3">Têxtil</option>
                    <option value="4">Decoração</option>
                    <option value="5">Instrumentos</option>
                </select>
            </div>
        </fieldset>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Cadastrar</button>
        </div>
    </div>



</form:form>

<script>
    document.getElementById("uploadBtn").onchange = function () {
        document.getElementById("uploadFile").value = this.value;
    };
</script>

<c:import url="../rodape.jsp"/>
