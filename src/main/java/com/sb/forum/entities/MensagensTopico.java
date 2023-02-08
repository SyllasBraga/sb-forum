package com.sb.forum.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class MensagensTopico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagemConteudo;

    @OneToOne
    private Topico topico;

    @OneToOne
    private Usuario usuario;

    @OneToMany
    private List<RespostasMensagensTopico> respostasMensagem;
}
