package com.example.usuarioApi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Table(name = "BLOQUEO_USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloqueoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloqueo")
    private Integer idBloqueo;

    @ManyToOne
    @JoinColumn(name = "id_usuario_que_bloquea", nullable = false)
    private Usuario usuarioQueBloquea;

    @ManyToOne
    @JoinColumn(name = "id_usuario_bloqueado", nullable = false)
    private Usuario usuarioBloqueado;

    @Column(name = "fecha_bloqueo", insertable = false, updatable = false)
    private Timestamp fechaBloqueo;
}
