package per.chc.spring.gestionUsuario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Clase que se encarga de comunicarse con la base de datos, JPA , Hibernate , para  devolver a la parte JAVA ,
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue
    private Long idUsuario;

    @Column(name = "user")
    private String user;

    @Column(name = "pass")
    private String pass;
}