package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Ab;
import ru.job4j.cars.model.catologmodel.Body;
import ru.job4j.cars.model.catologmodel.Category;
import ru.job4j.cars.model.catologmodel.Mark;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
public class AbRepository implements IWrapper {
    private static final String HQL_AB = new StringBuilder()
            .append("select distinct ab from Ab ab ")
            .append("join fetch ab.user us ")
            .append("join fetch ab.car c ")
            .append("join fetch c.category ca ")
            .append("join fetch c.model mo ")
            .append("join fetch c.photos ph ")
            .append("join fetch mo.mark ma ")
            .append("join fetch c.year ye ")
            .append("join fetch c.body bo ")
            .append("join fetch c.engine en ")
            .append("join fetch c.transmission tr ")
            .append("join fetch c.color co ")
            .append("join fetch c.drivers di")
            .toString();

    private final SessionFactory sf;

    public AbRepository(SessionFactory sf) {
        this.sf = sf;
    }

    public boolean created(final Ab ab) {
        AtomicBoolean rsl = new AtomicBoolean(false);
        try {
            tx(session -> session.save(ab), sf);
            rsl.set(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl.get();
    }

    public boolean update(int id, final Ab ab) {
        AtomicBoolean result = new AtomicBoolean(false);
        try {
            this.tx(session -> {
                        ab.setId(id);
                        session.update(ab);
                        result.set(true);
                        return result.get();
                    },
                    sf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.get();
    }

    public Ab findById(int id) {
        return tx(session -> session.createQuery(HQL_AB + " where ab.id =:abId", Ab.class)
                .setParameter("abId", id).uniqueResult(), sf);
    }

    public List<Ab> findAll() {
        return tx(session -> session.createQuery(HQL_AB, Ab.class).list(), sf);
    }

    /**
     * Показать объявления за последний день.
     *
     * @return List
     */
    public List<Ab> getLastDay() {
        return tx(session ->
                session.createQuery(HQL_AB + " where ab.created >=: lastDay", Ab.class)
                        .setParameter("lastDay", LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT))
                        .list(), sf
        );
    }

    /**
     * Показать объявление только с фото.
     *
     * @return List
     */
    public List<Ab> getWithActive() {
        return tx(session ->
                session.createQuery(HQL_AB + " where ab.done is null", Ab.class)
                        .list(), sf
        );
    }

    /**
     * Показать объявление определенной марки автомобиля.
     *
     * @param mark Mark
     * @return List.
     */
    public List<Ab> getWithMark(final Mark mark) {
        return tx(session ->
                session.createQuery(HQL_AB + " where mo.mark =: mark", Ab.class)
                        .setParameter("mark", mark)
                        .list(), sf
        );
    }

    /**
     * Показать объявление определенной категории автомобиля.
     *
     * @param category Category.
     * @return List.
     */
    public List<Ab> getWithCategory(final Category category) {
        return tx(session ->
                session.createQuery(HQL_AB + " where c.category =: category", Ab.class)
                        .setParameter("category", category)
                        .list(), sf
        );
    }

    /**
     * Показать объявлений по типу кузова автомобиля.
     *
     * @param body Body.
     * @return List.
     */
    public List<Ab> getWithBody(final Body body) {
        return tx(session ->
                session.createQuery(HQL_AB + " where c.body =: body", Ab.class)
                        .setParameter("body", body)
                        .list(), sf
        );
    }
}
