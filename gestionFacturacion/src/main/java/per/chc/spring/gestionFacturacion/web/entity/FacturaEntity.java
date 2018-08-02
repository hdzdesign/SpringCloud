package per.chc.spring.gestionFacturacion.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facturas")
public class FacturaEntity {

    private Long idFactura;

    @Column(name = "nombre_empresa_emisora")
    private String nombreEmpresaEmisora;
    @Column(name = "nombre_empresa_receptora")
    private String nombreEmpresaReceptora;
    private String direccion;
    private String CP;
    private String total;
    private String NIF;
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.DATE)
    private Date fechaYHora;
}
