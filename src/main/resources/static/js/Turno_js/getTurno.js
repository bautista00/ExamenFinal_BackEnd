
window.addEventListener('load', function () {
    (function(){

const url = '/turnos';
const settings = {
  method: 'GET'
}

fetch(url,settings)
    .then(response => response.json())
    .then(data => {

  for(turno of data){

    var table = document.getElementById("turnosTableBody");
    var turnoRow = table.insertRow();
    let tr_id = 'tr_' + turno.id;
    turnoRow.id = tr_id;
    let deleteButton = '<button' +
                              ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                              ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                              '&times' +
                              '</button>';


    let updateButton = '<button' +
                              ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                              ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                              paciente.id +
                              '</button>';

    turnoRow.innerHTML =
            '<td class=\"td_id\">' + turno.id + '</td>' +
            '<td class=\"td_nombre\">' + turno.paciente_id + '</td>' +
            '<td class=\"td_apellido\">' + turno.odontologo_id + '</td>' +
            '<td class=\"td_apellido\">' + turno.fecha.toString() + '</td>' +
            '<td class=\"updateButton\">' + updateButton + '</td>' +
           '<td class=\"deleteButton\">' + deleteButton + '</td>';
        }
    })
 })

    (function(){
    let pathname = window.location.pathname;
    if (pathname == "/listarTurno.html") {
    document.querySelector(".nav .nav-item a:last").addClass("active");
      }
   })

})