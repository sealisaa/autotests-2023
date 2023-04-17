package customMatchers;

import org.hamcrest.CustomMatcher;
import utils.User;

public class IsValidUserData extends CustomMatcher<String> {
    private final User user;

    public IsValidUserData(User user) {
        super("Корректные имя и фамилия");
        this.user = user;
    }

    @Override
    public boolean matches(Object o) {
        return o.equals(user.getFirstName() + " " + user.getLastName());
    }
}
