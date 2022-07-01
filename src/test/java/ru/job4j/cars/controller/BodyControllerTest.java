package ru.job4j.cars.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.cars.model.User;
import ru.job4j.cars.model.catologmodel.Body;
import ru.job4j.cars.service.servcatalog.BodyService;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * BodyController Test MOCKITO
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 01.07.2022
 */
class BodyControllerTest {

    @Test
    void fBodySelect() {
        Body body = Body.of("1");
        body.setId(1);
        Body body1 = Body.of("2");
        body.setId(2);
        List<Body> bodies = List.of(body, body1);
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        BodyService bodyService = mock(BodyService.class);
        when(bodyService.findAllBody()).thenReturn(bodies);
        BodyController bodyController = new BodyController(bodyService);
        String page = bodyController.fBodySelect(model, session);
        verify(model).addAttribute("user", bodyController.getUserSession(session));
        verify(model).addAttribute("bodies", bodyService.findAllBody());
        String expectPage = "car/fBody";
        assertEquals(expectPage, page);
    }
}