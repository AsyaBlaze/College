package subjects.java.twentyFirstNov;

import subjects.java.ConnectDB;
import subjects.java.twentySecondNov.Animal;
import subjects.java.twentySecondNov.CRUDService;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Creator {
    private static final ConnectDB connectDB = new ConnectDB();

    public static void main(String[] args) throws SQLException {
        try {
            Book book = new Book(1, "Беззвездное море", "Фантастика", "Эрин Моргенштерн");
            createTableFromClass(book.getClass());
            CRUDService<Book> bookCRUDService = new CRUDService<>();
            bookCRUDService.create(book);
            System.out.println("Создали одну книгу: ");
            bookCRUDService.findAll(Book.class).stream().forEach(System.out::println);
            bookCRUDService.create(new Book(2, "Тайная история", "Триллер","Донна Тарт"));
            System.out.println("Добавили ещё одну книгу: ");
            bookCRUDService.findAll(Book.class).stream().forEach(System.out::println);
            bookCRUDService.update(new Book(2, "Тайная история", "Детектив","Донна Тарт"), 2);
            System.out.println("Обновили одну книгу: ");
            bookCRUDService.findAll(Book.class).stream().forEach(System.out::println);
            bookCRUDService.delete(Book.class, 2);
            System.out.println("Удалили одну книгу: ");
            bookCRUDService.findAll(Book.class).stream().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            deleteTable(Book.class);
        }
        System.out.println("\n\n");
        try {
            Animal animal = new Animal(1, "Jekky", "Dog");
            createTableFromClass(animal.getClass());
            CRUDService<Animal> animalCRUDService = new CRUDService<>();
            animalCRUDService.create(animal);
            System.out.println("Создали одно животное: ");
            animalCRUDService.findAll(Animal.class).stream().forEach(System.out::println);
            animalCRUDService.create(new Animal(2, "Lora", "Cat"));
            System.out.println("Добавили ещё одно животное: ");
            animalCRUDService.findAll(Animal.class).stream().forEach(System.out::println);
            animalCRUDService.update(new Animal(2, "Lora", "Panther"), 2);
            System.out.println("Обновили одно животное: ");
            animalCRUDService.findAll(Animal.class).stream().forEach(System.out::println);
            animalCRUDService.delete(Animal.class, 2);
            System.out.println("Удалили одно животное: ");
            animalCRUDService.findAll(Animal.class).stream().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            deleteTable(Animal.class);
        }
    }

    private static void createTableFromClass(Class<? extends Object> clazz) throws Exception {
        StringBuilder statement = new StringBuilder();
        if (clazz.isAnnotationPresent(Table.class)) {
            statement.append("CREATE TABLE ").append(clazz.getAnnotation(Table.class).name()).append(" (\n");
        } else {
            throw new IllegalArgumentException("Class don't have Table annotation");
        }
        Field[] fields = clazz.getDeclaredFields();
        int idPresent = 0;
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (fields[i].isAnnotationPresent(Column.class)) {
                //Пока что обработка только двух типов: String и int
                String type = fields[i].getType().getSimpleName().equals("String") ? "varchar" : "int";
                statement.append(fields[i].getAnnotation(Column.class).name()).append(" ").append(type);
                if (fields[i].isAnnotationPresent(Id.class)) {
                    statement.append(" PRIMARY KEY");
                    idPresent++;
                }
                if (fields[i].getAnnotation(Column.class).unique()) statement.append(" UNIQUE");
                if (i != fields.length - 1) statement.append(",");
                statement.append("\n");
            }
        }
        statement.append(")");
        if (idPresent == 0 || idPresent > 1) throw new Exception("Class don't have any id or id more then one");

        PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(statement.toString());
        preparedStatement.execute();
        preparedStatement.close();
    }

    private static void deleteTable(Class<? extends Object> clazz) throws SQLException {
        StringBuilder statement = new StringBuilder();
        statement.append("DROP TABLE ").append(clazz.getAnnotation(Table.class).name());
        PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(statement.toString());
        preparedStatement.execute();
        preparedStatement.close();
    }
}
