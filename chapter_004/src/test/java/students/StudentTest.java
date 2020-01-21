package students;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void levelOfTest() {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("Mark", 80));
        students.add(null);
        students.add(new Student("Alex", 60));
        students.add(null);
        students.add(new Student("Mike", 55));
        students.add(null);
        List<Student> exp = List.of(new Student("Mark", 80),
                new Student("Alex", 60)
        );
        assertThat(students.get(0).levelOf(students, 55),
                is(exp));
    }
}
