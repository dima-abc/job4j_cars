/* Таблица history_owner связь Many-To-Mane между таблицами cars <-> drivers.
По ней можно получить список всех машин водителя,
а также получить список всех владельцев по машине.*/
create table if not exists history_owner(
    ho_id serial primary key,
    driver_id int not null references drivers(driver_id),
    vin int not null references cars(vin)
);