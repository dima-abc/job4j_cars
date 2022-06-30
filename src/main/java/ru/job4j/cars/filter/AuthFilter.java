package ru.job4j.cars.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * AuthFilter позволяет добавлять объявления только авторизованным пользователям.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 30.06.2022
 */
@Component
public class AuthFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.endsWith("login") || uri.endsWith("newUser")) {
            chain.doFilter(req, res);
            return;
        }
        if ((uri.endsWith("selectMark") || uri.endsWith("createCar")) && req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
        }
        chain.doFilter(req, res);
    }
}
