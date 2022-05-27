/* Таблица cars хранит атомобили и их характеристики.
Связь Many to One к таблице engines. cars -1-> engines*/
create table if not exists cars(
    car_id serial primary key,
    car_name varchar(255) not null,
    engine_id int not null unique references engines(engine_id)
);