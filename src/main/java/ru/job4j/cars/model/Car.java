package ru.job4j.cars.model;

import ru.job4j.cars.model.cmodel.*;

import javax.persistence.*;
import java.io.Serializable;
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
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;
    @Column(name = "vin", nullable = false, unique = true)
    private int vin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", foreignKey = @ForeignKey(name = "CATEGORY_ID_FK"), nullable = false)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", foreignKey = @ForeignKey(name = "MODEL_ID_FK"))
    private Model model;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_id", foreignKey = @ForeignKey(name = "YEAR_ID_FK"), nullable = false)
    private Year year;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "body_id", foreignKey = @ForeignKey(name = "BODY_ID_FK"), nullable = false)
    private Body body;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"), nullable = false)
    private Engine engine;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trans_id", foreignKey = @ForeignKey(name = "TRANS_ID_FK"), nullable = false)
    private Transmission transmission;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", foreignKey = @ForeignKey(name = "COLOR_ID_KEY"), nullable = false)
    private Color color;
    @Column(name = "description")
    private String description;
    @Column(name = "photo")
    private byte[] photo;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "driver_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "vin", nullable = false, updatable = false)})
    private final Set<Driver> drivers = new CopyOnWriteArraySet<>();

    public static Car of(int vin, Category category, Model model, Year year, Body body,
                  Engine engine, Transmission transmission, Color color,
                  String description, byte[] photo) {
        Car car = new Car();
        car.vin = vin;
        car.category = category;
        car.model = model;
        car.year = year;
        car.body = body;
        car.engine = engine;
        car.transmission = transmission;
        car.color = color;
        car.description = description;
        car.photo = photo;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
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

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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
        return "Car{id=" + id + "vin=" + vin +", category=" + category + ", model=" + model + ", year=" + year
                + ", body=" + body + ", engine=" + engine + ", transmission=" + transmission
                + ", color=" + color + ", description='" + description + '\'' + ", photo="
                + Arrays.toString(photo) + ", drivers=" + drivers + '}';
    }
}
