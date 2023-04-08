package BackEnd.P_I.Repository;

import BackEnd.P_I.Entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    @Query("select pct from Paciente pct where pct.email =?1")
    Optional<Paciente>findByEmail(String email);
}