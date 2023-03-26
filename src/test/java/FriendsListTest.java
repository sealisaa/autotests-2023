import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.FriendsPage;
import utils.FriendCardWrapper;
import utils.User;
import utils.UserData;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class FriendsListTest extends BaseTest {
    private final User user = UserData.user;
    private static LoginPage loginPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
    }

    @Test
    void loginTest() {
        MainPage mainPage = loginPage.login(user);
        FriendsPage friendsPage = mainPage.goToFriendsPage();
        Map<String, FriendCardWrapper> userFriends = friendsPage.getUserFriends();
        assertThat(userFriends.keySet()).containsExactlyInAnyOrderElementsOf(user.getFriends());
    }

    @AfterEach
    public void setDown() {
    }
}
