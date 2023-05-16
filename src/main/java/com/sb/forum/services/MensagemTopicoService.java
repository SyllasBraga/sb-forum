package com.sb.forum.services;

import com.sb.forum.dtos.MensagensTopicoDto;
import com.sb.forum.entities.MensagensTopico;
import com.sb.forum.exceptions.NotFoundException;
import com.sb.forum.repository.MensagensRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MensagemTopicoService {

    private final MensagensRepository mensagensRepository;
    private final ModelMapper modelMapper;
    private final TopicoService topicoService;
    private final String mensagemEntidadeNaoEncontrada = "Entidade não encontrada.";

    public MensagemTopicoService(MensagensRepository mensagensRepository, ModelMapper modelMapper, TopicoService topicoService) {
        this.mensagensRepository = mensagensRepository;
        this.modelMapper = modelMapper;
        this.topicoService = topicoService;
    }

    public MensagensTopicoDto getById(Long idMsg){
        return toMsgDto(mensagensRepository.findById(idMsg).orElseThrow(
                ()-> new NotFoundException(mensagemEntidadeNaoEncontrada)));
    }

    public MensagensTopicoDto create(Long idTopico, MensagensTopicoDto mensagensTopicoDto){

        mensagensTopicoDto.setIdTopico(topicoService.getById(idTopico));
        MensagensTopico msgTopico = toMsgEntity(mensagensTopicoDto);

        return toMsgDto(mensagensRepository.save(msgTopico));
    }

    public MensagensTopicoDto update(Long idTopico, Long idMensagem, MensagensTopicoDto mensagensTopicoDto){

        topicoService.getById(idTopico);

        return mensagensRepository.findById(idMensagem).map(Record -> {
            Record.setMensagemConteudo(mensagensTopicoDto.getMensagemConteudo());

            return toMsgDto(mensagensRepository.save(Record));
        }).orElseThrow(()-> new NotFoundException(mensagemEntidadeNaoEncontrada));
    }

    public String delete(Long idMsg){

        return mensagensRepository.findById(idMsg).map(Record -> {
           mensagensRepository.deleteById(Record.getId());
           return "A mensagem foi deletada com sucesso.";
        }).orElseThrow(()-> new NotFoundException(mensagemEntidadeNaoEncontrada));
    }

    public MensagensTopicoDto toMsgDto(MensagensTopico msg){
        return modelMapper.map(msg, MensagensTopicoDto.class);
    }

    public MensagensTopico toMsgEntity(MensagensTopicoDto msgDto){
        return modelMapper.map(msgDto, MensagensTopico.class);
    }


}
