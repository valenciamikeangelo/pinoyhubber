# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  account_id                bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  fullname                  varchar(255),
  nickname                  varchar(255),
  about                     varchar(255),
  role                      varchar(255),
  company                   varchar(255),
  is_admin                  boolean,
  is_online                 boolean,
  constraint uq_account_email unique (email),
  constraint pk_account primary key (account_id))
;

create table post (
  post_id                   bigint not null,
  title                     varchar(255),
  posted_at                 timestamp,
  CONTENT                   clob(1024),
  author_account_id         bigint,
  constraint pk_post primary key (post_id))
;

create sequence account_seq;

create sequence post_seq;

alter table post add constraint fk_post_author_1 foreign key (author_account_id) references account (account_id) on delete restrict on update restrict;
create index ix_post_author_1 on post (author_account_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists account;

drop table if exists post;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists account_seq;

drop sequence if exists post_seq;

