
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <title>Usuarios </title>

    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-5">
                <div class = "card-body">
                    <form action="principalCTR?menu=usuarios" method="POST">
                        <div class="form-group">
                            <h4> Ingrese Datos del Usuario<span class="badge badge-secondary"></span></h4>


                            <div class="row">
                                <div class="col-md-6 d-flex form-group">
                                    <input type="text" name="ciper" class="form-control" placeholder="Numero de Doc." required value="${usuario1.getPersona().getPers_Ci()}">
                                    <input type="submit" name="accion" value="Buscar" class="btn btn-success">
                                    <input  name="idper" type="hidden" value="${usuario1.getPersona().getPers_id_persona()}">

                                </div>

                                <div class="col-md-6 d-flex form-group">
                                    <input type="text" name="nomper" placeholder="nombre de la persona"  required value="${usuario1.getPersona().getPers_nombre()}" class="text-muted " readonly="readonly">

                                </div>

                            </div>



                            <label>Nombre de usuario </label>
                            <input type="text" value="${usuario.getUsu_nombre()}" name="nombre"  class="form-control">
                            <small class="form-text text-muted">Ingrese el usuario unico de la persona</small>

                            <label>Clave</label>
                            <input type="text" value="${usuario.getUsu_contraseña()}" name="clave"  class="form-control">
                            <small class="form-text text-muted">Ingrese la contraseña </small>
                            <p>
                            </p>
                            <div class="form-row">

                                <div class="form-group col-md-4">  
                                    <label>Tipo de usuario</label>
                                    <select class="form-control form-control-sm" name="rol">
                                        <option value="1" ${usuario.getUsu_rol() == "1" ? "SELECTED" : ""} >Administrador</option>
                                        <option value="2" ${usuario.getUsu_rol() == "2" ? "SELECTED" : ""} >Ganadero</option>
                                        <option value="3" ${usuario.getUsu_rol() == "3" ? "SELECTED" : ""} >Veterinario</option> 



                                    </select>
                                </div>
                                <div class="form-group col-md-4">  
                                    <label>Estado de usuario</label>
                                    <select class="form-control form-control-sm" name="estado">
                                        <option value="1" ${usuario.getUsu_estado() == "1" ? "SELECTED" : ""} >Activo</option>
                                        <option value="2" ${usuario.getUsu_estado() == "2" ? "SELECTED" : ""} >Desactivado</option>

                                    </select>
                                </div>
                            </div>


                        </div>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-success ">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-warning ">

                    </form> 
                </div>
            </div>
            <div class="col-sm-7">
                <table class="table table-hover">
                    <thead>
                        <tr>

                            <th>Nombre usuario</th>
                            <th>clave</th>
                            <th>rol</th>
                            <th>estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="usuario" items="${usuarios}">
                            <tr>
                                <td>${usuario.getUsu_nombre()}</td>
                                <td>${usuario.getUsu_contraseña()}</td>
                                <td>${usuario.getUsu_rol()}</td>
                                <td>${usuario.getUsu_estado()}</td>


                                <td>
                                    <a class="btn btn-warning" href="principalCTR?menu=usuarios&accion=modificar&id_usuario=${usuario.getUsu_id_usuario()}"><i class="fas fa-history"></i></a>
                                    <a class="btn btn-danger" href="principalCTR?menu=usuarios&accion=eliminar&id_usuario=${usuario.getUsu_id_usuario()}"><i class="fas fa-trash"></i></a> 
                                </td>
                            </tr>

                        </c:forEach>

                    </tbody>
                </table>

            </div>

        </div>
        

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
       
    </body>

</html>
