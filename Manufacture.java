import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Manufacture")
public class Manufacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 128)
    private String name = "";
    @Column
    private String location;
    @Column
    private int employee = 0;

    @OneToMany(mappedBy = "manufacture", fetch = FetchType.EAGER)
    private List<Phone> phones = new ArrayList<>();

    public Manufacture() {
    }



    public Manufacture(String name, String location, int employee) {
        this.name = name;
        this.location = location;
        this.employee = employee;
    }

    public Manufacture(String name, String location, int employee, List<Phone> phones) {
        this.name = name;
        this.location = location;
        this.employee = employee;
        this.phones = phones;
    }

    public Manufacture(int id, String name, String location, int employee) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.employee = employee;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", employee=" + employee +
//                ", phones=" + phones.toString() +
                '}';
    }
}
