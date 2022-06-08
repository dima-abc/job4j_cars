/* Таблица cars хранит атомобили и их характеристики.
Связь Many to One к таблице engines. cars -1-> engines*/
create table if not exists cars(
    car_id serial primary key,
    vin int not null unique,
    cat_id int not null references categories(cat_id),
    model_id int not null references models(model_id),
    year_id int not null references years(year_id),
    body_id int not null references bodies(body_id),
    engine_id int not null references engines(engine_id),
    trans_id int not null references transmissions(trans_id),
    color_id int not null references colors(color_id),
    description varchar(1000),
    photo bytea
);