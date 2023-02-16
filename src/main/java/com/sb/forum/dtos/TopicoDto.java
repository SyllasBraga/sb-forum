package com.sb.forum.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TopicoDto {

    private Long id;

    @NotBlank(message = "Não pode ser nulo.")
    private String titulo;

    @Size(min = 10, max = 1000, message = "Deve conter de 5 à 1000 caracteres")
    private String conteudo;

    @NotBlank(message = "Não pode ser nulo.")
    private Date dataPublicacao;

    private UsuarioDto idAutor;

    @JsonManagedReference
    private List<MensagensTopicoDto> listMensagens;
}
