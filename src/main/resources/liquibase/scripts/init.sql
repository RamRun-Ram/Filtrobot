--liquibase formatted sql

--changeset MikhailShad:create_project_schema
create schema if not exists filterbot;
--rollback
--drop schema filterbot;

--changeset MikhailShad:create_orders_table
set schema 'filterbot';
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
