package BackEnd.P_I.Controller;


import BackEnd.P_I.Entities.Paciente;
import BackEnd.P_I.Exceptions.BadRequestException;
import BackEnd.P_I.Exceptions.ConflictEmailException;
import BackEnd.P_I.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registroPaciente")
    public ResponseEntity<?> registrarPaciente(@RequestBody Paciente paciente) throws ConflictEmailException {
        pacienteService.guardarPaciente(paciente);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Paciente> buscarXid(@PathVariable("id") Long id) throws BadRequestException {
        return pacienteService.buscarPaciente(id);
    }

    @GetMapping
    public List<Paciente> todosPacientes(){
        return pacienteService.buscarTodosPacientes();
    }

    @GetMapping("/email/{email}")
    public Optional<Paciente> encontrarXemail(@PathVariable("email") String email) throws BadRequestException {
       return pacienteService.buscarXemail(email);
    }

    @PutMapping
    public ResponseEntity<?> actualizacionPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarXid(@PathVariable("id")Long id) throws BadRequestException {
        pacienteService.eliminarXid(id);
        return ResponseEntity.ok("El paciente con el id: " + id + "ha sido eliminado correctamente");
    }

}
