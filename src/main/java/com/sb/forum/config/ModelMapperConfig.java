package com.sb.forum.config;

import com.sb.forum.dtos.TopicoDto;
import com.sb.forum.entities.Topico;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Topico, TopicoDto>() {
            @Override
            protected void configure() {
                map().setIdAutor(source.getIdAutor().getId());
            }
        });
        return modelMapper;
    }
}
