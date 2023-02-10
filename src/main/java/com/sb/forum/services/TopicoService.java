package com.sb.forum.services;

import com.sb.forum.entities.Topico;
import com.sb.forum.repository.TopicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    private TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public List<Topico> getAll(){
        List<Topico> listaTopicos = topicoRepository.findAll();

        return listaTopicos;
    }

    public Topico getById(Long id){

        Topico topico = topicoRepository.findById(id).get();

        return topico;
    }

    public Topico create(Topico topico){
        return topicoRepository.save(topico);
    }

    public Topico update(Long id, Topico topico) {

        return topicoRepository.findById(id).map(Record ->{
            Record.setConteudo(topico.getConteudo());
            Record.setListMensagens(topico.getListMensagens());
            Record.setTitulo(topico.getTitulo());

            topicoRepository.save(Record);

            return Record;
        }).orElse(null);
    }

    public String delete(Long id){
        Topico topico = getById(id);

        topicoRepository.deleteById(topico.getId());

        return "O usuário "+ topico.getTitulo() + " foi deletado do sistema com sucesso.";
    }

}
