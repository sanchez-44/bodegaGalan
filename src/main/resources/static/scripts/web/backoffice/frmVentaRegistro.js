
function listarTodasLasVentas() {
    $.ajax({
        type: "GET",
        url: "/backoffice/registroVentas/listar",
        dataType: "json",
        success: function (resultado) {
            actualizarTabla(resultado);
        }
    });
}


function buscarVentas() {
    var codigoVenta = $('#codigoVenta').val();
    var dniCliente = $('#dniCliente').val();

    $.ajax({
        type: "GET",
        url: "/backoffice/registroVentas/buscar",
        data: {
            codigoVenta: codigoVenta,
            dniCliente: dniCliente,
        },
        dataType: "json",
        success: function (resultado) {
            actualizarTabla(resultado);
        }
    });
}


function actualizarTabla(datos) {
    $("#tblventa > tbody").html("");
    $.each(datos, function (index, value) {
        $("#tblventa > tbody").append("<tr>" +
            "<td>" + value.codigoVenta + "</td>" +
            "<td>" + value.codigoEmpleado.codigoEmpleado + "</td>" +
            "<td>" + value.dniCliente + "</td>" +
            "<td>" + value.fecha + "</td>" +
            "<td>" + (value.descripcion ? value.descripcion : 'N/A') + "</td>" +
            "<td>" + value.tipoPago.nombre + "</td>" +
            "</tr>"
        );
    });
}


