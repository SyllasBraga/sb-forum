drop database if exists sb_forum;
create database sb_forum;
use sb_forum;

create table usuario(
id int primary key not null auto_increment,
nome varchar(255) not null,
login varchar(255) unique not null,
senha varchar(255) not null
);

create table topico(
id int primary key not null auto_increment,
titulo varchar(255) not null,
conteudo varchar(255) not null,
id_autor int not null
);

create table mensagens_topico(
id int primary key not null auto_increment,
mensagem_conteudo varchar(255) not null,
id_topico int not null,
id_usuario int not null
);

create table respostas_mensagens_topico(
id int primary key not null auto_increment,
resposta_conteudo varchar(255) not null,
id_mensagem int not null,
id_usuario int not null
);

alter table topico
add foreign key fk_id_autor(id_autor) references usuario(id);

alter table mensagens_topico
add foreign key fk_id_topico_msg(id_topico) references topico(id);

alter table mensagens_topico
add foreign key fk_id_usuario_msg(id_usuario) references usuario(id);

alter table respostas_mensagens_topico
add foreign key fk_id_topico_respt(id_mensagem) references respostas_mensagens_topico(id);

alter table respostas_mensagens_topico
add foreign key fk_id_usuario_respt(id_usuario) references usuario(id);

insert into usuario(nome, login, senha) values ("João", "joão", "123"), ("Maria", "maria", "123");

insert into topico values (default, "Programadores", "Os devs são f0d@s", 1);

insert into mensagens_topico values(default, "Com toda certeza!", 1, 2);

insert into respostas_mensagens_topico values (default, "Obrigado pela mensagem!", 1, 1);
insert into respostas_mensagens_topico values (default, "O prazer é meu", 1, 2);