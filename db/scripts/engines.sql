/* Таблица engines хронит данные о моделях двиготелей автомобилей */
create table if not exists engines(
    engine_id serial primary key,
    engine_name varchar(255) not null unique
);