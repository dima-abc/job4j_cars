package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.List;

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
    private static final String HQL_CAR = "select ca from Car ca "
            + "join fetch ca.category cat "
            + "join fetch ca.model mo "
            + "join fetch mo.mark ma "
            + "join fetch ca.year ye "
            + "join fetch ca.body bo "
            + "join fetch ca.engine en "
            + "join fetch ca.transmission tr "
            + "join fetch ca.color co "
            + "join fetch ca.drivers di "
            + "join fetch ca.photos ph";

    public CarRepository(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public boolean created(Car car) {
        boolean result = false;
        try {
            tx(session -> session.save(car), sf);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(int id, Car car) {
        boolean result = false;
        try {
            result = this.tx(session -> {
                        car.setId(id);
                        session.update(car);
                        return true;
                    },
                    sf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
