package ru.job4j.cars.persistence;

import org.hibernate.SessionFactory;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.06.2022
 */
public class CarRepository {
    private final SessionFactory sf;

    public CarRepository(SessionFactory sf) {
        this.sf = sf;
    }
}
