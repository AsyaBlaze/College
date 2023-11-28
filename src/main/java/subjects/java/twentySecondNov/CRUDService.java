package subjects.java.twentySecondNov;

import com.fasterxml.jackson.databind.ObjectMapper;
import subjects.java.ConnectDB;
import subjects.java.twentyFirstNov.Column;
import subjects.java.twentyFirstNov.Id;
import subjects.java.twentyFirstNov.Table;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDService<T> {
    private static final ConnectDB connectDB = new ConnectDB();
    private String tableName(Class<? extends Object> clazz) {
        return clazz.getAnnotation(Table.class).name();
    }

    public CRUDService() {
    }

    public void create(T obj) throws SQLException, IllegalAccessException {
        StringBuilder statement = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        Class<? extends Object> tClass = obj.getClass();
        statement.append("INSERT INTO ").append(tableName(tClass)).append("(");
        Field[] classFields = tClass.getDeclaredFields();
        for (int i = 0; i < classFields.length; i++) {
            statement.append(classFields[i].getAnnotation(Column.class).name()).append(i < classFields.length - 1 ? ", " : ") ");
        }
        statement.append("VALUES (");
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object objField = fields[i].get(obj);
            if (objField instanceof String) {
                statement.append("'").append(objField).append("'");
            } else {
                statement.append(objField);
            }
            statement.append(i < fields.length - 1 ? ", " : ") ");
        }
        PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(statement.toString());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public Object findById(Class<? extends Object> clazz, Object id) throws IllegalAccessException, SQLException, InstantiationException {
        StringBuilder statement = new StringBuilder();
        statement.append("SELECT * FROM ").append(tableName(clazz)).append(" WHERE ").append(findId(clazz)).append(" = ").append(id);
        PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(statement.toString());
        ResultSet res = preparedStatement.executeQuery();
        res.next();
        int i = 1;
        Object toReturn = clazz.newInstance();
        for (Field field : toReturn.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            field.set(toReturn, res.getObject(i));
            i++;
        }
        preparedStatement.close();
        return toReturn;
    }

    private String findId(Class<? extends Object> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field.getAnnotation(Column.class).name();
            }
        }
        throw new IllegalArgumentException("This class don't have any Id");
    }

    public List<Object> findAll(Class<? extends Object> clazz) throws SQLException, InstantiationException, IllegalAccessException {
        StringBuilder statement = new StringBuilder();
        statement.append("SELECT * FROM ").append(tableName(clazz));
        PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(statement.toString());
        ResultSet res = preparedStatement.executeQuery();
        List<Object> rsl = new ArrayList<>();
        while (res.next()) {
            int i = 1;
            Object toReturn = clazz.newInstance();
            for (Field field : toReturn.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                field.set(toReturn, res.getObject(i));
                i++;
            }
            rsl.add(toReturn);
        }

        preparedStatement.close();
        return rsl;
    }

    public void delete(Class<? extends Object> clazz, Object id) throws SQLException {
        StringBuilder statement = new StringBuilder();
        statement.append("DELETE FROM ").append(tableName(clazz)).append(" WHERE ").append(findId(clazz)).append(" = ").append(id);
        PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(statement.toString());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void update(T obj, Object id) throws SQLException, IllegalAccessException {
        StringBuilder statement = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        statement.append("UPDATE ").append(tableName(obj.getClass())).append(" SET ");
        for (int i = 0; i < fields.length; i++) {
            statement.append(fields[i].getAnnotation(Column.class).name()).append(" = ");
            fields[i].setAccessible(true);
            Object objField = fields[i].get(obj);
            if (objField instanceof String) {
                statement.append("'").append(objField).append("'");
            } else {
                statement.append(objField);
            }
            statement.append(i < fields.length - 1 ? ", " : "");
        }
        statement.append(" WHERE ").append(findId(obj.getClass())).append(" = ").append(id);
        PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(statement.toString());
        preparedStatement.execute();
        preparedStatement.close();
    }
}
