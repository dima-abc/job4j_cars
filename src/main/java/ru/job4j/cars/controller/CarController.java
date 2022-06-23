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
import ru.job4j.cars.service.*;
import ru.job4j.cars.service.servcatalog.*;

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
    private final ModelService modelService;
    private final YearService yearService;
    private final BodyService bodyService;
    private final EngineService engineService;
    private final CategoryService categoryService;

    public CarController(CarService carService, MarkService markService, ModelService modelService,
                         YearService yearService, BodyService bodyService, EngineService engineService,
                         CategoryService categoryService) {
        this.carService = carService;
        this.markService = markService;
        this.modelService = modelService;
        this.yearService = yearService;
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.categoryService = categoryService;
    }

    @GetMapping("/createCar")
    public String selectModel(@RequestParam("mark.id") int markId, Model model) {
        model.addAttribute("mark", markService.findByIdMark(markId));
        model.addAttribute("models", modelService.findAllMark(markId));
        model.addAttribute("categories", categoryService.findAllCategory());
        model.addAttribute("years", yearService.findAllYear());
        model.addAttribute("bodies", bodyService.findAllBody());
        model.addAttribute("engines", engineService.findAllEngine());
        return "car/createCar";
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
