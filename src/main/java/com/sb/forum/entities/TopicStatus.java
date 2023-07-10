package com.sb.forum.entities;

import com.sb.forum.enums.TopicStatusEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TopicStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private TopicStatusEnum status;
    private String mensagem;
    @OneToOne
    private Topico idTopico;
    @OneToOne
    private Usuario idModerator;
}
