/* Таблица abs содержит обявления на площадке */
create table if not exists abs
(
    ab_id      serial primary key,
    ab_name    varchar(255),
    ab_created timestamp not null,
    ab_done    timestamp default null,
    car_id     int       not null references cars (car_id),
    user_id    int       not null references users (user_id)
);