package pages;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends LoadablePage {
    private static final By NAME_FIELD = byXpath("//a[contains(@class, 'nav-side_i') and contains(@data-l, 'userPage')]");
    private static final By TOOLBAR = byXpath("//*[contains(@data-l, 'navigationToolbar')]");
    private static final By TOOLBAR_MENU = byXpath("//*[contains(@class, 'toolbar-menu')]");
    private static final By LOGOUT = byXpath("//a[@data-l='t,logout']");
    private static final By SUBMIT_LOGOUT = byXpath("//input[@data-l='t,logout']");
    private static final By FRIENDS_BUTTON = byXpath("//a[contains(@data-l, 'userFriend')]");
    private static final By GROUPS_BUTTON = byXpath("//a[contains(@data-l, 'userAltGroup')]");
    private static final By MUSIC_BUTTON = byXpath("//*[@id='hook_Block_MusicToolbarButton']");

    public MainPage() {
        checkPage();
    }

    @Override
    void checkPage() {
        $(NAME_FIELD)
                .shouldBe(Condition.visible.because("Не отображаются имя и фамилия пользователя"));
        $(TOOLBAR)
                .shouldBe(Condition.visible.because("Не отображаются верхнее меню"));
    }

    public String getUserName() {
        return $(NAME_FIELD)
                .shouldBe(Condition.visible.because("Не отображаются имя и фамилия пользователя"))
                .text();
    }

    public void logout() {
        $(TOOLBAR_MENU)
                .shouldBe(Condition.visible.because("Не отображается кнопка в верхнем меню"))
                .click();
        $(LOGOUT)
                .shouldBe(Condition.visible.because("Не отображается кнопка Выйти в меню"))
                .click();
        $(SUBMIT_LOGOUT)
                .shouldBe(Condition.visible.because("Не отображается кнопка Выйти"))
                .click();
    }

    public FriendsPage goToFriendsPage() {
        $(FRIENDS_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Друзья"))
                .click();
        return new FriendsPage();
    }

    public GroupsPage goToGroupsPage() {
        $(GROUPS_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Группы"))
                .click();
        return new GroupsPage();
    }

    public MusicPage goToMusicPage() {
        $(MUSIC_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка перехода к музыке"))
                .click();
        return new MusicPage();
    }
}
