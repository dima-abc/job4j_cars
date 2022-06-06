package ru.job4j.cars.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * Fuel Модель данных описывает вит топлива двигателя.
 * Bidirectional Engine -> fuel
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 06.06.2022
 */
@Entity
@Table(name = "fuels")
public class Fuel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fuel_id")
    private int id;
    @Column(name = "fuel", unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "fuel", fetch = FetchType.LAZY)
    private List<Engine> engines = new ArrayList<>();

    public static Fuel of(String name) {
        Fuel fuel = new Fuel();
        fuel.name = name;
        return fuel;
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

    public void setName(String fuel) {
        this.name = fuel;
    }

    public List<Engine> getEngines() {
        return engines;
    }

    public void setEngines(List<Engine> engines) {
        this.engines = engines;
    }

    public void addEngine(Engine engine) {
        this.engines.add(engine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fuel fuel = (Fuel) o;
        return id == fuel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Fuel{id=" + id + ", fuel='" + name + '\'' + ", engines=" + engines + '}';
    }
}
