<%@page import="br.com.fatec.loja.modelo.User"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:import url="../cabecalho.jsp"/>

<style>
    .row.vdivide [class*='col-']:not(:last-child):after {
        background: #e0e0e0;
        width: 1px;
        content: "";
        display:block;
        position: absolute;
        top:0;
        bottom: 0;
        right: 0;
        min-height: 70px;
    }
</style>

<style>
    a.selected {
        background-color:#1F75CC;
        color:white;
        z-index:100;
    }

    .messagepop {
        background-color:#FFFFFF;
        border:1px solid #999999;
        cursor:default;
        display:none;
        margin-top: 15px;
        position:absolute;
        text-align:left;
        width:394px;
        z-index:50;
        padding: 25px 25px 20px;
    }

    label {
        display: block;
        margin-bottom: 3px;
        padding-left: 15px;
        text-indent: -15px;
    }

    .messagepop p, .messagepop.div {
        border-bottom: 1px solid #EFEFEF;
        margin: 8px 0;
        padding-bottom: 8px;
    }
</style>

<script>
    function deselect(e) {
        $('.pop').slideFadeToggle(function () {
            e.removeClass('selected');
        });
    }

    $(function () {
        $('#recuperar').on('click', function () {
            if ($(this).hasClass('selected')) {
                deselect($(this));
            } else {
                $(this).addClass('selected');
                $('.pop').slideFadeToggle();
            }
            return false;
        });

        $('.close').on('click', function () {
            deselect($('#recuperar'));
            return false;
        });
    });

    $.fn.slideFadeToggle = function (easing, callback) {
        return this.animate({opacity: 'toggle', height: 'toggle'}, 'fast', easing, callback);
    };

    $(document).mouseup(function (e)
    {
        var container = $('.pop');

        if (!container.is(e.target) // if the target of the click isn't the container...
                && container.has(e.target).length === 0) // ... nor a descendant of the container
        {
            container.hide();
        }
    });



    jQuery(document).ready(function () {
        jQuery('#hideshow').on('click', function (event) {
            jQuery('#content').toggle('show');

        });
    });



    function myFunction() {
        document.getElementById("email").readOnly = false;
        document.getElementById("nome").readOnly = false;
        document.getElementById("inputSenha").style.display = 'block';
        document.getElementById("inputNovaSenha").style.display = 'block';
        document.getElementById("habilitar").hidden = true;
        document.getElementById("Alterar").style.display = 'block';


    }

</script>



<div class="page-header">
    <h1>Minha Conta</h1>
    <p class="lead">${msg.getTexto()}</p>

</div>
<%   User usuario = (User) session.getAttribute("user");

    if (usuario == null) {
%>

<div class="container">
    <div class="row vdivide">
        <div class="col-sm-6 text-center">
            <h2 style="text-align: center;">Logar</h2>
            <form class="form-horizontal" action="efetuaLogin" method="POST" style="width:75%;margin:auto;">
                <input type="hidden" name="pagina" value="${pagina}" />
                <div class="form-group">    

                    <div class="col-sm-12">
                        <input class="form-control" id="email" type="email" name="login" value="" size="20" placeholder="Email"/>
                    </div>
                </div>
                <div class="form-group">    

                    <div class="col-sm-12">
                        <input class="form-control" id="senha" type="password" name="senha" value="" size="20" placeholder="Senha"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-1">
                        <button type="submit" class="btn btn-default">Logar</button>
                    </div>
                </div>
            </form>

            <div class="messagepop pop">

                <form action="recuperarSenha" method="POST">
                    <h4>Recuperar Senha</h4>
                    <div class="form-group">
                        <div class="col-sm-8">
                            <input class="form-control" id="email" type="email" name="login" value="" size="20" placeholder="Email"/>
                        </div>
                        <div class="col-sm-4">
                            <button type="submit" class="btn btn-default">Recuperar</button>
                        </div>
                    </div>
                </form>

            </div>

            <div class="col-sm-6">
                <a href="#" id="recuperar">Esqueceu sua senha?</a>
            </div>
        </div>



        <div class="col-sm-6 text-center">
            <h2 style="text-align: center;">Cadastre-se</h2>
            <form class="form-horizontal" action="cadastraUser" method="POST" style="width:75%;margin:auto;">
                <div class="form-group">    

                    <div class="col-sm-12">
                        <input class="form-control" id="email" type="email" name="login" value="" size="20" placeholder="Email"/>
                    </div>
                </div>
                <div class="form-group">    

                    <div class="col-sm-12">
                        <input class="form-control" id="nome" type="text" name="nome" value="" size="20" placeholder="Nome"/>
                    </div>
                </div>

                <div class="form-group">    

                    <div class="col-sm-12">
                        <input class="form-control" id="senha" type="password" name="senha" value="" size="20" placeholder="Senha" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-1">
                        <button type="submit" class="btn btn-default">Cadastrar</button>
                    </div>
                </div>

            </form>
        </div>

    </div>
</div>

<%} else {%>

<a id="habilitar" href="#" onclick="myFunction()">Atualizar dados</a>

<form class="form-horizontal" action="alteraUser" method="POST" style="width:50%;">
    <input type="hidden" name="id" value="<%=usuario.getId()%>"/>
    <div class="form-group">    
        <div class="col-sm-3">
            <label for="email">Email</label>
        </div>
        <div class="col-sm-9">
            <input class="form-control" id="email" type="email" name="login" value="<%=usuario.getLogin()%>" size="20" placeholder="Email" readonly/>
        </div>
    </div>
    <div class="form-group">    
        <div class="col-sm-3">
            <label for="nome">Nome</label>
        </div>
        <div class="col-sm-9">
            <input class="form-control" id="nome" type="text" name="nome" value="<%=usuario.getNome()%>" size="20" placeholder="Nome" readonly />
        </div>
    </div>

    <div class="form-group" id="inputSenha" style="display: none;">    
        <div class="col-sm-3">
            <label for="senha">Antiga senha</label>
        </div>
        <div class="col-sm-9">
            <input class="form-control" id="senha" type="password" name="senha" size="20"/>
        </div>
    </div>


    <div class="form-group" id="inputNovaSenha" style="display: none;">
        <div class="col-sm-3">
            <label for="novaSenha">Nova senha</label>
        </div>
        <div class="col-sm-9">
            <input class="form-control" id="novaSenha" type="password" name="novaSenha" size="20"/>
        </div>
    </div>


    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-1">
            <button class="btn btn-default" type="submit" name="cancel" id="Alterar" style="display:none;">Alterar</button>
        </div>
    </div>



</form>


<%}%>





</div> 

</body>

</html>
