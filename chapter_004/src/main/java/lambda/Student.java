package lambda;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Student {
    private String name;
    private Set<String> units;
    public Student(String name, Set<String> units) {
        this.name = name;
        this.units = units;
    }
    public String getName() {
        return name;
    }
    public Set<String> getUnits() {
        return units;
    }
    public List<Holder> holders() {
        return this.getUnits().stream().
                collect(Collectors.mapping(sec -> new Holder(sec, this.getName()), Collectors.toList()));
    }
}