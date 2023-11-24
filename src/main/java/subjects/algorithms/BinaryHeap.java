package subjects.algorithms;


import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {
    private List<T> heap;

    public BinaryHeap() {
        heap = new ArrayList<>();
    }

    public void insert(T element) {
        heap.add(element);
        int index = heap.size() - 1;
        heapifyUp(index);
    }

    public T extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }

        T minElement = heap.get(0);
        int lastIndex = heap.size() - 1;
        T lastElement = heap.get(lastIndex);

        heap.set(0, lastElement);
        heap.remove(lastIndex);
        heapifyDown(0);

        return minElement;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T currentElement = heap.get(index);
            T parentElement = heap.get(parentIndex);

            if (currentElement.compareTo(parentElement) >= 0) {
                break;
            }

            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChildIndex;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Heap is empty.");
            return;
        }

        printHeap(0, 0);
    }

    private void printHeap(int index, int level) {
        if (index < heap.size()) {
            printHeap(2 * index + 2, level + 1);

            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(heap.get(index));

            printHeap(2 * index + 1, level + 1);
        }
    }
}

