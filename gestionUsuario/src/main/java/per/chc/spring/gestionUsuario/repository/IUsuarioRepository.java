package per.chc.spring.gestionUsuario.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import per.chc.spring.gestionUsuario.entity.UsuarioEntity;

/**
 * Interfaz que se utiliza para implementar JpaRepository.
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
   @Query(value = "SELECT * FROM usuario WHERE user = :user AND pass = :pass",nativeQuery = true)
   UsuarioEntity findByUsuarioEntityByUserAndPass (@Param("user") String user, @Param("pass") String pass);

}
