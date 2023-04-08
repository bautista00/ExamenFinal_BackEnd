package BackEnd.P_I.Security;


import BackEnd.P_I.Entities.RollUsuario;
import BackEnd.P_I.Entities.Usuario;
import BackEnd.P_I.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {


    private UsuarioRepository usuarioRepository;

    @Autowired
    public CargadoraDeDatos(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passACifrar="181222";
        String passCifrada=cifrador.encode(passACifrar);
        Usuario usuarioAInsertar= new Usuario("Bautista","Quesada",
                "bauti@gmail.com", passCifrada,RollUsuario.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);

        BCryptPasswordEncoder cifradorAdmin = new BCryptPasswordEncoder();
        String passAdmin ="788622";
        String passAdminLista = cifradorAdmin.encode(passAdmin);
        Usuario usuarioAdmin = new Usuario("Rodolfo","Baspineiro",
                "rodo@gmail.com",passAdminLista,RollUsuario.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);

    }

}

