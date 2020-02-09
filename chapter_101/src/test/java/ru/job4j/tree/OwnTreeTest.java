package ru.job4j.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OwnTreeTest {

    @Test
public void when6ElFindLastThen6() {
    SimpleTree<Integer> tree = new OwnTree<>(1);
    tree.add(1, 2);
    tree.add(1, 3);
    tree.add(1, 4);
    tree.add(4, 5);
    tree.add(5, 6);
    assertThat(
            tree.findBy(2).isPresent(),
            is(true)
    );
}

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        SimpleTree<Integer> tree = new OwnTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorTest() {
        SimpleTree<Integer> tree = new OwnTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Integer> it = tree.iterator();
        int checkSum = 0;
        while (it.hasNext()) {
            checkSum = checkSum + it.next();
        }
        assertThat(checkSum, is(1 + 2 + 3 + 4 + 5 + 6));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorCheckModificationsCountAndNotAddingExistingElements() {
        SimpleTree<Integer> tree = new OwnTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Integer> it = tree.iterator();
        int checkSum = 0;
        while (it.hasNext()) {
            checkSum = checkSum + it.next();
        }
        tree.add(4, 5);
        assertThat(it.hasNext(), is(false));
        tree.add(4, 7);
        it.hasNext();
    }

    @Test
    public void confirmThatBinary() {
        OwnTree<Integer> tree = new OwnTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(5, 6);
        tree.add(5, 7);
        assertThat(
                tree.isBinary(),
                is(true)
        );
    }

    @Test
    public void confirmThatNotBinary() {
        OwnTree<Integer> tree = new OwnTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(5, 6);
        tree.add(5, 7);
        tree.add(5, 8);
        assertThat(
                tree.isBinary(),
                is(false)
        );
    }
}
