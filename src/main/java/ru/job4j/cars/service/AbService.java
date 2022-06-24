package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Ab;
import ru.job4j.cars.repository.Repository;

import java.util.List;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * AbService слой бизнес логики объявлений.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.06.2022
 */
@Service
public class AbService {
    private final Repository store;

    public AbService(Repository store) {
        this.store = store;
    }

    public boolean create(Ab ab) {
        return store.created(ab);
    }

    public List<Ab> getWithPhotoAb() {
        return store.getWithPhoto();
    }

    public List<Ab> findAllAb() {
        return store.findAll();
    }
}
