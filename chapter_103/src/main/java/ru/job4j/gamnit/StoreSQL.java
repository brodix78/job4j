package ru.job4j.gamnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StoreSQL implements AutoCloseable {

    private final Config config;
    private Connection connect;
    private int entryNumbers;

    public StoreSQL(Config config, int entryNumbers) {
        this.config = config;
        this.entryNumbers = entryNumbers;
    }

    public List<Entry> load() {
        config.init();
        ArrayList<Entry> rsl = new ArrayList<>();
        try {
            this.connect = DriverManager.getConnection(config.get("url"));
            prepareTable();
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("SELECT field FROM entry");
            while (rs.next()) {
                rsl.add(new Entry(rs.getInt(1)));
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rsl;
    }
    
    private void prepareTable() {
        try {
            DatabaseMetaData dbm = connect.getMetaData();
            Statement st = connect.createStatement();
            ResultSet rs = dbm.getTables(null, null, "entry", null);
            if (!rs.next()) {
                st.executeUpdate("CREATE TABLE entry (field INTEGER)");
            } else {
                st.executeUpdate("DELETE FROM entry");
            }
            generate(entryNumbers);
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void generate(int size) {

        try (Statement st = connect.createStatement()){
            connect.setAutoCommit(false);
            for (int i = 1; i <= entryNumbers; i++) {
                st.addBatch(String.format("INSERT INTO entry VALUES(%s)", i));
            }
            st.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.rollback();
            connect.close();
        }
    }
}