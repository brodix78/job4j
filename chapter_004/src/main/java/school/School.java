package school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {
    private List<Student> students = new ArrayList<Student>();

    public List<Student> collect(List<Student> list, Predicate<Student> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public void add(Student student) {
        this.students.add(student);
    }

    public boolean goToA(Student student) {
        return student.getScore() >= 70 && student.getScore() <= 100;
    }

    public boolean goToB(Student student) {
        return student.getScore() >= 50 && student.getScore() < 70;
    }

    public boolean goToC(Student student) {
        return student.getScore() > 0 && student.getScore() < 50;
    }

    public Map<String, Student> studentsMap() {
        return this.students.stream().distinct().collect(Collectors.toMap(
                student -> student.getSurname(),
                student -> student
        ));
    }
}