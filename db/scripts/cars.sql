/* Таблица cars хранит атомобили и их характеристики.
Связь Many to One к таблице engines. cars -1-> engines*/
create table if not exists cars(
    car_id serial primary key,
    vin varchar(200),
    car_price decimal,
    car_mileage int,
    cat_id int references categories(cat_id),
    model_id int references models(model_id),
    year_id int references years(year_id),
    body_id int references bodies(body_id),
    engine_id int references engines(engine_id),
    trans_id int references transmissions(trans_id),
    color_id int references colors(color_id),
    description varchar(1000),
    photo bytea
);