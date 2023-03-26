package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserData {
    private static final List<String> userFriends = Arrays.asList("user1", "user2");
    public static User user = new User("botS23AT21", "botS23AT21", "login", "password", userFriends);
}
