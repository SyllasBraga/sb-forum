package com.sb.forum.controller;

import com.sb.forum.dtos.TopicoDto;
import com.sb.forum.services.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public ResponseEntity<List<TopicoDto>> getAll(){
        return ResponseEntity.ok(topicoService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TopicoDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(topicoService.getById(id));
    }

    @GetMapping(path = "/busca-conteudo")
    public ResponseEntity<List<TopicoDto>> getByConteudo(@RequestBody TopicoDto topicoDto){
        return ResponseEntity.ok(topicoService.getByTopicoConteudo(topicoDto.getConteudo()));
    }

    @PostMapping
    public ResponseEntity<TopicoDto> createTopico(@PathVariable Long id, @Valid @RequestBody TopicoDto topico){
        return ResponseEntity.ok().body(topicoService.create(topico));
    }

    @PutMapping(path = "/{idTopico}")
    public ResponseEntity<TopicoDto> updateTopico(@PathVariable Long idTopico,
                                                  @Valid @RequestBody TopicoDto topico){
        return ResponseEntity.ok().body(topicoService.update(idTopico, topico));
    }

    @DeleteMapping(path = "/{idTopico}")
    public ResponseEntity<String> deleteTopico(@PathVariable Long idTopico){
        return ResponseEntity.ok().body(topicoService.delete(idTopico));
    }
}
