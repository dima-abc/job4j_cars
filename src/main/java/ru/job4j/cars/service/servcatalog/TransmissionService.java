package ru.job4j.cars.service.servcatalog;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.catologmodel.Transmission;
import ru.job4j.cars.repository.repcatalog.ICatalog;
import ru.job4j.cars.repository.repcatalog.TransmissionRepository;

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
public class TransmissionService {
    private final ICatalog<Transmission> transmissionRepository;

    public TransmissionService(ICatalog<Transmission> transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    public Transmission findByIdTransmission(int idTransmission) {
        return transmissionRepository.findById(idTransmission);
    }

    public List<Transmission> findAllTransmission() {
        return transmissionRepository.findAll();
    }
}
