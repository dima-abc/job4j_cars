/* Таблица users содержит пользователей(создателей объявлений)*/
create table if not exists users(
    user_id serial primary key,
    user_email varchar(255) not null unique,
    user_password varchar(16) not null
);