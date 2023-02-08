package com.sb.forum.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RespostasMensagensTopico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String respostaMensagem;

    @OneToOne
    private MensagensTopico mensagensTopico;

    @OneToOne
    private Usuario usuario;
}
