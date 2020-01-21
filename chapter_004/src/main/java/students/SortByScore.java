package students;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class SortByScore implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1 == null) {
            return o2 == null ? 0 : -1;
        } else if (o2 == null) {
            return 1;
        } else {
            return o1.compareTo(o2);
        }
    }
}