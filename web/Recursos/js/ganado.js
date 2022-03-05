function bloquearComponentes() {
//    $("#padre_txt").attr("readonly", true);
//    $("#madre_txt").attr("readonly", true);
}

function liberarComponentes() {
    $("#padre_txt").removeAttr("readonly");
    $("#madre_txt").removeAttr("readonly");
}

function validarPadre() {
//    alert("hola");
    var codigoPadre = $("#padre_txt").val();
//    alert(codigoPadre);

    $.post("get_sexo", {sql: codigoPadre})
            .done(function (data) {
//                alert(data);

                if (data === 'null') {
                    Swal.fire({
                        text: "Atencion, Cod. de gando inexistente",
                        icon: 'error',
                        confirmButtonText: 'Entendido',
                        backdrop: true,
                        toast: true,
                        position: 'top',
                        allowOutsideClick: false,
                        allowEscapeKey: true,
                        allowEnterKey: true,
                        confirmButtonColor: '#28A745',

                    });
                } else if (data === 'Macho') {

                    Swal.fire({
                        text: "Cod. de ganado consultado Macho",
                        icon: 'success',
                        confirmButtonText: 'Entendido',
                        backdrop: true,
                        toast: true,
                        position: 'top',
                        allowOutsideClick: false,
                        allowEscapeKey: true,
                        allowEnterKey: true,
                        confirmButtonColor: '#28A745',

                    });
                    $("#sexo_ganado_padre").val(data);

                } else {
                    Swal.fire({
                        text: "Cod. de ganado consultado Hembra",
                        icon: 'error',
                        confirmButtonText: 'Entendido',
                        backdrop: true,
                        toast: true,
                        position: 'top',
                        allowOutsideClick: false,
                        allowEscapeKey: true,
                        allowEnterKey: true,
                        confirmButtonColor: '#28A745',

                    });
                }


            });
}
function validarMadre() {
    var codigoPadre = $("#madre_txt").val();
//    console.log(codigoPadre);

    $.post("get_sexo", {sql: codigoPadre})
            .done(function (data) {
//                console.log(data);

                if (data === 'null') {

                    Swal.fire({
                        text: "Atencion, Cod. de gando inexistente",
                        icon: 'error',
                        confirmButtonText: 'Entendido',
                        backdrop: true,
                        toast: true,
                        position: 'top',
                        allowOutsideClick: false,
                        allowEscapeKey: true,
                        allowEnterKey: true,
                        confirmButtonColor: '#28A745',

                    });

                } else if (data === 'Hembra') {

                    Swal.fire({
                        text: "Cod. de ganado consultado Hembra",
                        icon: 'success',
                        confirmButtonText: 'Entendido',
                        backdrop: true,
                        toast: true,
                        position: 'top',
                        allowOutsideClick: false,
                        allowEscapeKey: true,
                        allowEnterKey: true,
                        confirmButtonColor: '#28A745',

                    });
                    $("#sexo_ganado_madre").val(data);


                } else {
                    Swal.fire({
                        text: "Cod. de ganado consultado Macho",
                        icon: 'error',
                        confirmButtonText: 'Entendido',
                        backdrop: true,
                        toast: true,
                        position: 'top',
                        allowOutsideClick: false,
                        allowEscapeKey: true,
                        allowEnterKey: true,
                        confirmButtonColor: '#28A745',

                    });
                }


            });
}


//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------

function validarControlGanado() {

    if ($("#cod_ganado").val().trim().length == 0) {
        Swal.fire({
            text: "Para inicar el registro consulte un Codigo",
            icon: 'info',
            confirmButtonText: 'Entendido',
            backdrop: true,
            position: 'center',
            allowOutsideClick: false,
            allowEscapeKey: true,
            allowEnterKey: true,
            confirmButtonColor: '#28A745',
            iconColor: ''

        });
        $(".btnoperacion").attr('disabled', true);
    } else {

    }


}

//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------



function validarUsu() {

    if ($("#id_usu").val().trim().length == 0) {
        alert('Para Comenzar Consulte el nro de Documento');
        $(".btnoperacion1").attr('disabled', true);
    } else {

    }
}

//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------
function validarGestacion(){
    let sexo = $("#sexo").val();
    
    if(sexo.length === 0){
        //nada
    }else{
        if(sexo === 'Macho'){
             Swal.fire({
                        text: "Cod. de ganado consultado Macho",
                        icon: 'error',
                        confirmButtonText: 'Entendido',
                        backdrop: true,
                        toast: true,
                        position: 'top',
                        allowOutsideClick: false,
                        allowEscapeKey: true,
                        allowEnterKey: true,
                        confirmButtonColor: '#28A745',

                    });
             $(".btnoperacion").attr('disabled', true);
        }
    }
}