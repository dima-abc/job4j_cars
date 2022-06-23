package ru.job4j.cars.repository.repcatalog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Function;

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
     * Шаблон проектирования WRAPPER.
     *
     * @param command Function
     * @param <T>     T type.
     * @return T type.
     */
    default <T> T tx(final Function<Session, T> command, SessionFactory sf) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            tx.rollback();
            throw e;
        }
    }
}
