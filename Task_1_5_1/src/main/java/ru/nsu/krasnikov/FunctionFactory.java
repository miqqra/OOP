package ru.nsu.krasnikov;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * functions factory, have ability to add new functions.
 */
public class FunctionFactory {
    public static Map<String, FunctionProperty> functions = new HashMap<>();

    /**
     * add default functions to calculator.
     */
    public FunctionFactory() {
        functions.put("sqrt", new FunctionProperty("sqrt", Math::sqrt));
        functions.put("sin", new FunctionProperty("sin", Math::sin));
        functions.put("cos", new FunctionProperty("cos", Math::cos));
        functions.put("+", new FunctionProperty("+", Double::sum));
        functions.put("-", new FunctionProperty("-", ((x, y) -> x - y)));
        functions.put("/", new FunctionProperty("/", ((x, y) -> x / y)));
        functions.put("*", new FunctionProperty("*", ((x, y) -> x * y)));
        functions.put("log", new FunctionProperty("log", Math::log));
        functions.put("pow", new FunctionProperty("pow", Math::pow));
    }

    /**
     * add new unary function.
     *
     * @param signature new signature of a function.
     *                  If hashmap contained that key earlier, it will be overwritten.
     * @param func      unary function.
     */
    public void addFunc(String signature, UnaryOperator<Double> func) {
        functions.put(signature, new FunctionProperty(signature, func));
    }

    /**
     * add new binary functions.
     *
     * @param signature new signature of a function.
     *                  If hashmap contained that key earlier, it will be overwritten.
     * @param biFunc    binary function.
     */
    public void addFunc(String signature, BinaryOperator<Double> biFunc) {
        functions.put(signature, new FunctionProperty(signature, biFunc));
    }
}
