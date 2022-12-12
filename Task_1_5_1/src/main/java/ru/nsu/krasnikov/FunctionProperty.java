package ru.nsu.krasnikov;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * function class.
 */
public class FunctionProperty {
    private final int argsNum;
    private final String signature;
    private final UnaryOperator<Double> func;
    private final BinaryOperator<Double> biFunc;

    /**
     * initialize new unary function.
     *
     * @param signature signature of a function.
     * @param func      function.
     */
    public FunctionProperty(String signature, UnaryOperator<Double> func) {
        this.argsNum = 1;
        this.signature = signature;
        this.func = func;
        this.biFunc = null;
    }

    /**
     * initialize new binary function.
     *
     * @param signature signature of a function.
     * @param biFunc    function.
     */
    public FunctionProperty(String signature, BinaryOperator<Double> biFunc) {
        this.argsNum = 2;
        this.signature = signature;
        this.biFunc = biFunc;
        this.func = null;
    }

    /**
     * get amount of arguments of function.
     *
     * @return amount of arguments.
     */
    public int getArgsNum() {
        return this.argsNum;
    }

    /**
     * get function.
     *
     * @return unary operator of function.
     */
    public UnaryOperator<Double> getFunction() {
        return this.func;
    }

    /**
     * get function.
     *
     * @return binary operator of function.
     */
    public BinaryOperator<Double> getBiFunction() {
        return this.biFunc;
    }
}
