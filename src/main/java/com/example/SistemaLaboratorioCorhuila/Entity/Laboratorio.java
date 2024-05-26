package com.example.SistemaLaboratorioCorhuila.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "laboratorios")
public class Laboratorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    @Column(name = "estado",nullable = false)
    private boolean estado;

    @Column(name = "fecha_eliminacion")
    private LocalDateTime deleteAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_laboratorio_id")
    TipoLaboratorio tipoLaboratorio;
}
