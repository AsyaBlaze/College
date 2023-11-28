package subjects.zarali;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class First {
    private static final FileWriter fileWriterK;
    private static final Scanner scanner = new Scanner(System.in);

    static {
        try {
            fileWriterK = new FileWriter("OUTPUT.txt", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void isLuckyNumber() throws IOException {
        char[] numbers = scanner.nextLine().toCharArray();
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < 3; i++) {
            sum1 += numbers[i];
            sum2 += numbers[numbers.length - i - 1];
        }
        if (sum1 == sum2) {
            fileWriterK.write("YES");
        } else {
            fileWriterK.write("NO");
        }
        fileWriterK.close();
    }

    public static void howManyQueensAtTheDeskWithAtLeastOneFreeSpace() throws IOException {
        int n = Integer.parseInt(scanner.nextLine());
        int answer = (int) (Math.pow(n, 2) - 3 * n + 2);
        fileWriterK.write(answer);
    }

    public static void uravnenie() throws IOException {
        FileWriter fileWriter = new FileWriter("OUTPUT.txt", true);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (a != 0) {
            double d = Math.pow(b, 2) - 4 * a * c;
            double x1 = (-b + Math.sqrt(d)) / 2 * a;
            double x2 = (-b - Math.sqrt(d)) / 2 * a;
            if (d == 0) {
                fileWriter.write(0);
            } else {
                fileWriter.write("2\n");
                fileWriter.write(String.valueOf(x2));
                fileWriter.write(String.valueOf(x1));
            }
        } else if (b != 0){
            double x = -c / b;
            fileWriter.write("1\n");
            fileWriter.write(String.valueOf(x));
        } else if (c != 0){
            fileWriter.write("0");
        } else {
            fileWriter.write("-1");
        }
        fileWriter.close();
    }

    public static void baketsWithCoins() {
        int n = scanner.nextInt(); // количество корзин
        int w = scanner.nextInt(); // вес настоящих монет
        int d = scanner.nextInt(); // разница фальшивых в весе
        int p = scanner.nextInt(); // вес отобранных монет
        int quantity = ((1 + (n-1)) / 2) * (n-1);
        int between = quantity * w - p;
        int answer = between == 0 ? n : between / d;
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        baketsWithCoins();
    }
}
