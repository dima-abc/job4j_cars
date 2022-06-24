package ru.job4j.cars.repository.repcatalog;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.catologmodel.Color;

import java.util.List;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.06.2022
 */
@Service
public class ColorService {
    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public Color findByIdColor(int idColor) {
        return colorRepository.findById(idColor);
    }

    public List<Color> findAllColor() {
        return colorRepository.findAll();
    }
}
