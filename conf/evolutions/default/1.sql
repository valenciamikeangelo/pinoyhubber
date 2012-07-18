# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  fullname                  varchar(255),
  nickname                  varchar(255),
  about                     varchar(255),
  role                      varchar(255),
  company                   varchar(255),
  is_admin                  boolean,
  is_online                 boolean,
  constraint pk_user primary key (id))
;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

