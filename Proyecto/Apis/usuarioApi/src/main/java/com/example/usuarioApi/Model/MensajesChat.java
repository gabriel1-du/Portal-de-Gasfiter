package com.example.usuarioApi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Table(name = "MENSAJES_CHAT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajesChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje_chat")
    private Integer idMensajeChat;

    @ManyToOne
    @JoinColumn(name = "id_chat", nullable = false)
    private ChatInbox chatInbox;

    @ManyToOne
    @JoinColumn(name = "id_autor_mensaje", nullable = false)
    private Usuario autor;

    @Lob
    @Column(name = "mensaje_texto", nullable = false)
    private String mensajeTexto;

    @Column(name = "fecha_hora_envio", insertable = false, updatable = false)
    private Timestamp fechaHoraEnvio;
}
