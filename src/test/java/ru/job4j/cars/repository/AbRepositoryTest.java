package ru.job4j.cars.repository;

import org.checkerframework.checker.units.qual.A;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Ab;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.User;
import ru.job4j.cars.model.catologmodel.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * AbRepository TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.07.2022
 */
class AbRepositoryTest {
    private static SessionFactory sf;
    private static List<Ab> expected = new ArrayList<>();
    private static List<Car> cars = new ArrayList<>();
    private static AbRepository abRepository;
    private static Category category = Category.of("moto");
    private static Mark mark = Mark.of("LADA");
    private static Mark markFromFind = Mark.of("BMW");
    private static Model model = Model.of("NIVA", mark);
    private static Model modelFromFind = Model.of("3 series", markFromFind);
    private static Year year = Year.of(2022);
    private static Body body = Body.of("Джип");
    private static Engine engine = Engine.of("1.6", "GAS");
    private static Transmission transmission = Transmission.of("ATT");
    private static Color color = Color.of("RED", "RRR");
    private static Driver driver = Driver.of("Iva", "iva@mail.ru");
    private static User user = User.of("Danil", "danil@mail.ru", "123");

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
        abRepository = new AbRepository(sf);
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            session.persist(category);
            session.persist(mark);
            session.persist(markFromFind);
            session.persist(model);
            session.persist(modelFromFind);
            session.persist(year);
            session.persist(body);
            session.persist(engine);
            session.persist(transmission);
            session.persist(color);
            session.persist(driver);
            session.persist(user);
            Car car1 = Car.of("vvv", 1000D, 120000,
                    category, model, year, body, engine, transmission,
                    color, "описание");
            car1.addPhoto(Photo.of(new byte[]{1}));
            car1.addDriver(driver);
            Car car2 = Car.of("www", 2000D, 50000,
                    category, model, year, body, engine, transmission,
                    color, "описание 2");
            car2.addPhoto(Photo.of(new byte[]{1}));
            car2.addDriver(driver);
            Car carFindMark = Car.of("findMark", 1000D, 2, category,
                    modelFromFind, year, body, engine, transmission, color, "");
            carFindMark.addPhoto(Photo.of(new byte[]{1}));
            carFindMark.addDriver(driver);
            session.persist(car1);
            session.persist(car2);
            session.persist(carFindMark);
            cars.add(car1);
            cars.add(car2);
            cars.add(carFindMark);
            Ab abLastDay = Ab.of(car1, user);
            Ab abDone = Ab.of(car2, user);
            abDone.setCreated(LocalDateTime.now().minusDays(100));
            abDone.setDone(LocalDateTime.now().withNano(0));
            Ab abCreateYesterday = Ab.of(car1, user);
            abCreateYesterday.setCreated(LocalDateTime.now().minusDays(5));
            Ab abFindByMark = Ab.of(carFindMark, user);
            session.persist(abLastDay);
            session.persist(abDone);
            session.persist(abCreateYesterday);
            session.persist(abFindByMark);
            expected.add(abLastDay);
            expected.add(abDone);
            expected.add(abCreateYesterday);
            expected.add(abFindByMark);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    @Test
    void createdAbThenTrue() {
        Ab newAb = Ab.of(cars.get(1), user);
        boolean result = abRepository.created(newAb);
        Ab abInDb = abRepository.findById(newAb.getId());
        expected.add(newAb);
        assertEquals(abInDb, newAb);
        assertTrue(result);

    }

    @Test
    void updateAbThenTrue() {
        Ab newAb = Ab.of(cars.get(1), user);
        abRepository.created(newAb);
        newAb.setDone(LocalDateTime.now().withNano(0));
        boolean result = abRepository.update(newAb.getId(), newAb);
        expected.add(newAb);
        Ab abInDb = abRepository.findById(newAb.getId());
        assertEquals(newAb, abInDb);
        assertTrue(result);

    }

    @Test
    void findByIdAb() {
        Ab result = abRepository.findById(expected.get(1).getId());
        assertEquals(expected.get(1), result);
    }

    @Test
    void findByIdAbThenNull() {
        Ab ab = abRepository.findById(-1);
        assertNull(ab);
    }

    @Test
    void findAllAb() {
        List<Ab> result = abRepository.findAll();
        assertEquals(expected, result);
    }

    @Test
    void getLastDay() {
        List<Ab> result = abRepository.getLastDay();
        assertEquals(List.of(expected.get(0), expected.get(3), expected.get(4)), result);
    }

    @Test
    void getWithActive() {
        List<Ab> result = abRepository.getWithActive();
        List<Ab> expect = List.of(expected.get(0), expected.get(2), expected.get(3), expected.get(4));
    }

    @Test
    void getWithMark() {
        List<Ab> result = abRepository.getWithMark(markFromFind);
        List<Ab> expect = List.of(expected.get(3));
        assertEquals(expect, result);
    }

    @Test
    void getWithCategory() {
        Ab ab = expected.get(0);
        List<Ab> result = abRepository.getWithCategory(category);
        assertEquals(expected, result);
    }

    @Test
    void getWithBody() {
        List<Ab> result = abRepository.getWithBody(body);
        assertEquals(expected, result);
    }

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }
}










/**
 * Я осознаю что этот тест г-но.
 */