/* Таблица bodies описвыает спаровчник типов кузовов автомобилей */
create table if not exists bodies(
    body_id serial primary key,
    body_name varchar(255) not null unique
);