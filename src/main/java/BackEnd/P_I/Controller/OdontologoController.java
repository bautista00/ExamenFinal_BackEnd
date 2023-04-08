package BackEnd.P_I.Controller;

import BackEnd.P_I.Entities.Odontologo;
import BackEnd.P_I.Exceptions.BadRequestException;
import BackEnd.P_I.Service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registroOdontologo")
    public ResponseEntity<?> registrarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.guardarOdontologo(odontologo);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Odontologo> buscarOdontologo(@PathVariable("id") Long id) throws BadRequestException {
        return odontologoService.buscarOdontologo(id);
    }

    @GetMapping("/buscarOdontologos")
    public List<Odontologo> buscarTodosOdontologos(){
        return odontologoService.buscarTodosOdontolgos();
    }

    @PutMapping
    public ResponseEntity<?> actualizarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        odontologoService.actualizarOdontologo(odontologo);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable("id")Long id) throws BadRequestException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("El odontologo con el id " + id + " ha sido borrado correctamente");
    }
}
