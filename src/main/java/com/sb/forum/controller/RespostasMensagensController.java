package com.sb.forum.controller;

import com.sb.forum.dtos.RespostasMensagensTopicosDto;
import com.sb.forum.services.RespostasMensagensService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos/{idtopico}/mensagens/{idmensagem}/respostas")
public class RespostasMensagensController {

    private final RespostasMensagensService respostasService;

    public RespostasMensagensController(RespostasMensagensService respostasService) {
        this.respostasService = respostasService;
    }

    @PostMapping
    public ResponseEntity<RespostasMensagensTopicosDto> create(@PathVariable Long idmensagem,
                                                               @Valid @RequestBody RespostasMensagensTopicosDto respostaDto){
        return ResponseEntity.ok(respostasService.create(idmensagem, respostaDto));
    }

    @PutMapping(path = "/{idresposta}")
    public ResponseEntity<RespostasMensagensTopicosDto> update(@PathVariable Long idmensagem,
                                                               @PathVariable Long idresposta,
                                                               @Valid @RequestBody RespostasMensagensTopicosDto respostaDto){
        return ResponseEntity.ok(respostasService.update(idmensagem, idresposta, respostaDto));
    }

    @DeleteMapping(path = "/{idresposta}")
    public ResponseEntity<String> delete(@PathVariable Long idmensagem,
                                         @PathVariable Long idresposta){
        return ResponseEntity.ok(respostasService.delete(idresposta));
    }
}
