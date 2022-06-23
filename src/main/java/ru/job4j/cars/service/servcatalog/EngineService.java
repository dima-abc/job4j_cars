package ru.job4j.cars.service.servcatalog;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.catologmodel.Engine;
import ru.job4j.cars.repository.repcatalog.EngineRepository;

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
public class EngineService {
    private final EngineRepository engineRepository;

    public EngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    public Engine findByIdEngine(int engineId) {
        return engineRepository.findById(engineId);
    }

    public List<Engine> findAllEngine() {
        return engineRepository.findAll();
    }
}
