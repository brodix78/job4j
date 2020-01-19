package school;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SchoolTest {
    @Test
    public void whenAClass() {
        Student alex = new Student(45);
        Student alena = new Student(65);
        Student vova = new Student(75);
        Student masha = new Student(85);
        List<Student> students = Arrays.asList(alex, alena, vova, masha);
        School school = new School();
        assertThat(school.collect(students, student -> school.goToA(student)), is(
                Arrays.asList(vova, masha)
        ));
    }

    @Test
    public void whenBClass() {
        Student alex = new Student(45);
        Student alena = new Student(65);
        Student vova = new Student(75);
        Student masha = new Student(85);
        List<Student> students = Arrays.asList(alex, alena, vova, masha);
        School school = new School();
        assertThat(school.collect(students, student -> school.goToB(student)), is(
                Arrays.asList(alena)
        ));
    }

    @Test
    public void whenCClass() {
        Student alex = new Student(45);
        Student alena = new Student(65);
        Student vova = new Student(75);
        Student masha = new Student(85);
        List<Student> students = Arrays.asList(alex, alena, vova, masha);
        School school = new School();
        assertThat(school.collect(students, student -> school.goToC(student)), is(
                Arrays.asList(alex)
        ));
    }
}
