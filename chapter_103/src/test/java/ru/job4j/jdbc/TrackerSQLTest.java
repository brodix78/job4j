package ru.job4j.jdbc;

import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.theInstance;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")


            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String id = tracker.add(new Item("desc")).getId();
            assertThat(tracker.findByName("desc").get(0).getId(), is(id));
        }
    }

    @Test
    public void replaceItem() throws SQLException {
        try (TrackerSQL tracker =  new TrackerSQL(ConnectionRollback.create(this.init()))){
            String id = tracker.add(new Item("desc")).getId();
            tracker.replaceById(id, new Item("star"));
            assertThat(tracker.findByName("star").get(0).getId(), is(id));
        }
    }

    @Test
    public void deleteItem() throws SQLException {
        try (TrackerSQL tracker =  new TrackerSQL(ConnectionRollback.create(this.init()))){
            String id = tracker.add(new Item("desc")).getId();
            tracker.deleteById(id);
            assertThat(tracker.findByName("desc").size(), is(0));
        }
    }

    @Test
    public void findAllItems() throws SQLException {
        try (TrackerSQL tracker =  new TrackerSQL(ConnectionRollback.create(this.init()))){
            tracker.add(new Item("desc")).getId();
            tracker.add(new Item("star")).getId();
            assertThat(tracker.findAll().size(), is(2));
        }
    }

    @Test
    public void findItemByName() throws SQLException {
        try (TrackerSQL tracker =  new TrackerSQL(ConnectionRollback.create(this.init()))){
            tracker.add(new Item("desc")).getId();
            String id = tracker.add(new Item("star")).getId();
            assertThat(tracker.findByName("star").get(0).getId(), is(id));
        }
    }

    @Test
    public void findItemById() throws SQLException {
        try (TrackerSQL tracker =  new TrackerSQL(ConnectionRollback.create(this.init()))){
            tracker.add(new Item("desc")).getId();
            String id = tracker.add(new Item("star")).getId();
            assertThat(tracker.findById(id).getName(), is("star"));
        }
    }
}
