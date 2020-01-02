package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNull;
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

   @Test
   public void replaceTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("First");
        tracker.add(item);
        String[] answers = {item.getId(), "Second"};
        StartUI.replace(new StubInput(answers), tracker);
        Item replaced = tracker.findById(item.getId());
        assertThat(replaced.getName(), is ("Second"));
   }

   @Test
    public void deleteTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item ("One");
        Item item2 = new Item ("Two");
        tracker.add(item1);
        tracker.add(item2);
        String[] answers = {item1.getId()};
        StartUI.delete(new StubInput(answers),tracker);
        assertNull(tracker.findById(answers[0]));
        assertThat(tracker.findAll()[0], is(item2));
    }
}