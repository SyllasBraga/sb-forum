package com.sb.forum.services;

import com.sb.forum.dtos.UsuarioDto;
import com.sb.forum.entities.Usuario;
import com.sb.forum.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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
    void whenFindByIdReturnsAnUser() {

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optUser);

        UsuarioDto usuario = service.getById(ID);

        Assertions.assertEquals(UsuarioDto.class, usuario.getClass());

    }

    @Test
    void getById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
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