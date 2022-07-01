package ru.job4j.cars.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * Реализовать площадку продаж машин. [#4747]
 * Ab модель данных описывает обьявление на площадке
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.05.2022
 */
@Entity
@Table(name = "abs")
public class Ab implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ab_id")
    private int id;
    @Column(name = "ab_name")
    private String name;
    @Column(name = "ab_created")
    private LocalDateTime created = LocalDateTime.now().withNano(0);
    @Column(name = "ab_done")
    private LocalDateTime done;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", foreignKey = @ForeignKey(name = "CAR_ID_FK"))
    private Car car;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private User user;
    @Transient
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");

    public static Ab of(Car car, User user) {
        Ab ab = new Ab();
        ab.name = ab.createName(car);
        ab.car = car;
        ab.user = user;
        return ab;
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

    private String createName(Car car) {
        return new StringBuilder()
                .append(car.getModel().getMark().getName())
                .append("(")
                .append(car.getModel().getName())
                .append("), ")
                .append(car.getYear().getRelease()).append(" год.").toString();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getDone() {
        return done;
    }

    public void setDone(LocalDateTime done) {
        this.done = done;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedFormat() {
        return formatter.format(this.created);
    }

    public String getDoneFormat() {
        return formatter.format(this.done);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ab ab = (Ab) o;
        return id == ab.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ab{id=" + id + '}';
    }
//
//    @Override
//    public String toString() {
//        return "Ab{id=" + id + ", name='" + name + '\'' + ", created=" + created
//                + ", done=" + done + ", car=" + car + ", user=" + user + '}';
//    }
}
