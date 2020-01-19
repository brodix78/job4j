package school;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {
    public List<Student> collect(List<Student> list, Predicate<Student> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
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
}