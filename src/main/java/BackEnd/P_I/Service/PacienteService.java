package BackEnd.P_I.Service;



import BackEnd.P_I.Entities.Paciente;
import BackEnd.P_I.Exceptions.BadRequestException;
import BackEnd.P_I.Exceptions.ConflictEmailException;
import BackEnd.P_I.Repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PacienteService {


    private PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }

    @Autowired
    ObjectMapper mapper;

    public void guardarPaciente(Paciente paciente) throws ConflictEmailException {
        Optional<Paciente> pacienteAVerificar = pacienteRepository.findByEmail(paciente.getEmail());
        if (pacienteAVerificar.isPresent()){
            throw new ConflictEmailException("Ya existe un paciente con ese email");
        }else{
            pacienteRepository.save(paciente);
        }
    }

    public Optional<Paciente> buscarPaciente(Long id) throws BadRequestException{
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        if(pacienteBuscado.isPresent()){
            return pacienteBuscado;

        }else{
             throw new BadRequestException("No se encuentra un paciente con ese id: " + id);
        }
    }

    public List<Paciente> buscarTodosPacientes(){
        return pacienteRepository.findAll();
    }


    public Optional<Paciente> buscarXemail(String email) throws  BadRequestException{
        Optional<Paciente> pacienteBuscado = pacienteRepository.findByEmail(email);
        if(pacienteBuscado.isPresent()){
            return pacienteBuscado;
        }else{
            throw new BadRequestException("No se pudo encontrar el paciente con ese email: " + email);
        }
    }


    public void actualizarPaciente(Paciente paciente) throws BadRequestException{
        if(pacienteRepository.findById(paciente.getId()).isPresent()){
            pacienteRepository.save(paciente);
        }else{
            throw new BadRequestException("No se pudo actualizar el paciente de forma correcta");
        }

    }

    public void eliminarXid(Long id) throws BadRequestException {
        Optional<Paciente> pacienteEliminado = pacienteRepository.findById(id);
        if(pacienteEliminado.isPresent()){
           pacienteRepository.deleteById(id);
        }else{
           throw new BadRequestException("No se pudo encontrar al paciente con ese id: " + id);
        }
    }

}
