package ru.job4j.cars.repository.repcatalog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.catologmodel.Mark;
import ru.job4j.cars.model.catologmodel.Model;
import ru.job4j.cars.repository.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.07.2022
 */
class ModelRepositoryTest {
    private static SessionFactory sf;
    public static List<Model> expected = new ArrayList<>();
    public static List<Mark> marks = new ArrayList<>();
    private static ModelRepository modelRepository;

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
        modelRepository = new ModelRepository(sf);
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            Mark marc1 = Mark.of("LADA");
            Mark marc2 = Mark.of("BMW");
            session.persist(marc1);
            session.persist(marc2);
            marks.add(marc1);
            marks.add(marc2);
            Model type1 = Model.of("NIVA", marc1);
            Model type2 = Model.of("series 3", marc2);
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
        Model result = modelRepository.findById(expected.get(0).getId());
        assertEquals(expected.get(0), result);
    }

    @Test
    void findByIdThenNull() {
        Model result = modelRepository.findById(-1);
        assertNull(result);
    }

    @Test
    void findByMark() {
        List<Model> result = modelRepository.findByMark(marks.get(0).getId());
        List<Model> expect = List.of(modelRepository.findById(expected.get(0).getId()));
        assertEquals(expect, result);
    }

    @Test
    void findByMarkThenEmpty() {
        List<Model> result = modelRepository.findByMark(-1);
        assertTrue(result.isEmpty());
    }

    @Test
    void findAll() {
        List<Model> result = modelRepository.findAll();
        assertEquals(expected, result);
    }
}