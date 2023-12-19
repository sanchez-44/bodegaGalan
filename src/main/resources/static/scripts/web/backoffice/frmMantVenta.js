$(document).ready(function () {
    var currentDate = new Date();
    var formattedDate = currentDate.toISOString().split('T')[0];

    $('#txtfecha').val(formattedDate);
});

function buscarYllenarProducto(codigoProducto) {
    console.log("Iniciando búsqueda para el código: " + codigoProducto);

    $.ajax({
        type: "GET",
        url: "/backoffice/producto/buscar/" + codigoProducto,
        dataType: "json",
        success: function(producto) {
            console.log("Respuesta exitosa:", producto);
            if (producto != null) {
                agregarFilaTabla(producto.fecha, producto.codigoProducto, producto.nombreProducto, producto.cantidad, producto.precio, producto.dni, producto.codigoEmpleado, producto.descripcion);

            } else {
                console.log("Producto no encontrado.");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("Error", textStatus, errorThrown);
        }
    });
}

$(document).on("click", ".btnEliminarFila", function() {
    $(this).closest("tr").remove();
});
var tiposDePago = [
    { codigo: '1', nombre: 'Efectivo' },
    { codigo: '2', nombre: 'Yape' },
    { codigo: '3', nombre: 'Plin' },
];


function llenarSelectTiposPago() {
    var select = $('#selTiposPago');
    select.empty();

    tiposDePago.forEach(function(tipoPago) {
        select.append('<option value="' + tipoPago.codigo + '">' + tipoPago.nombre + '</option>');
    });
}

$(document).ready(function() {
    llenarSelectTiposPago();
});

function agregarFilaTabla(fecha, codigoProducto, nombreProducto, cantidad, montoTotal, dni, codigoEmpleado, descripcion,codigoTipoPago, nombreTipoPago) {
    var tbody = $('#tblDetalle tbody');
    var filaExistente = tbody.find('tr[data-codigoproducto="' + codigoProducto + '"]');

    if (filaExistente.length > 0) {
        var nuevaCantidad = parseInt(filaExistente.find('.cantidad').text()) + parseInt(cantidad);
        var nuevoMonto = parseFloat(filaExistente.find('.montoTotal').text()) + parseFloat(montoTotal);

        filaExistente.find('.cantidad').text(nuevaCantidad);
        filaExistente.find('.montoTotal').text(nuevoMonto.toFixed(2));
    } else {

        var nuevaFila = $('<tr>');
        nuevaFila.attr('data-codigoproducto', codigoProducto);

        $('<td>').text(fecha).appendTo(nuevaFila);
        $('<td>').text(codigoProducto).appendTo(nuevaFila);
        $('<td>').text(nombreProducto).appendTo(nuevaFila);
        $('<td>').text(cantidad).addClass('cantidad').appendTo(nuevaFila);
        $('<td>').text(montoTotal).addClass('montoTotal').appendTo(nuevaFila);
        $('<td>').text(dni).appendTo(nuevaFila);
        $('<td>').text(codigoEmpleado).appendTo(nuevaFila);
        $('<td>').text(descripcion).appendTo(nuevaFila);

        $('<td>').text(codigoTipoPago).addClass('codigoTipoPago').hide().appendTo(nuevaFila);
        $('<td>').text(nombreTipoPago).addClass('nombreTipoPago').appendTo(nuevaFila);

        $('<td>').html('<button class="btnEliminarFila">Eliminar</button>').appendTo(nuevaFila);

        tbody.append(nuevaFila);

    }
}

function limpiarCasillas() {
    $('#txtcodigoproducto').val('');
    $('#txtnombreProducto').val('');
    $('#txtcantidad').val('');
    $('#txtprecio').val('');
    $('#txtdni').val('');
    $('#txtcodigoEmpleado').val('');
    $('#txtdescripcion').val('');
}

$(document).on("click", "#btningresar", function(){
    var fecha = $('#txtfecha').val();
    var codigoProducto = $('#txtcodigoproducto').val();
    var cantidad = $('#txtcantidad').val();
    var dni = $('#txtdni').val();
    var codigoEmpleado = $('#txtcodigoEmpleado').val();
    var descripcion = $('#txtdescripcion').val();
    var codigoTipoPago = $('#selTiposPago').val();
    var nombreTipoPago = $('#selTiposPago option:selected').text();

    $.ajax({
        type: "GET",
        url: "/backoffice/producto/buscar/" + codigoProducto,
        dataType: "json",
        success: function(producto) {
            if (producto != null) {
                $("#txtnombreProducto").val(producto.nombre);
                $("#txtprecio").val(producto.precio);

                var montoTotal = cantidad * producto.precio;
                agregarFilaTabla(fecha, codigoProducto, producto.nombre, cantidad, montoTotal, dni, codigoEmpleado, descripcion,codigoTipoPago, nombreTipoPago);

                $("#mensajeError").hide();
            } else {
                $("#errorText").text("Producto no encontrado");
                $("#mensajeError").show();

                $("#txtnombreProducto").val("");
                $("#txtprecio").val("");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $("#errorText").text("¡ERROR!Producto no encontrado");
            $("#mensajeError").show();
        }
    });
});


$(document).on("click", "#btnlimpiar", function() {
    limpiarCasillas();
});


$(document).on("click", "#btneliminarTabla", function() {
    $('#tblDetalle tbody').empty();
});


$(document).on("click", "#btncancelarVenta", function() {
    $('#tblDetalle tbody').empty();

    $('#txtcodigoproducto').val('');
    $('#txtnombreProducto').val('');
    $('#txtcantidad').val('');
    $('#txtdni').val('');
    $('#txtprecio').val('');
    $('#txtcodigoEmpleado').val('');
    $('#txtdescripcion').val('');
});


function obtenerDatosTabla() {
    var datosTabla = [];

    $('#tblDetalle tbody tr').each(function() {
        var fila = $(this);
        var fecha = fila.find('td:eq(0)').text();
        var codigoProducto = parseInt(fila.find('td:eq(1)').text(),10);
        var cantidad = parseInt(fila.find('td:eq(3)').text(), 10);
        var montoTotal = parseFloat(fila.find('td:eq(4)').text());
        var dni = parseInt(fila.find('td:eq(5)').text(), 10);
        var codigoEmpleado = parseInt(fila.find('td:eq(6)').text(), 10);
        var descripcion = fila.find('td:eq(7)').text();
        var codigoTipoPago = parseInt(fila.find('td.codigoTipoPago').text(), 10);

        var datosFila = {
            fecha: fecha,
            codigoProducto: codigoProducto,
            cantidad: cantidad,
            montoTotal: montoTotal,
            dni: dni,
            codigoEmpleado: codigoEmpleado,
            descripcion: descripcion,
            codigoTipoPago: codigoTipoPago,
        };

        datosTabla.push(datosFila);
    });

    return datosTabla;
}


$(document).on("click", "#btnregistrar", function() {
    var datosTabla = obtenerDatosTabla();

    $.ajax({
        type: "POST",
        url: "/backoffice/registrarVentas",
        contentType: "application/json",
        data: JSON.stringify(datosTabla),
        success: function(response) {
            console.log(response);
            alert("Ventas registradas con éxito");
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("Error al registrar ventas:", textStatus, errorThrown);
            alert("Error al registrar ventas");
        }
    });
});


function obtenerDatosTabla2() {
    var datosTabla = [];

    $('#tblDetalle tbody tr').each(function () {
        var fila = $(this);
        var nombreProducto = fila.find('td:eq(2)').text();
        var precio = fila.find('td:eq(4)').text();

        var datosFila = {
            nombreProducto: nombreProducto,
            precio: precio
        };

        datosTabla.push(datosFila);
    });

    return datosTabla;
}



$(document).on('click', "#btnGenerarComprobante", function () {

    var detalleComprobante = obtenerDatosTabla2();

    $('#fechaActual').text(new Date().toISOString().split('T')[0]);
    $('#productos').text(obtenerDetalleComprobanteTexto(detalleComprobante));
    $('#dni').text('');
    $('#igv').text('');
    $('#subtotal').text('');
    $('#total').text('');

    $('#comprobanteModal').modal('show');
});

function obtenerDetalleComprobanteTexto(detalleComprobante) {
    var tbody = $('#productosTableBody');
    tbody.empty();

    detalleComprobante.forEach(function (detalle) {
        var fila = '<tr><td>' + detalle.nombreProducto + '</td><td>' + detalle.precio + '</td></tr>';
        tbody.append(fila);
    });
}


$(document).on('click', "#btnImprimir", function () {
    var contenidoPopUp = $('#comprobanteModal .modal-body').html();

    var ventanaImpresion = window.open('', '_blank');
    ventanaImpresion.document.write('<html><head><title>Comprobante de Pago</title></head><body>');
    ventanaImpresion.document.write(contenidoPopUp);
    ventanaImpresion.document.write('</body></html>');

    ventanaImpresion.print();
    ventanaImpresion.document.close();
});
