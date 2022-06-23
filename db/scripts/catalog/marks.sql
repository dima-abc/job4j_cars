/* Таблицы marks описывает марку автомобиля(группа автомобилей) */
create table if not exists marks(
    mark_id serial primary key,
    mark varchar(255) not null unique
);