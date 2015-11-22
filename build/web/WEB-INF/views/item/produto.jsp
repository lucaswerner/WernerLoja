<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../cabecalho.jsp"/>

<style>
    .imagemOcupa{
        height:98%;
        width:98%;

    }
</style>

<div class="row">
    <c:import url="../menuLateral.jsp"/>

    <div class="col-sm-8">

        <div class="col-sm-6">

            <img data-src='<c:url value="/resources/images/${item.img}.jpg"/>'  class="img-rounded imagemOcupa" style="margin:5px;">
        </div>
        <div class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-6">
                    <h1  class="page-header">${item.nome}</h1>
                    <div class="col-sm-6">
                        <p class="form-control-static" style="font-size:18px;"><strong>R$ ${item.valor}</strong></p>
                    </div>
                    <div class="col-sm-6">
                        <p class="form-control-static">Disponível: ${item.quant}</p>
                    </div>
                    <div class="col-sm-12"><br></div>
                    <div class="col-sm-12">
                        <form  action="adicionaItemCarrinho" method="POST">
                            <input type="hidden" name="id" value="${item.id}">
                            <input class="btn btn-default" type="submit" value="Adicionar ao carrinho"/>
                        </form>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <p class="form-control-static descricao">${item.descricao}</p>
        </div>

    </div>
</div>

<c:import url="../rodape.jsp"/>