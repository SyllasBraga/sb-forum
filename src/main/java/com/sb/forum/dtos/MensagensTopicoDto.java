package com.sb.forum.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MensagensTopicoDto {

    private Long id;

    @Size(min = 5, max = 500)
    private String mensagemConteudo;

    @NotNull
    private Date dataPublicacao;
    @NotNull
    private TopicoDto idTopico;
    @NotNull
    private UsuarioDto idUsuario;

    private List<RespostasMensagensTopicosDto> listaRespostas;
}
