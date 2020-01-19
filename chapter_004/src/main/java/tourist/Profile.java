package tourist;

import java.util.Objects;
import java.util.function.Predicate;

public class Profile {
    private Address address;

    public Profile(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Profile profile = (Profile) obj;
        return Objects.equals(this.address, profile.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.address);
    }
}
