package ru.job4j.cars.model.cmodel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * Category. Описывает модель данных категории автомобилей.
 * Легковой, Грузовой, Мотоцикл.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 06.06.2022
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private int id;
    @Column(name = "category", unique = true, nullable = false)
    private String name;

    public static Category of(String name) {
        Category category = new Category();
        category.name = name;
        return category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String category) {
        this.name = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Category{id=" + id + ", category='" + name + '\''
                + ", models=" + '}';
    }
}
