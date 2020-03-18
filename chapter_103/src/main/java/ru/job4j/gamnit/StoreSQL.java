package ru.job4j.gamnit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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

    public void generate(int size) {
        try {
            connect.setAutoCommit(false);
            PreparedStatement ps = connect.prepareStatement("INSERT INTO entry VALUES(?)");
            for (int i = 1; i <= entryNumbers; i++) {
                ps.setInt(1, i);
                ps.executeUpdate();
            }
            connect.commit();
            connect.rollback();
            connect.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Entry> load() {
        config.init();
        ArrayList<Entry> rsl = new ArrayList<>();
        try {
            this.connect = DriverManager.getConnection(config.get("url"));
            Statement st = connect.createStatement();
            DatabaseMetaData dbm = connect.getMetaData();
            ResultSet rs = dbm.getTables(null, null, "entry", null);
            if (!rs.next()) {
                st.executeUpdate("CREATE TABLE entry (field INTEGER)");
            } else {
                st.executeUpdate("DELETE FROM entry");
            }
            generate(entryNumbers);
            rs = st.executeQuery("SELECT field FROM entry");
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

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}