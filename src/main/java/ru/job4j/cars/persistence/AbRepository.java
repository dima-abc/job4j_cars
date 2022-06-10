package ru.job4j.cars.persistence;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Ab;
import ru.job4j.cars.model.cmodel.Mark;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
public class AbRepository implements IAbRepository<Ab> {
    private static final String HQL_AB = new StringBuilder()
            .append("select ab from Ab ab ")
            .append("join fetch ab.car ca ")
            .append("join fetch ca.category cat ")
            .append("join fetch ca.model mo ")
            .append("join fetch mo.mark ma ")
            .append("join fetch ca.year ye ")
            .append("join fetch ca.body bo ")
            .append("join fetch ca.engine en ")
            .append("join fetch ca.transmission tr ")
            .append("join fetch ca.color co ")
            .append("join fetch ca.drivers dr ")
            .append("join fetch ab.user").toString();

    private final SessionFactory sf;

    public AbRepository(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public boolean created(Ab type) {
        return false;
    }

    @Override
    public boolean update(int id, Ab type) {
        return false;
    }

    @Override
    public Ab findById(int id) {
        return null;
    }

    @Override
    public List<Ab> findAll() {
        return tx(session -> session.createQuery("select a from Ab a "
                + "join fetch a.car c "
                + "join fetch c.category ca "
                + "join fetch c.model mo "
                + "join fetch mo.mark ma "
                + "join fetch c.year ye "
                + "join fetch c.body bo "
                + "join fetch c.engine en "
                + "join fetch c.transmission tr "
                + "join fetch c.color co", Ab.class).list(), sf);
    }

    /**
     * Показать объявления за последний день.
     *
     * @return List
     */
    @Override
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
    @Override
    public List<Ab> getWithPhoto() {
        return tx(session ->
                session.createQuery(HQL_AB + " where ca.photo != null", Ab.class)
                        .list(), sf
        );
    }

    /**
     * Показать объявление определенной марки автомобиля.
     *
     * @param mark Mark
     * @return List.
     */
    @Override
    public List<Ab> getWithMark(Mark mark) {
        return tx(session ->
                session.createQuery(HQL_AB + " where mo.mark =: mark", Ab.class)
                        .setParameter("mark", mark)
                        .list(), sf
        );
    }
}
