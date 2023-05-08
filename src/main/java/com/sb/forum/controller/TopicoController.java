package com.sb.forum.controller;

import com.sb.forum.dtos.TopicoDto;
import com.sb.forum.services.TopicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/topicos")
public class TopicoController {

    private TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public List<TopicoDto> getAll(){
        return topicoService.getAll();
    }

    @GetMapping(path = "/{id}")
    public TopicoDto getById(@PathVariable Long id){
        return topicoService.getById(id);
    }

    @GetMapping(path = "/busca-conteudo")
    public ResponseEntity<List<TopicoDto>> getByConteudo(@RequestBody TopicoDto topicoDto){
        return ResponseEntity.ok(topicoService.getByTopicoConteudo(topicoDto.getConteudo()));
    }
}
