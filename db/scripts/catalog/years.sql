/*Таблица справочников годов выпуска автомобилей*/
create table if not exists years(
    year_id serial primary key,
    release int not null unique
);