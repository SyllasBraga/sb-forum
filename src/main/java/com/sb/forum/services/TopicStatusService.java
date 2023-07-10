package com.sb.forum.services;

import com.sb.forum.entities.TopicStatus;
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
}
