package com.sb.forum.services;

import com.sb.forum.dtos.TopicoDto;
import com.sb.forum.dtos.UsuarioDto;
import com.sb.forum.entities.Roles;
import com.sb.forum.entities.Usuario;
import com.sb.forum.enums.RolesEnum;
import com.sb.forum.exceptions.NotFoundException;
import com.sb.forum.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final TopicoService topicoService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final String mensagemEntidadeNaoEncontrada = "Entidade não encontrada.";

    public UsuarioService(UsuarioRepository usuarioRepository, TopicoService topicoService, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.topicoService = topicoService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioDto> getAll(){

        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        List<UsuarioDto> listaDto = new ArrayList<>();

        for (Usuario user : listaUsuarios){
            listaDto.add(toUsuarioDto(user));
        }

        return listaDto;
    }

    public UsuarioDto getById(UUID id){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new NotFoundException(mensagemEntidadeNaoEncontrada));

        return toUsuarioDto(usuario);
    }

    public UsuarioDto create(UsuarioDto usuarioDto){

        Usuario usuario = toUsuario(usuarioDto);
        usuario.setId(UUID.randomUUID());
        usuario.setAcessos(List.of(new Roles(1L, RolesEnum.USER)));
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return toUsuarioDto(usuarioRepository.save(usuario));
    }

    public UsuarioDto update(UUID id, UsuarioDto usuarioDto) {

        Usuario usuario = toUsuario(usuarioDto);

        return usuarioRepository.findById(id).map(Record ->{
            Record.setNome(usuario.getNome());
            Record.setLogin(usuario.getLogin());
            Record.setSenha(passwordEncoder.encode(usuario.getSenha()));
            usuarioRepository.save(Record);

            return toUsuarioDto(Record);
        }).orElseThrow(() -> new NotFoundException(mensagemEntidadeNaoEncontrada));
    }

    public String delete(UUID id){
        UsuarioDto usuario = getById(id);

        usuarioRepository.deleteById(usuario.getId());

        return "O usuário "+ usuario.getNome() + " foi deletado do sistema com sucesso.";
    }

    public UsuarioDto toUsuarioDto(Usuario usuario){
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    public Usuario toUsuario(UsuarioDto usuarioDto){
        return modelMapper.map(usuarioDto, Usuario.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(username);

        if (usuario == null){
            throw new UsernameNotFoundException("Login não encontrado");
        }
        return usuario;
    }
}
