$(document).on("click", "#btnagregar", function(){
    $("#txtnomproveedor").val("");
    $("#txtdirproveedor").val("");
    $("#txttelproveedor").val("");
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#txtnomproveedor").val($(this).attr("data-nombre"));
    $("#txtdirproveedor").val($(this).attr("data-direccion"));
    $("#txttelproveedor").val($(this).attr("data-telefono"));
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/backoffice/proveedor/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            nombre: $("#txtnomproveedor").val(),
            direccion: $("#txtdirproveedor").val(),
            telefono: $("#txttelproveedor").val(),
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarProveedores();
            }
            alert(resultado.mensaje);
            $("#modalNuevo").modal("hide");
        }
    });
});




function listarProveedores() {
    $.ajax({
        type: "GET",
        url: "/backoffice/proveedor/listar",
        dataType: "json",
        success: function(resultado) {
            $("#tblproveedor > tbody").html(""); // Corrección aquí
            $.each(resultado, function(index, value) {
                $("#tblproveedor > tbody").append("<tr>" +
                    "<td>" + value.codProveedor + "</td>" +
                    "<td>" + value.nombre + "</td>" +
                    "<td>" + value.direccion + "</td>" +
                    "<td>" + value.telefono + "</td>" +
                    "<td>" +
                    "<button type='button' class='btn btn-info btnactualizar'" +
                    " data-codProveedor='" + value.codProveedor + "'" +
                    " data-nombre='" + value.nombre + "'" +
                    " data-direccion='" + value.direccion + "'" +
                    " data-telefono='" + value.telefono+ "'" +
                    "><i class='fas fa-edit'></i></button></td></tr>"
                );
            });
        }
    });
}
