package ru.nsu.krasnikov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * tests for calculator.
 */
public class CalculatorTest {
    private static final double pi = Math.PI;
    private static final double e = Math.E;
    private static final double accuracy = 0.0000001;

    @Test
    public void test1() {
        Calculator calculator = new Calculator();
        Assertions.assertTrue(Math.abs(calculator.calculate("- 1 1")) < accuracy);
        Assertions.assertTrue(Math.abs(calculator.calculate("+ 1 1") - 2d) < accuracy);
        Assertions.assertTrue(Math.abs(calculator.calculate("/ 9 2") - 4.5) < accuracy);
        Assertions.assertTrue(Math.abs(calculator.calculate("* 3 2") - 6d) < accuracy);
        Assertions.assertTrue(Math.abs(calculator.calculate("log 1")) < accuracy);
        Assertions.assertTrue(Math.abs(calculator.calculate("pow 2 5") - 32d) < accuracy);
        Assertions.assertTrue(Math.abs(calculator.calculate("sqrt 16") - 4d) < accuracy);
        Assertions.assertTrue(Math.abs(calculator.calculate("sin 0")) < accuracy);
        Assertions.assertTrue(Math.abs(calculator.calculate("cos 0") - 1d) < accuracy);
    }

    @Test
    public void test2() {
        Assertions.assertTrue(Math.abs((new Calculator()).calculate("sin + - 1 2 1"))
                < accuracy);
    }

    @Test
    public void test3() {
        Assertions.assertTrue(
                Math.abs((new Calculator()).calculate("cos / " + pi + " 3")
                        - 0.5) < accuracy);
    }

    @Test
    public void test4() {
        Assertions.assertTrue(
                Math.abs((new Calculator()).calculate("sin   /  " + (-pi) + "   4")
                        + (Math.sqrt(2d) / 2)) < accuracy);
    }

    @Test
    public void test5() {
        Assertions.assertTrue(
                Math.abs((new Calculator()).calculate("log pow " + e + " 2")
                        - 2) < accuracy);
    }

    @Test
    public void test6() {
        Assertions.assertTrue(
                Math.abs((new Calculator()).calculate("log sqrt " + e)
                        - 0.5d) < accuracy);
    }

    @Test
    public void test7() {
        Assertions.assertTrue(
                Math.abs((new Calculator()).calculate("log pow " + e + " 0.5")
                        - 0.5d) < accuracy);
    }

    @Test
    public void test8() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> (new Calculator()).calculate("log sqrt0.5"));
    }

    @Test
    public void test9() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> (new Calculator()).calculate("+ 1 1 1"));
    }

    @Test
    public void test10() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> (new Calculator()).calculate("1 1"));
    }

    @Test
    public void test11() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> (new Calculator()).calculate("+ 1 1 1 +"));
    }

    @Test
    public void test12() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> (new Calculator()).calculate("+ sin log pow 1 1 1 1"));
    }

    @Test
    public void test13() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> (new Calculator()).calculate("1 1 1 1 1"));
    }

    @Test
    public void test14() {
        Assertions.assertEquals(
                (new Calculator()).calculate("pow 2 cos 0"), 2d);
    }

    @Test
    public void test15() {
        Assertions.assertEquals(
                (new Calculator()).calculate("- * + 1 1 2 * 3 5"), -11d
        );
    }

    @Test
    public void test16() {
        Assertions.assertEquals(
                (new Calculator()).calculate("sin * 30 sin - sqrt 1 1"), 0d
        );
    }

    @Test
    public void test17() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> (new Calculator()).calculate(null));
    }

    @Test
    public void test18() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> (new Calculator()).calculate(""));
    }

    @Test
    public void test19() {
        Calculator calc = new Calculator();
        calc.addFunc("+m", (a, b) -> (a + b) / 2);
        calc.addFunc("-u", a -> -a);
        Assertions.assertTrue(
                Math.abs((new Calculator()).calculate("+m -u -2 4")
                        - 3d) < accuracy);
    }

    @Test
    public void test20() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> (new Calculator()).calculate("+ 0p 1haha"));
    }
}
