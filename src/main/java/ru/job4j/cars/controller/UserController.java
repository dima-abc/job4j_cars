package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.06.2022
 */
@Controller
public class UserController implements IController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод GET вид для авторизации пользователя.
     *
     * @param model   Model
     * @param userErr Boolean
     * @param session HttpSession
     * @return String
     */
    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(value = "userErr", required = false) Boolean userErr,
                        HttpSession session) {
        model.addAttribute("user", getUserSession(session));
        model.addAttribute("userErr", userErr != null);
        return "user/login";
    }

    /**
     * Метод POST авторизация пользователя.
     *
     * @param user User
     * @param req  HttpServletRequest req
     * @return String
     */
    @PostMapping("/login")
    public String loginPost(@ModelAttribute User user,
                            HttpServletRequest req) {
        Optional<User> userDb = userService.findUserByEmailPassword(user);
        if (userDb.isEmpty()) {
            return "redirect:/login?userErr=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/";
    }

    /**
     * Метод GET вид для регистрации пользователя.
     *
     * @param model   Model
     * @param userErr Boolean
     * @param session HttpSession
     * @return String
     */
    @GetMapping("/newUser")
    public String createUser(Model model,
                             @RequestParam(value = "userErr", required = false) Boolean userErr,
                             HttpSession session) {
        model.addAttribute("user", getUserSession(session));
        model.addAttribute("userErr", userErr != null);
        return "user/newUser";
    }

    /**
     * Метод POST создание и авторизации пользователя.
     *
     * @param user User
     * @param req  HttServletRequest
     * @return String
     */
    @PostMapping("newUser")
    public String createUserPost(@ModelAttribute("user") User user,
                                 HttpServletRequest req) {
        if (!userService.create(user)) {
            return "redirect:/newUser?userErr=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        return "redirect:/";
    }

    /**
     * Метод GET вид для редактирования пользователя.
     *
     * @param model   Model
     * @param userErr Boolean
     * @param session HttpSession
     * @return String
     */
    @GetMapping("/editUser")
    public String editUser(Model model,
                           @RequestParam(value = "userErr", required = false) Boolean userErr,
                           HttpSession session) {
        User user = getUserSession(session);
        model.addAttribute("userErr", userErr != null);
        model.addAttribute("user", user);
        model.addAttribute("editUser", user);
        return "user/editUser";
    }

    /**
     * Метод POST сохроняет изменение пользователя.
     *
     * @param editUser User
     * @param req      HttpServletRequest
     * @return String
     */
    @PostMapping("/editUser")
    public String editUserPost(@ModelAttribute("editUser") User editUser,
                               HttpServletRequest req) {
        if (!userService.update(editUser)) {
            return "redirect:/editUser/?userErr=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", editUser);
        return "redirect:/?statusSuccess=true";
    }

    /**
     * Выход из учетной записи пользователя.
     *
     * @param session Session
     * @return String.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
