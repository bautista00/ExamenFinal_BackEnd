package BackEnd.P_I.Controller;


import BackEnd.P_I.Entities.Odontologo;
import BackEnd.P_I.Entities.Paciente;
import BackEnd.P_I.Entities.TurnoDto;
import BackEnd.P_I.Exceptions.BadRequestException;
import BackEnd.P_I.Service.OdontologoService;
import BackEnd.P_I.Service.PacienteService;
import BackEnd.P_I.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping("/guardarTurno")
    public ResponseEntity<TurnoDto> guardarTurno(@RequestBody TurnoDto turno) throws BadRequestException {
        ResponseEntity<TurnoDto> respuesta;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turno.getPaciente_id());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turno.getOdontologo_id());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            respuesta=ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else{
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }


    @GetMapping("/optional/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoOptional(@PathVariable Long id) throws BadRequestException {
        Optional<TurnoDto> turnoBuscado= turnoService.buscarTurno(id);
        if (turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/buscarTurnos")
    public ResponseEntity<List<TurnoDto>> buscarTodosTurnos(){
       ResponseEntity<List<TurnoDto>> respuesta;
       respuesta = ResponseEntity.ok(turnoService.buscarTodosLosTurnos());
       return respuesta;
    }

    @PutMapping("/actualizarTurno")
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDto turno) throws BadRequestException {
        turnoService.actualizarTurno(turno);
        return ResponseEntity.ok("El turno ha sido modificado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws BadRequestException {
        Optional<TurnoDto> turnoBuscado=turnoService.buscarTurno(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se eliminó el turno con id: " + id);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se logró eliminar " +
                    "el turno con id= "+id+" dado que el mismo no existe");
        }
    }

}
