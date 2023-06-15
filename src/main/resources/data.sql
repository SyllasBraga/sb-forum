insert into usuario(nome, login, senha) values ('João', 'syllasbraga2@gmail.com', '$2a$10$6z.pEKyNF3kRJrNu54o0Au1TjjtxgAfPihxisatQtYvMprxvoWwRe');
insert into usuario(nome, login, senha) values ('Maria', 'antoniobraga230959@gmail.com', '$2a$10$6z.pEKyNF3kRJrNu54o0Au1TjjtxgAfPihxisatQtYvMprxvoWwRe');

insert into topico values (default, 'Programadores', 'Os devs são f0d@s', '2023-02-10 17:05:39', 1);

insert into mensagens_topico values(default, 'Com toda certeza!','2023-02-10 17:10:55', 1, 2);

insert into respostas_mensagens_topico values (default, 'Obrigado pela mensagem!','2023-02-10 17:11:21', 1, 1);
insert into respostas_mensagens_topico values (default, 'O prazer é meu','2023-02-10 17:12:34', 1, 2);

insert into topico values (default, 'S.O.L.I.D.', 'Design de programação muito popular', '2023-02-10 17:05:39', 1);

insert into mensagens_topico values(default, 'Devs que conhecem são mais disputados','2023-02-10 17:10:55', 2, 2);

insert into respostas_mensagens_topico values (default, 'Sim, por isso a remuneração é maior','2023-02-10 17:11:21', 2, 1);
insert into respostas_mensagens_topico values (default, 'Na maioria das vezes','2023-02-10 17:12:34', 2, 2);

insert into roles values (default, 'user');
insert into roles values (default, 'moderator');

insert into usuario_roles values (1,1);
insert into usuario_roles values (2,1);
insert into usuario_roles values (1,2);