package com.sb.forum.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String conteudo;

    @OneToOne
    private Usuario usuario;
}
