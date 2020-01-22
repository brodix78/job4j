package tourist;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfilesTest {

    @Test
    public void whenNotContainEquals() {
        List<Profile> profiles = List.of(
                new Profile(new Address("Ozy", "Crazy", 1, 1)),
                new Profile(new Address("Gotham", "Amazing", 1, 1)),
                new Profile(new Address("Mordor", "Dirty", 1, 1)),
                new Profile(new Address("Gotham", "Amazing", 1, 1))
        );
        assertThat(new Profiles().addresses(profiles).toString(), is(
                "[Gotham Amazing 1 1, Mordor Dirty 1 1, Ozy Crazy 1 1]"
        ));
    }

    @Test
    public void whenContainEquals() {
        List<Profile> profiles = List.of(
                new Profile(new Address("Ozy", "Crazy", 1, 1)),
                new Profile(new Address("Gotham", "Amazing", 1, 1)),
                new Profile(new Address("Mordor", "Dirty", 1, 1)),
                new Profile(new Address("Gotham", "Amazing", 2, 1))
        );
        assertThat(new Profiles().addresses(profiles).toString(), is(
                "[Gotham Amazing 1 1, Gotham Amazing 2 1, Mordor Dirty 1 1, Ozy Crazy 1 1]"
        ));
    }
}