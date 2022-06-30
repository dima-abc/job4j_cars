package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cars.model.catologmodel.Body;
import ru.job4j.cars.service.servcatalog.BodyService;

import javax.servlet.http.HttpSession;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * BodyController
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 30.06.2022
 */
@Controller
public class BodyController implements IController {
    private final BodyService bodyService;

    public BodyController(BodyService bodyService) {
        this.bodyService = bodyService;
    }

    @GetMapping("/fBody")
    public String fBodySelect(Model model, HttpSession session) {
        model.addAttribute("user", getUserSession(session));
        model.addAttribute("bodies", bodyService.findAllBody());
        return "car/fBody";
    }
}
