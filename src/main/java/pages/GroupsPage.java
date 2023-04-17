package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class GroupsPage extends LoadablePage {
    private static final By GROUPS_CATALOG = byXpath("//*[contains(@id, 'UserGroupsCatalogContent')]");
    private static final By GROUPS_MENU = byXpath("//*[contains(@id, 'UserGroupsCatalogHeader')]");
    private static final By MY_GROUPS_BUTTON = byXpath("//*[contains(@id,'MyGroupsTopBlock')]//a[contains(@hrefattrs, 'GroupsSubMenu_User_MyGroupsNav_All')]");
    private static final By OFFICIAL_GROUPS_BUTTON = byXpath("//a[contains(@href, 'groups/official')]");
    private static final By USER_GROUP_CARD = byXpath("//*[contains(@class, 'user-groups')]//*[contains(@data-l, 'groupCard')]");
    private static final By USER_GROUP_TITLE = byXpath(".//span");
    private static final By JOIN_GROUP_BUTTON = byXpath("//a[contains(@class, 'group-join_btn')]");
    private static final List<String> userGroups = new ArrayList<>();

    @Override
    void checkPage() {
        $(GROUPS_MENU).shouldBe(Condition.visible.because("Не отображается меню"));
        $(GROUPS_CATALOG).shouldBe(Condition.visible.because("Не отображаются рекомендуемые группы"));
    }

    public GroupsPage goToOfficialGroups() {
        $(OFFICIAL_GROUPS_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Официальные"))
                .click();
        return this;
    }

    public GroupsPage joinGroup() {
        $(JOIN_GROUP_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка вступить"))
                .click();
        return this;
    }

    public GroupsPage goToMyGroups() {
        $(MY_GROUPS_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Показать все"))
                .click();
        ElementsCollection userGroupsCollection = $$(USER_GROUP_CARD);
        for (SelenideElement group : userGroupsCollection) {
            userGroups.add(group.$(USER_GROUP_TITLE).text());
        }
        return this;
    }

    public List<String> getUserGroups() {
        return userGroups;
    }
}
