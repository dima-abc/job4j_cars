/*Таблица для справочников видов топлива*/
create table if not exists engines(
    engine_id serial primary key,
    engine varchar(255) not null,
    fuel varchar(100) not null
);