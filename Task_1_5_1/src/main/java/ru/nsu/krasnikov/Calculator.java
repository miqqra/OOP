package ru.nsu.krasnikov;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * calculates expressions in prefix form.
 */
public class Calculator {
    private final ArrayDeque<FunctionProperty> functionStorage;
    private final ArrayDeque<Double> valueStorage;
    private final List<FunctionProperty> functions;

    /**
     * create calculator and add functions, used in calculator.
     */
    public Calculator() {
        this.functionStorage = new ArrayDeque<>();
        this.valueStorage = new ArrayDeque<>();
        this.functions = new ArrayList<>(
                List.of(
                        new FunctionProperty("sqrt", 1, Math::sqrt),
                        new FunctionProperty("sin", 1, Math::sin),
                        new FunctionProperty("cos", 1, Math::cos),
                        new FunctionProperty("+", 2, Double::sum),
                        new FunctionProperty("-", 2, ((x, y) -> x - y)),
                        new FunctionProperty("/", 2, ((x, y) -> x / y)),
                        new FunctionProperty("*", 2, ((x, y) -> x * y)),
                        new FunctionProperty("log", 1, Math::log),
                        new FunctionProperty("pow", 2, Math::pow)
                )
        );
    }

    /**
     * calculates expression in prefix form.
     *
     * @param expression expression.
     * @return result of an expression.
     */
    public Double calculate(String expression) {
        functionStorage.clear();
        valueStorage.clear();

        for (String elem : expression.toLowerCase().split(" ")) {
            Optional<FunctionProperty> func;
            if (elem.equals("")) {
                continue;
            }
            if ((func = functions.stream()
                    .filter(x -> x.getSignature().equals(elem))
                    .findAny())
                    .isPresent()
            ) {
                functionStorage.push(func.get());
            } else {
                calcFunc(Double.parseDouble(elem));
            }
        }

        if (!functionStorage.isEmpty()) {
            calcFunc(valueStorage.pop());
        }
        if (valueStorage.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            Double answer = valueStorage.remove();
            if (valueStorage.isEmpty() && functionStorage.isEmpty()) {
                return answer;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    private void calcFunc(Double value) {
        FunctionProperty functionFromStack = functionStorage.peekFirst();
        if (functionFromStack == null) {
            throw new IllegalArgumentException();
        } else if (functionFromStack.getArgsNum() == 1) {
            functionStorage.pop();
            valueStorage.push(
                    functionFromStack.getFunction().apply(value));
        } else if (functionFromStack.getArgsNum() == 2) {
            if (valueStorage.isEmpty()) {
                valueStorage.push(value);
            } else {
                functionStorage.pop();
                valueStorage.push(
                        functionFromStack.getBiFunction().apply(
                                valueStorage.pop(),
                                value));
            }

        }
    }

    private static class FunctionProperty {
        private final int argsNum;
        private final String signature;
        private final UnaryOperator<Double> func;
        private final BinaryOperator<Double> biFunc;

        public FunctionProperty(String signature, int argsNum, UnaryOperator<Double> func) {
            this.argsNum = argsNum;
            this.signature = signature;
            this.func = func;
            this.biFunc = null;
        }

        public FunctionProperty(String signature, int argsNum, BinaryOperator<Double> biFunc) {
            this.argsNum = argsNum;
            this.signature = signature;
            this.biFunc = biFunc;
            this.func = null;
        }

        private int getArgsNum() {
            return this.argsNum;
        }

        private String getSignature() {
            return this.signature;
        }

        private UnaryOperator<Double> getFunction() {
            return this.func;
        }

        private BinaryOperator<Double> getBiFunction() {
            return this.biFunc;
        }
    }
}
