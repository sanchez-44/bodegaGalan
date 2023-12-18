$(document).on("click", "#btnagregar", function(){
    $("#txtnomproducto").val("");
    $("#txtcantidad").val("");
    $("#txtpreciounit").val("0");
    listarCategorias(0); // Función para cargar las categorías
    listarProveedores(0); // Función para cargar los proveedores
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#txtnomproducto").val($(this).attr("data-prodname"));
    $("#txtcantidad").val($(this).attr("data-prodcant"));
    $("#txtpreciounit").val($(this).attr("data-produnit"));
    $("#hddcodprod").val($(this).attr("data-prodcod"));
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/backoffice/product/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            codigo: $("#hddcodprod").val(),
            nombre: $("#txtnomproducto").val(),
            precio: $("#txtpreciounit").val(),
            categoriaProducto: {
                codCategoriaP: $("#cbocategoria").val(),
            },
            proveedor: {
                codProveedor: $("#cboproveedor").val(),
            },
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarProductos();
            }
            alert(resultado.mensaje);
            $("#modalNuevo").modal("hide");
        }
    });
});


function listarProveedores(codProveedor) {

    $.ajax({
        type: "GET",
        url: "/backoffice/proveedor/listar",
        dataType: "json",
        success: function (resultado) {
            $("#cboproveedor").empty();

            $.each(resultado, function (index, value) {
                $("#cboproveedor").append(
                    `<option value="${value.codProveedor}">${value.nombre}</option>`
                );
            });

            if (codProveedor > 0) {
                $("#cboproveedor").val(codProveedor);
            }
        },
    });
}



function listarCategorias(codCategoriaP) {
    $.ajax({
        type: "GET",
        url: "/backoffice/categoria/listar",
        dataType: "json",
        success: function (resultado) {
            $("#cbocategoria").empty();

            $.each(resultado, function (index, value) {
                $("#cbocategoria").append(
                    `<option value="${value.codCategoriaP}">${value.nombreCategoria}</option>`
                );
            });

            if (codCategoriaP > 0) {
                $("#cbocategoria").val(codCategoriaP);
            }
        },
    });
}

function listarProductos() {
    $.ajax({
        type: "GET",
        url: "/backoffice/product/listar",
        dataType: "json",
        success: function(resultado) {
            $("#tblproducto > tbody").html("");
            $.each(resultado, function(index, value) {
                $("#tblproducto > tbody").append("<tr>" +
                    "<td>" + value.codigo + "</td>" +
                    "<td>" + value.nombre + "</td>" +
                    "<td>" + value.precio + "</td>" +
                    "<td>" + value.categoriaProducto.categoriaNombre + "</td>" +
                    "<td>" + value.proveedor.nombreProveedor + "</td>" +
                    "<td>" +
                    "<button type='button' class='btn btn-info btnactualizar'" +
                    " data-prodcod='" + value.codigo + "'" +
                    " data-prodname='" + value.nombre + "'" +
                    " data-produnit='" + value.precio + "'" +
                    " data-prodcateg='" + value.categoriaProducto.codigoCategoriaProducto + "'" +
                    " data-prodprov='" + value.proveedor.codigoProveedor + "'" +
                    "><i class='fas fa-edit'></i></button></td></tr>"
                );
            });
        }
    });
}