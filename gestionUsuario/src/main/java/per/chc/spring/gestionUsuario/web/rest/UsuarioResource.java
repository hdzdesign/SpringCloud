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
    @RequestMapping(value = "usuario" ,method = RequestMethod.POST)
    public ResponseEntity<Void> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
        log.debug("Usuario Recibido: {}",usuarioDTO.toString());
        UsuarioDTO result = usuarioService.crearUsuario(usuarioDTO.getUser(),usuarioDTO.getPass());

        //Pregntar si no sería mejor insertar esta url desde un archivo en propiedades, preguntar para que se usa el %s
        String location = String.format("http://localhost:8089/usuario/%s",result.getIdUsuario());
        // Clase que nos permite recibir una respuesta on la URI de la localización.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI(location));
        return new ResponseEntity(httpHeaders,HttpStatus.CREATED);



}
    @RequestMapping(value = "usuario/{idUsuario}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioDTO> recuperarUsuarioPorId(@PathVariable(value = "idUsuario") Long idUsuario){

        return ResponseEntity.ok(usuarioService.getUsuario(idUsuario));
    }
    @RequestMapping(value = "usuario/{user}/{pass}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioDTO> recuperarUsuarioRegistrado (@PathVariable String user, @PathVariable String pass){
        UsuarioDTO usuarioDTO = usuarioService.getUsuario(user, pass);
        return ResponseEntity.ok(usuarioDTO);
    }
    @RequestMapping(value = "usuario/{idUsuario}",method = RequestMethod.DELETE)
    public  ResponseEntity<String> eliminarUsuarioYTodosSusProductos(@PathVariable Long idUsuario){
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.ok("Todo Correcto");
    }
}
