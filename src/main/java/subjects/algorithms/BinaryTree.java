package subjects.algorithms;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree implements Comparable<BinaryTree> {
    private int key;
    private BinaryTree leftChild;
    private BinaryTree rightChild;
    private int height;

    public BinaryTree(int rootObj) {
        this.key = rootObj;
        this.leftChild = null;
        this.rightChild = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public BinaryTree getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(int leftChild) {
        this.leftChild = new BinaryTree(leftChild);
    }

    public void setLeftChild(BinaryTree leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTree getRightChild() {
        return rightChild;
    }

    public void setRightChild(int rightChild) {
        this.rightChild = new BinaryTree(rightChild);
    }

    public void setRightChild(BinaryTree rightChild) {
        this.rightChild = rightChild;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BinaryTree getNodeByOrderIn(int i) {
        List<BinaryTree> rsl = new ArrayList<>();
        return inOrderTraverse(this, rsl).get(i - 1);
    }

    private List<BinaryTree> inOrderTraverse(BinaryTree root, List<BinaryTree> rsl) {
        if (root != null) {
            inOrderTraverse(root.leftChild, rsl);
            rsl.add(root);
            inOrderTraverse(root.rightChild, rsl);
        }
        return rsl;
    }

    public void printTree() {
        printTree(this, "", true);
    }

    private void printTree(BinaryTree node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }

        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getKey());

        if (node.getLeftChild() != null || node.getRightChild() != null) {
            printTree(node.getLeftChild(), prefix + (isTail ? "    " : "│   "), false);
            printTree(node.getRightChild(), prefix + (isTail ? "    " : "│   "), true);
        }
    }


    @Override
    public int compareTo(BinaryTree o) {
        BinaryTree binaryTree = (BinaryTree) o;
        return this.key - binaryTree.key;
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }
}
