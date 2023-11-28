package subjects.java;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ValueUsage {
    public static void main(String[] args) throws NoSuchFieldException, IOException, IllegalAccessException {
        User user = new User();
        Class<? extends User> userClass = user.getClass();
        FileReader fileReader = new FileReader("C:\\projects\\College\\src\\main\\resources\\app.txt");
        StringBuilder builder = new StringBuilder();
        while (fileReader.ready()) {
            builder.append((char) fileReader.read());
        }
        String[] tempMap = builder.toString().split("[=|\n]");
        Map<String, String> fileMap = new HashMap<>();
        for (int i = 0; i < tempMap.length; i += 2) {
            fileMap.put(tempMap[i], tempMap[i + 1]);
        }
        System.out.println("До обработки username = " + user.getUsername());
        System.out.println("До обработки password = " + user.getPassword());
        for (Field field : userClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Value.class) && fileMap.containsKey(field.getAnnotation(Value.class).key())) {
                String value = fileMap.get(field.getAnnotation(Value.class).key());
                if (field.getType().getName().equals("java.lang.String")) {
                    field.set(user, value.replace("\r", ""));
                }
                if (field.getType().getName().equals("int")) {
                    Integer valueInt = Integer.valueOf(value);
                    field.set(user, valueInt);
                }
            }
        }
        System.out.println("После обработки username = " + user.getUsername());
        System.out.println("После обработки password = " + user.getPassword());
        System.out.println(user);
        fileReader.close();
    }
}
