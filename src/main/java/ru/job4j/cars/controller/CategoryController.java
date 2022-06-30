package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cars.service.servcatalog.CategoryService;

import javax.servlet.http.HttpSession;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * CategoryController
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 30.06.2022
 */
@Controller
public class CategoryController implements IController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/fCategory")
    public String fCategorySelect(Model model, HttpSession session) {
        model.addAttribute("user", getUserSession(session));
        model.addAttribute("categories", categoryService.findAllCategory());
        return "car/fCategory";
    }
}
