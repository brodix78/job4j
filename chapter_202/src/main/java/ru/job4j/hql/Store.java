package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.Closeable;
import java.util.List;
import java.util.function.Function;

public class Store implements Closeable{

    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();

    private final SessionFactory sF = new MetadataSources(registry).buildMetadata()
            .buildSessionFactory();

    private <T> T trans(final Function<Session, T> command) {
        final Session session = sF.openSession();
        final Transaction trans = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            trans.commit();
            return rsl;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public <T> T saveOrUpdateObj(T obj) {
        return trans(session -> {
            session.saveOrUpdate(obj);
            return obj;
        });
    }

    public List<Candidate> getCandidates() {
        return trans(session ->
                session.createQuery("from Candidate").list()
        );
    }

    public Candidate getCandidateById(int id) {
        return (Candidate) trans(session ->
                session.createQuery("from Candidate c where c.id = :cId")
                        .setParameter("cId", id).uniqueResult()
        );
    }

    public Candidate getCandidateByName(String name) {
        return (Candidate) trans(session ->
                session.createQuery("from Candidate c where c.name = :cName")
                        .setParameter("cName", name).uniqueResult()
        );
    }

    public int delCandidateById(int id) {
        return trans(session -> session.createQuery("delete from Candidate c where c.id = :cId")
                    .setParameter("cId", id).executeUpdate()
        );
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
