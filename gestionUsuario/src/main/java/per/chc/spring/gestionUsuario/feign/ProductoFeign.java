package per.chc.spring.gestionUsuario.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ms-gestion-producto")
public interface ProductoFeign {
    @RequestMapping(value = "producto/{idUsuario}", method = RequestMethod.DELETE)
    ResponseEntity<String> eliminarProductoByIdUsuario(Long idUsuario);
}
