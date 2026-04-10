package com.example.usuarioApi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PARTICIPANTES_CHAT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantesChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participantes_chat")
    private Integer idParticipantesChat;

    @ManyToOne
    @JoinColumn(name = "id_chat_inbox", nullable = false)
    private ChatInbox chatInbox;

    @ManyToOne
    @JoinColumn(name = "id_usuario_paticipante", nullable = false)
    private Usuario usuario;
}