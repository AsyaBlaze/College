package algoritms.yandex.second.a;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Задание по ссылке: https://contest.yandex.ru/contest/28736/problems/A/
 *                                            Забавный конфуз
 *
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Пусть A — массив, состоящий из N элементов A1,…,AN. Обозначим его максимальное и минимальное значение через max(A) и min(A) соответственно.
 * Вычислим сумму элементов S, S=A1+A2+…+AN. Заменим каждый элемент массива на разницу S и этого элемента:
 * Такое преобразование массива A назовем операцией Confuse. Напишите программу, которая по массиву B,
 * полученному в результате K-кратного применения операции Confuse к некоторому массиву A, вычислит разность max(A)-min(A).
 *
 * Формат ввода:
 * Первая строка входного файла содержит целые числа N и K, где N — количество элементов массива B (),
 * а K — количество применений операции Confuse к начальному массиву A, Вторая строка файла содержит N элементов массива B.
 * Элементы массива B — целые числа, принадлежащие диапазону от -2 000 000 000 до 2 000 000 000.
 *
 * Формат вывода:
 * Единственная строка выходного файла должна содержать целое число - разность max(A) и min(A).
 * Пример:
 *
 * Ввод:
 * 4 2
 * 45 52 47 46
 *
 * Вывод:
 * 7
 *
 */

public class SolutionA {
    public static void main(String[] args) {
        confuse();
    }

    public static void confuse() {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        String[] secondLine = scanner.nextLine().split(" ");

        int N = Integer.parseInt(firstLine[0]);
        int K = Integer.parseInt(firstLine[1]);
        long[] arrayB = Arrays.stream(secondLine).mapToLong(Long::parseLong).toArray();
        long[] arrayA = new long[N];

        for (int i = 0; i < K; i++) {
            long sum = Arrays.stream(arrayB).sum();
            for (int k = 0; k < N; k++) {
                arrayA[k] = sum - arrayB[k];
            }
            arrayB = arrayA.clone();
            arrayA = new long[N];
        }

        long max = Arrays.stream(arrayB).max().orElse(arrayB[0]);
        long min = Arrays.stream(arrayB).min().orElse(arrayB[0]);

        System.out.println(max - min);
    }
}
