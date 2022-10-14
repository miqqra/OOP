package ru.nsu.krasnikov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class for tree stack class and its functions.
 */
public class TreeTest {

    @Test
    public void test() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1);
        Assertions.assertEquals(tree.getValue(), 1);
        Tree<Integer> node2 = tree.add(2);
        Assertions.assertEquals(tree.getModCount(), 2);
        Assertions.assertEquals(node2.getValue(), 2);
        Tree<Integer> node3 = tree.add(tree, 3);
        Assertions.assertEquals(node3.getValue(), 3);
        Tree<Integer> node4 = tree.add(new Tree<>(4));
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

        Assertions.assertEquals(tree.getSize(tree), 4);

        Assertions.assertEquals(node7, tree.add(7));
        Assertions.assertEquals(node6, tree.add(new Tree<>(6)));
        Assertions.assertNull(tree.add(new Tree<>(70), 6));
        Assertions.assertNull(tree.add(new Tree<>(2), 7));
        Assertions.assertNull(tree.add(new Tree<>(70), node6));
        Assertions.assertNull(tree.add(new Tree<>(2), node7));
        Assertions.assertEquals(tree.add(tree.add(node2, 4)), node4);
        Assertions.assertEquals(tree.add(tree.add(node2, node4)), node4);

        Assertions.assertThrows(IllegalStateException.class, () -> tree.iterator(null));

        Iterator<Tree<Integer>> iterDfs = tree.iterator();
        Assertions.assertEquals(iterDfs.next().getValue(), 1);
        Assertions.assertEquals(iterDfs.next().getValue(), 5);
        Assertions.assertEquals(iterDfs.next().getValue(), 4);
        Assertions.assertEquals(iterDfs.next().getValue(), 3);
        Assertions.assertEquals(iterDfs.next().getValue(), 2);
        Assertions.assertEquals(iterDfs.next().getValue(), 7);
        Assertions.assertEquals(iterDfs.next().getValue(), 9);
        Assertions.assertEquals(iterDfs.next().getValue(), 8);
        Assertions.assertEquals(iterDfs.next().getValue(), 6);

        tree.changeMode(Tree.IteratorType.BFS);
        Iterator<Tree<Integer>> iterBfs = tree.iterator(Tree.IteratorType.BFS);
        Assertions.assertEquals(iterBfs.next().getValue(), 1);
        Assertions.assertEquals(iterBfs.next().getValue(), 2);
        Assertions.assertEquals(iterBfs.next().getValue(), 3);
        Assertions.assertEquals(iterBfs.next().getValue(), 4);
        Assertions.assertEquals(iterBfs.next().getValue(), 5);
        Assertions.assertEquals(iterBfs.next().getValue(), 6);
        Assertions.assertEquals(iterBfs.next().getValue(), 7);
        Assertions.assertEquals(iterBfs.next().getValue(), 8);
        Assertions.assertEquals(iterBfs.next().getValue(), 9);

        Assertions.assertEquals(node6, tree.findNode(6));
        Assertions.assertEquals(tree, tree.findNode(1));
        Assertions.assertNull(tree.findNode(900));

        List<Integer> sons = new ArrayList<>();
        Integer[] actualSons = new Integer[]{2, 3, 4, 5};
        for (Tree<Integer> son : tree.getSubtrees()) {
            sons.add(son.getValue());
        }
        Assertions.assertArrayEquals(sons.toArray(), actualSons);

        List<Integer> sons2 = new ArrayList<>();
        Integer[] actualSons2 = new Integer[]{8, 9};
        for (Tree<Integer> son : tree.getSubtrees(node7)) {
            sons2.add(son.getValue());
        }
        Assertions.assertArrayEquals(sons2.toArray(), actualSons2);

        Assertions.assertEquals(node2, node2.getParent(node7));
        Assertions.assertEquals(node7, tree.getParent(node8));
        Assertions.assertEquals(node2, tree.getParent(6));
        Assertions.assertEquals(tree, tree.getParent(5));
        Assertions.assertNull(tree.getParent(tree));
        Assertions.assertNull(tree.getParent(1));

        Assertions.assertEquals(tree.getValue(), 1);
        Assertions.assertEquals(node8.getValue(), 8);
        node8.setValue(88);
        Assertions.assertEquals(node8.getValue(), 88);

        Assertions.assertTrue(tree.isRoot());
        Assertions.assertFalse(node6.isRoot());

        Assertions.assertFalse(tree.remove(70));
        Assertions.assertFalse(tree.remove(new Tree<>(90)));
        Assertions.assertTrue(tree.remove(node7));
        Assertions.assertFalse(tree.remove(new Tree<>(2)));

        tree.remove(2);
        Assertions.assertEquals(tree.getParent(node6), tree);
        Assertions.assertEquals(tree.getParent(node9), tree);

        tree.remove(1);
        Assertions.assertNull(tree.getValue());
        tree.setValue(1);
        Assertions.assertEquals(tree.getValue(), 1);
        tree.remove(tree);
        Assertions.assertTrue(tree.isRoot());
        Assertions.assertNull(tree.getValue());
        tree.setValue(1);

        System.out.println(tree.printChildren());
        System.out.println(tree);
    }
}
