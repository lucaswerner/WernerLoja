<%@page import="br.com.fatec.loja.modelo.User"%>
<%@page import="br.com.fatec.loja.modelo.Carrinho"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Werner Loja</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="shortcut icon" href="<c:url value="/resources/images/iconeBrowser.png"/>">

        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
        <link href="<c:url value="/resources/LazySpinner/dist/jquery.lazyloadxt.fadein.css"/>" rel="stylesheet" >
        <link href="<c:url value="/resources/LazySpinner/css/style.css"/>" rel="stylesheet" >
        <script src="<c:url value="/resources/LazySpinner/libs/jquery/jquery.js"/>"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="<c:url value="/resources/LazySpinner/dist/jquery.lazyloadxt.js"/>"></script>

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

                .iconeBarraComImagem{
                    padding-bottom: 0;
                    padding-top: 13px;
                } 

                .error {
                    color: red; font-weight: bold;
                }

                .imagemOcupa{
                    height:98%;
                    width:98%;

                }
            </style>

            <script>

                document.getElementById("uploadBtn").onchange = function () {
                    document.getElementById("uploadFile").value = this.value;
                };

                $(function () {
                    $("li a").on("click", function () {
                        $("li").find(".active").removeClass("active");
                        $(this).parent().addClass("active");
                    });

                }

            </script>



        </head>
        <% User user = (User) session.getAttribute("user");%>
        <body>
            <div class="container">

                <header>


                    <nav class="navbar navbar-default">
                        <div class="container-fluid">
                            <!-- Brand and toggle get grouped for better mobile display -->
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="principal">WernerLoja</a>
                            </div>

                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav">
                                    <% if (user != null) {%>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Anuncie já!<span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="listaItens">Meus itens</a></li>
                                            <li><a href="itemForm">Cadastrar item</a></li>
                                        </ul>
                                    </li>
                                    <%}%>
                                </ul>
                                <form action="principal" class="navbar-form navbar-left" role="search" method="post">
                                    <div class="form-group">
                                        <% String a = (String) request.getAttribute("filtro");
                                            if (a == null) {
                                                a = "";
                                            } else {
                                                a = new String(a.getBytes("ISO8859_1"), "UTF-8");
                                            }
                                        %>
                                        <input type="hidden" name="id" value="<%=request.getAttribute("id")%>" />
                                        <input type="text"  name="filtro" class="form-control" placeholder="Buscar" value="<%=a%>">
                                    </div>
                                    <button type="submit" class="btn btn-default"> <img src='<c:url value="/resources/images/procura.png"/>' width="24" height="24"></button>
                                </form>


                                <% Carrinho carrinho = new Carrinho();
                                    carrinho = (Carrinho) session.getAttribute("carrinho");
                                %>





                                <ul class="nav navbar-nav navbar-right">
                                    <%if (user != null) {%>    
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                            <img src='<c:url value="/resources/images/pessoa.png"/>' width="24" height="24">Olá <%=user.getNome()%>
                                            <span class="caret"></span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li><a href="loginForm">Minha conta</a></li>
                                            <li><a href="historicoCompras">Histórico compras</a></li>
                                            <li role="separator" class="divider"></li>
                                            <li><form action="logout" method="POST"/>
                                                <input type="hidden" name="pagina" value="<%=request.getAttribute("pagina")%>" />
                                                <input type="submit" class="btn btn-default" value="Sair"  style="border: none;"/>
                                                </form>
                                            </li>
                                        </ul>
                                    </li>
                                    <% } else {%>
                                    <li><a href="loginForm">
                                            <img src='<c:url value="/resources/images/pessoa.png"/>' width="24" height="24">LOGIN | CADASTRE-SE
                                        </a>
                                    </li>
                                    <%}%>

                                    <li class="dropdown">

                                        <%if (carrinho == null) {%>
                                        <a href="#" class="dropdown-toggle iconeBarraComImagem" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                            <img src="/WernerLoja/resources/images/shoppingCartEmpty.png" width="24" height="24">
                                            <span class="caret"></span>
                                        </a>

                                        <ul class="dropdown-menu" style="padding-bottom: 0;">
                                            <li><p class="text-center"><strong>Carrinho está vazio</strong></p></li>
                                                <%} else {%>
                                            <a href="#" class="dropdown-toggle iconeBarraComImagem" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                                <img src="/WernerLoja/resources/images/shoppingCart.png" width="24" height="24">
                                                <span class="caret"></span>
                                            </a>
                                            <ul class="dropdown-menu" style="padding-bottom: 0;">

                                                <table class="table">
                                                    <caption style="margin-left: 10px;">Meu Carrinho</caption>
                                                    <c:forEach var="item" items="${carrinho.itens}" varStatus="id">


                                                        <li><tr>

                                                            <td><img src='<c:url value="/resources/images/${item.img}.jpg"/>' width="30" height="20"></td>
                                                            <td><a class="btn btn-default" type="button" href="produto?id=${item.id}"
                                                                   style="border: none;
                                                                   padding-bottom: 0px;
                                                                   padding-left: 7px;"
                                                                   >${item.nome}</a></td>

                                                            <td>${item.quant}x</td>
                                                            <td>R$${item.valor}</td>
                                                            <td style="
                                                                width: 10px;
                                                                height: 10px;
                                                                padding-top: 5px;
                                                                ">
                                                                <form action="removeItemCarrinho" />
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
                                                        </tr></li>
                                                    </c:forEach>
                                                </table>

                                                <li role="separator" class="divider" style="
                                                    margin-bottom: 0px;padding-bottom: 0px;margin-top: 0px;"></li></li>  
                                                <li></li>
                                                <li><form class="form-group" action="revisaoCompra" style="margin-bottom: 5px;">
                                                        <div class="col-sm-offset-3 col-sm-2 ">
                                                            <p class="form-control-static"><strong>Total: </strong></p>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <p class="form-control-static">R$ ${carrinho.total}</p>
                                                        </div>
                                                        <div class="col-sm-4" style="padding-left: 0px;">
                                                            <button type="submit" class="btn btn-primary" 
                                                                    style="width: 90px;
                                                                    height: 30px;
                                                                    border: none;
                                                                    padding-top: 0px;
                                                                    padding-bottom: 0px;
                                                                    padding-left: 7px;
                                                                    background-color: rgb(230, 135, 0);
                                                                    border-color: rgb(230, 135, 0);
                                                                    ">
                                                                Comprar
                                                            </button>
                                                        </div>

                                                    </form></li>
                                                    <% }%>
                                            </ul>
                                    </li>
                                </ul>
                            </div><!-- /.navbar-collapse -->
                        </div><!-- /.container-fluid -->
                    </nav>


                </header>
