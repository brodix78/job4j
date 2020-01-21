package matrix;

import org.junit.Test;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixTest {
    @Test
    public void matrixTest() {
        List<List<Integer>> matrix = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6)
        );
        List<Integer> exp = List.of(1, 2, 3, 4, 5, 6);
        assertThat(new Matrix(matrix).listFlat(), is(exp));
    }
}
