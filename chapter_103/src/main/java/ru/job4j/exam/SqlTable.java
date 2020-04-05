package ru.job4j.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class SqlTable implements AutoCloseable {

    protected Properties initData;
    protected Connection connection;
    protected final Logger Log = LoggerFactory.getLogger(SqlTable.class);
    protected String tableInit;
    protected String tableName;


    public boolean init() {
        try {
            this.connection = DriverManager.getConnection(
                    initData.getProperty("url"),
                    initData.getProperty("username"),
                    initData.getProperty("password")
            );
            checkTableExist();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return this.connection != null;
    }

    protected void checkTableExist() {
        try (ResultSet rs = connection.getMetaData().getTables(null, null,
                tableName, null)) {
            if (!rs.next()) {
                updateBase(statement(this.tableInit, List.of()));
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    protected PreparedStatement statement (String query, List<String> riddles){
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

    protected int updateBase(PreparedStatement statement) {
        int rsl = 0;
        try {
            rsl = statement.executeUpdate();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return rsl;
    }

    protected ArrayList<String[]> receiveFromBase(PreparedStatement statement) {
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
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                Log.error(e.getMessage(), e);
            }
        }
    }
}
