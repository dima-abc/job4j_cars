package ru.job4j.cars.repository.repcatalog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ru.job4j.cars.model.catologmodel.Body;
import ru.job4j.cars.repository.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * BodyRepository TEST/
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.07.2022
 */
class BodyRepositoryTest {
    private static SessionFactory sf;
    public static List<Body> expected = new ArrayList<>();
    public static BodyRepository bodyRepository;

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
        bodyRepository = new BodyRepository(sf);
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            Body body = Body.of("седан");
            Body body1 = Body.of("баклажан");
            session.persist(body);
            session.persist(body1);
            expected.add(body);
            expected.add(body1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    @Test
    void findById() {
        Body result = bodyRepository.findById(expected.get(1).getId());
        assertEquals(expected.get(1), result);
    }

    @Test
    void findByIdThenNull() {
        Body result = bodyRepository.findById(-1);
        assertNull(result);
    }

    @Test
    void findAll() {
        List<Body> result = bodyRepository.findAll();
        assertEquals(expected, result);
    }
}