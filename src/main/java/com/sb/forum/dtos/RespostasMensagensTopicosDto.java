package com.sb.forum.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class RespostasMensagensTopicosDto {

    private Long id;

    @Size(min = 5, max = 500, message = "Deve conter de 5 à 500 caracteres")
    private String respostaConteudo;

    @NotBlank(message = "Não pode ser nulo.")
    private Date dataPublicacao;

    @JsonBackReference
    private MensagensTopicoDto idMensagem;

    @NotBlank(message = "Não pode ser nulo.")
    private UsuarioDto idUsuario;
}
