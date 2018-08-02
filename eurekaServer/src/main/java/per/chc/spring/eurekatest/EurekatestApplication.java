package per.chc.spring.eurekatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Levantamos el servidor Eureka con la anotación.
 * Añadimos en el application.properties el  puerto de eureka en el que se levanta
 * Habilitamos las propiedades para que se puedan registrar clientes.
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekatestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekatestApplication.class, args);
    }
}
