package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cars.service.servcatalog.MarkService;

import javax.servlet.http.HttpSession;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * MarcController контроллер отображения Марок авто.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 23.06.2022
 */
@Controller
public class MarkController implements IController {
    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping("/selectMark")
    public String selectMark(Model model, HttpSession session) {
        model.addAttribute("user", getUserSession(session));
        model.addAttribute("marks", markService.findAllMark());
        return "car/selectMark";
    }
}
