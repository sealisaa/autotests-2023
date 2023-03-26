package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import jdk.nashorn.internal.scripts.JO;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class GroupsPage extends LoadablePage {
    private static final By GROUPS_CATALOG = byXpath("//*[contains(@id, 'UserGroupsCatalogContent')]");
    private static final By GROUPS_MENU = byXpath("//*[contains(@id, 'UserGroupsCatalogHeader')]");
    private static final By MY_GROUPS_BUTTON = byXpath("//*[contains(@id, 'my-groups')]");
    private static final By ALL_MY_GROUPS_BUTTON = byXpath("//*[contains(@text, 'Все мои группы')]");
    private static final By OFFICIAL_GROUPS_BUTTON = byXpath("//a[contains(@href, 'groups/official')]");
    private static final By USER_GROUP_CARD = byXpath("//*[contains(@class, 'user-groups')]//*[contains(@data-l, 'groupCard')]");
    private static final By USER_GROUP_TITLE = byXpath(".//span");
    private static final By JOIN_GROUP_BUTTON = byXpath("//a[contains(@class, 'group-join_btn')]");
    private static List<String> userGroups = new ArrayList<>();

    @Override
    void checkPage() {
        $(GROUPS_MENU).shouldBe(Condition.visible.because("Не отображается меню"));
        $(GROUPS_CATALOG).shouldBe(Condition.visible.because("Не отображаются рекомендуемые группы"));
    }

    public void goToOfficialGroups() {
        $(OFFICIAL_GROUPS_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Официальные"))
                .click();
    }

    public void joinGroup() {
        goToOfficialGroups();
        $(JOIN_GROUP_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка вступить"))
                .click();
    }

    public void goToMyGroups() {
        refresh();
        $(MY_GROUPS_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Мои"))
                .click();
        $(ALL_MY_GROUPS_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Все мои группы"))
                .click();
        ElementsCollection userGroupsCollection = $$(USER_GROUP_CARD);
        for (SelenideElement group : userGroupsCollection) {
            userGroups.add(group.$(USER_GROUP_TITLE).text());
        }
    }

    public List<String> getUserGroups() {
        goToMyGroups();
        return userGroups;
    }
}
