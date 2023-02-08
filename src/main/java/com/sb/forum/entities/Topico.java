package com.sb.forum.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String conteudo;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id_autor")
    private Usuario usuario;

    @OneToMany
    private List<MensagensTopico> mensagens;
}
