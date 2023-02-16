package com.sb.forum.controller;

import com.sb.forum.dtos.MensagensTopicoDto;
import com.sb.forum.services.MensagemTopicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/topicos/{idtopico}/mensagens")
public class MensagemTopicoController {

    private MensagemTopicoService msgService;

    public MensagemTopicoController(MensagemTopicoService msgService) {
        this.msgService = msgService;
    }

    @PostMapping
    public ResponseEntity<MensagensTopicoDto> create(@PathVariable Long idtopico, @RequestBody MensagensTopicoDto msg){
        return ResponseEntity.ok(msgService.create(idtopico, msg));
    }

    @PutMapping(path = "/{idmensagem}")
    public ResponseEntity<MensagensTopicoDto> update(@PathVariable Long idtopico, @PathVariable Long idmensagem,
                                                     @RequestBody MensagensTopicoDto msg){
        return ResponseEntity.ok(msgService.update(idtopico, idmensagem, msg));
    }

    @DeleteMapping(path = "/{idmensagem}")
    public ResponseEntity<String> delete(@PathVariable Long idmensagem){
        return ResponseEntity.ok(msgService.delete(idmensagem));
    }
}
