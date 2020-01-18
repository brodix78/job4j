package functions;

import java.util.function.BiFunction;
import java.util.function.Consumer;
public class Calculator {
    public void multiple(int start, int finish, int value,
                         BiFunction<Integer, Integer, Double> oper,
                         Consumer<Double> media) {
        for (int i = start; i < finish; i++) {
            media.accept(oper.apply(value, i));
        }
    }
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.multiple(0, 10, 2,
                (value, i) -> {
                    double res = value * i;
                    System.out.printf("Multiple %s * %s = ", (double) value, (double) i);
                    return res;
                },
                res -> System.out.println(res)
        );
    }
}