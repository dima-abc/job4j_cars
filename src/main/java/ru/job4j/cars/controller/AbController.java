package ru.job4j.cars.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.service.AbService;
import ru.job4j.cars.service.CarService;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * AbController контроллер отображение видов обьявлений.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.06.2022
 */
@Controller
public class AbController {
    private final AbService abService;
    private final CarService carService;

    public AbController(AbService abService, CarService carService) {
        this.abService = abService;
        this.carService = carService;
    }

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("**************************" + abService.findAllAb());
        System.out.println("**************************" + carService.findByIdCar(1));
        model.addAttribute("abAll", abService.findAllAb());
        return "index";
    }


    @GetMapping("/photoCar/{carId}")
    public ResponseEntity<Resource> download(@PathVariable("carId") Integer carId) {
        Car car = carService.findByIdCar(carId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(car.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(car.getPhoto()));
    }


}
