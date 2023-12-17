$(document).on("click", "#btnagregar", function(){
    $("#txtnomproducto").val("");
    $("#txtpreciounit").val("");
    $("#hddcodprod").val("0");
    $("#cbocategoria").empty();
    $("#cboproveedor").empty();
    $("#switchproducto").hide();
    $("#chkdescontinuado").prop("checked", false);
    listarCategoriasProveedores(0, 0);
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#txtnomproducto").val($(this).attr("data-prodname"));
    $("#txtpreciounit").val($(this).attr("data-produnit"));
    $("#hddcodprod").val($(this).attr("data-prodcod"));
    $("#cbocategoria").empty();
    $("#cboproveedor").empty();
    listarCategoriasProveedores($(this).attr("data-prodcateg"), $(this).attr("data-prodprov"));
    $("#switchproducto").show();
    if($(this).attr("data-descontinuado") === "true")
        $("#chkdescontinuado").prop("checked", true);
    else
        $("#chkdescontinuado").prop("checked", false);
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/backoffice/product/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            productid: $("#hddcodprod").val(),
            productname: $("#txtnomproducto").val(),
            unitprice: $("#txtpreciounit").val(),
            categoryid: $("#cbocategoria").val(),
            supplierid: $("#cboproveedor").val(),
            discontinued: $("#chkdescontinuado").prop("checked")
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarProductos()
            }
            alert(resultado.mensaje);
            $("#modalNuevo").modal("hide");
        }
    })
});

function listarCategoriasProveedores(idcate, idprov){
    $.ajax({
        type: "GET",
        url: "/backoffice/category/listar",
        dataType: "json",
        success: function(resultado){
            $.each(resultado, function(index, value){
                $("#cbocategoria").append(
                `<option value="${value.categoryid}">${value.categoryname}</option>`
                )
            })
            if(idcate > 0) {
                $("#cbocategoria").val(idcate);
            }
            $.ajax({
                    type: "GET",
                    url: "/backoffice/supplier/listar",
                    dataType: "json",
                    success: function(resultado){
                        $.each(resultado, function(index, value){
                            $("#cboproveedor").append(
                            `<option value="${value.supplierid}">${value.companyname}</option>`
                            )
                        })
                        if(idprov > 0) {
                            $("#cboproveedor").val(idprov);
                        }
                    }
                })
        }
    })
}

function listarProductos(){
    $.ajax({
        type: "GET",
        url: "/backoffice/product/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblproducto > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblproducto > tbody").append("<tr>"+
                    "<td>"+value.productid+"</td>"+
                    "<td>"+value.productname+"</td>"+
                    "<td>"+value.unitprice+"</td>"+
                    "<td>"+value.category.categoryname+"</td>"+
                    "<td>"+value.supplier.companyname+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-info btnactualizar'" +
                            " data-prodcod='"+value.productid+"'"+
                            " data-prodname='"+value.productname+"'"+
                            " data-produnit='"+value.unitprice+"'"+
                            " data-prodcateg='"+value.category.categoryid+"'"+
                            " data-prodprov='"+value.supplier.supplierid+"'"+
                            "><i class='fas fa-edit'></i></button></td></tr>"
                );
            })
        }
    })
}


$(document).ready(function () {
    var currentDate = new Date();
    var formattedDate = currentDate.toISOString().split('T')[0];

    $('#txtfecha').val(formattedDate);
});

function buscarYllenarProducto(codigoProducto) {
    console.log("Iniciando búsqueda para el código: " + codigoProducto);

    // Realiza la llamada AJAX para buscar el producto por el código
    $.ajax({
        type: "GET",
        url: "/backoffice/producto/buscar/" + codigoProducto,
        dataType: "json",
        success: function(producto) {
            console.log("Respuesta exitosa:", producto);
            if (producto != null) {
                // Solo llamada para agregar fila a la tabla
                agregarFilaTabla(producto.fecha, producto.codigoProducto, producto.nombreProducto, producto.cantidad, producto.precio, producto.dni, producto.codigoEmpleado, producto.descripcion);
            } else {
                // Producto no encontrado, podrías mostrar un mensaje o manejarlo según tus necesidades
                console.log("Producto no encontrado.");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("Error en la llamada AJAX:", textStatus, errorThrown);
            // Manejo de errores si es necesario
        }
    });
}

$(document).on("click", ".btnEliminarFila", function() {
    // Encuentra la fila a la que pertenece el botón y elimínala
    $(this).closest("tr").remove();
});
var tiposDePago = [
    { codigo: '1', nombre: 'Efectivo' },
    { codigo: '2', nombre: 'Yape' },
    { codigo: '3', nombre: 'Plin' },
];

// Función para llenar el select de tipos de pago
function llenarSelectTiposPago() {
    var select = $('#selTiposPago');

    // Limpia el select
    select.empty();

    // Agrega las opciones desde la lista de tiposDePago
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
        // Si ya existe una fila con el mismo código, actualiza la cantidad y el precio
        var nuevaCantidad = parseInt(filaExistente.find('.cantidad').text()) + parseInt(cantidad);
        var nuevoMonto = parseFloat(filaExistente.find('.montoTotal').text()) + parseFloat(montoTotal);

        filaExistente.find('.cantidad').text(nuevaCantidad);
        filaExistente.find('.montoTotal').text(nuevoMonto.toFixed(2));
    } else {
        // Si no existe una fila con el mismo código, agrega una nueva fila
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

        // Agrega el tipo de pago (código y nombre)
        $('<td>').text(codigoTipoPago).addClass('codigoTipoPago').hide().appendTo(nuevaFila);
        $('<td>').text(nombreTipoPago).addClass('nombreTipoPago').appendTo(nuevaFila);

        // Agregar botón de eliminar
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

    // Realiza la llamada AJAX para buscar el producto por el código
    $.ajax({
        type: "GET",
        url: "/backoffice/producto/buscar/" + codigoProducto,
        dataType: "json",
        success: function(producto) {
            if (producto != null) {
                // Llena las casillas con la información del producto
                $("#txtnombreProducto").val(producto.nombre);
                $("#txtprecio").val(producto.precio);

                // Calcula el monto total
                var montoTotal = cantidad * producto.precio;
                // Agrega la fila a la tabla
                agregarFilaTabla(fecha, codigoProducto, producto.nombre, cantidad, montoTotal, dni, codigoEmpleado, descripcion,codigoTipoPago, nombreTipoPago);

                // Oculta el mensaje de error
                $("#mensajeError").hide();
            } else {
                // Producto no encontrado, mostrar mensaje de error
                $("#errorText").text("Producto no encontrado");
                $("#mensajeError").show();

                // Limpia las casillas
                $("#txtnombreProducto").val("");
                $("#txtprecio").val("");
                // ... (otros campos)
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // Manejo de errores si es necesario
            $("#errorText").text("¡ERROR!Producto no encontrado");
            $("#mensajeError").show();
        }
    });
});


$(document).on("click", "#btnlimpiar", function() {
    limpiarCasillas();
});

$(document).on("click", "#btneliminarTabla", function() {
    // Elimina todas las filas de la tabla
    $('#tblDetalle tbody').empty();
});

$(document).on("click", "#btncancelarVenta", function() {
    // Elimina todas las filas de la tabla
    $('#tblDetalle tbody').empty();

    // Limpia las casillas de ingreso de datos
    $('#txtfecha').val('');
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

    // Iterar sobre cada fila en el cuerpo de la tabla
    $('#tblDetalle tbody tr').each(function() {
        var fila = $(this);
        var fecha = fila.find('td:eq(0)').text();
        var codigoProducto = parseInt(fila.find('td:eq(1)').text(),10);
        var cantidad = parseInt(fila.find('td:eq(3)').text(), 10);  // Convertir a entero
        var montoTotal = parseFloat(fila.find('td:eq(4)').text());  // Convertir a decimal
        var dni = parseInt(fila.find('td:eq(5)').text(), 10);  // Convertir a entero
        var codigoEmpleado = parseInt(fila.find('td:eq(6)').text(), 10);  // Convertir a entero
        var descripcion = fila.find('td:eq(7)').text();
        var codigoTipoPago = parseInt(fila.find('td.codigoTipoPago').text(), 10); // Convertir a entero

        // Crear un objeto con los datos de la fila actual
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

        // Agregar los datos al array
        datosTabla.push(datosFila);
    });

    return datosTabla;
}


$(document).on("click", "#btnregistrar", function() {
    var datosTabla = obtenerDatosTabla();

    // Enviar datos al servidor
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


















