package com.sb.forum.controller;

import com.sb.forum.dtos.TopicoDto;
import com.sb.forum.dtos.UsuarioDto;
import com.sb.forum.entities.Topico;
import com.sb.forum.entities.Usuario;
import com.sb.forum.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAll() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto usuario) {
        return ResponseEntity.ok(usuarioService.create(usuario));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @RequestBody UsuarioDto usuario) {
        return ResponseEntity.ok(usuarioService.update(id, usuario));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.delete(id));
    }

    @PostMapping(path = "/{id}/topicos")
    public ResponseEntity<TopicoDto> createTopico(@PathVariable Long id, @RequestBody TopicoDto topico){
        return ResponseEntity.ok().body(usuarioService.createTopico(id, topico));
    }

    @PutMapping(path = "/{id}/topicos/{idTopico}")
    public ResponseEntity<TopicoDto> updateTopico(@PathVariable Long id, @PathVariable Long idTopico,
                                               @RequestBody TopicoDto topico){
        return ResponseEntity.ok().body(usuarioService.updateTopico(id, idTopico, topico));
    }

    @DeleteMapping(path = "/{id}/topicos/{idTopico}")
    public ResponseEntity<String> deleteTopico(@PathVariable Long id, @PathVariable Long idTopico){
        return ResponseEntity.ok().body(usuarioService.deleteTopico(id, idTopico));
    }
}
