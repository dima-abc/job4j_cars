package ru.job4j.cars.repository.repcatalog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.catologmodel.Category;
import ru.job4j.cars.repository.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * CategoryRepository TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.07.2022
 */
class CategoryRepositoryTest {
    private static SessionFactory sf;
    public static List<Category> expected = new ArrayList<>();
    public static CategoryRepository categoryRepository;

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
        categoryRepository = new CategoryRepository(sf);
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            Category category = Category.of("мото");
            Category category1 = Category.of("вело");
            session.persist(category);
            session.persist(category1);
            expected.add(category);
            expected.add(category1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    @Test
    void findById() {
        Category result = categoryRepository.findById(expected.get(0).getId());
        assertEquals(expected.get(0), result);
    }

    @Test
    void findByIdThenNull() {
        Category result = categoryRepository.findById(-1);
        assertNull(result);
    }

    @Test
    void findAll() {
        List<Category> result = categoryRepository.findAll();
        assertEquals(expected, result);
    }
}