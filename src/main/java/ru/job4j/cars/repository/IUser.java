package ru.job4j.cars.repository;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.06.2022
 */
public interface IUser<T> extends IWrapper {
    boolean create(T type);

    T findById(int id);

    boolean update(T type);

    T findByEmailPassword(T type);

}
