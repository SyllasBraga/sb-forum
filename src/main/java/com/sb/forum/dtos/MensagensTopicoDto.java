package com.sb.forum.dtos;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MensagensTopicoDto {

    private Long id;

    @Size(min = 5, max = 500, message = "Deve conter de 5 à 500 caracteres")
    private String mensagemConteudo;

    @NotBlank(message = "Não pode ser nulo.")
    private Date dataPublicacao;
    @NotBlank(message = "Não pode ser nulo.")
    @JsonBackReference
    private TopicoDto idTopico;
    @NotBlank(message = "Não pode ser nulo.")
    private UsuarioTopicoDto idUsuario;

    @JsonManagedReference
    private List<RespostasMensagensTopicosDto> listaRespostas;
}
