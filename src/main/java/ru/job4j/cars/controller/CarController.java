package ru.job4j.cars.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.service.CarService;
import ru.job4j.cars.service.MarkService;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.06.2022
 */
@Controller
public class CarController {
    private final CarService carService;
    private final MarkService markService;

    public CarController(CarService carService, MarkService markService) {
        this.carService = carService;
        this.markService = markService;
    }

    /**
     * Добавление номера кузова автомобиля.
     *
     * @return
     */
    @GetMapping("/enterVin")
    public String enterVin() {
        return "car/selectMark";
    }

    @GetMapping("selectMark")
    public String selectMark(Model model, @RequestParam("vin") String vin) {
        Car car = new Car();
        car.setVin(vin);
        model.addAttribute("car", car);
        return "car/selectModel";
    }

    /**
     * Отображение изображения.
     *
     * @param carId int.
     * @return ResponseEntity
     */
    @GetMapping("/imgCar/{carId}")
    public ResponseEntity<Resource> download(@PathVariable("carId") Integer carId) {
        Car car = carService.findByIdCar(carId);
        System.out.println("*********************************" + car.getPhoto().toString());
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(car.getPhoto().length)
                .body(new ByteArrayResource(car.getPhoto()));
    }
}
