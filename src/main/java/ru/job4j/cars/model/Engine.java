package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * Модели и связи. Машины и владельцы [#4744]
 * Engine модел данных описывает характеристики двиготеля.
 * Один из компонентов характеристики модели Car.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.05.2022
 */
@Entity
@Table(name = "engines")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "engine_id")
    private int id;
    @Column(name = "engine_name", nullable = false)
    private String name;

    public Engine of(String name) {
        Engine engine = new Engine();
        engine.name = name;
        return engine;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Engine{id=" + id
                + ", name='" + name + '\'' + '}';
    }
}
