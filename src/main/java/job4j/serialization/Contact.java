package job4j.serialization;

import org.json.JSONPropertyIgnore;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
public class Contact {

    @XmlAttribute
    private String phone;

    private Person person;

    public Contact() {
    }

    public Contact(String phone) {
        this.phone = phone;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPhone() {
        return phone;
    }

    @JSONPropertyIgnore
    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}
