package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.*;

import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.function.Consumer;

public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;
    private static final Logger Log = LoggerFactory.getLogger(SQconnect.class);

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
           // Class.forName(config.getProperty("driver-class-name")); -  А для чего нужна эта строка ?
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    public ArrayList<String[]> toSQLBase(String query, boolean needBackData) {
        if (this.connection == null) {
            if (!init()) {
                throw new NoSuchElementException("Wrong data in app.properties or no such SQL database");
            }
        }
        ArrayList<String[]> rsl = new ArrayList<>();
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query)) {
            if (needBackData) {
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                while (rs.next()) {
                    String[] row = new String[columns];
                    for (int i = 0; i < columns; i++) {
                        row[i] = rs.getString(i + 1);
                    }
                    rsl.add(row);
                }
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public void close(){
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                Log.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public Item add(Item item) {
        toSQLBase(String.format("INSERT INTO items (item) VALUES ('%s')", item.getName()), false);
        String id = toSQLBase(String.format("SELECT MAX(id) FROM items WHERE item='%s'", item.getName()), true).get(0)[0];
        item.setId(id);
        return item;
    }

    @Override
    public boolean replaceById(String id, Item item) {
        toSQLBase(String.format("UPDATE items SET item = '%s' WHERE id = '%s'", item.getName(), id), false);
        return true;
    }

    @Override
    public boolean deleteById(String id) {
        toSQLBase(String.format("DELETE FROM items WHERE id = '%s'", id), false);
        return true;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        ArrayList<String[]> rsl = toSQLBase("SELECT * FROM items", true);
        for (String[] row:rsl) {
            Item item = new Item(row[1]);
            item.setId(row[0]);
            items.add(item);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        ArrayList<String[]> rsl = toSQLBase((String.format("SELECT * FROM items WHERE item = '%s';", key)), true);
        for (String[] row : rsl) {
            Item item = new Item(row[1]);
            item.setId(row[0]);
            items.add(item);
        }
            return items;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        ArrayList<String[]> rsl = toSQLBase(String.format("SELECT item FROM items WHERE id = '%s'", id), true);
        if (rsl.size() == 1) {
            item = new Item(rsl.get(0)[0]);
            item.setId(id);
        }
        return item;
    }

    public static void main(String[] args) {
        ITracker tracker = new TrackerSQL();
        Consumer<String> output = System.out::println;
        Input input = new ValidateInput(new ConsoleInput(output), output);
        List<UserAction> userActions = Arrays.asList(new CreateItem(), new ListItems(), new EditItem(), new DeleteItem(), new FindById(), new FindByName(), new Exit());
        new StartUI(input, output, tracker, userActions).init();
    }
}