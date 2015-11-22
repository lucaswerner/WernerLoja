<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../cabecalho.jsp"/>

<style>
    a img 
    { 
        border: none; 
    }

    /* make sidebar nav vertical */ 
    .linkCategoria{
        border: none;
        text-align: left;
        padding-left: 7px;

    }

    .imagemOcupa{
        height:98%;
        width:98%;

    }

    .borda{
        border-style: solid;
        border-width: 1px;
        border-color: #E7E7E7;
        border-radius: 5px;
    }

    .borda:hover{
        border-style: solid;
        border-color: #DFB67C;
    }

    .infos:hover{
        text-decoration: none;
        color: #2b81af;
    }

    .descricao{
        
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }


</style>


        <div class="row">
            <c:import url="../menuLateral.jsp"/>
            <div class="col-sm-8">
                <c:forEach var="item" items="${itens}" varStatus="id">
                    <form class="form-horizontal" >
                        <div class="form-group borda">
                            <div class="col-sm-2">
                                <a href="produto?id=${item.id}"><img data-src='<c:url value="/resources/images/${item.img}.jpg"/>'  class="img-rounded imagemOcupa" style="margin:5px;"></a>
                            </div>
                            <div class="col-sm-7">
                                <div class="form-group">
                                    <div class="col-sm-8">
                                        <p class="form-control-static"><a class="infos" href="produto?id=${item.id}">${item.nome}</a></p>
                                    </div>
                                    <div class="col-sm-1"></div>
                                    <div class="col-sm-3">
                                        <p class="form-control-static" style="font-size:18px;"><strong>R$ ${item.valor}</strong></p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="form-control-static descricao" style="font-size:11px;">${item.descricao}</p>
                                    </div>
                                    <div class="col-sm-3">
                                        <p class="form-control-static" style="font-size:12px;">Disponível: ${item.quant}</p>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <div class="col-sm-7">
                                    <a href="adicionaItemCarrinho?id=${item.id}" class="btn" role="button">
                                        <img src='<c:url value="/resources/images/carrinho.png"/>' width="24" height="24">Comprar
                                    </a>
                                </div>
                                <div class="col-sm-7">
                                    <a href="produto?id=${item.id}" class="btn" role="button">
                                        <img src='<c:url value="/resources/images/detalhes.png"/>' width="24" height="24">Detalhes
                                    </a>
                                </div>
                            </div>
                        </div>
                    </form>

                </c:forEach>
            </div>
        </div>



<c:import url="../rodape2.jsp"/>



