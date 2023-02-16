package com.sb.forum.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class RespostasMensagensTopicosDto {

    private Long id;

    @Size(min = 5, max = 500)
    private String respostaConteudo;

    @NotNull
    private Date dataPublicacao;

    @JsonBackReference
    private MensagensTopicoDto idMensagem;

    @NotNull
    private UsuarioDto idUsuario;
}
