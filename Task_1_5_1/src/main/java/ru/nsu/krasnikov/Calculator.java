package ru.nsu.krasnikov;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * calculates expressions in prefix form.
 */
public class Calculator {
    private final ArrayDeque<Double> valueStorage;
    private static final FunctionFactory functionsModifier = new FunctionFactory();

    /**
     * create calculator and add functions, used in calculator.
     */
    public Calculator() {
        this.valueStorage = new ArrayDeque<>();
    }

    /**
     * calculates expression in prefix form.
     *
     * @param expression expression.
     * @return result of an expression.
     */
    public Double calculate(String expression) {
        if (expression == null) {
            throw new NullPointerException();
        }
        valueStorage.clear();
        List<String> elements = Arrays.asList(expression.toLowerCase().split(" "));
        Collections.reverse(elements);
        FunctionProperty curFunction;

        for (String elem : elements) {
            if (elem.equals("")) {
                continue;
            }
            if ((curFunction = FunctionFactory.functions.get(elem)) == null
            ) {
                valueStorage.push(Double.parseDouble(elem));
            } else {
                if (valueStorage.isEmpty()) {
                    throw new IllegalArgumentException();
                }
                Double value = valueStorage.pop();
                if (curFunction.getArgsNum() == 1) {
                    valueStorage.push(curFunction.getFunction().apply(value));
                } else {
                    valueStorage.push(curFunction.getBiFunction().apply(value, valueStorage.pop()));
                }
            }
        }

        if (valueStorage.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            Double answer = valueStorage.remove();
            if (valueStorage.isEmpty()) {
                return answer;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * add new unary function.
     *
     * @param signature new signature of a function.
     *                  If hashmap contained that key earlier, it will be overwritten.
     * @param func      unary function.
     */
    public void addFunc(String signature, UnaryOperator<Double> func) {
        functionsModifier.addFunc(signature, func);
    }

    /**
     * add new binary functions.
     *
     * @param signature new signature of a function.
     *                  If hashmap contained that key earlier, it will be overwritten.
     * @param biFunc    binary function.
     */
    public void addFunc(String signature, BinaryOperator<Double> biFunc) {
        functionsModifier.addFunc(signature, biFunc);
    }
}
