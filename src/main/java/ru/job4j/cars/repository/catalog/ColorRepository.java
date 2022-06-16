package ru.job4j.cars.repository.catalog;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.cmodel.Color;

import java.util.List;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * ColorRepository управление справочником цветов.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.06.2022
 */
@Repository
public class ColorRepository implements ICatalog<Color> {
    private final SessionFactory sf;

    public ColorRepository(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Найти цвет по id.
     *
     * @param id int
     * @return Color
     */
    @Override
    public Color findById(int id) {
        return tx(session -> session.get(Color.class, id), sf);
    }

    /**
     * Весь справочник Color.
     *
     * @return List
     */
    @Override
    public List<Color> findAll() {
        return tx(session -> session.createQuery("from Color").list(), sf);
    }
}
