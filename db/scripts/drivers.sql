/*Таблица drivers хранит данные о владельцах автомобилей*/
create table if not exists drivers(
    driver_id serial primary key,
    driver varchar(255) not null,
    email varchar(255) not null
);