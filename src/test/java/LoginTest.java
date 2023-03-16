import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import utils.User;
import utils.UserData;

public class LoginTest extends BaseTest {
    private final User user = UserData.user;
    private static LoginPage loginPage;
    private static MainPage mainPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
    }

    @Test
    void loginTest() {
        mainPage = loginPage.login(user);
        assert(mainPage.getName().startsWith(user.getFirstName())
                && mainPage.getName().endsWith(user.getLastName()));
    }

    @AfterEach
    public void setDown() {
//        mainPage.logout();
    }
}
