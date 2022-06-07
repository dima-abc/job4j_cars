package ru.job4j.cars.model.cmodel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * Модели и связи. Машины и владельцы [#4744]
 * Engine модель данных описывает характеристики двигателя.
 * Один из компонентов характеристики модели Model.
 * Bidirectional Engine -> fuel
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.05.2022
 */
@Entity
@Table(name = "engines")
public class Engine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "engine_id")
    private int id;
    @Column(name = "engine", nullable = false)
    private String name;
    @Column(name = "fuel")
    private String fuel;

    public static Engine of(String name, String fuel) {
        Engine engine = new Engine();
        engine.name = name;
        engine.fuel = fuel;
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

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
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
        return "Engine{id=" + id + ", engine='" + name + '\'' +
                ", fuel=" + fuel + '}';
    }
}
