package com.example.usuarioApi.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OFICIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Oficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oficio")
    private Integer idOficio;

    @Column(name = "nombre_oficio", nullable = false, length = 100)
    private String nombreOficio;
}