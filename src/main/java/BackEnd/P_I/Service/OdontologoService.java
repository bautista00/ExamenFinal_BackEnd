package BackEnd.P_I.Service;


import BackEnd.P_I.Entities.Odontologo;
import BackEnd.P_I.Exceptions.BadRequestException;
import BackEnd.P_I.Repository.OdontologoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private final OdontologoRepository odontologoRepository;



    public OdontologoService(OdontologoRepository odontologoRepository){
        this.odontologoRepository = odontologoRepository;
    }


    public void guardarOdontologo(Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarOdontologo(Long id) throws BadRequestException {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            return odontologo;
        }
        throw new BadRequestException("No existe un odontologo con ese id: " + id);
    }

    public List<Odontologo> buscarTodosOdontolgos(){
        return odontologoRepository.findAll();
    }

    public void actualizarOdontologo(Odontologo odontologo)  throws  BadRequestException{
        if(odontologoRepository.findById(odontologo.getId()).isPresent()){
            odontologoRepository.save(odontologo);
        }else {
            throw new BadRequestException("No se pudo actualizar el odontologo correctamente");
        }
    }

    public void eliminarOdontologo(Long id) throws BadRequestException {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            odontologoRepository.deleteById(id);
        }
        else{
            throw new BadRequestException("No existe el paciente con ese id: " + id);
        }
    }
}