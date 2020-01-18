package functions;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FuncTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        Func function = new Func();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        Func function = new Func();
        List<Double> result = function.diapason(5, 8, x -> Math.pow(x, 2) + x);
        List<Double> expected = Arrays.asList(30D, 42D, 56D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogFunctionThenLogResults() {
        Func function = new Func();
        List<Double> result = function.diapason(5, 8, x -> Math.log(x));
        System.out.println(result);
        List<Double> expected = Arrays.asList(Math.log(5), Math.log(6), Math.log(7));
        assertThat(result, is(expected));
    }
}