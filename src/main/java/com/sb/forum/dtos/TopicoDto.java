package com.sb.forum.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoDto {

    private Long id;

    @NotBlank(message = "Não pode ser nulo.")
    @Size(min = 5, max = 100, message = "Deve conter de 5 à 100 caracteres")
    private String titulo;

    @Size(min = 100, max = 10000, message = "Deve conter de 100 à 10000 caracteres")
    private String conteudo;

    @PastOrPresent(message = "Não pode ser nulo e nem futura.")
    private Date dataPublicacao;

    private UsuarioTopicoDto idAutor;

    @JsonManagedReference
    private List<MensagensTopicoDto> listMensagens;
}
