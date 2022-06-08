package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.persistence.CarRepository;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.06.2022
 */
@Service
public class CarService {
    private final CarRepository store;

    public CarService(CarRepository store) {
        this.store = store;
    }

    public Car findByIdCar(int id) {
        return store.findById(id);
    }
}
