package ru.job4j.cars.repository.repcatalog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.catologmodel.Mark;
import ru.job4j.cars.repository.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * MarkRepository TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.07.2022
 */
class MarkRepositoryTest {
    private static SessionFactory sf;
    public static List<Mark> expected = new ArrayList<>();
    private static MarkRepository markRepository;

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
        markRepository = new MarkRepository(sf);
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            Mark type1 = Mark.of("LADA");
            Mark type2 = Mark.of("BMW");
            session.persist(type1);
            session.persist(type2);
            expected.add(type1);
            expected.add(type2);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    @Test
    void findById() {
        Mark result = markRepository.findById(expected.get(0).getId());
        assertEquals(expected.get(0), result);
    }

    @Test
    void findByIdThenNull() {
        Mark result = markRepository.findById(-1);
        assertNull(result);
    }

    @Test
    void findAll() {
        List<Mark> result = markRepository.findAll();
        assertEquals(expected, result);
    }
}