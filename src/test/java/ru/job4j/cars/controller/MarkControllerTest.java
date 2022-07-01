package ru.job4j.cars.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.cars.model.User;
import ru.job4j.cars.model.catologmodel.Mark;
import ru.job4j.cars.service.servcatalog.MarkService;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * MarkController test MOCKITO
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 01.07.2022
 */
class MarkControllerTest {

    @Test
    void selectMark() {
        List<Mark> marks = List.of(Mark.of("1"), Mark.of("2"));
        MarkService markService = mock(MarkService.class);
        when(markService.findAllMark()).thenReturn(marks);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        MarkController markController = new MarkController(markService);
        String page = markController.selectMark(model, session);
        verify(model).addAttribute("user", markController.getUserSession(session));
        verify(model).addAttribute("marks", markService.findAllMark());
        String expectPage = "car/selectMark";
        assertEquals(expectPage, page);
    }

    @Test
    void fMarkSelect() {
        List<Mark> marks = List.of(Mark.of("1"), Mark.of("2"));
        MarkService markService = mock(MarkService.class);
        when(markService.findAllMark()).thenReturn(marks);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        MarkController markController = new MarkController(markService);
        String page = markController.fMarkSelect(model, session);
        verify(model).addAttribute("user", markController.getUserSession(session));
        verify(model).addAttribute("marks", markService.findAllMark());
        String expectPage = "car/fMark";
        assertEquals(expectPage, page);
    }
}