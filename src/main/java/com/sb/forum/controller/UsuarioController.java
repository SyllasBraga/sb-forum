package com.sb.forum.controller;

import com.sb.forum.dtos.TopicoDto;
import com.sb.forum.dtos.UsuarioDto;
import com.sb.forum.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

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
    public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuario) {
        return ResponseEntity.ok(usuarioService.update(id, usuario));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.delete(id));
    }

}
