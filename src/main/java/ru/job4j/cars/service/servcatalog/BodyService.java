package ru.job4j.cars.service.servcatalog;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.catologmodel.Body;
import ru.job4j.cars.repository.repcatalog.BodyRepository;

import java.util.List;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 23.06.2022
 */
@Service
public class BodyService {
    private final BodyRepository bodyRepository;

    public BodyService(BodyRepository bodyRepository) {
        this.bodyRepository = bodyRepository;
    }

    public Optional<Body> findByIdBody(int idBody) {
        return Optional.ofNullable(bodyRepository.findById(idBody));
    }

    public List<Body> findAllBody() {
        return bodyRepository.findAll();
    }
}
