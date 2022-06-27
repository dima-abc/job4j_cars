package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Ab;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.AbService;
import ru.job4j.cars.service.CarService;
import ru.job4j.cars.service.servcatalog.ModelService;

import java.io.IOException;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * AbController контроллер отображение видов объявлений.
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

    /**
     * Отображение всех объявлений.
     *
     * @param model Model
     * @return index.html
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("abAll", abService.findAllAb());
        return "ab/index";
    }

    /**
     * Метод пост сохраняет новое объявление.
     *
     * @param car  Car
     * @param file MultipartFile
     * @return String
     * @throws IOException Exception.
     */
    @PostMapping("/createAb")
    public String create(@ModelAttribute Car car,
                         @RequestParam("file") MultipartFile file) throws IOException {
        car.setPhoto(file.getBytes());
        carService.create(car);
        Car newCar = carService.findByIdCar(car.getId());
        User user = new User();
        user.setId(1);
        abService.create(Ab.of(newCar, user));
        return "redirect:/";
    }


    @GetMapping("/detailAb/{abId}")
    public String detailAb(Model model, @PathVariable("abId") int abId) {
        model.addAttribute("ab", abService.findByIdAb(abId).get());
        return "ab/detailAb";
    }


}
