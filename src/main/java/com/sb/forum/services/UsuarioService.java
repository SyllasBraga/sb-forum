package com.sb.forum.services;

import com.sb.forum.dtos.TopicoDto;
import com.sb.forum.dtos.UsuarioDto;
import com.sb.forum.entities.Usuario;
import com.sb.forum.exceptions.NotFoundException;
import com.sb.forum.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;
    TopicoService topicoService;
    ModelMapper modelMapper;
    EmailService emailService;

    public UsuarioService(UsuarioRepository usuarioRepository, TopicoService topicoService, ModelMapper modelMapper, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.topicoService = topicoService;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
    }

    public List<UsuarioDto> getAll(){

        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        List<UsuarioDto> listaDto = new ArrayList<>();

        for (Usuario user : listaUsuarios){
            listaDto.add(toUsuarioDto(user));
        }

        return listaDto;
    }

    public UsuarioDto getById(Long id){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Entidade não encontrada."));

        return toUsuarioDto(usuario);
    }

    public UsuarioDto create(UsuarioDto usuarioDto){

        Usuario usuario = toUsuario(usuarioDto);

        return toUsuarioDto(usuarioRepository.save(usuario));
    }

    public UsuarioDto update(Long id, UsuarioDto usuarioDto) {

        Usuario usuario = toUsuario(usuarioDto);

        return usuarioRepository.findById(id).map(Record ->{
            Record.setNome(usuario.getNome());
            Record.setLogin(usuario.getLogin());
            Record.setSenha(usuario.getSenha());
            usuarioRepository.save(Record);

            return toUsuarioDto(Record);
        }).orElseThrow(() -> new NotFoundException("Entidade não encontrada."));
    }

    public String delete(Long id){
        UsuarioDto usuario = getById(id);

        usuarioRepository.deleteById(usuario.getId());

        return "O usuário "+ usuario.getNome() + " foi deletado do sistema com sucesso.";
    }
    public TopicoDto createTopico(Long idUsuario, TopicoDto topico){
        UsuarioDto usuario = getById(idUsuario);
        topico.setIdAutor(usuario);

        List<UsuarioDto> list = getAll();
        list.remove(usuario);

        TopicoDto topicoCriado = topicoService.create(topico);

        emailService.sendForListOfUser(list, topico);

        return topicoCriado;
    }

    public TopicoDto updateTopico(Long idUsuario, Long idTopico, TopicoDto topico){
        getById(idUsuario);

        return topicoService.update(idTopico, topico);
    }

    public String deleteTopico(Long idUsuario, Long idTopico){
        getById(idUsuario);

        return topicoService.delete(idTopico);
    }

    public UsuarioDto toUsuarioDto(Usuario usuario){
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    public Usuario toUsuario(UsuarioDto usuarioDto){
        return modelMapper.map(usuarioDto, Usuario.class);
    }
}
