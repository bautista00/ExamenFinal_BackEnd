package BackEnd.P_I.Service;


import BackEnd.P_I.Entities.Odontologo;
import BackEnd.P_I.Entities.Paciente;
import BackEnd.P_I.Entities.Turno;
import BackEnd.P_I.Entities.TurnoDto;
import BackEnd.P_I.Exceptions.BadRequestException;
import BackEnd.P_I.Repository.OdontologoRepository;
import BackEnd.P_I.Repository.PacienteRepository;
import BackEnd.P_I.Repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private TurnoRepository turnoRepository;

    private OdontologoRepository odontologoRepository;

    private PacienteRepository pacienteRepository;

   @Autowired
   ObjectMapper mapper;


    public TurnoService(TurnoRepository turnoRepository, OdontologoRepository odontologoRepository, PacienteRepository pacienteRepository) {
        this.turnoRepository = turnoRepository;
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public TurnoDto  guardarTurno (TurnoDto turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(turno.getPaciente_id());
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(turno.getOdontologo_id());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            return convertirTurnoaTurnoDto(turnoRepository.save(convertirTurnoDtoaTurno(turno)));
        }
        else {
            throw new BadRequestException("Error al crear turno, no se encuentra el paciente o odontologo");
        }
    }

    public Optional<TurnoDto> buscarTurno(Long id) throws BadRequestException {
       Optional<Turno> turnoBuscado = turnoRepository.findById(id);
       if (turnoBuscado.isPresent()){
           return Optional.of(convertirTurnoaTurnoDto(turnoBuscado.get()));
       }else{
            throw new BadRequestException("No se pudo encontrar el turno con ese id: " + id);
       }
   }

   public List<TurnoDto> buscarTodosLosTurnos(){
       List<Turno> listaTurnos = turnoRepository.findAll();
       List<TurnoDto> listaTurnoDto = new ArrayList<>();
       for (Turno  listaTurno: listaTurnos) {
           listaTurnoDto.add(convertirTurnoaTurnoDto(listaTurno));
       }
       return listaTurnoDto;
   }


    public  TurnoDto actualizarTurno(TurnoDto turno) throws BadRequestException {
        if(turnoRepository.findById(turno.getId()).isPresent()){
            turnoRepository.save(convertirTurnoDtoaTurno(turno));
        }
        else{
            throw new BadRequestException("No se pudo actualizar el turno correctamente");
        }

        return turno;
    }


    public void eliminarTurno(Long id) throws  BadRequestException{
      if(turnoRepository.findById(id).isPresent()){
          turnoRepository.deleteById(id);
      }else{
          throw new BadRequestException("No se pudo actualizar el turno correctamente" + id);
      }
    }


    private Turno convertirTurnoDtoaTurno(TurnoDto turnoDTO){
        Turno turno= new Turno();
        Paciente paciente= new Paciente();
        Odontologo odontologo= new Odontologo();
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        paciente.setId(turnoDTO.getPaciente_id());
        odontologo.setId(turnoDTO.getOdontologo_id());

        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        return turno;
    }
    private TurnoDto convertirTurnoaTurnoDto(Turno turno){
        TurnoDto TurnoDto= new TurnoDto();

        TurnoDto.setId(turno.getId());
        TurnoDto.setOdontologo_id(turno.getOdontologo().getId());
        TurnoDto.setPaciente_id(turno.getPaciente().getId());
        TurnoDto.setFecha(turno.getFecha());


        return TurnoDto;
    }
}

