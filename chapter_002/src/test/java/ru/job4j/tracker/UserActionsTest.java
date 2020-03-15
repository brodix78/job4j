package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserActionsTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream newout = new PrintStream(out);

        @Override
        public void accept(String s) {
            newout.println(s);
        }

        @Override
        public String toString() {
            return new String(out.toByteArray());
        }
    };

    @Test
    public void createItemTest0() {
        ITracker tracker = new Tracker();
        String[] answers = {"0", "Save me", "0", "System fault", "System not responding"};
        StubInput input = new StubInput(answers);
        UserAction ua = new CreateItem();
        for (int i = 0; i < answers.length; i++) {
            ua.execute(input, this.output, tracker);
        }
        List<Item> items = tracker.findAll();
        for (int i = 0; i < answers.length; i++) {
            assertThat(items.get(i).getName(), is(answers[i]));
        }
    }

    @Test
    public void editItemTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("First");
        tracker.add(item);
        String[] answers = {item.getId(), "Second"};
        UserAction ua = new EditItem();
        ua.execute(new StubInput(answers), this.output, tracker);
        assertThat(tracker.findAll().get(0).getName(),
                is("Second"));
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
        ua.execute(new StubInput(answers), this.output, tracker);
        assertThat(tracker.findAll().get(0).getName(), is("Two"));
    }

    @Test
    public void findByIdTestTrue() {
        Tracker tracker = new Tracker();
        Item item = new Item("One");
        tracker.add(item);
        String[] answers = {tracker.findAll().get(0).getId()};
        new FindById().execute(new StubInput(answers), this.output, tracker);
        assertThat(this.out.toString(),
                is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("One id:" + answers[0])
                        .toString()
                )
        );
    }

    @Test
    public void findByIdTestFalse() {
        Tracker tracker = new Tracker();
        Item item = new Item("One");
        tracker.add(item);
        String[] answers = {tracker.findAll().get(0).getId() + "a"};
        new FindById().execute(new StubInput(answers), this.output, tracker);
        assertThat(this.output.toString(),
                is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("No item")
                        .toString()
                )
        );
    }

    @Test
    public void findByNameTrue() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("One");
        Item item2 = new Item("Two");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item2);
        tracker.add(item1);
        String[] answers = {"One"};
        new FindByName().execute(new StubInput(answers), this.output, tracker);
        assertThat(this.output.toString(),
                is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("One id:" + tracker.findAll().get(0).getId())
                        .add("One id:" + tracker.findAll().get(3).getId())
                        .toString()
                )
        );

    }

    @Test
    public void findByNameFalse() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("One");
        Item item2 = new Item("Two");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item2);
        tracker.add(item1);
        String[] answers = {"Three"};
        new FindByName().execute(new StubInput(answers), this.output, tracker);
        assertThat(this.output.toString(),
                is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("No item")
                        .toString()
                )
        );
    }

    @Test
    public void listIItemsTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("One");
        Item item2 = new Item("Two");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item2);
        tracker.add(item1);
        String[] answers = {""};
        new ListItems().execute(new StubInput(answers), this.output, tracker);
        assertThat(this.output.toString(),
                is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("#0. One id:" + tracker.findAll().get(0).getId())
                        .add("#1. Two id:" + tracker.findAll().get(1).getId())
                        .add("#2. Two id:" + tracker.findAll().get(2).getId())
                        .add("#3. One id:" + tracker.findAll().get(3).getId())
                        .toString()
                )
        );
    }
}
