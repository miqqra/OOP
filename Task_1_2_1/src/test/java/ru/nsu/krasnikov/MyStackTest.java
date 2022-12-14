package ru.nsu.krasnikov;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class for testing stack class and its functions.
 */
public class MyStackTest {

    @Test
    public void testInteger() {
        StackInterface<Integer> f = new MyStack<>();

        Assertions.assertThrows(EmptyStackException.class, () -> new MyStack<>().pop());

        Assertions.assertThrows(NullPointerException.class, () -> f.push(null));

        Assertions.assertThrows(NullPointerException.class, () -> f.pushStack(null));

        f.push(1);
        Assertions.assertEquals(f.count(), 1);
        f.push(2);
        Assertions.assertEquals(f.count(), 2);
        f.pushStack(new MyStack<>(new Integer[]{1, 2, 3, 4, 5, 6,
            7, 8, 9, 10}));
        Assertions.assertEquals(f.count(), 12);
        Assertions.assertEquals(f.pop(), 10);
        Assertions.assertEquals(f.count(), 11);
        Assertions.assertEquals(f.pop(), 9);
        Assertions.assertEquals(f.count(), 10);
        Assertions.assertArrayEquals(f.popStack(4).getArrayFromStack(),
            new MyStack<>(new Integer[]{5, 6, 7, 8}).getArrayFromStack());
        Assertions.assertEquals(f.count(), 6);
        f.pushStack(new MyStack<>(new Integer[]{}));
        Assertions.assertEquals(f.count(), 6);
        Assertions.assertThrows(EmptyStackException.class, () -> f.popStack(8));
    }

    @Test
    public void testString() {
        StackInterface<String> f = new MyStack<>();
        f.push("hello");
        Assertions.assertEquals(f.count(), 1);
        f.push("world");
        Assertions.assertEquals(f.count(), 2);
        f.pushStack(new MyStack<>(new String[]{"he", "ll", "o ", "wo",
            "rl", "d ", " d", "lr", "ow", " o"}));
        Assertions.assertEquals(f.count(), 12);
        Assertions.assertSame(f.pop(), " o");
        Assertions.assertEquals(f.count(), 11);
        Assertions.assertSame(f.pop(), "ow");
        Assertions.assertEquals(f.count(), 10);
        Assertions.assertArrayEquals(f.popStack(4).getArrayFromStack(),
            new MyStack<>(new String[]{"rl", "d ", " d", "lr"}).getArrayFromStack());
        Assertions.assertEquals(f.count(), 6);
        f.pushStack(new MyStack<>(new String[]{}));
        Assertions.assertEquals(f.count(), 6);
    }

    @Test
    public void testLong() {
        StackInterface<Long> f = new MyStack<>();
        f.push(1L);
        Assertions.assertEquals(f.count(), 1);
        f.push(2L);
        Assertions.assertEquals(f.count(), 2);
        f.pushStack(new MyStack<>(new Long[]{123232L, 2L, 3L, 4L, 5L,
            6L, 7L, 8L, 9L, 10L}));
        Assertions.assertEquals(f.count(), 12);
        Assertions.assertEquals(f.pop(), 10L);
        Assertions.assertEquals(f.count(), 11);
        Assertions.assertEquals(f.pop(), 9L);
        Assertions.assertEquals(f.count(), 10);
        Assertions.assertArrayEquals(f.popStack(4).getArrayFromStack(),
            new MyStack<>(new Long[]{5L, 6L, 7L, 8L}).getArrayFromStack());
        Assertions.assertEquals(f.count(), 6);
        f.pushStack(new MyStack<>(new Long[]{}));
        Assertions.assertEquals(f.count(), 6);
    }

    @Test
    public void testCharacter() {
        StackInterface<Character> f = new MyStack<>();
        f.push('h');
        Assertions.assertEquals(f.count(), 1);
        f.push('e');
        Assertions.assertEquals(f.count(), 2);
        f.pushStack(new MyStack<>(new Character[]{'h', 'e', 'l', 'l', 'o',
            ' ', 'w', 'o', 'r', 'l', 'd'}));
        Assertions.assertEquals(f.count(), 13);
        Assertions.assertEquals(f.pop(), 'd');
        Assertions.assertEquals(f.count(), 12);
        Assertions.assertEquals(f.pop(), 'l');
        Assertions.assertEquals(f.count(), 11);
        Assertions.assertArrayEquals(f.popStack(4).getArrayFromStack(),
            new MyStack<>(new Character[]{' ', 'w', 'o', 'r'}).getArrayFromStack());
        Assertions.assertEquals(f.count(), 7);
        f.pushStack(new MyStack<>(new Character[]{}));
        Assertions.assertEquals(f.count(), 7);
    }

    @Test
    public void testFloat() {
        StackInterface<Float> f = new MyStack<>();
        f.push(1.34F);
        Assertions.assertEquals(f.count(), 1);
        f.push(2.53233F);
        Assertions.assertEquals(f.count(), 2);
        f.pushStack(new MyStack<>(new Float[]{1.3F, 2.2F, 3.2F, 4.9F, 5.8F,
            6.7F, 7.6F, 8.5F, 9.4F, 10.1F}));
        Assertions.assertEquals(f.count(), 12);
        Assertions.assertEquals(f.pop(), 10.1F);
        Assertions.assertEquals(f.count(), 11);
        Assertions.assertEquals(f.pop(), 9.4F);
        Assertions.assertEquals(f.count(), 10);
        Assertions.assertArrayEquals(f.popStack(4).getArrayFromStack(),
            new MyStack<>(new Float[]{5.8F, 6.7F, 7.6F, 8.5F}).getArrayFromStack());
        Assertions.assertEquals(f.count(), 6);
        f.pushStack(new MyStack<>(new Float[]{}));
        Assertions.assertEquals(f.count(), 6);
    }

    @Test
    public void testBoolean() {
        StackInterface<Boolean> f = new MyStack<>();
        f.push(true);
        Assertions.assertEquals(f.count(), 1);
        f.push(false);
        Assertions.assertEquals(f.count(), 2);
        f.pushStack(new MyStack<>(new Boolean[]{true, false, true, false,
            true, false, true, false, true, false}));
        Assertions.assertEquals(f.count(), 12);
        Assertions.assertFalse(f.pop());
        Assertions.assertEquals(f.count(), 11);
        Assertions.assertTrue(f.pop());
        Assertions.assertEquals(f.count(), 10);
        Assertions.assertArrayEquals(f.popStack(4).getArrayFromStack(),
            new MyStack<>(new Boolean[]{true, false, true, false}).getArrayFromStack());
        Assertions.assertEquals(f.count(), 6);
        f.pushStack(new MyStack<>(new Boolean[]{}));
        Assertions.assertEquals(f.count(), 6);
    }
}
