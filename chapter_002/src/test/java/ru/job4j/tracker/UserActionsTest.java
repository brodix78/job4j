package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserActionsTest {
    @Test
    public void createItemTest0() {
        Tracker tracker =new Tracker();
        String[] answers = {"0", "Save me", "0", "System fault", "System not responding"};
        StubInput input = new StubInput(answers);
        StartUI startUI = new StartUI();
        UserAction ua = new CreateItem();
        for (int i = 0; i < answers.length; i++){
            ua.execute(input, tracker);
        }
        Item[] items = tracker.findAll();
        for (int i = 0; i < answers.length; i++) {
            assertThat(items[i].getName(), is(answers[i]));
        }
    }

    @Test
    public void editItemTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("First");
        tracker.add(item);
        String[] answers = {item.getId(), "Second"};
        UserAction ua = new EditItem();
        ua.execute(new StubInput(answers), tracker);
        assertThat(tracker.findAll()[0].getName(), is ("Second"));
    }

    @Test
    public void deleteItemTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("One");
        Item item2 = new Item("Two");
        tracker.add(item1);
        tracker.add(item2);
        String[] answers = {item1.getId()};
        UserAction ua = new DeleteItem();
        ua.execute(new StubInput(answers),tracker);
        assertThat(tracker.findAll()[0].getName(), is ("Two"));
    }
}
