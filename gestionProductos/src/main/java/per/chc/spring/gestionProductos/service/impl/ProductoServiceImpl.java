package per.chc.spring.gestionProductos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.chc.spring.gestionProductos.entity.ProductoEntity;
import per.chc.spring.gestionProductos.repository.IProductoRepository;
import per.chc.spring.gestionProductos.service.IProductoService;
import per.chc.spring.gestionProductos.web.dto.ProductoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    IProductoRepository productoRepository;

    @Override
    public ProductoDTO crearProducto(Long idProducto, String name, String codigo) {

        return null;
    }

    @Override
    public List<ProductoDTO> getAllProductos() {
        List<ProductoDTO> collect = productoRepository.findAll()
                .stream()
                .map(
                        entity -> new ProductoDTO(
                                entity.getIdProducto(),
                                entity.getIdUsuario(),
                                entity.getNombre(),
                                entity.getCodigo()
                        )
                ).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ProductoDTO getProducto(String name) {
        return null;
    }

    @Override
    public ProductoDTO getProducto(Long idProducto) {
        return null;
    }

    @Override
    public List<ProductoDTO> getProductoByIdUsuario(Long idUsuario) {
        /**
         * Sentencia que convierte una lista de Entity a una lista de DTO
         */
        List<ProductoDTO> collect = productoRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(entity -> new ProductoDTO(
                        entity.getIdProducto(),
                        entity.getIdUsuario(),
                        entity.getNombre(),
                        entity.getCodigo()
                )).collect(Collectors.toList());
        return collect;
    }

    /**
     * Metodo que nos crea un producto dependiendo del usuario
     *
     * @param idUsuario
     * @param producto
     * @return
     */
    @Override
    public ProductoDTO crearProductoByUsuario(Long idUsuario, ProductoDTO producto) {
        ProductoEntity entity = new ProductoEntity(
                null
                ,producto.getIdUsuario()
                ,producto.getNombre()
                ,producto.getCodigo());
        ProductoEntity resultadoEntity = productoRepository.save(entity);
        return new ProductoDTO(
                resultadoEntity.getIdProducto(),
                resultadoEntity.getIdUsuario(),
                resultadoEntity.getNombre(),
                resultadoEntity.getCodigo());
    }

    @Override
    public List<ProductoDTO> eliminarProductosByUsuario(Long idUsuario) {
        List<ProductoEntity> productosEliminados = productoRepository.deleteByIdUsuario(idUsuario);
        return productosEliminados.stream().map(entity -> new ProductoDTO(
                entity.getIdProducto(),
                entity.getIdUsuario(),
                entity.getNombre(),
                entity.getCodigo()
        )).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoActualizado) {
        return null;
    }

    @Override
    public ProductoDTO eliminarProducto(Long id) {
        return null;
    }
}
