package BackEnd.P_I.Entities;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String apellido;
    @Column
    private String nombre;
    @Column
    private String documento;
    @Column
    private LocalDate fechaIngreso;
    @Column
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente",fetch = FetchType.LAZY)
    private Set<Turno> turnos = new HashSet<>();

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }


}
