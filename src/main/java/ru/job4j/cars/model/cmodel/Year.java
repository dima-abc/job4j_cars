package ru.job4j.cars.model.cmodel;

import javax.persistence.*;
import java.io.Serializable;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year_id")
    private int id;
    @Column(name = "release", unique = true, nullable = false)
    private int release;

    public static Year of(int name) {
        Year year = new Year();
        year.release = name;
        return year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
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

        return id == year.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Year{id=" + id + ", release=" + release + ", models=" + '}';
    }
}
