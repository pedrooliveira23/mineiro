insert into mineiro.Grupo(nome, sigla)
values('MINEIRO - Administrador','MINEIRO - Administrador'),
('MINEIRO - Gestor de Qualidade','MINEIRO - Gestor de Qualidade'),
('MINEIRO - Gestor Técnico','MINEIRO - Gestor Técnico'),
('MINEIRO - Observador','MINEIRO - Observador');


insert into mineiro.Usuario(matricula,nome,senha)
values('admin','Administrador','328ae0550235960fb0544bdc3f65e2d0f2100720');

insert into mineiro.UsuarioGrupo(usuario_id,grupo_id)
values(1,1),(1,2),(1,3),(1,4);
