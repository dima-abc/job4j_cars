package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * Реализовать площадку продаж машин. [#4747]
 * User модель данных описывает пользователя-создателя обьявления.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.05.2022
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "user_email", nullable = false, unique = true)
    private String email;
    @Column(name = "user_password", nullable = false)
    private String password;

    public static User of(String email, String password) {
        User user = new User();
        user.email = email;
        user.password = password;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", email='" + email + '\'' + '}';
    }
}
