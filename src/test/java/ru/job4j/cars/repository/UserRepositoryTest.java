package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * UserRepository TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.07.2022
 */
class UserRepositoryTest {
    private static SessionFactory sf;
    public static List<User> expected = new ArrayList<>();
    private static UserRepository userRepository;

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
        userRepository = new UserRepository(sf);
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            User type1 = User.of("Lana", "lana@mail.ru", "1");
            User type2 = User.of("Gena", "Gena@mail.ru", "2");
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
    void createUserThenTrue() {
        User user = User.of("1", "1@amil.ru", "123");
        boolean result = userRepository.create(user);
        User expect = userRepository.findById(user.getId());
        assertTrue(result);
        assertEquals(expect, user);
    }

    @Test
    void createUserThenFalse() {
        boolean result = userRepository.create(User.of("Ivan", expected.get(0).getEmail(), "123"));
        assertFalse(result);
    }

    @Test
    void findByIdUser() {
        User result = userRepository.findById(expected.get(0).getId());
        assertEquals(expected.get(0), result);
    }

    @Test
    void findByIdUserThenNull() {
        User result = userRepository.findById(-1);
        assertNull(result);
    }

    @Test
    void updateUserThenTrue() {
        User user = userRepository.findById(expected.get(1).getId());
        user.setName("Dima");
        boolean result = userRepository.update(user);
        User userInDb = userRepository.findById(user.getId());
        assertTrue(result);
        assertEquals(user, userInDb);
    }

    @Test
    void findByEmailPassword() {
        User expect = userRepository.findById(expected.get(1).getId());
        User result = userRepository.findByEmailPassword(expect);
        assertEquals(expect, result);
    }
}