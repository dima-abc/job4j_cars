/*Таблица справочников видов автомобилей*/
create table if not exists categories (
    cat_id serial primary key,
    category varchar(100) not null unique
);