import jakarta.persistence.*;

@Entity
@Table(name = "Phone")

public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 128)
    private String name = "";
    @Column(nullable = false)
    private String color;
    @Column
    private String country;
    @Column(nullable = false)
    private int price;
    @Column
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacture_id", nullable = false)
    private Manufacture manufacture;


    public Phone() {

    }

    public Phone(String name, String color, String country, int price, int quantity, Manufacture manufacture) {
        this.name = name;
        this.color = color;
        this.country = country;
        this.price = price;
        this.quantity = quantity;
        this.manufacture = manufacture;
    }

    public Phone(int id, String name, String color, String country, int price, int quantity, Manufacture manufacture) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.country = country;
        this.price = price;
        this.quantity = quantity;
        this.manufacture = manufacture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
//                ", manufacture=" + manufacture.toString() +
                '}';
    }
}
