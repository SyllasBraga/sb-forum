package com.sb.forum.dtos;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private TopicoDto idTopico;
    @NotNull
    private UsuarioDto idUsuario;

    @JsonManagedReference
    private List<RespostasMensagensTopicosDto> listaRespostas;
}
