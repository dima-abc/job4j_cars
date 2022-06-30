package ru.job4j.cars.service.servcatalog;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.catologmodel.Category;
import ru.job4j.cars.repository.repcatalog.CategoryRepository;

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
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findByIdCategory(int categoryId) {
        return Optional.ofNullable(categoryRepository.findById(categoryId));
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
}
