package com.sb.forum.services;

import com.sb.forum.dtos.MensagensTopicoDto;
import com.sb.forum.entities.MensagensTopico;
import com.sb.forum.exceptions.NotFoundException;
import com.sb.forum.repository.MensagensRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MensagemTopicoService {

    private MensagensRepository mensagensRepository;
    private ModelMapper modelMapper;
    private TopicoService topicoService;

    public MensagemTopicoService(MensagensRepository mensagensRepository, ModelMapper modelMapper, TopicoService topicoService) {
        this.mensagensRepository = mensagensRepository;
        this.modelMapper = modelMapper;
        this.topicoService = topicoService;
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
        }).orElseThrow(()-> new NotFoundException("Entidade não encontrada."));
    }

    public String delete(Long idMsg){

        return mensagensRepository.findById(idMsg).map(Record -> {
           mensagensRepository.deleteById(Record.getId());
           return "A mensagem foi deletada com sucesso.";
        }).orElseThrow(()-> new NotFoundException("Entidade não encontrada."));
    }

    public MensagensTopicoDto toMsgDto(MensagensTopico msg){
        return modelMapper.map(msg, MensagensTopicoDto.class);
    }

    public MensagensTopico toMsgEntity(MensagensTopicoDto msgDto){
        return modelMapper.map(msgDto, MensagensTopico.class);
    }


}
