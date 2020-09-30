package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class CarsTest implements AutoCloseable{

    final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final Session session = sf.openSession();
    private final Transaction tx = session.beginTransaction();


    @Override
    public void close() {
        session.getTransaction().rollback();
        session.close();
        StandardServiceRegistryBuilder.destroy(registry);
    }


    @Test
    public void oneCarWithHistorySave() {
        Engine engine = Engine.of("Some");
        Car car = Car.of(engine);
        Driver first = Driver.of("First");
        Driver second = Driver.of("Second");
        assert (Integer) session.save(first) > 0;
        assert (Integer) session.save(second) > 0;
        car.addDriver(first);
        car.addDriver(second);
        assert (Integer) session.save(engine) > 0;
        assert (Integer) session.save(car) > 0;
        session.getTransaction().rollback();
    }

    @Test
    public void oneCarWithHistoryAddOneMoreDriver() {
        Engine engine = Engine.of("Some");
        Car car = Car.of(engine);
        Driver first = Driver.of("First");
        Driver second = Driver.of("Second");
        Driver third = Driver.of("Third");
        session.save(first);
        session.save(second);
        session.save(third);
        car.addDriver(first);
        car.addDriver(second);
        session.save(engine);
        session.save(car);
        car.addDriver(third);
        session.update(car);
        Car rsl = session.get(Car.class, car.getId());
        assertThat(rsl.getEngine(), is(engine));
        assertThat(rsl.getDrivers().size(), is(3));
        session.getTransaction().rollback();
    }

    @Test
    public void addCarAddDeleteItThenReturnNull() {
        Engine engine = Engine.of("Some");
        Car car = Car.of(engine);
        Driver first = Driver.of("First");
        session.save(first);
        car.addDriver(first);
        session.save(engine);
        session.save(car);
        Car rsl = session.get(Car.class, car.getId());
        int id = rsl.getId();
        session.delete(car);
        assertNull(session.get(Car.class, id));
        session.getTransaction().rollback();
    }
}