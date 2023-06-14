package com.sb.forum.dtos;

import com.sb.forum.entities.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long id;

    @Size(min = 3, max = 255, message = "Deve conter de 3 à 255 caracteres")
    private String nome;

    @Email(message = "Insira um email valido.")
    private String login;

    @Size(min = 8, max = 255, message = "Deve conter de 8 à 255 caracteres")
    private String senha;

    private List<RolesDto> acessos;
}
