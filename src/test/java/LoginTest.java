import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import utils.User;
import utils.UserData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        assertThat(mainPage.getUserName(), startsWith(user.getFirstName()));
        assertThat(mainPage.getUserName(), endsWith(user.getLastName()));
        assertThat(mainPage.getUserName(), validUserData);
    }

    @AfterEach
    public void setDown() {
//        mainPage.logout();
    }

    Matcher<String> validUserData = new CustomMatcher<String>("Корректные имя и фамилия") {
        @Override
        public boolean matches(Object o) {
            return o.equals(user.getFirstName() + " " + user.getLastName());
        }
    };
}
