// Nota: el flujo de "Editar" en este proyecto se resuelve en el servidor
// (el boton Editar envia un POST a AlumnoServlet, que llena el formulario
// de arriba con los datos del alumno seleccionado). Esta funcion queda
// disponible por si se quiere implementar edicion 100% en el cliente.
function editar(nl, nombre, paterno, materno, ddi, dwi, ecbd) {
    document.getElementById('tfNL').value = nl;
    document.getElementById('tfNLold').value = nl;
    document.getElementById('tfNombre').value = nombre;
    document.getElementById('tfPaterno').value = paterno;
    document.getElementById('tfMaterno').value = materno;
    document.getElementById('tfDDI').value = ddi;
    document.getElementById('tfDWI').value = dwi;
    document.getElementById('tfECBD').value = ecbd;
    document.getElementById('accion').value = 'Modificar';
    document.getElementById('btnAccion').value = 'Modificar';
    window.scrollTo({ top: 0, behavior: "smooth" });
}
