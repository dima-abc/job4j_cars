package ru.job4j.cars.repository.catalog;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.cmodel.Category;

import java.util.List;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * CategoryRepository управление справочником категорий авто.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.06.2022
 */
@Repository
public class CategoryRepository implements ICatalog<Category> {
    private SessionFactory sf;

    public CategoryRepository(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Найти категорию по id.
     *
     * @param id int
     * @return Category
     */
    @Override
    public Category findById(int id) {
        return tx(session -> session.get(Category.class, id), sf);
    }

    /**
     * Весь справочник Category.
     *
     * @return List
     */
    @Override
    public List<Category> findAll() {
        return tx(session -> session.createQuery("from Category").list(), sf);
    }
}
