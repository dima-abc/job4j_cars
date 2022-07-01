package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.catologmodel.*;
import ru.job4j.cars.repository.repcatalog.YearRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * CarRepository TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 01.07.2022
 */
class CarRepositoryTest {
    private static SessionFactory sf;
    public static List<Car> expected = new ArrayList<>();
    private static CarRepository carRepository;
    private static Category category = Category.of("moto");
    private static Mark mark = Mark.of("LADA");
    private static Model model = Model.of("NIVA", mark);
    private static Year year = Year.of(2022);
    private static Body body = Body.of("Джип");
    private static Engine engine = Engine.of("1.6", "GAS");
    private static Transmission transmission = Transmission.of("ATT");
    private static Color color = Color.of("RED", "RRR");
    private static Driver driver = Driver.of("Iva", "iva@mail.ru");

    @AfterAll
    public static void close() {
        if (sf != null) {
            sf.close();
        }
    }

    @BeforeAll
    public static void init() {
        sf = HibernateUtil.getSessionFactory();
        carRepository = new CarRepository(sf);
    }

    @BeforeAll
    public static void addModelToData() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            session.persist(category);
            session.persist(mark);
            session.persist(model);
            session.persist(year);
            session.persist(body);
            session.persist(engine);
            session.persist(transmission);
            session.persist(color);
            session.persist(driver);
            Car type1 = Car.of("vvv", 1000D, 120000,
                    category, model, year, body, engine, transmission,
                    color, "описание");
            type1.addPhoto(Photo.of(new byte[]{10}));
            type1.addDriver(driver);
            Car type2 = Car.of("www", 2000D, 50000,
                    category, model, year, body, engine, transmission,
                    color, "описание 2");
            type2.addPhoto(Photo.of(new byte[]{11}));
            type2.addDriver(driver);
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
    void createdCarThenTrue() {
        Car newCar = Car.of("ppp", 100D, 2000, category, model,
                year, body, engine, transmission, color, "description");
        newCar.addPhoto(Photo.of(new byte[]{12}));
        newCar.addDriver(driver);
        boolean result = carRepository.created(newCar);
        Car carInDb = carRepository.findById(newCar.getId());
        assertTrue(result);
        assertEquals(newCar, carInDb);
    }

    @Test
    void updateCarThenTrue() {
        Car car = carRepository.findById(expected.get(0).getId());
        car.setVin("NEW_VIN");
        boolean result = carRepository.update(car.getId(), car);
        Car carInDb = carRepository.findById(car.getId());
        assertEquals(car, carInDb);
        assertTrue(result);
    }

    @Test
    void findById() {
        Car result = carRepository.findById(expected.get(1).getId());
        assertEquals(expected.get(1), result);
    }

    @Test
    void findAll() {
        List<Car> result = carRepository.findAll();
        assertEquals(expected, result);
    }
}