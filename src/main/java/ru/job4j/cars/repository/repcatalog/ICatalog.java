package ru.job4j.cars.repository.repcatalog;

import ru.job4j.cars.repository.IWrapper;

import java.util.List;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * ICatalog интерфейс описывает управление справочниками.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.06.2022
 */
public interface ICatalog<T> extends IWrapper {
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
