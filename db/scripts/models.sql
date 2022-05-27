/* Таблица models справочник моделей автомобилей */
create table if not exists models(
    model_id serial primary key,
    model_name varchar(255) not null,
    mark_id int not null references marks(mark_id)
);