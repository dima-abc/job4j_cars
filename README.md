[![Java CI with Maven job4j_cars](https://github.com/Dima-Stepanov/job4j_cars/actions/workflows/maven.yml/badge.svg)](https://github.com/Dima-Stepanov/job4j_cars/actions/workflows/maven.yml)
[![codecov](https://codecov.io/gh/Dima-Stepanov/job4j_cars/branch/master/graph/badge.svg?token=5PkSEUrrf8)](https://codecov.io/gh/Dima-Stepanov/job4j_cars)
<h1>Проект job4j_cars</h1><br>
<h2>Реализацие площадки машин.</h2><br>

<h6>Опсиание:</h6><br>
Проект содержит базу данных cars_db.
Схема таблиц базы данных: <br>

![](img/sql_diagram.jpg) <br>

Между таблицами categories, models, marks, years, bodies, users, abs, cars, <br>
drivers, engines, transmissions, colors, history_owner, <br>
возникают следующие связи: <br>
Many to One <br>
Models -1-> Marks <br>
Photo -1-> Cars <br>
Cars -1-> Engines <br>
Cars -1-> Bodies <br>
Cars -1-> Category <br>
Cars -1-> Transmissions <br>
Cars -1-> Models <br>
Cars -1-> Colors <br>
Cars -1-> Years <br>
Abs -1-> Cars <br>
Abs -1-> Users <br>
Many to Many <br>
CarsHistory_ownerDriver <br>

<b>Реализованы 13 модели данных:</b><br>
Body - описывает кузов автомобиля, одна из характеристик Car. <br>
Category - описывает категорию автомобиля, одна из характеристик Car. <br>
Color - цвет автомобиля, одна из характеристик Car. <br>
Driver - описывает владельца автомобиля, одна из характеристик Car. <br>
Engine - описывает двигатель автомобиля, одна из характеристик Car. <br>
Mark - описывает марку автомобиля, составляет Model. <br>
Model - описывает модель автомобиля, одна из характеристик Car. <br>
Photo - описывает модель фото автомобиля, одна из характеристик Car. <br>
Transmission - описывает модель трансмиссию автомобиля, одна из характеристик Car. <br>
Year - описывает модель год выпуска авто, одна из характеристик Car. <br>
Car - описывает характеристики автомобиля.<br>
User - описывает автора обьявления. <br>
Ab - описывает модель самого обьявления. <br>

В моделях данных организован mapping данных к таблицам базы данных cars_db: <br>
Справочники: bodies.sql, categories.sql, colors.sql, engines.sql, marks.sql, <br>
models.sql, transmissions.sql, years.sql, drivers.sql, engines.sql, history_owner.sql. <br>
Основные таблицы: cars.sql, users.sql, abs.sql drivers.sql. <br>

<b>MVC модель приложения и зависимости между формами и запросами. </b>

![](img/MVC.jpg) <br>

<h3>Основная страница. таблица со всеми объявлениям машин на продажу.</h3>
На странице присутствует кнопка добавить новое объявление. <br>

![](img/index.jpg) <br>

<h3>Переходить на страницу добавления. </h3>
Открывается вид выбора марки авто. <br>

![](img/selectMark.jpg) <br>

Далее отображается форма дял добавления нового объявления, различные характеристики и до 3-х фото. <br>

![](img/createCar.jpg) <br>

После добавления объявления осуществляется переход на главную страницу со всеми объявлениями

Добавление нового объявления разрешено только авторизованным пользователям. <br>
Если пользователь не авторизован клиент перенаправляется на страницу авторизации. <br>

![](img/login.jpg) <br>

<h3>Объявление имеет статус продано. или нет.</h3>

<h3>При нажатии на фото можно просмотреть детали объявления </h3>
При этом авторизованному создателю объявления доступны редактирование и закрытие объявления.<br>
На виде подробного описания объявления отображается несколько картинок в виде карусели. <br>

![](img/detailAb.jpg) <br>

<h3>Фильтры для поиска </h3>
В панели навигации расположены кнопки для фильтрации поиска объявлений. <br>
Возможно отфильтровать объявления по категории, по марке авто, по типу кузова, <br>
опубликованные сегодня, поиск активных объявлений. <br>

![](img/filter.jpg) <br>


<h3>Для пользователей</h3>
Для пользователей есть возможность авторизоваться, покинуть сессию, изменить учетные данные, <br>
и зарегистрироваться новому пользователя. <br>

Вид для нового пользователя <br>

![](img/newUser.jpg) <br>

Вид для редактирования пользователя <br>

![](img/editUser.jpg) <br>


### Контакты

> email: [haoos@inbox.ru](mailto:haoos@inbox.ru) <br>
> tl: [Dima_software](https://t.me/Dima_software) <br>
> github.com: [Dima-Stepanov](https://github.com/Dima-Stepanov)

