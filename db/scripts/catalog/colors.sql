/*Таблица справочника цветов*/
create table if not exists colors(
    color_id serial primary key,
    color varchar(200) not null unique,
    code varchar(100) not null unique
);