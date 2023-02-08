package com.sb.forum.services;

import com.sb.forum.entities.Usuario;
import com.sb.forum.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
}
