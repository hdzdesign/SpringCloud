package per.chc.spring.gestionUsuario.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import per.chc.spring.gestionUsuario.entity.UsuarioEntity;
import per.chc.spring.gestionUsuario.feign.ProductoFeign;
import per.chc.spring.gestionUsuario.repository.IUsuarioRepository;
import per.chc.spring.gestionUsuario.service.IUsuarioService;
import per.chc.spring.gestionUsuario.web.dto.UsuarioDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    /**
     * Inyeccion de dependencia de la interfaz IUsuarioRepository
     */
    @Autowired
    IUsuarioRepository usuarioRepository;
    @Autowired
    ProductoFeign productoFeign;

    /**
     * Metodo que crea un nuevo usuario en base de datos , autogenerando un ID, y pasandole por parametros user y pass
     * @param user
     * @param pass
     * @return un UsuarioDTO con parametros el ID autogenrado, el usuario dado y el pass dado.
     */
    @Override
    public UsuarioDTO crearUsuario(String user, String pass) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(null,user,pass);
       UsuarioEntity result = usuarioRepository.save(usuarioEntity);

        return new UsuarioDTO(result.getIdUsuario(),result.getUser(),result.getPass());
    }

    @Override
    public List<UsuarioDTO> getListaUsuarios() {

        List<UsuarioDTO> listaUsuarios = usuarioRepository.findAll()
                .stream()
                .map(entity -> new UsuarioDTO(
                                entity.getIdUsuario(),
                                entity.getUser(),
                                entity.getPass()
                        )
                ).collect(Collectors.toList());
        return listaUsuarios;
    }

    @Override
    public UsuarioDTO getUsuario(String user, String pass) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByUsuarioEntityByUserAndPass(user,pass);
        return new UsuarioDTO(usuarioEntity.getIdUsuario(),usuarioEntity.getUser(),usuarioEntity.getPass());
    }

    @Override
    public UsuarioDTO getUsuario(Long idUsuario) {

        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findById(idUsuario);
        UsuarioDTO usuarioDTO = null;
        if(usuarioEntityOptional.isPresent()){
            usuarioDTO = new UsuarioDTO(
                    usuarioEntityOptional.get().getIdUsuario(),
                    usuarioEntityOptional.get().getUser(),
                    usuarioEntityOptional.get().getPass()
            );
        }
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO actualizarUsuarios(UsuarioDTO usuarioActualizado) {


        return null;
    }

    /**
     * Usamos la interfaz RestTemplate, estamos usando el Zuul , recuperamos un objeto pasandole por parametros:
     * La url donde lo va a encotrar y el idUsuario que le viene por parametros.
     * @param idUsuario
     * @return
     */

    @Override
    //@HystrixCommand(fallbackMethod = "eliminarUsuarioDefault")
    public UsuarioDTO eliminarUsuario(Long idUsuario) {
        System.out.println("llego aqui");
        usuarioRepository.deleteById(idUsuario);
        productoFeign.eliminarProductoByIdUsuario(idUsuario);
   /*
     new RestTemplate().
                delete(
                        "http://localhost:8765/ms-gestion-productos/producto/{idUsuario}",
                        idUsuario);
     */
        return new UsuarioDTO();
    }
    private String eliminarUsuarioDefault(){
        return "Wait...";
    }
}
