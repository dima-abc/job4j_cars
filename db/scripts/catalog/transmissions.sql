/*таблица для справочника коробка передач*/
create table if not exists transmissions(
    trans_id serial primary key,
    transmission varchar(100) not null unique
);