package ru.job4j.cars.model.catologmodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * Color. Модель данных описывает цвет транспортных средств.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 06.06.2022
 */
@Entity
@Table(name = "colors")
public class Color implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private int id;
    @Column(name = "color", unique = true, nullable = false)
    private String name;
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    public static Color of(String name, String code) {
        Color color = new Color();
        color.name = name;
        color.code = code;
        return color;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Color color = (Color) o;
        return id == color.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Color{id=" + id + ", name='" + name + '\''
                + ", code='" + code + '\'' + '}';
    }
}
