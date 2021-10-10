drop table if exists desafio CASCADE;
drop table if exists tarefa CASCADE;    
drop table if exists usuario CASCADE;
    
create table desafio (
   logic integer not null,
    model varchar(255) not null,
    mxf integer,
    mxr integer,
    plat integer,
    ptid varchar(255),
    sam integer check (sam>=0),
    serial varchar(255) not null,
    verfm varchar(255),
    version varchar(255) not null,
    primary key (logic)
);

create table tarefa (
    id BIGINT not null,
    data_atualizacao timestamp,
    data_cadastro timestamp,
    descricao varchar(255),
    resumo varchar(255),
    status varchar(255),
    usuario_id BIGINT not null,
    primary key (id)
);
    
create table usuario (
    id BIGINT not null,
    email varchar(255) not null,
    senha varchar(255),
    usersuper boolean,
    conta integer,
    primary key (id)
);

alter table usuario 
   add constraint UK_5171l57faosmj8myawaucatdw unique (email);
    
alter table tarefa 
   add constraint FKh1rhpa56mvm9tur1y66hfrvhn 
   foreign key (usuario_id) 
   references usuario;
   
-- senha usuários itau@123 - aXRhdUAxMjM= 
insert into usuario (id, email, senha, usersuper, conta) values (1, 'user01@itau.com.br', 'aXRhdUAxMjM=',true, 1), (2, 'user02@itau.com.br', 'aXRhdUAxMjM=', false, 1), (3, 'user03@itau.com.br', 'aXRhdUAxMjM=', false, 1);

insert into tarefa (id, data_cadastro, descricao, resumo, status, usuario_id) values (1, now(), 'Criação de nova funcionalidade','Para isso é necessário contratar um dev bom :P', 'pedding',2), (2, now(), 'Realizar entrevista','Para contratar o dev bom :P é necessário realizar a entrevista tecnica', 'completed',3), (3, now(), 'Realizar entrevista parte 2','Conversar com o DEV sobre planos e benefícios', 'pedding',3);