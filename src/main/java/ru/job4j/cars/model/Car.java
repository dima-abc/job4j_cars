package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * Модели и связи. Машины и владельцы [#4744]
 * Car модель данных описиывает автомобиль и его характеристики.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.05.2022
 */
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;
    @Column(name = "car_description")
    private String description;
    @Column(name = "car_photo")
    private Byte[] photo;
    @ManyToOne
    @JoinColumn(name = "model_id", foreignKey = @ForeignKey(name = "MODEL_ID_FK"))
    private Model model;
    @ManyToOne
    @JoinColumn(name = "body_id", foreignKey = @ForeignKey(name = "MODEL_ID_FK"), nullable = false)
    private Body body;
    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"), nullable = false)
    private Engine engine;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "driver_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "car_id", nullable = false, updatable = false)})
    private final Set<Driver> drivers = new CopyOnWriteArraySet<>();

    public static Car of(String description,
                         Byte[] photo, Body body, Engine engine) {
        Car car = new Car();
        car.description = description;
        car.photo = photo;
        car.body = body;
        car.engine = engine;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void addDriver(Driver driver) {
        this.drivers.add(driver);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{id=" + id + ", description='" + description + '\''
                + ", photo=" + Arrays.toString(photo) + ", model=" + model
                + ", body=" + body + ", engine=" + engine + ", drivers=" + drivers + '}';
    }
}
