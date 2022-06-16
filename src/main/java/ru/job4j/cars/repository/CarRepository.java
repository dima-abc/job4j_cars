package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.catalog.ICatalog;

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
public class CarRepository implements ICatalog<Car> {
    private final SessionFactory sf;

    public CarRepository(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Car findById(int id) {
        return (Car) tx(session -> session.createQuery("select ca from Car ca where id=:id")
                .setParameter("id", id)
                .uniqueResult(), sf);
    }

    @Override
    public List<Car> findAll() {
        return tx(session -> session.createQuery("from Car").list(), sf);
    }
}
