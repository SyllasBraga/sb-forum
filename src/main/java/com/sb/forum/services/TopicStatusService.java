package com.sb.forum.services;

import com.sb.forum.entities.TopicStatus;
import com.sb.forum.enums.TopicStatusEnum;
import com.sb.forum.exceptions.NotFoundException;
import com.sb.forum.repository.TopicStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicStatusService {

    private final TopicStatusRepository topicStatusRepository;

    public TopicStatusService(TopicStatusRepository topicStatus) {
        this.topicStatusRepository = topicStatus;
    }

    public void criarTopicStatus(TopicStatus topicStatus){
        topicStatusRepository.save(topicStatus);
    }

    public void atualizarTopicStatus(Long idTopico, String mensagem, TopicStatusEnum topicStatusEnum){
        TopicStatus topicStatus = topicStatusRepository.findByIdTopico(idTopico);

        topicStatusRepository.findById(topicStatus.getId()).map(obj -> {
                obj.setMensagem(mensagem);
                obj.setStatus(topicStatusEnum);
                return topicStatusRepository.save(obj);
            }
        ).orElseThrow(() -> new NotFoundException("Entidade não encontrada"));
    }

    public TopicStatus buscarPeloIdTopico(Long idTopico){
        return topicStatusRepository.findByIdTopico(idTopico);
    }
}
