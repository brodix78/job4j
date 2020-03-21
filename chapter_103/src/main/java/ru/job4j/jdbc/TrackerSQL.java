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

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    public TrackerSQL() {}

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            checkTableExist();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    private void checkTableExist() {
        try (ResultSet rs = connection.getMetaData().getTables(null, null, "items", null)) {
            if (!rs.next()) {
                updateBase(statement("CREATE TABLE items (id VARCHAR(50), item VARCHAR(50))", List.of()));
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    private PreparedStatement statement (String query, List<String> riddles){
        if (this.connection == null) {
            if (!init()) {
                throw new NoSuchElementException("Wrong data in app.properties or no such SQL database");
            }
        }
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            int index = 1;
            for (String riddle:riddles) {
                statement.setString(index++, riddle);
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return statement;
    }

    public int updateBase(PreparedStatement statement) {
        int rsl = 0;
        try {
            rsl = statement.executeUpdate();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return rsl;
    }

    public ArrayList<String[]> receiveFromBase(PreparedStatement statement) {
        ArrayList<String[]> rsl = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery()){
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            while (rs.next()) {
                String[] row = new String[columns];
                for (int i = 0; i < columns; i++) {
                    row[i] = rs.getString(i + 1);
                }
                rsl.add(row);
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
        String id = generateId();
        updateBase(statement("INSERT INTO items VALUES (?, ?)", List.of(id, item.getName())));
        item.setId(id);
        return item;
    }

    @Override
    public boolean replaceById(String id, Item item) {
        return updateBase(statement("UPDATE items SET item = ? WHERE id = ?", List.of(item.getName(), id))) == 1;
    }

    @Override
    public boolean deleteById(String id) {
        return updateBase(statement("DELETE FROM items WHERE id = ?", List.of(id))) == 1;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        ArrayList<String[]> rsl = receiveFromBase(statement("SELECT * FROM items", List.of()));
        for (String[] row:rsl) {
            Item item = new Item(row[1]);
            item.setId(row[0]);
            items.add(item);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> items = new ArrayList<>();
        ArrayList<String[]> rsl = receiveFromBase(statement("SELECT * FROM items WHERE item = ?", List.of(name)));
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
        ArrayList<String[]> rsl = receiveFromBase(statement("SELECT item FROM items WHERE id = ?", List.of(id)));
        if (rsl.size() == 1) {
            item = new Item(rsl.get(0)[0]);
            item.setId(id);
        }
        return item;
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        ITracker tracker = new TrackerSQL();
        Consumer<String> output = System.out::println;
        Input input = new ValidateInput(new ConsoleInput(output), output);
        List<UserAction> userActions = Arrays.asList(new CreateItem(), new ListItems(), new EditItem(), new DeleteItem(), new FindById(), new FindByName(), new Exit());
        new StartUI(input, output, tracker, userActions).init();
    }
}