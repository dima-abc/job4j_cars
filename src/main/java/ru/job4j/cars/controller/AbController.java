package ru.job4j.cars.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Ab;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.AbService;
import ru.job4j.cars.service.CarService;

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

    @PostMapping("/createAb")
    public String create(@ModelAttribute Car car,
                         @RequestParam("file") MultipartFile file) throws IOException {
        car.setPhoto(file.getBytes());
        carService.create(car);
        User user = new User();
        user.setId(1);
        Ab newAb = Ab.of(car.getModel().getName(), car, user);
        abService.create(newAb);
        return "redirect:/";
    }


}
