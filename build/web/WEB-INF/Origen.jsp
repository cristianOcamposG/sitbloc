<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Origen </title>
       
    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-5">
            <div class = "card-body">
                <form action="principalCTR?menu=origenes" method="POST">
                      <div class="row"">
                                <div class="col-md-6 d-flex form-group">
                                    <input type="text" name="codigopadre" class="form-control" placeholder="Cod. Padre" value="${padre.getGana_cod_ganado()}">
                                    <input type="submit" name="accion" value="ValidarP" class="btn btn-outline-dark">
                                </div>
                                
                                <div class="col-md-6 d-flex form-group">
                                     <input type="text" name="sexoganado" placeholder="sexo del ganado"  value="${padre.getGana_sexo()}" class="form-control">
                                    
                                </div>
   
                            </div>
             
                            <div class="row"">
                                 <div class="col-md-6 d-flex form-group">
                                    <input type="text" name="codigomadre" class="form-control" placeholder="Cod. Madre" value="${madre.getGana_cod_ganado()}">
                                    <input type="submit" name="accion" value="ValidarM" class="btn btn-outline-dark">
                                </div>
                                
                                <div class="col-md-6 d-flex form-group">
                                     <input type="text" name="sexoganado" placeholder="sexo del ganado"  value="${madre.getGana_sexo()}" class="form-control">
                                    
                                </div>
   
                            </div>

                    
                    <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                     <button name="accion" value="Salir" class="dropdown-item" href="#">Cerrar</button>
                      
                </form> 
            </div>
        </div>
            
        </div>
        
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>

</html>
