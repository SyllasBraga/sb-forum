package com.sb.forum.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UsuarioTopicoDto {
    private UUID id;
    private String nome;
}
