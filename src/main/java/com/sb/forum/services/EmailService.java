package com.sb.forum.services;

import com.sb.forum.dtos.TopicoDto;
import com.sb.forum.dtos.UsuarioDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String APP_EMAIL;
    private JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendForListOfUser(List<UsuarioDto> listUsers, TopicoDto topicoDto){

        SimpleMailMessage msg = new SimpleMailMessage();

        for (UsuarioDto user : listUsers){
            msg.setFrom(APP_EMAIL);
            msg.setTo(user.getLogin());
            msg.setSubject("Novo tópico: " + topicoDto.getTitulo());
            msg.setText(topicoDto.getConteudo().substring(0, 300) +
                    "... \n\nClique no link para continuar lendo.\n\nhttps://github.com/SyllasBraga/sb-forum");

            emailSender.send(msg);
        }
    }
}
