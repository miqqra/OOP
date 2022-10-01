package ru.nsu.krasnikov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class for tree stack class and its functions.
 */
public class TreeTest {

    @Test
    public void test1() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1);
        Tree<Integer> node2 = tree.add(2);
        Tree<Integer> node3 = tree.add(3);
        Tree<Integer> node4 = tree.add(4);
        Tree<Integer> node5 = tree.add(5);
        Tree<Integer> node6 = node2.add(6);

        Tree<Integer> node7 = tree.add(node2, 7);

        Tree<Integer> node8 = new Tree<>(8);
        node7.add(node8);

        Tree<Integer> node9 = node7.add(9);

        System.out.println("Get Height");
        System.out.println("1 - " + tree.getHeight());
        System.out.println("2 - " + tree.getHeight(node2));
        System.out.println("3 - " + tree.getHeight(node3));
        System.out.println("4 - " + node4.getHeight());
        System.out.println("5 - " + node5.getHeight());
        System.out.println("6 - " + tree.getHeight(node6));
        System.out.println("7 - " + tree.getHeight(node7));
        System.out.println("8 - " + tree.getHeight(node8));
        System.out.println("9 - " + node9.getHeight());

        Assertions.assertEquals(node6, tree.findNode(6));
        Assertions.assertEquals(node7, tree.getParent(node8));
        Assertions.assertEquals(node2, tree.getParent(6));
        System.out.println("get values");
        System.out.println("1 - " + tree.getValue());
        System.out.println("2 - " + node2.getValue());

        System.out.println("is root");
        System.out.println("1 - " + tree.isRoot());
        System.out.println("2 - " + node2.isRoot());
        System.out.println("3 - " + node3.isRoot());
        System.out.println("4 - " + node4.isRoot());
        System.out.println("5 - " + node5.isRoot());
        System.out.println("6 - " + node6.isRoot());
        System.out.println("7 - " + node7.isRoot());
        System.out.println("8 - " + node8.isRoot());
        System.out.println("9 - " + node9.isRoot());

        System.out.println("to string");
        System.out.println(tree.printChildren());
        System.out.println(node2.printChildren());
        System.out.println(node3.printChildren());
        System.out.println(node4.printChildren());
        System.out.println(node5.printChildren());
        System.out.println(node6.printChildren());
        System.out.println(node7.printChildren());
        System.out.println(node8.printChildren());
        System.out.println(node9.printChildren());

        System.out.println("the whole tree");
        System.out.println(tree);
    }
}
