package ru.job4j.cars.repository;

import org.checkerframework.checker.units.qual.A;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import ru.job4j.cars.model.*;
import ru.job4j.cars.model.catologmodel.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * 3.3.3. HQL, Criteria
 * 2. Фильтры для площадок машин [#4745]
 * AbRepositoryTest тестирование.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 28.05.2022
 */
class AbRepositoryTest {
    private static SessionFactory sf;
    public static List<Ab> expected = new ArrayList<>();

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            Driver driver = Driver.of("DriverCar", "mail@mail.ru");
            Engine engine = Engine.of("2.0 TDI", "Дизель");
            Body body = Body.of("Sedan");
            Mark mark = Mark.of("Lada");
            Model model = Model.of("NIVA", mark);
            Category category = Category.of("Легковой");
            Year year = Year.of(2020);
            Transmission transmission = Transmission.of("Автомат");
            Color color = Color.of("Красный", "RED");
            User user = User.of("Dima", "mail@mail.ru", "123");
            session.persist(driver);
            session.persist(engine);
            session.persist(body);
            session.persist(mark);
            session.persist(model);
            session.persist(category);
            session.persist(year);
            session.persist(transmission);
            session.persist(color);
            session.persist(user);
            Car car = Car.of("111", 1000.00D, 152000, category, model,
                    year, body, engine, transmission,
                    color, "");
            Car car1 = Car.of("222", 2000.00D, 222000, category, model,
                    year, body, engine, transmission,
                    color, "");
            car.addDriver(driver);
            car.addPhoto(Photo.of(new byte[]{1}));
            session.persist(car);
            car1.addDriver(driver);
            car1.addPhoto(Photo.of(new byte[]{2}));
            session.persist(car1);
            Ab ab = Ab.of(car, user);
            ab.setDone(LocalDateTime.now().withNano(0));
            Ab ab1 = Ab.of(car1, user);
            ab.setCreated(LocalDateTime.now().minusDays(2));
            session.persist(ab);
            session.persist(ab1);
            tx.commit();
            expected.add(ab);
            expected.add(ab1);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    @Test
    void getLastDay() {
        AbRepository abRepository = new AbRepository(sf);
        List<Ab> result = abRepository.getLastDay();
        assertEquals(List.of(expected.get(1)), result);
    }

    @Test
    void getWithMark() {
        AbRepository abRepository = new AbRepository(sf);
        Mark mark = new Mark();
        mark.setId(1);
        List<Ab> result = abRepository.getWithMark(mark);
        assertEquals(expected, result);
    }


}