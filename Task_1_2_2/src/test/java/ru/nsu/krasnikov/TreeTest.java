package ru.nsu.krasnikov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

/**
 * Class for tree stack class and its functions.
 */
public class TreeTest {

    @Test
    public void test1() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1);
        Assertions.assertEquals(tree.getValue(), 1);
        Tree<Integer> node2 = tree.add(2);
        Assertions.assertEquals(node2.getValue(), 2);
        Tree<Integer> node3 = tree.add(3);
        Assertions.assertEquals(node3.getValue(), 3);
        Tree<Integer> node4 = tree.add(4);
        Assertions.assertEquals(node4.getValue(), 4);
        Tree<Integer> node5 = tree.add(5);
        Assertions.assertEquals(node5.getValue(), 5);
        Tree<Integer> node6 = node2.add(6);
        Assertions.assertEquals(node6.getValue(), 6);
        Tree<Integer> node7 = tree.add(node2, 7);
        Assertions.assertEquals(node7.getValue(), 7);
        Tree<Integer> node8 = new Tree<>(8);
        tree.add(node7, node8);
        Assertions.assertEquals(node8.getValue(), 8);
        Tree<Integer> node9 = node7.add(9);
        Assertions.assertEquals(node9.getValue(), 9);

        Assertions.assertEquals(node6, tree.findNode(6));
        Assertions.assertThrows(NoSuchElementException.class, () -> tree.findNode(45));
        Tree<Integer> newNode = new Tree<>(100);
        Tree<Integer> newNode2 = new Tree<>(200);
        Assertions.assertThrows(NoSuchElementException.class, () -> tree.add(newNode, 300));
        Assertions.assertThrows(NoSuchElementException.class, () -> tree.add(newNode, newNode2));

        Assertions.assertEquals(node7, tree.getParent(node8));
        Assertions.assertEquals(node2, tree.getParent(6));
        Assertions.assertNull(tree.getParent(tree));

        Assertions.assertEquals(tree.getValue(), 1);
        Assertions.assertEquals(node8.getValue(), 8);
        node8.setValue(88);
        Assertions.assertEquals(node8.getValue(), 88);

        Assertions.assertTrue(tree.isRoot());
        Assertions.assertFalse(node6.isRoot());

        Assertions.assertFalse(tree.remove(70));
        Assertions.assertFalse(tree.remove(new Tree<>(90)));
        Assertions.assertTrue(tree.remove(node7));

        tree.remove(2);

        tree.remove(1);
        Assertions.assertNull(tree.getValue());

        System.out.println(tree.printChildren());
        System.out.println(tree);
    }
}
