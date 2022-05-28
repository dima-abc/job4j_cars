package ru.job4j.cars.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import ru.job4j.cars.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * 3.3.3. HQL, Criteria
 * 2. Фильтры для площадок машин [#4745]
 * AbRepositoryTest Поврехностное тестирование.
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 28.05.2022
 */
class AbRepositoryTest {
    private static SessionFactory sf;
    public static List<Ab> expected = new ArrayList<>();

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
    }

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            Driver driver = Driver.of("DriverCar");
            Engine engine = Engine.of("2.0 TDI");
            Body body = Body.of("Sedan");
            Mark mark = Mark.of("Lada");
            session.save(driver);
            session.save(engine);
            session.persist(body);
            session.persist(mark);
            Model model = Model.of("NIVA", mark);
            session.persist(model);
            Car car = Car.of("description", new Byte[]{1}, model, body, engine);
            car.addDriver(driver);
            session.persist(car);
            User user = User.of("mail@mail.ru", "123");
            session.persist(user);
            Ab ab = Ab.of("Sale Lada", car, user);
            session.persist(ab);
            tx.commit();
            expected.add(ab);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    void getLastDay() {
        AbRepository abRepository = new AbRepository(sf);
        List<Ab> result = abRepository.getLastDay();
        assertEquals(expected, result);
    }

    @Test
    void getWithPhoto() {
        AbRepository abRepository = new AbRepository(sf);
        List<Ab> result = abRepository.getWithPhoto();
        assertEquals(expected, result);
    }

    @Test
    void getWithMark() {
        AbRepository abRepository = new AbRepository(sf);
        Mark mark = Mark.of("");
        mark.setId(1);
        List<Ab> result = abRepository.getWithMark(mark);
        assertEquals(expected, result);
    }
}