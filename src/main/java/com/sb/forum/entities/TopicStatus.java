package com.sb.forum.entities;

import com.sb.forum.enums.TopicStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
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

    public TopicStatus(TopicStatusEnum status, Topico idTopico) {
        this.status = status;
        this.idTopico = idTopico;
    }
}
