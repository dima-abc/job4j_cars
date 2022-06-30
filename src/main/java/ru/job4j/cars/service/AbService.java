package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Ab;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.catologmodel.Body;
import ru.job4j.cars.model.catologmodel.Category;
import ru.job4j.cars.model.catologmodel.Mark;
import ru.job4j.cars.repository.AbRepository;

import java.util.List;
import java.util.Optional;

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
    private final AbRepository abRepository;

    public AbService(AbRepository store) {
        this.abRepository = store;
    }

    public boolean create(Ab ab) {
        return abRepository.created(ab);
    }

    public boolean update(int idAb, Ab ab) {
        return abRepository.update(idAb, ab);
    }

    public Optional<Ab> findByIdAb(int idAb) {
        return Optional.ofNullable(abRepository.findById(idAb));
    }

    public List<Ab> getWithActive() {
        return abRepository.getWithActive();
    }

    public List<Ab> getWithLatDay() {
        return abRepository.getLastDay();
    }

    public List<Ab> findAllAb() {
        return abRepository.findAll();
    }

    public List<Ab> getWithMark(final Mark mark) {
        return abRepository.getWithMark(mark);
    }

    public List<Ab> getWithCategory(final Category category) {
        return abRepository.getWithCategory(category);
    }

    public List<Ab> getWithBody(final Body body) {
        return abRepository.getWithBody(body);
    }
}
