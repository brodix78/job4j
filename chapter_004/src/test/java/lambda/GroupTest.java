package lambda;
import org.junit.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class GroupTest {
    @Test
    public void groupWhenAllHaveSameHobby() {
        List<Student> students = List.of(
                new Student("Ivan", Set.of("Fucking", "Drinking", "Fighting")),
                new Student("Vovan", Set.of("Fucking", "Drinking", "Fighting")),
                new Student("Lena", Set.of("Fucking", "Drinking", "Fighting"))
        );
        Map<String, Set<String>> rsl = Group.sections(students);
        Set<String> keysexp = Set.of("Fucking", "Drinking", "Fighting");
        Set<String> studexp = Set.of("Ivan", "Lena", "Vovan");
        assertThat(rsl.keySet().containsAll(keysexp), is(true));
        assertThat(rsl.size(), is(3));
        for (String key : rsl.keySet()) {
            assertThat(rsl.get(key).containsAll(studexp), is(true));
            assertThat(rsl.get(key).size(), is(3));
        }
    }
    @Test
    public void groupWhenAllHaveDifferentHobby() {
        List<Student> students = List.of(
                new Student("Ivan", Set.of("Fucking", "Fighting")),
                new Student("Vovan", Set.of("Drinking")),
                new Student("Lena", Set.of("Fighting"))
        );
        Map<String, Set<String>> rsl = Group.sections(students);
        Set<String> keysexp = Set.of("Fucking", "Drinking", "Fighting");
        assertThat(rsl.keySet().containsAll(keysexp), is(true));
        assertThat(rsl.size(), is(3));
        assertThat(rsl.get("Fucking").contains("Ivan"), is(true));
        assertThat(rsl.get("Fucking").size(), is(1));
        assertThat(rsl.get("Drinking").contains("Vovan"), is(true));
        assertThat(rsl.get("Drinking").size(), is(1));
        assertThat(rsl.get("Fighting").containsAll(List.of("Lena", "Ivan")), is(true));
        assertThat(rsl.get("Fighting").size(), is(2));
    }
}