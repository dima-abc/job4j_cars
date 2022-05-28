package ru.job4j.cars.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Ab;
import ru.job4j.cars.model.Mark;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * 3.3.3. HQL, Criteria
 * 2. Фильтры для площадок машин [#4745]
 * AbRepository слой persistence модели данны обьявлений "Ab"
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 28.05.2022
 */
@Repository
public class AbRepository {
    private static final String HQL_AB = new StringBuilder()
            .append("select ab from Ab ab ")
            .append("join fetch ab.car c ")
            .append("join fetch c.model mo ")
            .append("join fetch mo.mark ma ")
            .append("join fetch c.body b ")
            .append("join fetch c.engine e ")
            .append("join fetch c.drivers d ")
            .append("join fetch ab.user u where ").toString();

    private final SessionFactory sf;

    public AbRepository(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Показать обьявления за последний день.
     *
     * @return List
     */
    public List<Ab> getLastDay() {
        return tx(session ->
                session.createQuery(HQL_AB + "ab.created >=: lastDay", Ab.class)
                        .setParameter("lastDay", LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT))
                        .list()
        );
    }

    /**
     * Покозать обьявление только с фото.
     *
     * @return List
     */
    public List<Ab> getWithPhoto() {
        return tx(session ->
                session.createQuery(HQL_AB + "c.photo != null", Ab.class)
                        .list()
        );
    }

    /**
     * Покозать обьявление определенной марки автомобиля.
     *
     * @param mark Mark
     * @return List.
     */
    public List<Ab> getWithMark(Mark mark) {
        return tx(session ->
                session.createQuery(HQL_AB + "mo.mark =: mark", Ab.class)
                        .setParameter("mark", mark)
                        .list()
        );
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
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
