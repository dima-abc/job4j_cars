package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.cars.model.catologmodel.Mark;

import java.util.List;
import java.util.function.Function;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.06.2022
 */
public interface IRepository<T> extends IWrapper {
    boolean created(T type);

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

    /**
     * Показать объявления за последний день.
     *
     * @return List
     */
    List<T> getLastDay();

    /**
     * Показать объявление только с фото.
     *
     * @return List
     */
    List<T> getWithPhoto();

    /**
     * Показать объявление определенной марки автомобиля.
     *
     * @param mark Mark
     * @return List.
     */
    List<T> getWithMark(Mark mark);
}
