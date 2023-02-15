package com.sb.forum.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TopicoDto {

    private Long id;

    @NotNull
    private String titulo;

    @Size(min = 10, max = 1000)
    private String conteudo;

    @NotNull
    private Date dataPublicacao;

    @NotNull
    private UsuarioDto usuarioDto;

    private List<MensagensTopicoDto> listMensagens;
}
