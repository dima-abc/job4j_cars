package ru.job4j.cars.model.cmodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * Реализовать площадку продаж машин. [#4747]
 * Model модель данных описиывает модель автомобиля.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.05.2022
 */
@Entity
@Table(name = "models")
public class Model implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private int id;
    @Column(name = "model", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", foreignKey = @ForeignKey(name = "CATEGORY_ID_FK"), nullable = false)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mark_id", foreignKey = @ForeignKey(name = "MARK_ID_FK"), nullable = false)
    private Mark mark;
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

    public static Model of(String name, Category category, Mark mark, Year year,
                           Body body, Engine engine, Transmission transmission, Color color) {
        Model model = new Model();
        model.name = name;
        model.category = category;
        model.mark = mark;
        model.year = year;
        model.body = body;
        model.engine = engine;
        model.transmission = transmission;
        model.color = color;
        return model;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return id == model.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Model{id=" + id + ", name='" + name + '\'' + ", category=" + category + ", mark=" + mark
                + ", year=" + year + ", body=" + body + ", engine=" + engine + ", transmission=" + transmission
                + ", color=" + color + '}';
    }
}
