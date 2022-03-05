
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">



        <title>Ganados </title>

    </head>
    <body >


        <div class="d-flex">
            <div class="card col-sm-5">

                <form action="ganaderoCTR?menu=ganados" method="POST">
                    <div class="form-group" >
                        <h4> Nuevo ganado<span class="badge badge-secondary"></span></h4>

                        <label>Codigo del ganado</label>
                        <input type="text" value="${ganado.getGana_cod_ganado()}" required name="codgana" class="form-control">
                        <small class="form-text text-muted">Ingrese el codigo de la caravana</small>


                        <label>Fecha de Nacimiento (YYYY/MM/DD)</label>
                        <input type="date" value="${ganado.getGana_fec_nacimiento()}" name="fecnac" required class="form-control">

                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label>Sexo del ganado</label>
                                <select class="form-control form-control-sm" name="sexogana">
                                    
                                    <option ${ganado.getGana_sexo() == "Macho" ? "SELECTED" : ""} >Macho</option>
                                    <option ${ganado.getGana_sexo() == "Hembra" ? "SELECTED" : ""}>Hembra</option>
                                </select> 
                            </div>

                            <div class="form-group col-md-3">
                                <label>Estado</label>
                                <select class="form-control form-control-sm" name="estadogana">
                                    <option ${ganado.getGana_estado() == "Activo" ? "SELECTED" : ""} >Activo</option>
                                    <option ${ganado.getGana_estado() == "Inactivo" ? "SELECTED" : ""} >Inactivo</option>
                                </select>
                            </div>
                            <div class="form-group col-md-3">
                                <label>Raza del ganado</label>
                                <select class="form-control form-control-sm" name="id_raza_gana">
                                    <c:forEach var="r" items="${raza_id_gana}">
                                        <option value="${r.getRaza_id_raza()}" ${ganado.getRaza().getRaza_descripcion() == r.getRaza_descripcion() ? "SELECTED" : ""} > ${r.getRaza_descripcion()} </option>  
                                    </c:forEach>   
                                </select> 

                            </div>
                            <div class="form-group col-md-12" >
                                <label>Peso incial del ganado </label>
                                <input type="number" value="${ganado.getGana_peso_ganado()}" required name="pesogana" class="form-control">
                            </div>

                        </div>
                    </div>

                            <div class="form-row" >
                                <label>Datos de trazabilidad </label>
                            </div>
                            
                    <div class="form-row" >
                        <div class="col-md-6 d-flex form-group" >
                            
                            <input type="text" name="codigopadre" id="padre_txt" class="form-control" placeholder="Cod. Padre"  value="${ganado.getGana_padre_ganado()}">
                          
                            <input type="button" onclick="validarPadre(); return false;" name="accion" value="Validar Padre"  class="btn btn-outline-dark">
                        </div>

                        <div class="col-md-6 d-flex form-group">
                            <input type="text" name="codigomadre" class="form-control" id="madre_txt" placeholder="Cod. Madre" value="${ganado.getGana_madre_ganado()}">
                            <input type="button" onclick="validarMadre(); return false;" name="accion" value="Validar Madre" class="btn btn-outline-dark" >
                        </div>

                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label>Lote de ubicacion</label>
                            <select class="form-control form-control-sm" name="id_lote_gana">
                                <c:forEach var="l" items="${lote_id_gana}">
                                    <option value="${l.getLote_id_lote()}"  ${ganado.getLote().getLote_descripcion() == l.getLote_descripcion() ? "SELECTED" : ""}>${l.getLote_descripcion()} </option>  
                                      
                                     
                                </c:forEach>   
                            </select> 




                        </div>
                       
                    </div>

                    <input type="submit" name="accion"  value="Guardar Nuevo Ganado" class="btn btn-success" >
                    <input type="submit" name="accion" value="Actualizar" class="btn btn-warning">
                     <button onclick="imprimirreporteganado(); return false;" type="button" class="btn btn-dark">Generar Reporte </button>

                    <p></p>

                </form> 
                

            </div>
            <div class="col-sm-7">
                <table class="table table-hover" id="myTable">
                    <thead>
                        <tr>

                            <th>caravana </th>
                            <th>Fec. Nac </th>
                            <th>Sexo</th>
                            <th>Estado</th>
                            <th>Peso Incial KG. </th>
                            <th>Accion </th>



                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ganado" items="${ganados}">
                            <tr>


                                <td>${ganado.getGana_cod_ganado()}</td>
                                <td>${ganado.getGana_fec_nacimiento()}</td>
                                <td>${ganado.getGana_sexo()} </td>
                                <td>${ganado.getGana_estado()}</td>
                                <td>${ganado.getGana_peso_ganado() } KG</td>

                                <td>
                                    <a class="btn btn-warning" href="ganaderoCTR?menu=ganados&accion=modificar&id_ganado=${ganado.getGana_id_ganado()}" ><i class="fas fa-history"></i></a>

                                  

                                </td>
                            </tr>

                        </c:forEach>

                    </tbody>
                </table>

            </div>
        </div>
        <iframe name="contenedor2" style="height:350px;width:50%; border:none  " ></iframe>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="Recursos/js/jquery-1.12.2.min.js"></script>
        
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
                $('#myTable').DataTable();
            });</script>





        <script src="Recursos/js/reporte.js"></script>
            <script src="Recursos/js/ganado.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="Recursos/js/sweetalert2.js"></script>
        
    </body>

</html>
