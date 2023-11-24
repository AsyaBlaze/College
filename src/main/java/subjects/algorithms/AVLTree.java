package subjects.algorithms;
//

public class AVLTree<T extends Comparable<T>> implements Tree<T> {

    private BinaryTree root;

    public BinaryTree getRoot() {
        return root;
    }

    public void setRoot(BinaryTree root) {
        this.root = root;
    }

    @Override
    public Tree<BinaryTree> insert(BinaryTree data) {
        root = insert(data.getKey(), root);
        return (Tree<BinaryTree>) this;
    }

    private BinaryTree insert(Integer data, BinaryTree node) {
        if (node == null) {
            return new BinaryTree(data);
        }
        if (data.compareTo(node.getKey()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild()));
        } else if (data.compareTo(node.getKey()) > 0) {
            node.setRightChild(insert(data, node.getRightChild()));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
    }



    @Override
    public void delete(BinaryTree data) {
        root = delete(data.getKey(), root);
    }

    private BinaryTree delete(Integer data, BinaryTree node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getKey()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        } else if (data.compareTo(node.getKey()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else {
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }
            node.setKey(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getKey(), node.getLeftChild()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void traverse() {
        traverseInOrder(root);
    }

    private void traverseInOrder(BinaryTree node) {
        if (node != null) {
            traverseInOrder(node.getLeftChild());
            System.out.println(node);
            traverseInOrder(node.getRightChild());
        }
    }

    @Override
    public int getMax() {
        if (isEmpty()) {
            return -1;
        }
        return getMax(root);
    }

    private int getMax(BinaryTree node) {
        if (node.getRightChild() != null) {
            return getMax(node.getRightChild());
        }
        return node.getKey();
    }

    @Override
    public int getMin() {
        if (isEmpty()) {
            return -1;
        }
        return getMin(root);
    }

    private int getMin(BinaryTree node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getKey();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private BinaryTree applyRotation(BinaryTree node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }
            return node;
    }

    private BinaryTree rotateRight(BinaryTree node) {
        BinaryTree leftNode = node.getLeftChild();
        BinaryTree centerNode = leftNode.getRightChild();
        leftNode.setRightChild(node.getKey());
        node.setLeftChild(centerNode.getKey());
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private BinaryTree rotateLeft(BinaryTree node) {
        BinaryTree rightNode = node.getRightChild();
        BinaryTree centerNode = rightNode.getLeftChild();
        rightNode.setLeftChild(node.getKey());
        node.setRightChild(centerNode.getKey());
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private void updateHeight(BinaryTree node) {
        int maxHeight = Math.max(height(node.getLeftChild()), height(node.getRightChild()));
        node.setHeight(maxHeight + 1);
    }

    private int balance(BinaryTree node) {
        return node != null ? height(node.getLeftChild()) - height(node.getRightChild()) : 0;
    }

    private int height(BinaryTree node) {
        return node != null ? node.getHeight() : 0;
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(BinaryTree node, int indent) {
        if (node == null) return;

        printTree(node.getRightChild(), indent + 4);

        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }

        System.out.println(node.getKey());

        printTree(node.getLeftChild(), indent + 4);
    }

}
