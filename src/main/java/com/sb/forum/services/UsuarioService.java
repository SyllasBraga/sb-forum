package com.sb.forum.services;

import com.sb.forum.entities.Topico;
import com.sb.forum.entities.Usuario;
import com.sb.forum.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;
    TopicoService topicoService;

    public UsuarioService(UsuarioRepository usuarioRepository, TopicoService topicoService) {
        this.usuarioRepository = usuarioRepository;
        this.topicoService = topicoService;
    }

    public List<Usuario> getAll(){

        List<Usuario> listaUsuarios = usuarioRepository.findAll();

        return listaUsuarios;
    }

    public Usuario getById(Long id){

        Usuario usuario = usuarioRepository.findById(id).get();

        return usuario;
    }

    public Usuario create(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuario) {

        return usuarioRepository.findById(id).map(Record ->{
            Record.setNome(usuario.getNome());
            Record.setLogin(usuario.getLogin());
            Record.setSenha(usuario.getSenha());
            usuarioRepository.save(Record);

            return Record;
        }).orElse(null);
    }

    public String delete(Long id){
        Usuario usuario = getById(id);

        usuarioRepository.deleteById(usuario.getId());

        return "O usuário "+ usuario.getNome() + " foi deletado do sistema com sucesso.";
    }
    public Topico createTopico(Long idUsuario, Topico topico){
        Usuario usuario = getById(idUsuario);
        topico.setIdAutor(usuario);

        return topicoService.create(topico);
    }

    public Topico updateTopico(Long idUsuario, Long idTopico, Topico topico){
        getById(idUsuario);

        return topicoService.update(idTopico, topico);
    }

    public String deleteTopico(Long idUsuario, Long idTopico){
        getById(idUsuario);

        return topicoService.delete(idTopico);
    }
}
