package ru.job4j.cars.repository.repcatalog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.catologmodel.Engine;
import ru.job4j.cars.repository.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * EngineRepository TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.07.2022
 */
class EngineRepositoryTest {
    private static SessionFactory sf;
    public static List<Engine> expected = new ArrayList<>();
    private static EngineRepository engineRepository;

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
        engineRepository = new EngineRepository(sf);
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            Engine type1 = Engine.of("1.6", "GES");
            Engine type2 = Engine.of("2.0", "TDI");
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
        Engine result = engineRepository.findById(expected.get(1).getId());
        assertEquals(expected.get(1), result);
    }

    @Test
    void findByIdThenNull() {
        Engine result = engineRepository.findById(-1);
        assertNull(result);
    }

    @Test
    void findAll() {
        List<Engine> result = engineRepository.findAll();
        assertEquals(expected, result);
    }
}