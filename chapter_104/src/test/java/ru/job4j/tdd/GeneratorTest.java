package ru.job4j.tdd;

import org.junit.Test;
import java.util.HashMap;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class GeneratorTest {

    @Test
    public void mainTestWhenAllOK() {
        Generator generator =  new SimpleGenerator();
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Homer Simpson");
        args.put("subject", "greatest man");
        assertThat(generator.produce("We are sure that ${name} is the ${subject}."),
                is("We are sure that Homer Simpson is the greatest man."));
    }

    @Test
    public void whenToMuchArguments() {
        Generator generator =  new SimpleGenerator();
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Homer Simpson");
        args.put("subject", "greatest man");
        args.put("isnot", "fat pig");
        String rsl = null;
        try {
            generator.produce("We are sure that ${name} is the ${subject}.");
        } catch (Exception e) {
            rsl = e.getMessage();
        }
        assertThat(rsl, is("Values not in pattern -\"isnot\""));
    }

    @Test
    public void whenNotEnoughArguments() {
        Generator generator =  new SimpleGenerator();
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Homer Simpson");
        String rsl = null;
        try {
            generator.produce("We are sure that ${name} is the ${subject}.");
        } catch (Exception e) {
            rsl = e.getMessage();
        }
        assertThat(rsl, is("Not enough arguments. Need data for keys -\"subject\""));
    }
}
