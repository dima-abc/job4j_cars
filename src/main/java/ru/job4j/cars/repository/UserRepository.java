package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.06.2022
 */
@Repository
public class UserRepository {
    private final SessionFactory sf;

    public UserRepository(SessionFactory sf) {
        this.sf = sf;
    }
}
