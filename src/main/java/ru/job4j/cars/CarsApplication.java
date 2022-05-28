package ru.job4j.cars;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * 3.3.3. HQL, Criteria
 * 2. Фильтры для площадок машин [#4745]
 * CarsApplication содержит метод main, и объект SessionFactory
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 28.05.2022
 */
@SpringBootApplication
public class CarsApplication {

    @Bean(destroyMethod = "close")
    public SessionFactory sessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(CarsApplication.class, args);
        System.out.println("Go to http://loclhost:8080/");
    }
}
