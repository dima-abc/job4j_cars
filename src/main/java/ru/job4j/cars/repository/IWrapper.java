package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Function;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.06.2022
 */
public interface IWrapper {

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
