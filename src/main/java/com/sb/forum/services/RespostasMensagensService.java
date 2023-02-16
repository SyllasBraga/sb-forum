package com.sb.forum.services;

import com.sb.forum.dtos.RespostasMensagensTopicosDto;
import com.sb.forum.entities.RespostasMensagensTopico;
import com.sb.forum.exceptions.NotFoundException;
import com.sb.forum.repository.RespostaMensagensRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RespostasMensagensService {

    private RespostaMensagensRepository respostaRepository;
    private ModelMapper modelMapper;
    private MensagemTopicoService msgService;

    public RespostasMensagensService(RespostaMensagensRepository respostaRepository, ModelMapper modelMapper,
                                     MensagemTopicoService msgService) {
        this.respostaRepository = respostaRepository;
        this.modelMapper = modelMapper;
        this.msgService = msgService;
    }

    public RespostasMensagensTopicosDto create(Long idMensagem, RespostasMensagensTopicosDto respostaDto){

        respostaDto.setIdMensagem(msgService.getById(idMensagem));

        RespostasMensagensTopico resposta = toResposta(respostaDto);

        return toRespostaDto(respostaRepository.save(resposta));

    }

    public RespostasMensagensTopicosDto update(Long idMensagem, Long idResposta, RespostasMensagensTopicosDto respostaDto){

        msgService.getById(idMensagem);

        return respostaRepository.findById(idResposta).map((Record) -> {
            Record.setRespostaConteudo(respostaDto.getRespostaConteudo());

            return toRespostaDto(respostaRepository.save(Record));
        }).orElseThrow(()-> new NotFoundException("Entidade não encontrada"));

    }

    public String delete(Long idMensagem){

        return respostaRepository.findById(idMensagem).map(Record -> {
            respostaRepository.deleteById(Record.getId());
            return "A resposta foi deletada com sucesso.";
        }).orElseThrow(()-> new NotFoundException("Entidade não encontrada."));

    }

    public RespostasMensagensTopicosDto toRespostaDto(RespostasMensagensTopico respostasMensagensTopicos){
        return modelMapper.map(respostasMensagensTopicos, RespostasMensagensTopicosDto.class);
    }

    public RespostasMensagensTopico toResposta(RespostasMensagensTopicosDto respostasMensagensTopicosDto){
        return modelMapper.map(respostasMensagensTopicosDto, RespostasMensagensTopico.class);
    }
}
