
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

        <title>Lotes </title>

    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-5">
                <div class = "card-body">
                    <form action="principalCTR?menu=razas" method="POST">
                        <div class="form-group">
                            <h4> Ingrese una nueva raza<span class="badge badge-secondary"></span></h4>

                            <label>Descripcion</label>
                            <input type="text" value="${raza.getRaza_descripcion()}" name="desraza" required class="form-control">
                            <small class="form-text text-muted">Ingrese una nueva raza a la seccion de razas</small>

                        </div>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-success">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-warning">

                    </form> 
                </div>
            </div>
            <div class="col-sm-7">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Descripcion </th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="razas" items="${razas}">
                            <tr>
                                <td>${razas.getRaza_id_raza()}</td>
                                <td>${razas.getRaza_descripcion()}</td>


                                <td>
                                    <a class="btn btn-warning" href="principalCTR?menu=razas&accion=modificar&id_raza=${razas.getRaza_id_raza()}"><i class="fas fa-history"></i></a>
                                    <a class="btn btn-danger" href="principalCTR?menu=razas&accion=eliminar&id_raza=${razas.getRaza_id_raza()}"><i class="fas fa-trash"></i></a> 
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


