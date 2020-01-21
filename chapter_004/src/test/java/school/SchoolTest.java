package school;

import org.junit.Test;
import java.util.*;
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
    @Test
    public void studentsMapTest() {
        Student alex = new Student("Antonov", 45);
        Student alena = new Student("Petrov", 65);
        Student vova = new Student("Borisov", 75);
        Student masha = new Student("Antonov", 85);
        School school = new School();
        school.add(alex);
        school.add(alena);
        school.add(vova);
        school.add(masha);
        Map<String, Student> exp = new HashMap<String, Student>();
        exp.put(alex.getSurname(), alex);
        exp.put(alena.getSurname(), alena);
        exp.put(vova.getSurname(), vova);
        Map<String, Student> rsl = school.studentsMap();
        assertThat(rsl, is(exp));
    }
}
