package per.chc.spring.gestionUsuario.service;

import per.chc.spring.gestionUsuario.web.dto.UsuarioDTO;
import java.util.List;

/**
 * Interfaz que utilizaremos para declarar todos los metodos que seran implementados después por otras clases.
 */
public interface IUsuarioService {
    /**
     * Metodo para crear usuario en la base de datos
     * @param user
     * @param pass
     * @return
     */
    UsuarioDTO crearUsuario(String user, String pass);

    /**
     * Metodo para recuperar todos los usuarios
     * @return
     */
    List<UsuarioDTO> getUsuarios();

    /**
     * Metodo que devuelve un usuario dando su user y pass
     * @param user
     * @param pass
     * @return
     */
    UsuarioDTO getUsuario(String user, String pass);

    /**
     * Metodo que devuelve un usuario introduciendo el id
     * @param idUsuario
     * @return
     */

    UsuarioDTO getUsuario(Long idUsuario);

    /**
     * Metodo que actualiza un usuario dado
     * @param usuarioActualizado
     * @return
     */
    UsuarioDTO actualizarUsuarios(UsuarioDTO usuarioActualizado);

    /**
     * Metodo que elimina un usuario dado y detona la eliminación de todos sus datos
     * llamando a servios dependientes.
     * @param idUsuario
     * @return
     */
    UsuarioDTO eliminarUsuario(Long idUsuario);

}

