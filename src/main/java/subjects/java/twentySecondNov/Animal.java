package subjects.java.twentySecondNov;

import subjects.java.twentyFirstNov.Column;
import subjects.java.twentyFirstNov.Id;
import subjects.java.twentyFirstNov.Table;

@Table(name = "animal")
public class Animal {
    @Id
    @Column(name = "animal_id")
    private int id;

    @Column(name = "name_animal")
    private String name;

    @Column(name = "classification")
    private String classification;

    public Animal() {
    }

    public Animal(int id, String name, String classification) {
        this.id = id;
        this.name = name;
        this.classification = classification;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classification='" + classification + '\'' +
                '}';
    }
}
