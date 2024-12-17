package com.vasquez.infraccion_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "infracciones") 
@EntityListeners(AuditingEntityListener.class)
public class Infraccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(name = "dni", nullable = false, length = 8)  
    private String dni;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)  
    private Date fecha;

    @Column(name = "tipo_infraccion", nullable = false, length = 20) 
    private String tipoInfraccion;

    @Column(name = "ubicacion", nullable = false, length = 200) 
    private String ubicacion;

    @Column(name = "descripcion", length = 255) 
    private String descripcion;

    @Column(name = "monto_multa", nullable = false, precision = 8, scale = 2) 
    private BigDecimal montoMulta;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
}
