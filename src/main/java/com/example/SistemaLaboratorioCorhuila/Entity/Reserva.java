package com.example.SistemaLaboratorioCorhuila.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    @Column(name = "fecha_reserva",nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "hora_inicio",nullable = false)
    private LocalDateTime horaInicio;

    @Column(name = "hora_fin",nullable = false)
    private LocalDateTime horaFin;

    @Column(name = "estado",nullable = false)
    private boolean estado;
}
