-- apply changes
create table contrato (
  id                            bigint auto_increment not null,
  fecha_pago                    datetime(6) not null,
  version                       bigint not null,
  created                       datetime(6) not null,
  modified                      datetime(6) not null,
  constraint pk_contrato primary key (id)
);

create table persona (
  id                            bigint auto_increment not null,
  rut                           varchar(255) not null,
  nombre                        varchar(255) not null,
  apellidos                     varchar(255) not null,
  email                         varchar(255) not null,
  telefono                      varchar(255) not null,
  version                       bigint not null,
  created                       datetime(6) not null,
  modified                      datetime(6) not null,
  constraint pk_persona primary key (id)
);

