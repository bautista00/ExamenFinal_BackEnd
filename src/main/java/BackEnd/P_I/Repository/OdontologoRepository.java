package BackEnd.P_I.Repository;

import BackEnd.P_I.Entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {

}
