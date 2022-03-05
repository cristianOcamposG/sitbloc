function imprimirreporteganado() {
    var jsp = "/sitbloc/Salida/RepGanado.jsp";

    window.open(jsp);
}


$(document).on('click', '.imprimir-historial', function (evt) {
    let cod = $(this).closest("tr").find("td").filter(":eq(0)").text();
    
//    var jsp = "/sitbloc/Salida/historial.jsp?cod="+cod+"&fecha="+desde;
    var jsp = "/sitbloc/Salida/historial.jsp?cod="+cod;

    window.open(jsp);
});

$(document).on('click', '.imprimir-sanidad', function (evt) {
    let cod = $(this).closest("tr").find("td").filter(":eq(0)").text();
    
//    var jsp = "/sitbloc/Salida/historial.jsp?cod="+cod+"&fecha="+desde;
    var jsp = "/sitbloc/Salida/sanidadRepo.jsp?cod="+cod;

    window.open(jsp);
});



















