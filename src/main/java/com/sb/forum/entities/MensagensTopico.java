package com.sb.forum.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class MensagensTopico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagemConteudo;
    private Date dataPublicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "id_topico")
    private Topico idTopico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMensagem", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RespostasMensagensTopico> listaRespostas;
}
