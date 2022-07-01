package ru.job4j.cars.service.servcatalog;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.catologmodel.Model;
import ru.job4j.cars.repository.repcatalog.ModelRepository;

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
public class ModelService {
    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public Model findById(int modelId) {
        return modelRepository.findById(modelId);
    }

    public List<Model> findAllModelByMarkId(int markId) {
        return modelRepository.findByMark(markId);
    }
}
