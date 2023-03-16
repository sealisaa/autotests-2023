package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends LoadablePage {
    private static final By NAME_FIELD = byXpath("//a[contains(@class, 'nav-side_i') and contains(@data-l, 'userPage')]");
    private static final By TOOLBAR = byXpath("//*[contains(@data-l, 'navigationToolbar')]");
    private static final By TOOLBAR_MENU = byXpath("//*[contains(@class, 'toolbar-menu')]");
    private static final By LOGOUT = byXpath("//a[@data-l='t,logout']");
    private static final By SUBMIT_LOGOUT = byXpath("//input[@data-l='t,logout']");

    @Override
    void checkPage() {
        $(NAME_FIELD)
                .shouldBe(Condition.visible.because("Не отображаются имя и фамилия пользователя"));
        $(TOOLBAR)
                .shouldBe(Condition.visible.because("Не отображаются верхнее меню"));
    }

    public String getName() {
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
}
