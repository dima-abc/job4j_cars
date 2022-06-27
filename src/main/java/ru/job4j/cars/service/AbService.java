package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Ab;
import ru.job4j.cars.model.Car;
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


    public Optional<Ab> findByIdAb(int idAb) {
        return Optional.ofNullable(abRepository.findById(idAb));
    }

    public List<Ab> getWithPhotoAb() {
        return abRepository.getWithPhoto();
    }

    public List<Ab> findAllAb() {
        return abRepository.findAll();
    }
}
