package com.sb.forum.services;

import com.sb.forum.dtos.TopicoDto;
import com.sb.forum.entities.Topico;
import com.sb.forum.repository.TopicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicoService {

    private TopicoRepository topicoRepository;
    private ModelMapper modelMapper;

    public TopicoService(TopicoRepository topicoRepository, ModelMapper modelMapper) {
        this.topicoRepository = topicoRepository;
        this.modelMapper = modelMapper;
    }

    public List<TopicoDto> getAll(){

        List<TopicoDto> listaDto = new ArrayList<>();
        List<Topico> listaTopicos = topicoRepository.findAll();

        for (Topico topico: listaTopicos) {
            listaDto.add(toTopicoDto(topico));
        }

        return listaDto;
    }

    public TopicoDto getById(Long id){

        Topico topico = topicoRepository.findById(id).get();

        return toTopicoDto(topico);
    }

    public TopicoDto create(TopicoDto topicoDto){

        Topico topico = toTopico(topicoDto);

        return toTopicoDto(topicoRepository.save(topico));
    }

    public TopicoDto update(Long id, TopicoDto topicoDto) {

        Topico topico = toTopico(topicoDto);

        return topicoRepository.findById(id).map(Record ->{
            Record.setConteudo(topico.getConteudo());
            Record.setListMensagens(topico.getListMensagens());
            Record.setTitulo(topico.getTitulo());

            topicoRepository.save(Record);

            return toTopicoDto(Record);
        }).orElse(null);
    }

    public String delete(Long id){
        TopicoDto topico = getById(id);

        topicoRepository.deleteById(topico.getId());

        return "O tópico "+ topico.getTitulo() + " foi deletado do sistema com sucesso.";
    }

    public TopicoDto toTopicoDto(Topico topico){
        return modelMapper.map(topico, TopicoDto.class);
    }

    public Topico toTopico(TopicoDto topicoDto){
        return modelMapper.map(topicoDto, Topico.class);
    }
}
