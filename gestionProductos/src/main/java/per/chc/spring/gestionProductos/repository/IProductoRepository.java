package per.chc.spring.gestionProductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import per.chc.spring.gestionProductos.entity.ProductoEntity;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository <ProductoEntity, Long> {
    /**
     * Este metodo hace una busqueda en base de datos con la variable que le indicamos en el nombre del metodo
     * Este metodo no esta definido en ningún sitio.
     * Spring usa la reflexion para definir el metodo.
     * @param idUsuario
     * @return
     */
    List<ProductoEntity> findByIdUsuario(Long idUsuario);
    List<ProductoEntity> deleteByIdUsuario(Long idUsuario);
}
