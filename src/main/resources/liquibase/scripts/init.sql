--liquibase formatted sql

--changeset MikhailShad:create_project_schema
create schema if not exists filtrobot;
--rollback
--drop schema filtrobot;

--changeset MikhailShad:create_orders_table
set schema 'filtrobot';
create sequence if not exists channel_id_gen start 1000 increment 50;
create table if not exists channel
(
    id   bigint not null default nextval('channel_id_gen'),
    name text,
    url  text,
    primary key (id)
);
--rollback
--drop table channel;

--changeset MikhailShad:create_user_table
set schema 'filtrobot';
create table if not exists users
(
    id        bigint not null,
    username  text,
    chat_id   bigint,
    is_active boolean,
    primary key (id)
);
--rollback
--drop table users;

--changeset MikhailShad:create_preference_table
set schema 'filtrobot';
create sequence if not exists preference_id_gen start 1000 increment 50;
create table if not exists preference
(
    id              bigint not null default nextval('preference_id_gen'),
    user_id         bigint,
    keyword         text,
    preference_type text,
    primary key (id),
    constraint fk_user foreign key (user_id) references users (id)
);
--rollback
--drop table preference;

--changeset MikhailShad:added_channel_id_to_channel
alter table if exists channel
    add column if not exists channel_id bigint;
--rollback
--alter table if exists channel
--drop column if exists channel_id;

--changeset MikhailShad:create_channel_user_table
create table channel_user
(
    channel_id bigint,
    user_id    bigint,
    primary key (channel_id, user_id),
    constraint fk_channel foreign key (channel_id) references channel (id),
    constraint fk_user foreign key (user_id) references users (id)
);
--rollback
--drop table channel_user
