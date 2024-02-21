package algoritms;

import java.util.Arrays;
import java.util.Random;

public class test {
    public static void main(String[] args) {
        int duration = testRecursive(10000);
        System.out.println("Рекурсивный поиск: " + duration);

        duration = testMy(10000);
        System.out.println("Мой поиск: " + duration);
    }

    private static int testMy(int times) {
        Random random = new Random();
        int rslDuration = 0;
        for (int i = 0; i < times; i++) {
            int[] arr = generateArray(random.nextInt(10, 100));
            int indexFind = random.nextInt(0, arr.length - 1);
            long startTime = System.nanoTime();
            binarySearch(arr, arr[indexFind]);
            long endTime = System.nanoTime();
            rslDuration += (endTime - startTime);
        }
        return rslDuration / times;
    }

    private static int testRecursive(int times) {
        Random random = new Random();
        int rslDuration = 0;
        for (int i = 0; i < times; i++) {
            int[] arr = generateArray(random.nextInt(10, 100));
            int indexFind = random.nextInt(0, arr.length - 1);
            long startTime = System.nanoTime();
            binarySearch(arr, arr[indexFind], 0, arr.length - 1);
            long endTime = System.nanoTime();
            rslDuration += (endTime - startTime);
        }
        return rslDuration / times;
    }

    public static int[] generateArray(int number) {
        Random random = new Random();
        int[] arr = new int[number];
        for (int i = 0; i < number; i++) {
            arr[i] = random.nextInt(1, 800);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static int binarySearch(int[] array, int search, int start, int end) {
        if (start == end) {
            if (array[start] == search) {
                return start;
            } else {
                return -1;
            }
        }
        int middle = start + (end - start) / 2;
        if (array[middle] == search) {
            return middle;
        }
        if (array[middle] <= search) {
            return binarySearch(array, search, middle + 1, end);
        } else {
            return binarySearch(array, search, start, middle);
        }
    }

    public static int binarySearch(int[] array, int number) {
        int rsl = -1;
        int i;
        int start = 0;
        int finish = array.length - 1;
        while (rsl == -1 && start < array.length) {
            i = start + (finish - start) / 2;
            if (number < array[i]) {
                finish = i;
            } else if (number > array[i]) {
                start = i + 1;
            } else {
                rsl = i;
            }
        }
        return rsl;
    }
}
