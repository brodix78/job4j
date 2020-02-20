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
        List<File> rsl = search.files(path, List.of("java", "log"));
     //   assertThat(rsl.contains(new File("./src/main/java/ru/job4j/inout/temp/sys.log")), is(true));
     //   assertThat(rsl.contains(new File("./src/main/java/ru/job4j/inout/Config.java")), is(true));
     //   assertThat(rsl.contains(new File("./src/main/java/ru/job4j/inout/app.prop")), is(false));
    }
}