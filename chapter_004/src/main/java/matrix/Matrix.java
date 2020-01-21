package matrix;

import java.util.List;
import java.util.stream.Collectors;

public class Matrix {
    private List<List<Integer>> matrix;

    public Matrix(List<List<Integer>> inputList) {
        this.matrix = inputList;
    }

    public List<Integer> listFlat() {
        return this.matrix.stream().flatMap(List::stream).collect(Collectors.toList());
    }
}
