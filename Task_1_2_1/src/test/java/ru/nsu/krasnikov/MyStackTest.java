package ru.nsu.krasnikov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyStackTest {

    @Test
    public void fstTest() {
        MyStack<Integer> f = new MyStack<>();
        f.push(1);
        Assertions.assertEquals(f.count(), 1);
        f.push(2);
        Assertions.assertEquals(f.count(), 2);
        f.pushStack(new Integer[]{1,2, 3, 4,5,6,7,8,9,10});
        Assertions.assertEquals(f.count(), 12);
        f.pop();
        Assertions.assertEquals(f.count(), 11);
        f.pop();
        Assertions.assertEquals(f.count(), 10);
        f.popStack(4);
        Assertions.assertEquals(f.count(), 6);
    }

}
