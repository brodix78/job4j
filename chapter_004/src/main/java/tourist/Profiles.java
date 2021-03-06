package tourist;

import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public List<Address> addresses(List<Profile> profiles) {
        return profiles.stream().
                map(profile -> profile.getAddress()).
                distinct().
                sorted((a1, a2) -> a1.getCity().compareTo(a2.getCity())).
                collect(Collectors.toList());
    }
}