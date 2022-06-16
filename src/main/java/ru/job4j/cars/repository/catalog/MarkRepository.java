package ru.job4j.cars.repository.catalog;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.cmodel.Mark;

import java.util.List;

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
@Repository
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
        return tx(session -> session.get(Mark.class, id), sf);
    }

    /**
     * Весь справочник Mark.
     *
     * @return List
     */
    @Override
    public List<Mark> findAll() {
        return tx(session -> session.createQuery("from Mark").list(), sf);
    }
}
