package ru.job4j.tracker;

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
    }


   @Test
   public void replaceTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("First");
        tracker.add(item);
        String[] answers = {item.getId(), "Second"};
   }

   @Test
    public void deleteTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("One");
        Item item2 = new Item("Two");
        tracker.add(item1);
        tracker.add(item2);
        String[] answers = {item1.getId()};
    }
}