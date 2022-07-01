package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.catologmodel.Mark;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.06.2022
 */
@Repository
public class CarRepository implements ICrud<Car> {
    private final SessionFactory sf;
    private static final String HQL_CAR = new StringBuilder()
            .append("select ca from Car ca ")
            .append("join fetch ca.category cat ")
            .append("join fetch ca.model mo ")
            .append("join fetch mo.mark ma ")
            .append("join fetch ca.year ye ")
            .append("join fetch ca.body bo ")
            .append("join fetch ca.engine en ")
            .append("join fetch ca.transmission tr ")
            .append("join fetch ca.color co ")
            .append("join fetch ca.drivers di ")
            .append("join fetch ca.photos ph")
            .toString();

    public CarRepository(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public boolean created(Car car) {
        AtomicBoolean rsl = new AtomicBoolean(false);
        try {
            tx(session -> session.save(car), sf);
            rsl.set(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl.get();
    }

    @Override
    public boolean update(int id, Car car) {
        AtomicBoolean result = new AtomicBoolean(false);
        try {
            this.tx(session -> {
                        car.setId(id);
                        session.update(car);
                        result.set(true);
                        return result.get();
                    },
                    sf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.get();
    }

    @Override
    public Car findById(int id) {
        return (Car) tx(session -> session.createQuery(HQL_CAR + " where ca.id=:id")
                .setParameter("id", id)
                .uniqueResult(), sf);
    }

    @Override
    public List<Car> findAll() {
        return tx(session -> session.createQuery(HQL_CAR).list(), sf);
    }
}
