<style>

    @media (min-width: 768px) {
        .sidebar-nav .navbar .navbar-collapse {
            padding: 0;
            max-height: none;
        }
        .sidebar-nav .navbar ul {
            float: none;
            display: block;
        }
        .sidebar-nav .navbar li {
            float: none;
            display: block;
        }
        .sidebar-nav .navbar li a {
            padding-top: 12px;
            padding-bottom: 12px;
        }

        .linkBotao:hover {
            background-color: white;
        } 

    }
</style>


<div class="col-sm-3">
    <div class="sidebar-nav">
        <div class="navbar navbar-default" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <span class="visible-xs navbar-brand">Sidebar menu</span>
            </div>
            <div class="navbar-collapse collapse sidebar-navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Categorias</a></li>
                    <li class="linkBotao"><a href="principal?id=1" class="linkCategoria">Eletrônicos</a></li>
                    <li class="linkBotao"><a href="principal?id=2" class="linkCategoria">Utilidades</a></li>
                    <li class="linkBotao"><a href="principal?id=3" class="linkCategoria">Têxtil</a></li>
                    <li class="linkBotao"><a href="principal?id=4" class="linkCategoria">Decoração</a></li>
                    <li class="linkBotao"><a href="principal?id=5" class="linkCategoria">Instrumentos</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>
<script>
$(document).ready(function(){
   $('.linkCategoria').each(function(){
       if("/"+$(this).attr(href) == window.location){
           $(this).addClass("active");
           $(this).css('background','#fff');
       }
   }) 
    
});
</script>
