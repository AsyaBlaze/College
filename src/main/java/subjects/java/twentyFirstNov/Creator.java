package subjects.java.twentyFirstNov;

import subjects.java.ConnectDB;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;

public class Creator {
    private static final ConnectDB connectDB = new ConnectDB();

    public static void main(String[] args) throws Exception {
        Book book = new Book(1, "Беззвездное море", "Фантастика", "Эрин Моргенштерн");
        Class<? extends Book> bookClass = book.getClass();
        createTableFromClass(bookClass);
    }

    private static void createTableFromClass(Class<? extends Book> clazz) throws Exception {
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
                String type = fields[i].getType().toString().equals("java.lang.String") ? "varchar" : "int";
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
        System.out.println(statement);
        preparedStatement.execute();
        preparedStatement.close();
    }
}
