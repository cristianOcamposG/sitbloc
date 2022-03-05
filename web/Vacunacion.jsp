<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">




        <title>Vacunaciones </title>

    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-5">
                <div class = "card-body">
                    <form action="principalCTR?menu=vacunaciones" method="POST">
                        <div class="form-group">
                            <h4> Vacunaciones <span class="badge badge-secondary"></span></h4>

                            <div class="row">
                                <div class="col-md-6 d-flex form-group">
                                    <input type="text" name="ganavacu" class="form-control" placeholder="Cod. ganado" id="cod_ganado" required value="${ganado2.getVacu_ganado().getGana_cod_ganado()}">
                                    <input type="submit" name="accion" value="Consultar" class="btn  btn-success" >
                                           <input  name="idganavacu" type="hidden" value="${ganado2.getVacu_ganado().getGana_id_ganado()}">
                                </div>

                                <div class="col-md-6 d-flex form-group">
                                    <input type="text" name="estadoganado" placeholder="Estado del ganado"  value="${ganado2.getVacu_ganado().getGana_estado()}" class="text-muted " readonly>

                                </div>

                            </div>


                            <label>Fecha de vacunacion (YYYY/MM/DD)</label>
                            <input type="date" value="${vacunacion.getVacu_fecha()}" name="fechvacu" class="form-control">

                            <p></p>
                            <div class="form-row">

                                <div class="form-group col-md-4">  
                                    <label>Tipo de vacunacion</label>
                                    <select class="form-control form-control-sm" name="tipovacu">
                                        <option value="1" ${vacunacion.getVacu_tipo() == "1" ? "SELECTED" : ""}> Obligatorio</option>
                                        <option value="2" ${vacunacion.getVacu_tipo() == "2" ? "SELECTED" : ""}> Otros tipos</option>
                                        
                                            
                                        
                                    </select>
                                </div>

                                <div class="form-group col-md-5">
                                    <label>Nombre de vacunacion</label>
                                    <select class="form-control form-control-sm" name="id_nom_vac">
                                        <c:forEach var="n" items="${nom_id_vac}">
                                            <option value="${n.getId_nom_vacu()}" ${vacunacion.getVacu_nombre().getNombre_vacu() == n.getNombre_vacu() ? "SELECTED" : ""}> ${n.getNombre_vacu()} </option>  
                                            
                                            
                                           
                                        </c:forEach>   
                                    </select> 
                                </div>

                              
                            </div>

                            <label>Notas importantes</label>
                            <input type="text" value="${vacunacion.getVacu_nota()}" name="notavacu" class="form-control" placeholder="Especificar otras vacunas">
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
                            <th> Nombre de la vacuna</th>
                            <th> Tipo </th>

                            <th>Accion </th>



                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="vacunacion" items="${vacunaciones}">
                            <tr>


                                <td>${ vacunacion.getVacu_ganado().getGana_cod_ganado()}</td>
                                <td>${ vacunacion.getVacu_fecha()}</td>
                                <td>${ vacunacion.getVacu_nombre().getNombre_vacu()} </td>
                                <td>${ vacunacion.getVacu_tipo()} </td>
                                <td>
                                    <a class="btn btn-warning" href="principalCTR?menu=vacunaciones&accion=modificar&id_vacunacion=${vacunacion.getVacu_id_vacunacion()}"><i class="fas fa-history"></i></a>
                                    <a class="btn btn-danger" href="principalCTR?menu=vacunaciones&accion=eliminar&id_vacunacion=${vacunacion.getVacu_id_vacunacion()}"><i class="fas fa-trash"></i></a> 
                                     <a class="btn btn-primary imprimir-sanidad"  return false;"><i class="fas fa-print"></i></a> 
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

