package per.chc.spring.gestionProductos.service;

import per.chc.spring.gestionProductos.web.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {
    /**
     * Metodo para crear un producto en base de datos.
     * @param idProducto
     * @param name
     * @return Un productoDTO
     */
    ProductoDTO crearProducto(Long idProducto, String name, String codigo);

    /**
     * Metodo para almacenar en una lista todos los productos.
     * @return Lista de todos los productos DTO
     */
    List<ProductoDTO> getAllProductos();

    /**
     * Metodo para buscar un producto por el nombre
     * @param name
     * @return nos devuelve el producto buscado
     */
    ProductoDTO getProducto(String name);

    /**
     * Metodo para recuperar un producto por el id
     * @param idProducto
     * @return nos devuelve el producto por su id
     */
    ProductoDTO getProducto(Long idProducto);

    /**
     * Metodo para recuperar un producto indicando el usuario
     * @param idUsuario
     * @return
     */

    List<ProductoDTO> getProductoByIdUsuario(Long idUsuario);

    /**
     * Metodo para crear un producto indicando el usuario
     * @param idUsuario
     * @return
     */

    ProductoDTO crearProductoByUsuario(Long idUsuario, ProductoDTO producto);

    /**
     * Metodo para eliminar un producto indicando el usuario
     * @param idUsuario
     * @return
     */

    List<ProductoDTO> eliminarProductosByUsuario(Long idUsuario);

    /**
     * Metodo para actualizar un producto pasando el producto actualizado
     * @param productoActualizado
     * @return un producto actualizado
     */
    ProductoDTO actualizarProducto(ProductoDTO productoActualizado);

    /**
     * metodo para borrar un prducto por su id
     * @param id
     * @return un producto eliminado.
     */
    ProductoDTO eliminarProducto(Long id);

}
