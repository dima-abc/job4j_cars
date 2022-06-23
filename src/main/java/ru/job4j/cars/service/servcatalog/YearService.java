package ru.job4j.cars.service.servcatalog;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.catologmodel.Year;
import ru.job4j.cars.repository.repcatalog.YearRepository;

import java.util.List;

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
public class YearService {
    private final YearRepository yearRepository;

    public YearService(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }

    public Year findById(int yearId) {
        return yearRepository.findById(yearId);
    }

    public List<Year> findAllYear() {
        return yearRepository.findAll();
    }
}
