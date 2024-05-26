package com.example.SistemaLaboratorioCorhuila.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "correo", unique = true, nullable = false)
    private String correo;

    @Column(name = "telefono",unique = true, nullable = false)
    private String telefono;

    @Column(name = "estado",nullable = false)
    private boolean estado;

    @Column(name = "fecha_eliminacion")
    private LocalDateTime deleteAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
