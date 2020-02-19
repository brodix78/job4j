package ru.job4j.inout;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./src/main/java/ru/job4j/inout/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./src/main/java/ru/job4j/inout/app.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("few"));
    }


}