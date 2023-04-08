package BackEnd.P_I.Service;


import BackEnd.P_I.Entities.Domicilio;
import BackEnd.P_I.Entities.Paciente;
import BackEnd.P_I.Exceptions.BadRequestException;
import BackEnd.P_I.Exceptions.ConflictEmailException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
     void guardarPaciente() throws ConflictEmailException, BadRequestException {

        Paciente paciente= new Paciente();
        paciente.setId(1L);
        paciente.setApellido("Lucas");
        paciente.setNombre("Perez");
        paciente.setDocumento("40546890");
        paciente.setFechaIngreso(LocalDate.of(2022,3,29));
        paciente.setEmail("aaa");

        Domicilio  domicilio = new Domicilio();
        domicilio.setId(1L);
        domicilio.setCalle("Ciudad de la Paz");
        domicilio.setNumero("18");
        domicilio.setLocalidad("Belgrano");
        domicilio.setProvincia("CABA");
        paciente.setDomicilio(domicilio);
        pacienteService.guardarPaciente(paciente);

        Paciente paciente3 = new Paciente();
        paciente.setId(2L);
        paciente.setApellido("Lucio");
        paciente.setNombre("Gonzalez");
        paciente.setDocumento("38794576");
        paciente.setFechaIngreso(LocalDate.of(2022,3,29));
        paciente.setEmail("bbb");

        Domicilio  domicilio2 = new Domicilio();
        domicilio.setId(2L);
        domicilio.setCalle("Vidt");
        domicilio.setNumero("34");
        domicilio.setLocalidad("Palermo");
        domicilio.setProvincia("CABA");
        paciente.setDomicilio(domicilio2);

        pacienteService.guardarPaciente(paciente3);

        assertTrue(pacienteService.buscarPaciente(2L) != null);


    }
    @Test
    @Order(2)
        void buscarPaciente() throws BadRequestException {
       Optional<Paciente> paciente = pacienteService.buscarPaciente(1L);
       assertEquals(1,paciente.get().getId());
    }

    @Test
    @Order(3)
     void buscarXemail() throws BadRequestException {
        Optional<Paciente> paciente = pacienteService.buscarXemail("aaa");
        assertEquals("aaa",paciente.get().getEmail());
    }

    @Test
    @Order(4)
    void buscarTodosPacientes() {
        List<Paciente> paciente =pacienteService.buscarTodosPacientes() ;
        assertEquals(2,paciente.size());
    }

    @Test
    @Order(5)
    void actualizarPaciente() throws BadRequestException {
        Paciente paciente = new Paciente();
        paciente.setId(2L);
        paciente.setNombre("Luis");
        paciente.setApellido("Quesada");
        paciente.setEmail("sera@gmail.com");
        paciente.setFechaIngreso(LocalDate.of(2022,3,29));
        paciente.setDocumento("38798564");

        Domicilio  domicilio = new Domicilio();
        domicilio.setId(2L);
        domicilio.setCalle("Vidt");
        domicilio.setLocalidad("Palermo");
        domicilio.setNumero("3456");
        domicilio.setProvincia("CABA");
        paciente.setDomicilio(domicilio);

        pacienteService.actualizarPaciente(paciente);
        assertEquals("Luis", pacienteService.buscarPaciente(2L).get().getNombre());
    }

    @Test
    @Order(6)
     void eliminarXid() throws BadRequestException {
        pacienteService.eliminarXid(2L);
        assertEquals(1,pacienteService.buscarTodosPacientes().size());
    }
}
