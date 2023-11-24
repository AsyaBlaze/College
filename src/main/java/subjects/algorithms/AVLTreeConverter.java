package subjects.algorithms;

import java.util.ArrayList;
import java.util.List;

public class AVLTreeConverter {
//
    public AVLTree convertBinaryTreeToAVL(BinaryTree binaryTree) {
        List<BinaryTree> sortedList = new ArrayList<>();
        inOrderTraverse(binaryTree, sortedList);

        AVLTree<BinaryTree> avlTree = new AVLTree<>();
        avlTree.setRoot(sortedListToAVL(sortedList, 0, sortedList.size() - 1));

        return avlTree;
    }

    private BinaryTree sortedListToAVL(List<BinaryTree> sortedList, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        BinaryTree newNode = sortedList.get(mid);

        newNode.setLeftChild(sortedListToAVL(sortedList, start, mid - 1));
        newNode.setRightChild(sortedListToAVL(sortedList, mid + 1, end));

        newNode.setHeight(Math.max(height(newNode.getLeftChild()), height(newNode.getRightChild())) + 1);

        return newNode;
    }

    private void inOrderTraverse(BinaryTree node, List<BinaryTree> sortedList) {
        if (node != null) {
            inOrderTraverse(node.getLeftChild(), sortedList);
            sortedList.add(node);
            inOrderTraverse(node.getRightChild(), sortedList);
        }
    }

    private int height(BinaryTree node) {
        return node != null ? node.getHeight() : 0;
    }
}

