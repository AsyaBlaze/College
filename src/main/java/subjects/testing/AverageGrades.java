package subjects.testing;

import java.io.*;

public class AverageGrades {
    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader("C:\\Users\\Анастасия\\Documents\\Test\\grades.csv");
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter("C:\\Users\\Анастасия\\Documents\\Test\\averageGrades.csv", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                String[] line = bufferedReader.readLine().split(",");
                double averageGrades = 0;
                for (int i = 3; i < 8; i++) {
                    averageGrades += Double.parseDouble(line[i]);
                }
                bufferedWriter.write(line[0] + "," + line[1] + "," + (averageGrades / 5) + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
