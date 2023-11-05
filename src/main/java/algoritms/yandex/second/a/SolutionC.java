package algoritms.yandex.second.a;

import java.util.*;

/**
 *                    Шахматная доска
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Из шахматной доски по границам клеток выпилили связную (не распадающуюся на части) фигуру без дыр. Требуется определить ее периметр.
 *
 * Формат ввода:
 * Сначала вводится число N (1 ≤ N ≤ 64) – количество выпиленных клеток. В следующих N строках вводятся координаты выпиленных клеток,
 * разделенные пробелом (номер строки и столбца – числа от 1 до 8). Каждая выпиленная клетка указывается один раз.
 *
 * Формат вывода:
 * Выведите одно число – периметр выпиленной фигуры (сторона клетки равна единице).
 *
 * Пример 1
 * Ввод: 3
        1 1
        1 2
        2 1
 * Вывод: 8
 *
 * Пример 2
 * Ввод: 1
         8 8
 * Вывод: 4
 *
 * Пример 3
 * Ввод: 4
        3 4
        3 5
        3 6
        3 7
 * Вывод: 10
 */
public class SolutionC {
    private static List<Wall> walls = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quantity = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < quantity; i++) {
            int[] position = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            putWalls(position[0], position[1]);
        }
        for (int i = 0; i < walls.size(); i++) {
            for (int j = i + 1; j < walls.size(); j++) {
                if (walls.get(i).equals(walls.get(j))) {
                    walls.remove(i);
                    walls.remove(j - 1);
                    i--;
                    break;
                }
            }
        }

        System.out.println(walls.size());
    }

    private static void putWalls(int horiz, int vert) {
        Corner upperLeft = new Corner(horiz - 1, vert - 1);
        Corner upperRight = new Corner(horiz - 1, vert);
        Corner downLeft = new Corner(horiz, vert - 1);
        Corner downRight = new Corner(horiz, vert);
        walls.add(new Wall(upperLeft, upperRight)); // верхняя стена квадрата
        walls.add(new Wall(downLeft, downRight)); // нижняя стена квадрата
        walls.add(new Wall(upperLeft, downLeft)); // левая стена квадрата
        walls.add(new Wall(downRight, upperRight)); // правая стена квадрата
    }
}

class Wall {
    private final Corner firstCorner;
    private final Corner secondCorner;

    public Wall(Corner firstCorner, Corner secondCorner) {
        this.firstCorner = firstCorner;
        this.secondCorner = secondCorner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wall wall = (Wall) o;
        return (this.firstCorner.equals(wall.firstCorner) && this.secondCorner.equals(wall.secondCorner))
                || (this.secondCorner.equals(wall.firstCorner) && this.firstCorner.equals(wall.secondCorner));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstCorner, secondCorner);
    }

    @Override
    public String toString() {
        return "Wall{" +
                "firstCorner=" + firstCorner +
                ", secondCorner=" + secondCorner +
                '}';
    }
}

class Corner {
    private final int horizontal;
    private final int vertical;

    public Corner(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corner corner = (Corner) o;
        return this.horizontal == corner.horizontal && this.vertical == corner.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }

    @Override
    public String toString() {
        return "Corner{" +
                "horizontal=" + horizontal +
                ", vertical=" + vertical +
                '}';
    }
}