package com.sb.forum.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private UsuarioDto idAutor;

    @JsonManagedReference
    private List<MensagensTopicoDto> listMensagens;
}
