package ru.job4j.cars.repository.repcatalog;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.catologmodel.Engine;

import java.util.List;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * EngineRepository управление справочника двигателей.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.06.2022
 */
@Repository
public class EngineRepository implements ICatalog<Engine> {
    private final SessionFactory sf;

    public EngineRepository(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Найти двигатель по id.
     *
     * @param id int
     * @return Engines
     */
    @Override
    public Engine findById(int id) {
        return tx(session -> session.get(Engine.class, id), sf);
    }

    /**
     * Весь справочник Engines.
     *
     * @return List
     */
    @Override
    public List<Engine> findAll() {
        return tx(session -> session.createQuery("from Engine").list(), sf);
    }
}
