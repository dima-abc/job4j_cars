package ru.job4j.cars.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 03.07.2022
 */
class UserControllerTest {

    @Test
    void login() {
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        UserService userService = mock(UserService.class);
        UserController userController = new UserController(userService);
        String page = userController.login(model, false, session);
        String expectPage = "user/login";
        verify(model).addAttribute("user", userController.getUserSession(session));
        verify(model).addAttribute("userErr", true);
        assertEquals(expectPage, page);
    }

    @Test
    void loginPost() {
        UserService userService = mock(UserService.class);
        User user = User.of("name", "name@name", "123");
        user.setId(1);
        when(userService.findUserByEmailPassword(user)).thenReturn(Optional.of(user));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        UserController userController = new UserController(userService);
        String page = userController.loginPost(user, request);
        String expectPage = "redirect:/";
        verify(request.getSession()).setAttribute("user", user);
        assertEquals(expectPage, page);
    }

    @Test
    void loginPostErr() {
        UserService service = mock(UserService.class);
        User user = new User();
        when(service.findUserByEmailPassword(user)).thenReturn(Optional.empty());
        UserController controller = new UserController(service);
        HttpServletRequest request = mock(HttpServletRequest.class);
        String page = controller.loginPost(user, request);
        String expectPage = "redirect:/login?userErr=true";
        assertEquals(expectPage, page);
    }

    @Test
    void createUser() {
        UserService service = mock(UserService.class);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        UserController controller = new UserController(service);
        String page = controller.createUser(model, false, session);
        String expectPage = "user/newUser";
        model.addAttribute("user", controller.getUserSession(session));
        model.addAttribute("userErr", true);
        assertEquals(expectPage, page);
    }

    @Test
    void createUserPost() {
        UserService service = mock(UserService.class);
        User user = new User();
        when(service.create(user)).thenReturn(true);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        UserController userController = new UserController(service);
        String page = userController.createUserPost(user, request);
        String expectPage = "redirect:/";
        verify(request.getSession()).setAttribute("user", user);
        assertEquals(expectPage, page);
    }

    @Test
    void createUserPostErr() {
        UserService service = mock(UserService.class);
        User user = new User();
        when(service.create(user)).thenReturn(false);
        HttpServletRequest request = mock(HttpServletRequest.class);
        UserController userController = new UserController(service);
        String page = userController.createUserPost(user, request);
        String expectPage = "redirect:/newUser?userErr=true";
        assertEquals(expectPage, page);
    }

    @Test
    void editUser() {
        UserService service = mock(UserService.class);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        UserController userController = new UserController(service);
        User user = userController.getUserSession(session);
        String page = userController.editUser(model, false, session);
        String expectPage = "user/editUser";
        verify(model).addAttribute("userErr", true);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("editUser", user);
        assertEquals(expectPage, page);
    }

    @Test
    void editUserPost() {
        UserService userService = mock(UserService.class);
        User user = new User();
        when(userService.update(user)).thenReturn(true);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        UserController controller = new UserController(userService);
        String page = controller.editUserPost(user, request);
        String expect = "redirect:/?statusSuccess=true";
        verify(request.getSession()).setAttribute("user", user);
        assertEquals(expect, page);
    }

    @Test
    void editUserPostErr() {
        UserService userService = mock(UserService.class);
        User user = new User();
        when(userService.update(user)).thenReturn(false);
        HttpServletRequest request = mock(HttpServletRequest.class);
        UserController userController = new UserController(userService);
        String page = userController.editUserPost(user, request);
        String expect = "redirect:/editUser/?userErr=true";
        assertEquals(expect, page);
    }

    @Test
    void logout() {
        UserService service = mock(UserService.class);
        HttpSession session = mock(HttpSession.class);
        UserController userController = new UserController(service);
        String page = userController.logout(session);
        String expectPage = "redirect:/";
        assertEquals(expectPage, page);
    }
}