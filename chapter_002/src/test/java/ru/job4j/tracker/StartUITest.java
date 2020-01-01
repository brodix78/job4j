package ru.job4j.tracker;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartUITest {
    @Test
    public void createItemTest() {
        Tracker tracker = new Tracker();
        String[] answers = {"Save me", "System fault", "System not responding"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI();
        for (int i = 0; i < answers.length; i++) {
            startUI.createItem(input, tracker);
        }
        Item[] rsl = tracker.findAll();
        for (int i = 0; i < answers.length; i++) {
            assertThat(rsl[i].getName(), is (answers[i]));
        }
    }
}