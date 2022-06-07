/* Таблица models справочник моделей автомобилей */
create table if not exists models(
    model_id serial primary key,
    model varchar(255) not null,
    cat_id int not null references categories(cat_id),
    mark_id int not null references marks(mark_id),
    year_id int not null references years(year_id),
    body_id int not null references bodies(body_id),
    engine_id int not null references engines(engine_id),
    trans_id int not null references transmissions(trans_id),
    color_id int not null references colors(color_id)
);