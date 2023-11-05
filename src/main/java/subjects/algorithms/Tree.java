package subjects.algorithms;

public interface Tree<T extends Comparable<T>> {
    Tree<BinaryTree> insert(BinaryTree data);

    void delete(BinaryTree data);

    void traverse();

    int getMax();

    int getMin();

    boolean isEmpty();

}
