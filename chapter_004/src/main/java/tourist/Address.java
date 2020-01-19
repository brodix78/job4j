package tourist;

import java.util.Objects;

public class Address {
    private String city;
    private String street;
    private int home;
    private int apartment;

    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getHome() {
        return home;
    }

    public int getApartment() {
        return apartment;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", getCity(), getStreet(), getHome(), getApartment());
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Data equals method");
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Address address = (Address) obj;
        return Objects.equals(this.city, address.city) && Objects.equals(this.street, address.street)
                && Objects.equals(this.home, address.home) && Objects.equals(this.apartment, address.apartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.city, this.street, this.home, this.apartment);
    }
}
