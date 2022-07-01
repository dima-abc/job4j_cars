package ru.job4j.cars.repository.repcatalog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.catologmodel.Color;
import ru.job4j.cars.repository.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * ColorRepository TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.07.2022
 */
class ColorRepositoryTest {
    private static SessionFactory sf;
    public static List<Color> expected = new ArrayList<>();
    public static ColorRepository colorRepository;

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
        colorRepository = new ColorRepository(sf);
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            Color color1 = Color.of("red", "RR");
            Color color = Color.of("blue", "BB");
            session.persist(color);
            session.persist(color1);
            expected.add(color);
            expected.add(color1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    @Test
    void findById() {
        Color result = colorRepository.findById(expected.get(1).getId());
        assertEquals(expected.get(1), result);
    }

    @Test
    void findByIdThenNull() {
        Color result = colorRepository.findById(-1);
        assertNull(result);
    }

    @Test
    void findAll() {
        List<Color> result = colorRepository.findAll();
        assertEquals(expected, result);
    }
}