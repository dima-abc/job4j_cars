package ru.job4j.cars.repository;

import java.util.List;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * Интерфейс для Crud операций.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 30.06.2022
 */
public interface ICrud<T> extends IWrapper {
    /**
     * Метод добавления типа
     *
     * @param type Type
     * @return boolean
     */
    boolean created(T type);

    /**
     * Обновление типа
     *
     * @param id
     * @param type
     * @return
     */
    boolean update(int id, T type);

    /**
     * Данный метод должен возвращать модель T о его идентификатору.
     *
     * @param id int
     * @return T type.
     */
    T findById(int id);

    /**
     * Данный метод должен возвращать весь справочник.
     *
     * @return List
     */
    List<T> findAll();
}
