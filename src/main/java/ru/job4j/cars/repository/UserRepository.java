package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;
import ru.job4j.cars.model.catologmodel.Mark;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
public class UserRepository implements IUser<User> {
    private final SessionFactory sf;

    public UserRepository(SessionFactory sf) {
        this.sf = sf;
    }


    @Override
    public boolean create(final User user) {
        AtomicBoolean rsl = new AtomicBoolean(false);
        try {
            tx(session -> session.save(user), sf);
            rsl.set(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl.get();
    }

    @Override
    public User findById(int id) {
        return tx(session -> session.get(User.class, id), sf);
    }

    @Override
    public boolean update(final User user) {
        AtomicBoolean rsl = new AtomicBoolean(false);
        try {
            rsl.set(
                    tx(session -> session
                            .createQuery("update User set name=:name, email=:email, password=:password"
                                    + " where id=:id")
                            .setParameter("name", user.getName())
                            .setParameter("email", user.getEmail())
                            .setParameter("password", user.getPassword())
                            .setParameter("id", user.getId())
                            .executeUpdate() > 0, sf)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl.get();
    }

    @Override
    public User findByEmailPassword(User user) {
        return (User) tx(session -> session
                .createQuery("from User where email=:email and password=:password")
                .setParameter("email", user.getEmail())
                .setParameter("password", user.getPassword())
                .uniqueResult(), sf);
    }
}
