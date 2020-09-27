package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BrFill {

    public static void main(String[] args) {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf =
                    new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session =sf.openSession();
            session.beginTransaction();
            Brand brand = Brand.of("BMW");
            for (int i = 1; i < 6; i++) {
                Model model = Model.of(String.format("%s series", i));
                session.save(model);
                brand.addModel(model);
            }
            session.save(brand);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}