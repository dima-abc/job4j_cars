package ru.job4j.cars.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.cars.model.catologmodel.Category;
import ru.job4j.cars.service.servcatalog.CategoryService;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * CategoryController test MOCKITO
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 01.07.2022
 */
class CategoryControllerTest {

    @Test
    void fCategorySelect() {
        List<Category> categories = List.of(Category.of("1"), Category.of("2"));
        CategoryService categoryService = mock(CategoryService.class);
        when(categoryService.findAllCategory()).thenReturn(categories);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        CategoryController categoryController = new CategoryController(categoryService);
        String page = categoryController.fCategorySelect(model, session);
        verify(model).addAttribute("user", categoryController.getUserSession(session));
        verify(model).addAttribute("categories", categoryService.findAllCategory());
        String expectPage = "car/fCategory";
        assertEquals(expectPage, page);
    }
}