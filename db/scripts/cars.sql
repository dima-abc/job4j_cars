/* Таблица cars хранит атомобили и их характеристики.
Связь Many to One к таблице engines. cars -1-> engines*/
create table if not exists cars(
    car_id serial primary key,
    model_id int not null references models(model_id),
    car_description varchar(1000),
    car_photo bytea,
    body_id int not null references bodies(body_id),
    engine_id int not null references engines(engine_id)
);