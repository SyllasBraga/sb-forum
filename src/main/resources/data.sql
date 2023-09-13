drop database if exists sb_forum;
create database sb_forum;
use sb_forum;

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
id_role int not null,
id_usuario varchar(36) not null,
CONSTRAINT id_role_fk foreign key (id_role) references roles(id),
CONSTRAINT id_usuario_role_fk foreign key (id_usuario) references usuario(id)
);

create table topico_status(
id int not null auto_increment,
status varchar(255) not null,
mensagem varchar(1000) not null,
id_topico int not null,
id_moderator varchar(36),
primary key(id),
CONSTRAINT id_topico_status foreign key (id_topico) references topico(id),
CONSTRAINT id_moderator foreign key (id_moderator) references usuario(id)
);

insert into usuario(id, nome, login, senha) values ('bccddbc0-1ab0-11ee-be56-0242ac120002', 'João', 'syllasbraga2@gmail.com', '$2a$10$6z.pEKyNF3kRJrNu54o0Au1TjjtxgAfPihxisatQtYvMprxvoWwRe');
insert into usuario(id, nome, login, senha) values ('4f446fbe-1ab1-11ee-be56-0242ac120002', 'Maria', 'antoniobraga230959@gmail.com', '$2a$10$6z.pEKyNF3kRJrNu54o0Au1TjjtxgAfPihxisatQtYvMprxvoWwRe');

insert into topico values (default, 'Programadores', 'Os devs são f0d@s', '2023-02-10 17:05:39', '4f446fbe-1ab1-11ee-be56-0242ac120002');

insert into topico_status values (default, 'APPROVED', '', 1, 'bccddbc0-1ab0-11ee-be56-0242ac120002');

insert into mensagens_topico values(default, 'Com toda certeza!','2023-02-10 17:10:55', 1, 'bccddbc0-1ab0-11ee-be56-0242ac120002');

insert into respostas_mensagens_topico values (default, 'Obrigado pela mensagem!','2023-02-10 17:11:21', 1, '4f446fbe-1ab1-11ee-be56-0242ac120002');
insert into respostas_mensagens_topico values (default, 'O prazer é meu','2023-02-10 17:12:34', 1, 'bccddbc0-1ab0-11ee-be56-0242ac120002');

insert into topico values (default, 'S.O.L.I.D.', 'Design de programação muito popular', '2023-02-10 17:05:39', 'bccddbc0-1ab0-11ee-be56-0242ac120002');

insert into topico_status values (default, 'REVIEW', 'Detalhar o título', 1, 'bccddbc0-1ab0-11ee-be56-0242ac120002');

insert into mensagens_topico values(default, 'Devs que conhecem são mais disputados','2023-02-10 17:10:55', 2, '4f446fbe-1ab1-11ee-be56-0242ac120002');

insert into respostas_mensagens_topico values (default, 'Sim, por isso a remuneração é maior','2023-02-10 17:11:21', 2, 'bccddbc0-1ab0-11ee-be56-0242ac120002');
insert into respostas_mensagens_topico values (default, 'Na maioria das vezes','2023-02-10 17:12:34', 2, '4f446fbe-1ab1-11ee-be56-0242ac120002');

insert into roles values (default, 'USER');
insert into roles values (default, 'MODERATOR');

insert into usuario_roles values (1,'bccddbc0-1ab0-11ee-be56-0242ac120002');
insert into usuario_roles values (2,'bccddbc0-1ab0-11ee-be56-0242ac120002');
insert into usuario_roles values (1,'4f446fbe-1ab1-11ee-be56-0242ac120002');