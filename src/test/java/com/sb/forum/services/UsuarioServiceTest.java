package com.sb.forum.services;

import com.sb.forum.dtos.UsuarioDto;
import com.sb.forum.entities.Usuario;
import com.sb.forum.exceptions.NotFoundException;
import com.sb.forum.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class UsuarioServiceTest {

    public static final long ID = 1L;
    public static final String NOME = "João";
    public static final String LOGIN = "syllasbraga2@gmail.com";
    public static final String SENHA = "123";

    @InjectMocks
    private UsuarioService service;

    @Mock
    private UsuarioRepository repository;

    @Spy
    private ModelMapper modelMapper;

    private UsuarioDto userDto;
    private Usuario user;
    private Optional<Usuario> optUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void whenGetAllReturnsListUsersDto() {

        when(repository.findAll()).thenReturn(List.of(user));

        List<UsuarioDto> list = service.getAll();

        Assertions.assertEquals(UsuarioDto.class, list.get(0).getClass());
    }

    @Test
    void whenGetByIdReturnsAnUser() {

        when(repository.findById(anyLong())).thenReturn(optUser);

        UsuarioDto usuario = service.getById(ID);

        Assertions.assertEquals(UsuarioDto.class, usuario.getClass());
    }

    @Test
    void whenGetByIdReturnsEntityNotFound() {

        when(repository.findById(anyLong())).thenThrow(new NotFoundException("Entidade não encontrada"));

        try {
            service.getById(ID);
        }catch (Exception ex){
            Assertions.assertEquals(NotFoundException.class, ex.getClass());
        }

    }

    @Test
    void whenCreateReturnsOk() {

        when(repository.save(any())).thenReturn(user);

        UsuarioDto usuario = service.create(userDto);

        Assertions.assertEquals(UsuarioDto.class, usuario.getClass());

    }

    @Test
    void whenUpdateReturnsOk() {

        when(repository.findById(any())).thenReturn(optUser);

        UsuarioDto usuario = service.update(userDto.getId(), userDto);

        Assertions.assertEquals(UsuarioDto.class, usuario.getClass());

    }
    @Test
    void whenUpdateReturnsEntityNotFound() {

        when(repository.findById(any())).thenReturn(optUser);

        try {
            optUser.get().setId(3L);
            UsuarioDto usuario = service.update(userDto.getId(), userDto);
        }catch (Exception ex){
            Assertions.assertEquals(NotFoundException.class, ex.getClass());
        }

    }
    @Test
    void delete() {
    }

    @Test
    void createTopico() {
    }

    @Test
    void updateTopico() {
    }

    @Test
    void deleteTopico() {
    }

    @Test
    void toUsuarioDto() {
    }

    @Test
    void toUsuario() {
    }

    private void startUsers(){
        user = new Usuario(ID, NOME, LOGIN, SENHA);
        userDto = new UsuarioDto(ID, NOME, LOGIN, SENHA);
        optUser = Optional.of(new Usuario(ID, NOME, LOGIN, SENHA));
    }
}