package ru.job4j.cars.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * Модели и связи. Машины и владельцы [#4744]
 * Driver модель данных описывает владельца автомобиля.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.05.2022
 */
@Entity
@Table(name = "drivers")
public class Driver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int id;
    @Column(name = "driver", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public static Driver of(String name, String email) {
        Driver driver = new Driver();
        driver.name = name;
        driver.email = email;
        return driver;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Driver driver = (Driver) o;
        return id == driver.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Driver{id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}
