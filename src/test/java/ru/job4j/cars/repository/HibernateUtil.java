package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 28.05.2022
 */
public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
    }
}
