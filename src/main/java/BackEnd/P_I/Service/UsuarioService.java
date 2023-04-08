package BackEnd.P_I.Service;

import BackEnd.P_I.Entities.Usuario;
import BackEnd.P_I.Repository.UsuarioRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService  implements UserDetailsService {
    private UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Usuario> usuarioABuscar=usuarioRepository.findByEmail(userName);
        if (usuarioABuscar.isPresent()){
            return usuarioABuscar.get();
        }
        else{
            throw new UsernameNotFoundException("Error. Usuario con email "+userName+" no encontrado en la base de datos");
        }
    }

}
