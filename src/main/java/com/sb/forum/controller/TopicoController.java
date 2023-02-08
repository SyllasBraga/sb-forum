package com.sb.forum.controller;

import com.sb.forum.entities.Topico;
import com.sb.forum.services.TopicoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/topicos")
public class TopicoController {

    private TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public List<Topico> getAll(){
        return topicoService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Topico getById(@PathVariable Long id){
        return topicoService.getById(id);
    }
}
