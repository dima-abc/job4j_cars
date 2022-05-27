<h1>Проект job4j_cars</h1><br>
<h2>Реализацие площадки машин.</h2><br>

<h6>Опсиание:</h6><br>
Проект содержит базу данных cars_db.
Схема таблиц базы данных: <br>
![](img/sql_diagram.jpg) <br>

Между таблицами models, marks, bodies, users, abs, cars, drivers, engines, history_owner, <br>
возникают следующие связи: <br>
Many to One <br>
Models -1-> Marks <br>
Many to One <br>
Cars -1-> Engines <br>
Many to One <br>
Cars -1-> Bodies <br>
Many to One <br>
Abs -1-> Cars <br>
Many to One <br>
Abs -1-> Users <br>
Many to Many <br>
CarsHistory_ownerDriver <br>

<b>Реализованы 8 модели данных:</b><br>
Model - описывает модель автомобиля. <br>
Mark - описывает марку автомобиля, состовляет Model. <br>
Body - описывает кузов автомобиля, одна из характеристик Car. <br>
Driver - описывает владельца автомобиля, одна из характеристик Car. <br>
Engine - описывает двигатель автомобиля, одна из характеристик Car. <br>
Car - описывает характеристики автомобиля.<br>
User - описывает автора обьявления. <br>
Ab - описывает модель самого обьявления. <br>

В моделях данных организован mapping данных к таблицам базы данных cars_db: <br>
marks.sql, models.sql, bodies.sql, cars.sql, drivers.sql, engines.sql, history_owner.sql. <br>
users.sql, abs.sql. <br>