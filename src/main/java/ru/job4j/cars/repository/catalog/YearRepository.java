package ru.job4j.cars.repository.catalog;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.cmodel.Year;

import java.util.List;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * YearRepository управление справочником года выпуска автомобиля.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.06.2022
 */
@Repository
public class YearRepository implements ICatalog<Year> {
    private final SessionFactory sf;

    public YearRepository(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Найти год по ID
     *
     * @param id int
     * @return Year
     */
    @Override
    public Year findById(int id) {
        return tx(session -> session.get(Year.class, id), sf);
    }

    /**
     * Весь справочник Year
     *
     * @return List
     */
    @Override
    public List<Year> findAll() {
        return tx(session -> session.createQuery("from Year").list(), sf);
    }
}
