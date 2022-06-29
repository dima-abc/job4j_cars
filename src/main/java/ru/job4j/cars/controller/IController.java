package ru.job4j.cars.controller;

import ru.job4j.cars.model.User;

import javax.servlet.http.HttpSession;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.06.2022
 */
public interface IController {
    /**
     * Метод возврощает текущего пользовотеля из HttpSession.
     *
     * @param session HttpSession
     * @return User.
     */
    default User getUserSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = User.of("Гость", "", "");
            user.setId(-1);
        }
        return user;
    }
}
