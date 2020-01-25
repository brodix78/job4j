package lambda;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
public class Group {
    public static Map<String, Set<String>> sections(List<Student> students) {
        return students.stream().map(Student::holders).flatMap(Collection::stream).
                collect(Collectors.groupingBy(Holder::getKey,
                        Collectors.mapping(Holder::getValue, Collectors.toSet())
                ));
    }
}