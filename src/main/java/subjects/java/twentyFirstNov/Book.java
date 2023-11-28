package subjects.java.twentyFirstNov;

@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "author")
    private String author;


    public Book() {
    }

    public Book(int id, String title, String genre, String author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
