<!DOCTYPE html>
<html>
    <head>
        <title>www.rafaelwendel.com - Upload de arquivos com barra de progresso</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <style>

.barra-area {
    position:relative;
    display:block;
    width:300px
}
.barra {
    position:relative;
    display:block;
    width:100%;
    background-color:#eee;
    padding:3px;
    -webkit-border-radius:3px;
    -moz-border-radius:3px;
    -o-border-radius:3px;
    border-radius:3px;
    -webkit-box-shadow:inset 0 1px 3px rgba(0, 0, 0, .2);
    -moz-box-shadow:inset 0 1px 3px rgba(0, 0, 0, .2);
    -o-box-shadow:inset 0 1px 3px rgba(0, 0, 0, .2);
    box-shadow:inset 0 1px 3px rgba(0, 0, 0, .2)
}
.carga {
    height:20px;
    display:block;
    background-color:cornflowerblue;
    width:0%;
    border-radius:3px;
    -webkit-transition:width .8s ease;
    -moz-transition:width .8s ease;
    -o-transition:width .8s ease;
    transition:width .8s ease
}
        </style>
        
        <script>

var width = 0;
var tempo = 20;
var carga = document.querySelector('.carga');
var barra = setInterval(function(){
    width = width + 1;
    carga.style.width = width + '%';
    if (width === 100){ 
        clearInterval(barra);
        width = 0;
    }
},tempo);
            
        </script>
    </head>
    <body>

<div class="barra-area">
    <div class="barra">
            <span class="carga"></span>
    </div>
</div>
        </div>
    </body>
</html>