<h1>Проект job4j_cars</h1><br>
<h2>Реализацие площадки машин.</h2><br>

<h6>Опсиание:</h6><br>
Проект содержит базу данных cars_db.
Схема таблиц базы данных: <br>
![](img/sql_diagram.jpg) <br>

Между таблицами cars, drivers, engines, history_owner, <br>
возникают следующие связи: <br>
One to One <br>
Cars -1->Engines <br>
Many to Many <br>
CarsHistory_ownerDriver <br>

<b>Реализованы 3 модели данных:</b><br>
Car - описывает характеристики автомобиля.<br>
Driver - описывает владельца автомобиля, одна из характеристик Car. <br>
Engine - описывает двигатель автомобиля, одна из характеристик Car. <br>

В моделях данных организован mapping данных к таблицам базы данных cars_db: <br>
cars.sql, drivers.sql, engines.sql, history_owner.sql. <br>