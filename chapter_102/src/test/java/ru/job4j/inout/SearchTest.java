package ru.job4j.inout;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class SearchTest {

    @Test
    public void myFolderTesting() {
        String path = ".";
        Search search = new Search();
        List<File> rsl = search.files(path, List.of("java"));
        for (File file:rsl)
        System.out.println(file.toString());
        assertThat(rsl.contains(new File("./src/main/java/ru/job4j/inout/Abuse.java")), is(true));
        assertThat(rsl.contains(new File("./src/main/java/ru/job4j/inout/Config.java")), is(true));
        assertThat(rsl.contains(new File("./src/main/java/ru/job4j/inout/app.prop")), is(false));
    }
}