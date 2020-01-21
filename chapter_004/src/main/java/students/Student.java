package students;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student implements Comparable<Student> {
    private String name;
    private int scope;

    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public int getScope() {
        return scope;
    }

    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream().
                sorted(new SortByScore().reversed()).
                flatMap(Stream::ofNullable).
                takeWhile(s -> s.getScope() > bound).
                collect(Collectors.toList());
    }

    @Override
    public int compareTo(@NotNull Student obj) {

        return Integer.compare(this.getScope(), obj.getScope());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.scope);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return this.getName().equals(student.getName())
                && this.getScope() == student.getScope();
    }
}