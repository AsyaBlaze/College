package subjects.java;

import java.util.Scanner;

public class Calculator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        start();
        factorial();
    }

    private static void factorial() {
        System.out.println("\nХотите вычислить факториал? Да/Нет");
        String answer = scanner.nextLine();
        if (!"да".equalsIgnoreCase(answer))
            return;
        System.out.println("Введите число: ");
        int num = Integer.parseInt(scanner.nextLine());
        System.out.printf("Факториал от %d равен %d", num, calculateFactorial(num));
    }

    private static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите операцию +, -, /, * :");
        String operation = scanner.nextLine();
        if (!operation.equals("+") && !operation.equals("-") &&
                !operation.equals("*") && !operation.equals("/"))
            throw new IllegalArgumentException("Такой операции не существует");
        System.out.println("Введите два числа через пробел:");
        String[] numbers = scanner.nextLine().split(" ");
        try {
            int a = Integer.parseInt(numbers[0]);
            int b = Integer.parseInt(numbers[1]);
            calculate(a, b, operation);
        } catch (Exception e) {
            double a = Double.parseDouble(numbers[0]);
            double b = Double.parseDouble(numbers[1]);
            calculate(a, b, operation);
        }
    }

    private static void calculate(int a, int b, String operation) {
        int rsl = -1;
        switch (operation) {
            case "+":
                rsl = a + b;
                break;
            case "-":
                rsl = a - b;
                break;
            case "*":
                rsl = a * b;
                break;
            case "/":
                rsl = a / b;
                break;
        }
        System.out.printf("Результат выражения %d %s %d равняется %d", a, operation, b, rsl);
    }

    private static void calculate(double a, double b, String operation) {
        double rsl = -1;
        switch (operation) {
            case "+":
                rsl = a + b;
                break;
            case "-":
                rsl = a - b;
                break;
            case "*":
                rsl = a * b;
                break;
            case "/":
                rsl = a / b;
                break;
        }
        System.out.printf("Результат выражения " + a + " %s " + b + " равняется " + rsl, operation);
    }

    private static int calculateFactorial(int num) {
        if (num == 1 || num == 0) return 1;
        return num * calculateFactorial(num - 1);
    }
}
