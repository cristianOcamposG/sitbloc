<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">




        <title>Enfermedades </title>

    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-5">
                <div class = "card-body">
                    <form action="principalCTR?menu=enfermedades" method="POST">
                        <div class="form-group">
                            <h4> Enfermedades <span class="badge badge-secondary"></span></h4>

                            <div class="row">
                                <div class="col-md-6 d-flex form-group">
                                    <input type="text" name="ganaenfe" class="form-control" placeholder="Cod. ganado" id="cod_ganado" required value="${enfermedad2.getEnfe_ganado().getGana_cod_ganado()}">
                                    <input type="submit" name="accion" value="Consultar" class="btn btn-success" >
                                    <input  name="idganaenf" type="hidden" value="${enfermedad2.getEnfe_ganado().getGana_id_ganado()}">
                                </div>
                                <div class="col-md-6 d-flex form-group">
                                    <input type="text" name="estadoganado" placeholder="Estado del ganado"  value="${enfermedad2.getEnfe_ganado().getGana_estado()}" class="text-muted " readonly>
                                </div>

                            </div>
                            <label>Enfermedad del ganado</label>
                            <input type="text" value="${enfermedad.getEnfe_descripcion()}" name="descenfe"  class="form-control" placeholder="Especificar">

                            <label>Fecha de inicio de la enfermedad</label>
                            <input type="date" value="${enfermedad.getEnfe_fecha()}" name="fechenf" class="form-control" placeholder="Ej: 2021/10/25">   


                            <label>Notas/Recordatorio</label>
                            <input type="text" value="${enfermedad.getEnfe_notas()}" name="notaenf" class="form-control" placeholder="Datos importantes">
                       



                        <p></p>
                        <div class="form-row">


                            <div class="form-group col-md-5">
                                <label>Vacunacion / Tratamiento</label>
                                <select class="form-control form-control-sm" name="id_vac_enf">
                                    <c:forEach var="v" items="${vac_id_enf}">
                                       
                                       <option value="${v.getId_nom_vacu()}" ${enfermedad2.getEnfe_vacunacion().getNombre_vacu() == v.getNombre_vacu() ? "SELECTED" : ""}> ${v.getNombre_vacu()} </option>  
                                        
                     
                                          
                                    </c:forEach>   
                                </select> 
                            </div>

                         
                        </div>

                        </div>


                        <p></p>


                        <input type="submit" name="accion" value="Guardar" class="btn btn-success btnoperacion" >
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-warning btnoperacion">


                    </form> 
                </div>
            </div>

            <div class="col-sm-7">
                <table class="table table-hover" id="myTable">
                    <thead>
                        <tr>

                            <th>caravana </th>
                            <th>Fecha </th>
                             <th>Enfermedad </th>
                            <th> Nombre de la vacuna</th>
                      

                            <th>Accion </th>



                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="enfermedad" items="${enfermedades}">
                            <tr>


                                <td>${ enfermedad.getEnfe_ganado().getGana_cod_ganado()}</td>
                                <td>${ enfermedad.getEnfe_fecha()}</td>
                                 <td>${ enfermedad.getEnfe_descripcion()} </td>
                                <td>${ enfermedad.getEnfe_vacunacion().getNombre_vacu()} </td>
                               
                                <td>
                                    <a class="btn btn-warning" href="principalCTR?menu=enfermedades&accion=modificar&id_enfermedad=${enfermedad.getEnfe_id_enfermedades()}"><i class="fas fa-history"></i></a>
                                    <a class="btn btn-danger" href="principalCTR?menu=enfermedades&accion=eliminar&id_enfermedad=${enfermedad.getEnfe_id_enfermedades()}"><i class="fas fa-trash"></i></a> 
                                </td>
                            </tr>

                        </c:forEach>

                    </tbody>

                </table>

            </div>
        </div>
        <iframe name="contenedor2" style="height:350px;width:50%; border:none  " ></iframe>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
        <script>$.extend(true, $.fn.dataTable.defaults, {
                "language": {
                    "decimal": ",",
                    "thousands": ".",
                    "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                    "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                    "infoPostFix": "",
                    "infoFiltered": "(filtrado de un total de _MAX_ registros)",
                    "loadingRecords": "Cargando...",
                    "lengthMenu": "Mostrar _MENU_ registros",
                    "paginate": {
                        "first": "Primero",
                        "last": "Último",
                        "next": "Siguiente",
                        "previous": "Anterior"
                    },
                    "processing": "Procesando...",
                    "search": "Buscar:",
                    "searchPlaceholder": "Codigo del ganado",
                    "zeroRecords": "No se encontraron resultados",
                    "emptyTable": "Ningún dato disponible en esta tabla",
                    "aria": {
                        "sortAscending": ": Activar para ordenar la columna de manera ascendente",
                        "sortDescending": ": Activar para ordenar la columna de manera descendente"
                    },

                    "select": {
                        "rows": {
                            _: '%d filas seleccionadas',
                            0: 'clic fila para seleccionar',
                            1: 'una fila seleccionada'
                        }
                    }
                }
            });</script>
        <script> $(document).ready(function () {
         validarControlGanado();
                $('#myTable').DataTable();
            });</script>




        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="Recursos/js/reporte.js"></script>
          <script src="Recursos/js/ganado.js"></script>
           <script src="Recursos/js/sweetalert2.js"></script>
    </body>

</html>