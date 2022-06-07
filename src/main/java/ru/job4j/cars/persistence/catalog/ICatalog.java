package ru.job4j.cars.persistence.catalog;

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
public interface ICatalog<T> {

    T findById(int id);

    List<T> findAll();
}
