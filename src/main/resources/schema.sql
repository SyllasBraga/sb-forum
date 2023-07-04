create table usuario(
id varchar(36) not null,
nome varchar(255) not null,
login varchar(255) unique not null,
senha varchar(255) not null,
primary key(id)
);

create table topico(
id int not null auto_increment,
titulo varchar(255) not null,
conteudo varchar(10000) not null,
data_publicacao TIMESTAMP not null,
id_autor varchar(36) not null,
primary key(id),
CONSTRAINT autor_fk foreign key (id_autor) references usuario(id)
);

create table mensagens_topico(
id int not null auto_increment,
mensagem_conteudo varchar(255) not null,
data_publicacao TIMESTAMP not null,
id_topico int not null,
id_usuario varchar(36) not null,
primary key(id),
CONSTRAINT id_usuario_msg_fk foreign key (id_usuario) references usuario(id),
CONSTRAINT id_topico_fk foreign key (id_topico) references topico(id)
);

create table respostas_mensagens_topico(
id int not null auto_increment,
resposta_conteudo varchar(255) not null,
data_publicacao TIMESTAMP not null,
id_mensagem int not null,
id_usuario varchar(36) not null,
primary key(id),
CONSTRAINT id_usuario_respt_foreign foreign key (id_usuario) references usuario(id),
CONSTRAINT mensagem_fk foreign key (id_mensagem) references mensagens_topico(id)
);

create table roles(
id int not null auto_increment,
tipo_acesso varchar(255) not null,
primary key (id)
);

create table usuario_roles(
id_role varchar(36) not null,
id_usuario varchar(36) not null,
CONSTRAINT id_role_fk foreign key (id_role) references roles(id),
CONSTRAINT id_usuario_role_fk foreign key (id_usuario) references usuario(id)
);