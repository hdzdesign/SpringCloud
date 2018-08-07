package per.chc.spring.gestionProductos.web.rest;

import com.netflix.ribbon.proxy.annotation.Http;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import per.chc.spring.gestionProductos.service.IProductoService;
import per.chc.spring.gestionProductos.web.dto.ProductoDTO;

import java.util.List;

@RestController("/")
public class ProductosResource {

    @Autowired
    IProductoService productoService;

    /**
     * Metodo para devolver el estado de salud del producto.
     * @return
     */
    @RequestMapping(value = "health", method = RequestMethod.GET)
    public ResponseEntity<String> status(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "producto", method = RequestMethod.GET)
    public ResponseEntity<List<ProductoDTO>> getAllProductos(){
        List<ProductoDTO> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @RequestMapping(value = "producto/{idUsuario}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductoDTO>> getProductoByIdUsuario(@PathVariable Long idUsuario) {
        List<ProductoDTO> productoByIdUsuario = productoService.getProductoByIdUsuario(idUsuario);
        return ResponseEntity.ok(productoByIdUsuario);
    }

    @RequestMapping(value = "producto/{idUsuario}", method = RequestMethod.POST)
    public ResponseEntity<Void> crearProductoParaUsuario(@RequestBody ProductoDTO productoDTO,
                                                         @PathVariable Long idUsuario){
        ProductoDTO dto = productoService.crearProductoByUsuario(idUsuario, productoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "producto/{idUsuario}", method = RequestMethod.DELETE)
    public ResponseEntity<List<ProductoDTO>>EliminarProductoByIdUsuario(@PathVariable Long idUsuario){
        System.out.println("Entramos a borrar");
        List<ProductoDTO> productosEliminados = productoService.eliminarProductosByUsuario(idUsuario);
        return ResponseEntity.ok(productosEliminados);
    }
}
