package ru.job4j.cars.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * Year модель описывает год выпуска автомобиля.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 06.06.2022
 */
@Entity
@Table(name = "years")
public class Year implements Serializable {
    @Id
    @Column(name = "release", unique = true, nullable = false)
    private int release;
    @OneToMany(mappedBy = "year", fetch = FetchType.LAZY)
    private List<Model> models = new ArrayList<>();

    public static Year of(int name) {
        Year year = new Year();
        year.release = name;
        return year;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public List<Model> getModels() {
        return models;
    }

    public void addModel(Model model) {
        this.models.add(model);
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Year year = (Year) o;
        return release == year.release;
    }

    @Override
    public int hashCode() {
        return Objects.hash(release);
    }

    @Override
    public String toString() {
        return "Year{release=" + release + ", models=" + models + '}';
    }
}
