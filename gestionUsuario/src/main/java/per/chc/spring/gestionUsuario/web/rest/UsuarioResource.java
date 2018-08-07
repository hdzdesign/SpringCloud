package per.chc.spring.gestionUsuario.web.rest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import per.chc.spring.gestionUsuario.service.IUsuarioService;
import per.chc.spring.gestionUsuario.web.dto.UsuarioDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController("/")
public class UsuarioResource {
    /**
     * Iyeccion de dependencia de la interfaz IUsuarioService
     */
    @Autowired
    IUsuarioService usuarioService;

    /**
     * Metodo que utilizaremos para indicar al usuario el status de la peticion.
     * @return el estatus de la Entity.
     */
    @RequestMapping(name = "health" ,method = RequestMethod.GET)
    public ResponseEntity<String>getStatus(){
        System.out.println("Todo OK");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Metodo que utilizaremos para crar usuario, recuperando del body el usuario y el pass.
     * @param usuarioDTO
     * @return Status ok de creacion
     */
    /*
        ----- Este metodo estamos mapeando las variables directamente  y recuperandolas igual------

    @RequestMapping(value = "usuario" ,method = RequestMethod.POST)
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO){
      log.debug("Usuario Recibido: {}",usuarioDTO.toString());
      UsuarioDTO result = usuarioService.crearUsuario(usuarioDTO.getUser(),usuarioDTO.getPass());
        return ResponseEntity.ok(result);
    }
    */

    /**
     * Metodo para crear un usuario y que la respuesta sea un HttpHEaders donde nos indica donde se puede recuperar el usuario.
     * @param usuarioDTO
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = "usuario" ,method = RequestMethod.POST)
    public ResponseEntity<Void> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
        log.debug("Usuario Recibido: {}",usuarioDTO.toString());
        UsuarioDTO result = usuarioService.crearUsuario(usuarioDTO.getUser(),usuarioDTO.getPass());
        //Esta url ira en un archivo property
        String location = String.format("http://localhost:8089/usuario/%s",result.getIdUsuario());
        // Clase que nos permite recibir una respuesta on la URI de la localización.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI(location));
        return new ResponseEntity(httpHeaders,HttpStatus.CREATED);
}
        @RequestMapping(value = "usuario/todos", method = RequestMethod.GET)
        public ResponseEntity<List<UsuarioDTO>> recuperarTodosLosUsuarios(){
        List<UsuarioDTO> listaUsuarios = usuarioService.getListaUsuarios();
        return ResponseEntity.ok(listaUsuarios);
}

    /**
     * Metodo que utlilizamos para recuperar el usuario dando su idUsuario.
     * @param idUsuario
     * @return
     */
    @RequestMapping(value = "usuario/{idUsuario}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioDTO> recuperarUsuarioPorId(@PathVariable(value = "idUsuario") Long idUsuario){

        return ResponseEntity.ok(usuarioService.getUsuario(idUsuario));
    }

    /**
     * Metodo que utiliamos para recuperar un usuario dando su nombre de usuario y contraseña
     * Respondemos un UsuarioDTO con la información recuperada del servidor.
     * @return
     */
    @RequestMapping(value = "usuario/{user}/{pass}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioDTO> recuperarUsuarioRegistrado(@PathVariable String user, @PathVariable String pass){
        UsuarioDTO usuarioDTO = usuarioService.getUsuario(user, pass);
        return ResponseEntity.ok(usuarioDTO);
    }

    /**
     * Metodo que Elimina el usuario y desencadena una llamada a producto para poder borrar todos sus productos asociados.
     * @param idUsuario
     * @return
     */
    @RequestMapping(value = "usuario/{idUsuario}",method = RequestMethod.DELETE)
    public  ResponseEntity<String> eliminarUsuarioYTodosSusProductos(@PathVariable Long idUsuario){
        System.out.println("Todo ELIMINADO");
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.ok("Todo Correcto");
    }
}
