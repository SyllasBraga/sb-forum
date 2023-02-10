package com.sb.forum.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class RespostasMensagensTopico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String respostaConteudo;
    private Date dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "id_mensagem")
    @JsonBackReference
    private MensagensTopico idMensagem;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

}
