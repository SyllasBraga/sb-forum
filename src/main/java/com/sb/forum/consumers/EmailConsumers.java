package com.sb.forum.consumers;

import com.sb.forum.dtos.TopicoDto;
import com.sb.forum.dtos.UsuarioDto;
import com.sb.forum.services.EmailService;
import com.sb.forum.services.UsuarioService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailConsumers {

    EmailService emailService;
    UsuarioService usuarioService;

    public EmailConsumers(EmailService emailService, UsuarioService usuarioService) {
        this.emailService = emailService;
        this.usuarioService = usuarioService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload TopicoDto topicoDto) {

        List<UsuarioDto> listUsuarios = usuarioService.getAll();

        emailService.sendForListOfUser(listUsuarios, topicoDto);
    }
}
