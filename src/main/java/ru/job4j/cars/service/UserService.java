package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.IUser;
import ru.job4j.cars.repository.UserRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.06.2022
 */
@Service
public class UserService {
    private final IUser<User> userRepository;

    public UserService(IUser<User> userRepository) {
        this.userRepository = userRepository;
    }

    public boolean create(final User user) {
        return userRepository.create(user);
    }

    public boolean update(User user) {
        return userRepository.update(user);
    }

    public Optional<User> findUserById(int userId) {
        return Optional.ofNullable(userRepository.findById(userId));
    }

    public Optional<User> findUserByEmailPassword(User user) {
        return Optional.ofNullable(userRepository.findByEmailPassword(user));
    }
}
