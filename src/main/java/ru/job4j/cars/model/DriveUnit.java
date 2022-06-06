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
 * DriveUnit Модель данных описывает тип привода автомобиля.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 06.06.2022
 */
@Entity
@Table(name = "drive_units")
public class DriveUnit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drive_unit_id")
    private int id;
    @Column(name = "drive_unit", unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "driveUnit", fetch = FetchType.LAZY)
    private List<Model> models = new ArrayList<>();

    public static DriveUnit of(String name) {
        DriveUnit driveUnit = new DriveUnit();
        driveUnit.name = name;
        return driveUnit;
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

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public void addModel(Model model) {
        this.models.add(model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DriveUnit driveUnit = (DriveUnit) o;
        return id == driveUnit.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DriveUnit{id=" + id + ", name='" + name + '\'' +
                ", models=" + models + '}';
    }
}
