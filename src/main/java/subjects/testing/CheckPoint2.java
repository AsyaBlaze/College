package subjects.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CheckPoint2 {
    public static void main(String[] args) {
        try {
            // Переводим всех записанных пользователей из файла в объекты класса User
            Object obj = new JSONParser().parse(new FileReader("C:\\Users\\Анастасия\\Documents\\Test\\users.json"));
            JSONArray jsonArray = (JSONArray) obj;
            List<User1> user1s = new ArrayList<>();
            for (Object obj1 : jsonArray) {
                user1s.add(parseUser((JSONObject) obj1));
            }
            // Переводим все записанные книги из файла в объекты класса Book
            List<Book> books = parseBooks("C:\\Users\\Анастасия\\Documents\\Test\\books.csv");
            int index = books.size() - 1;
            int i = 0;
            // Раздаем книги пользователям
            while (index != 0) {
                if (i == user1s.size()) {
                    i = 0;
                }
                user1s.get(i).getBooks().add(books.get(index));
                i++;
                index--;
            }
            // Создаем файл json и записываем в него пользователей с их книгами
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("result.json"), user1s);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * Переносит объект из jsonObject в объект класса User
     * @return полученный объект User
     */
    private static User1 parseUser(JSONObject jsonObject) {
        User1 user1 = new User1();
        user1.setName((String) jsonObject.get("name"));
        user1.setAge((long) jsonObject.get("age"));
        String gender = (String) jsonObject.get("gender");
        user1.setGender(gender.equals("male") ? Gender.male : Gender.female);
        user1.setAddress((String) jsonObject.get("address"));
        return user1;
    }

    /**
     * В csv-файле содержится информация о книгах, в этом методе мы создаем из неё объекты класса Book
     * @param path путь файла
     * @return Массив книг созданных из файла
     */
    private static List<Book> parseBooks(String path) {
        List<Book> books = new ArrayList<>();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                String[] line = parseLine(bufferedReader.readLine().split(","));
                Book book = new Book();
                book.setTitle(line[0]);
                book.setAuthor(line[1]);
                book.setGenre(line[2]);
                book.setPages(Integer.parseInt(line[3]));
                books.add(book);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    /**
     * Разбивает строку на данные, учитывая кавычки
     * @param line Строка для обработки
     * @return Массив данных
     */
    private static String[] parseLine(String[] line) {
        String[] rsl = new String[line.length];
        StringBuilder temp = new StringBuilder();
        int i = 0;
        for (String l : line) {
            if (l.startsWith("\"")) {
                temp = new StringBuilder(l);
                continue;
            }
            if (!temp.toString().equals("")){
                temp.append(l);
                if (l.endsWith("\"")) {
                    rsl[i] = temp.toString().replace("\"", "");
                    temp = new StringBuilder();
                    i++;
                }
            } else {
                rsl[i] = l;
                i++;
            }

        }
        return rsl;
    }
}

enum Gender {
    female,
    male
}

class Book {
    private String title;
    private String author;
    private int pages;
    private String genre;

    public Book() {
    }

    public Book(String title, String author, int pages, String genre) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

class User1 {
    private String name;
    private Gender gender;
    private String address;
    private long age;
    private List<Book> books = new ArrayList<>();

    public User1() {
    }

    public User1(String name, Gender gender, String address, long age, List<Book> books) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", books=" + books +
                '}';
    }
}
