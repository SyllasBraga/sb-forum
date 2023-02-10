package com.sb.forum.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RespostasMensagensTopico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String respostaConteudo;

    @ManyToOne
    @JoinColumn(name = "id_mensagem")
    @JsonBackReference
    private MensagensTopico idMensagem;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id_usuario")
    private Usuario idUsuario;

}
