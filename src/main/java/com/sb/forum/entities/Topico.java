package com.sb.forum.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String conteudo;
    private Date dataPublicacao;

    @OneToOne
    @JoinColumn(name = "id")
    private Usuario idAutor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTopico", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<MensagensTopico> listMensagens;
}
