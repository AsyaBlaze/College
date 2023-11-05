package subjects.java;

import java.util.Arrays;

public class Tasks26Oct {
    public static void main(String[] args) {
        task5(2, new int[]{1,2}, 2, new int[]{3, 4});
        task4(5, new int[]{1, 2, 3, 4, 5});
        task6(4, new int[]{1, 2, 3, 4});
        task7(2, new int[]{1, 2});
        task8(4, new int[]{1, 2, 3, 4});
    }

    private static int[] task5(int size1, int[] arr1, int size2, int[] arr2) {
        int[] rslArr = new int[size1 + size2];
        int iterator = 0;
        for (int i = 0; i < size1; i++) {
            rslArr[iterator] = arr1[i];
            iterator++;
        }
        for (int i = 0; i < size2; i++) {
            rslArr[iterator] = arr2[i];
            iterator++;
        }
        return rslArr;
    }

    private static int task4(int size, int[] arr) {
        return arr[size / 2];
    }

    private static int[] task6(int size, int[] arr) {
        int[] rsl = arr.clone();
        int temp = rsl[0];
        rsl[0] = rsl[size - 1];
        rsl[size - 1] = temp;
        Arrays.stream(rsl).forEach(System.out::println);
        return rsl;
    }

    private static boolean task7(int size, int[] arr) {
        int summPow = 0;
        for (int i = 0; i < size; i++) {
            summPow += Math.pow(arr[i], 2);
        }
        return summPow % 2 == 0;
    }

    private static int task8(int size, int[] arr) {
        return Arrays.stream(arr).max().getAsInt() - Arrays.stream(arr).min().getAsInt();
    }
}
