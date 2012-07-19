# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table post (
  post_id                   bigint not null,
  title                     varchar(255),
  posted_at                 timestamp,
  CONTENT                   clob(1024),
  author_user_id            bigint,
  constraint pk_post primary key (post_id))
;

create table user (
  user_id                   bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  fullname                  varchar(255),
  nickname                  varchar(255),
  about                     varchar(255),
  role                      varchar(255),
  company                   varchar(255),
  is_admin                  boolean,
  is_online                 boolean,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (user_id))
;

create sequence post_seq;

create sequence user_seq;

alter table post add constraint fk_post_author_1 foreign key (author_user_id) references user (user_id) on delete restrict on update restrict;
create index ix_post_author_1 on post (author_user_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists post;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists post_seq;

drop sequence if exists user_seq;

