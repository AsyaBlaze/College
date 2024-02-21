package subjects.java.twentyNineNov;

import java.util.Arrays;

public class StreamTest {
    public static void main(String[] args) {
        MyStream.of(Arrays.asList("Гриша", "Миша", "Маша", "Ксюша")).filter(person -> person.startsWith("М")).forEach(System.out::println);
    }
}
