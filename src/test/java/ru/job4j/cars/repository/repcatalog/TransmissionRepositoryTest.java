package ru.job4j.cars.repository.repcatalog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.catologmodel.Transmission;
import ru.job4j.cars.repository.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * TransmissionRepository TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.07.2022
 */
class TransmissionRepositoryTest {
    private static SessionFactory sf;
    public static List<Transmission> expected = new ArrayList<>();
    private static TransmissionRepository transmissionRepository;

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
        transmissionRepository = new TransmissionRepository(sf);
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            Transmission type1 = Transmission.of("MTT");
            Transmission type2 = Transmission.of("ATT");
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
        Transmission result = transmissionRepository.findById(expected.get(1).getId());
        assertEquals(expected.get(1), result);
    }

    @Test
    void findByIdThenNull() {
        Transmission result = transmissionRepository.findById(-1);
        assertNull(result);
    }

    @Test
    void findAll() {
        List<Transmission> result = transmissionRepository.findAll();
        assertEquals(expected, result);
    }
}