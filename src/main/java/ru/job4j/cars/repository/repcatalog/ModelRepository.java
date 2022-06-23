package ru.job4j.cars.repository.repcatalog;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.catologmodel.Mark;
import ru.job4j.cars.model.catologmodel.Model;

import java.util.List;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * ModelRepository управление справочником моделей.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.06.2022
 */
@Repository
public class ModelRepository implements ICatalog<Model> {
    private final SessionFactory sf;

    public ModelRepository(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Найти модель по id.
     *
     * @param id int
     * @return Model
     */
    @Override
    public Model findById(int id) {
        return tx(session -> session.createQuery(
                        "select mo from Model mo "
                                + "join fetch mo.mark mr "
                                + "where mo.id = :mId", Model.class)
                .setParameter("mId", id)
                .uniqueResult(), sf);
    }

    /**
     * Поиск всех моделей одной марки.
     *
     * @param idMark int
     * @return List
     */
    public List<Model> findAllMark(int idMark) {
        Mark mark = new Mark();
        mark.setId(idMark);
        return tx(session -> session
                .createQuery("from Model where mark=:mark")
                .setParameter("mark", mark)
                .list(), sf);
    }

    /**
     * Весь справочник Model.
     *
     * @return List
     */
    @Override
    public List<Model> findAll() {
        return tx(session -> session
                        .createQuery("from Model order by id")
                        .list(),
                sf);
    }
}
