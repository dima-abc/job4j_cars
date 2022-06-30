package ru.job4j.cars.service.servcatalog;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.catologmodel.Mark;
import ru.job4j.cars.repository.repcatalog.MarkRepository;

import java.util.List;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.06.2022
 */
@Service
public class MarkService {
    private final MarkRepository markRepository;

    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public Optional<Mark> findByIdMark(int idMark) {
        return Optional.ofNullable(markRepository.findById(idMark));
    }

    public List<Mark> findAllMark() {
        return markRepository.findAll();
    }
}
