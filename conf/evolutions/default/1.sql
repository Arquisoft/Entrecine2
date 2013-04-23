# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cliente (
  id                        bigint not null,
  nombre                    varchar(255),
  login                     varchar(255),
  password                  varchar(255),
  constraint uq_cliente_login unique (login),
  constraint pk_cliente primary key (id))
;

create table empleado (
  id                        bigint not null,
  nombre                    varchar(255),
  login                     varchar(255),
  password                  varchar(255),
  admin                     boolean,
  constraint uq_empleado_login unique (login),
  constraint pk_empleado primary key (id))
;

create table entrada (
  id                        bigint not null,
  butaca                    integer,
  codigo                    bigint,
  sesion_id                 bigint,
  cliente_id                bigint,
  constraint pk_entrada primary key (id))
;

create table pelicula (
  id                        bigint not null,
  titulo                    varchar(255),
  anio                      integer,
  genero                    varchar(255),
  en_cartelera              boolean,
  constraint pk_pelicula primary key (id))
;

create table sala (
  id                        bigint not null,
  num_butacas               integer,
  constraint pk_sala primary key (id))
;

create table sesion (
  id                        bigint not null,
  tipo_id                   bigint,
  sala_id                   bigint,
  pelicula_id               bigint,
  inicio                    integer,
  dia                       date,
  constraint pk_sesion primary key (id))
;

create table tipo_sesion (
  id                        bigint not null,
  nombre                    varchar(255),
  precio                    double,
  constraint pk_tipo_sesion primary key (id))
;

create sequence cliente_seq;

create sequence empleado_seq;

create sequence entrada_seq;

create sequence pelicula_seq;

create sequence sala_seq;

create sequence sesion_seq;

create sequence tipo_sesion_seq;

alter table entrada add constraint fk_entrada_sesion_1 foreign key (sesion_id) references sesion (id) on delete restrict on update restrict;
create index ix_entrada_sesion_1 on entrada (sesion_id);
alter table entrada add constraint fk_entrada_cliente_2 foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;
create index ix_entrada_cliente_2 on entrada (cliente_id);
alter table sesion add constraint fk_sesion_tipo_3 foreign key (tipo_id) references tipo_sesion (id) on delete restrict on update restrict;
create index ix_sesion_tipo_3 on sesion (tipo_id);
alter table sesion add constraint fk_sesion_sala_4 foreign key (sala_id) references sala (id) on delete restrict on update restrict;
create index ix_sesion_sala_4 on sesion (sala_id);
alter table sesion add constraint fk_sesion_pelicula_5 foreign key (pelicula_id) references pelicula (id) on delete restrict on update restrict;
create index ix_sesion_pelicula_5 on sesion (pelicula_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists cliente;

drop table if exists empleado;

drop table if exists entrada;

drop table if exists pelicula;

drop table if exists sala;

drop table if exists sesion;

drop table if exists tipo_sesion;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists cliente_seq;

drop sequence if exists empleado_seq;

drop sequence if exists entrada_seq;

drop sequence if exists pelicula_seq;

drop sequence if exists sala_seq;

drop sequence if exists sesion_seq;

drop sequence if exists tipo_sesion_seq;

