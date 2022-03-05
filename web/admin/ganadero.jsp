
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
          <link href="Recursos/css/w3.css" rel="stylesheet" type="text/css"/>
        <link href="Recursos/css/master.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>  
        
        
         <style>
            body {
                background-image:url('Recursos/img/logo nuevo.jpg');
               
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: contain;
                background-position: center;
            }
            
        </style>

        <div class="w3-bar btn-success">
            <a href="#"  target="contenedor" class="w3-bar-item w3-button">Incio</a>
            <div class="w3-dropdown-hover">
                <button class="w3-button">Ganaderia</button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4">
                    <a href="ganaderoCTR?menu=ganados&accion=Listar" target="contenedor" class="w3-bar-item w3-button">Ingreso de Ganado</a>
                    <a href="ganaderoCTR?menu=controles&accion=Listar" target="contenedor" class="w3-bar-item w3-button">Control del Ganado</a>
                </div>
            </div>
          
            <div class="w3-dropdown-hover">
                <button class="w3-button">Parametros</button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4">
                    <a href="principalCTR?menu=lotes&accion=Listar" target="contenedor" class="w3-bar-item w3-button">Lotes</a>
                     <a href="principalCTR?menu=razas&accion=Listar" target="contenedor" class="w3-bar-item w3-button">Razas</a>
                    <a href="principalCTR?menu=alimentos&accion=Listar" target="contenedor"class="w3-bar-item w3-button">Alimentos</a>
                   
                </div>
            </div>

            <div class="w3-dropdown-hover w3-right text-center">
                <button class="w3-button">${USU_NOMBRE.getUsu_nombre()}</button>
                <div class="w3-dropdown-content w3-bar-block w3-border" style="right:0">
                    <img src="Recursos/img/user_person_people_6100.png" alt="45" width="45"/>
                    <a href="#" class="w3-bar-item w3-button text-center">${USU_NOMBRE.getUsu_nombre()}</a>
                    <div class="dropdown-divider"></div>
                    <form action="ValidarCTR" method="POST">  
                        <button name="accion" value="Salir" class="dropdown-item" href="#">Salir</button>
                    </form>
                </div>
            </div>
 </div>
         
                    <iframe name="contenedor" style="height:100%;width:100%; border:none; " >
                        
                    </iframe>
            
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
                <script src="Recursos/js/reportejs.js"></script>
    </body>
</html>
