package BackEnd.P_I.Entities;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String apellido;
    @Column
    private String nombre;
    @Column
    private String matricula;

   @OneToMany(mappedBy = "odontologo")
   private Set<Turno> turnos= new HashSet<>();

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


}
