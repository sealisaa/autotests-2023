package okTests.groups;

import okTests.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.GroupsPage;
import pages.LoginPage;
import pages.MainPage;
import utils.User;
import utils.UserData;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JoinGroupTest extends BaseTest {
    private final User user = UserData.user;
    private static LoginPage loginPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
    }

    @Test
    void joinGroupTest() {
        MainPage mainPage = loginPage.login(user);
        GroupsPage groupsPage = mainPage.goToGroupsPage();
        List<String> userGroupsOld = groupsPage.getUserGroups();
        groupsPage.joinGroup();
        List<String> userGroupsNew = groupsPage.getUserGroups();
        assertEquals(userGroupsNew.size(), userGroupsOld.size() + 1);
    }

    @AfterEach
    public void setDown() {
        //выходить из всех групп ?
    }
}
