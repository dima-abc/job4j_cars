package ru.job4j.cars.persistence.catalog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.cars.model.cmodel.Mark;
import ru.job4j.cars.model.cmodel.Year;

import java.util.List;
import java.util.function.Function;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * MarkRepository управление справочником марки автомобиля.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.06.2022
 */
public class MarkRepository implements ICatalog<Mark> {
    private final SessionFactory sf;

    public MarkRepository(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Найти марку по id.
     *
     * @param id int
     * @return Mark
     */
    @Override
    public Mark findById(int id) {
        return tx(session -> session.get(Mark.class, id));
    }

    /**
     * Весь справочник Mark.
     *
     * @return List
     */
    @Override
    public List<Mark> findAll() {
        return tx(session -> session.createQuery("from Mark").list());
    }

    /**
     * Шаблон проектирования WRAPPER.
     *
     * @param command Function
     * @param <T>     T
     * @return T
     */
    private <T> T tx(final Function<Session, T> command) {
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
