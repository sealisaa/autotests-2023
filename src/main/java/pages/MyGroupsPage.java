package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MyGroupsPage extends LoadablePage {
    private static final By USER_GROUPS = byXpath("//*[contains(@class, 'my-groups')]");
    private static final By USER_GROUP_CARD = byXpath("//*[contains(@class, 'user-groups')]//*[contains(@data-l, 'groupCard')]");
    private static final By USER_GROUP_TITLE = byXpath(".//span");
    private static final By OFFICIAL_GROUPS_BUTTON = byXpath("//a[contains(@href, 'groups/official')]");
    private static final List<String> userGroups = new ArrayList<>();

    @Override
    void checkPage() {
        $(USER_GROUPS).shouldBe(Condition.visible.because("Не отображается блок друзей пользователя"));
    }

    public List<String> getUserGroups() {
        ElementsCollection userGroupsCollection = $$(USER_GROUP_CARD);
        for (SelenideElement group : userGroupsCollection) {
            userGroups.add(group.$(USER_GROUP_TITLE).text());
        }
        return userGroups;
    }

    public OfficialGroupsPage goToOfficialGroups() {
        $(OFFICIAL_GROUPS_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Официальные"))
                .click();
        return new OfficialGroupsPage();
    }
}
