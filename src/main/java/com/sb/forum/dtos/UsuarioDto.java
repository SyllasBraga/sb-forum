package com.sb.forum.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDto {

    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    private String nome;

    @NotNull
    @Email
    private String login;
}
