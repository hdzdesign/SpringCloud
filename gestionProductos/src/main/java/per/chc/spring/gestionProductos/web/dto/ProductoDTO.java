package per.chc.spring.gestionProductos.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductoDTO {

    private Long idProducto;
    private Long idUsuario;
    private String nombre;
    private String codigo;
}
