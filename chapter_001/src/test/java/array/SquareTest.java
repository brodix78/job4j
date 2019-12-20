package array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SquareTest {

    @Test
    public void whenBound3Then149() {
        int bound = 3;
        Square square = new Square();
        int rst[] = square.calculate(bound);
        int expect[] = {1, 4, 9};
        assertThat(rst, is(expect));
    }
}
