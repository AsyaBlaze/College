package subjects.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Ответы на вопросы:
 *
 * Что такое BST: BST расшифровывается как Binary Search Tree, это древовидная структура данных,
 * при этом у каждого узла может быть только не более двух дочерних узлов. BST используется для хранения
 * и организации уникальных объектов (не повторяются)
 *
 */

public class FinalWork1 {
    public static void main(String[] args) {
        // сгенерировать список случайных чисел от 0 до 100 без повторений, длиной 30
        List<Integer> numbers = generate30RandomNumbers();
        numbers.forEach(num -> System.out.print(num + " "));

        // построить бинарное дерево поиска из этого списка и вывести в консоль
        System.out.println("\nБинарное дерево поиска\n");
        BinaryTree root = buildBinaryTree(numbers);
        root.printTree();

        // сбалансировать дерево и вывести в консоль
        System.out.println("\nAVL сбалансированное дерево\n");
        AVLTreeConverter avlTreeConverter = new AVLTreeConverter();
        AVLTree avlTree = avlTreeConverter.convertBinaryTreeToAVL(root);
        avlTree.printTree();

        // на основе списка из пункта 1 построить бинарную кучу (чем больше число, тем больше приоритет)
        // Отсортировать список из пункта 1 кучей (сделать хипсорт)
        System.out.println("\nОтсортированная бинарная куча\n");
        BinaryHeap binaryHeap = new BinaryHeap();
        for (int num : numbers) {
            binaryHeap.insert(num);
        }
        binaryHeap.print();


    }

    private static List<Integer> generate30RandomNumbers() {
        List<Integer> rsl =new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            int num = random.nextInt(0, 100);
            if (!rsl.contains(num)) {
                rsl.add(num);
            } else {
                i--;
            }
        }
        return rsl;
    }

    public static BinaryTree buildBinaryTree(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) return null;

        BinaryTree root = new BinaryTree(numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            insertIntoTree(root, numbers.get(i));
        }
        return root;
    }

    private static void insertIntoTree(BinaryTree root, int value) {
        if (value < root.getKey()) {
            if (root.getLeftChild() == null) {
                root.setLeftChild(value);
            } else {
                insertIntoTree(root.getLeftChild(), value);
            }
        } else {
            if (root.getRightChild() == null) {
                root.setRightChild(value);
            } else {
                insertIntoTree(root.getRightChild(), value);
            }
        }
    }


}
