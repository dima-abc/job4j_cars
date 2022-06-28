package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;
import ru.job4j.cars.model.catologmodel.Mark;

import java.util.List;

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
public class UserRepository implements IRepository<User> {
    private final SessionFactory sf;

    public UserRepository(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public boolean created(User user) {
        return false;
    }

    @Override
    public boolean update(int id, User user) {
        return false;
    }

    @Override
    public User findById(int id) {
        return tx(session -> session.get(User.class, id), sf);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> getLastDay() {
        return null;
    }

    @Override
    public List<User> getWithPhoto() {
        return null;
    }

    @Override
    public List<User> getWithMark(Mark mark) {
        return null;
    }
}
