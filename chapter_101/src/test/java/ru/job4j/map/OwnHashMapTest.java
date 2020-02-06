package ru.job4j.map;
import org.junit.Assert;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OwnHashMapTest {

    @Test
    public void addGetAndNullKey() {
        OwnHashMap<String, String> map = new OwnHashMap<>();
        map.insert("One", "A");
        map.insert("Two", "B");
        map.insert("Three", "C");
        map.insert(null, "D");
        assertThat(map.get("One"), is("A"));
        assertThat(map.get("Two"), is("B"));
        assertThat(map.get("Three"), is("C"));
        assertThat(map.get(null), is("D"));
    }

    @Test
    public void addSameKeyReplaceValue() {
        OwnHashMap<String, String> map = new OwnHashMap<>();
        map.insert("One", "A");
        map.insert("One", "B");
        assertThat(map.get("One"), is("B"));
    }

    @Test
    public void deleteItemAfterGetNull() {
        OwnHashMap<String, String> map = new OwnHashMap<>();
        map.insert("One", "A");
        map.insert("Two", "B");
        map.insert("Three", "C");
        map.insert("Four", "D");
        map.delete("Four");
        Assert.assertNull(map.get("Four"));
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator() {
        OwnHashMap<String, String> map = new OwnHashMap<>();
        map.insert("One", "A");
        map.insert("Two", "B");
        map.insert("Three", "C");
        map.insert("Four", "D");
        Iterator<String> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        System.out.println(it.next());
        assertThat(it.hasNext(), is(true));
        System.out.println(it.next());
        assertThat(it.hasNext(), is(true));
        System.out.println(it.next());
        assertThat(it.hasNext(), is(true));
        System.out.println(it.next());
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void bigValueTest() {
        OwnHashMap<Integer, Integer> map = new OwnHashMap<>();
        for (int i = 0; i < 10000; i++) {
            map.insert(i, i);
        }
        for (int i = 0; i < 5000; i++) {
            assertThat(map.get(i), is(i));
            assertThat(map.delete(i), is(true));
            Assert.assertNull(map.get(i));
        }
        Iterator<Integer> it = map.iterator();
        for (int i = 0; i < 5000; i++) {
            assertThat(it.hasNext(), is(true));
            it.next();
        }
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void modCountTest() {
        OwnHashMap<Integer, Integer> map = new OwnHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.insert(i, i);
        }
        Iterator<Integer> it = map.iterator();
        map.insert(20, 1);
        it.hasNext();
    }
}