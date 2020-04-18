package ru.job4j.tdd;

import org.junit.Test;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class GeneratorTest {
    @Test
    public void mainTestWhenAllOK() {
        Generator generator =  new SimpleGenerator();
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Homer Simpson");
        args.put("subject", "greatest man");
        assertThat(generator.produce("We are sure that ${name} is the ${subject}.", args),
                is("We are sure that Homer Simpson is the greatest man."));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenToMuchArguments() {
        Generator generator =  new SimpleGenerator();
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Homer Simpson");
        args.put("subject", "greatest man");
        args.put("isnot", "fat pig");
        String rsl = null;
        generator.produce("We are sure that ${name} is the ${subject}.", args);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNotEnoughArguments() {
        Generator generator = new SimpleGenerator();
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Homer Simpson");
        String rsl = null;
        generator.produce("We are sure that ${name} is the ${subject}.", args);
    }
}
