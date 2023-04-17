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
    private static final By GROUPS_MENU = byXpath("//*[contains(@id, 'UserGroupsCatalogHeader')]");
    private static final By MY_GROUPS_BUTTON = byXpath("//*[contains(@id,'MyGroupsTopBlock')]//a[contains(@hrefattrs, 'GroupsSubMenu_User_MyGroupsNav_All')]");
    private static final By OFFICIAL_GROUPS_BUTTON = byXpath("//a[contains(@href, 'groups/official')]");

    @Override
    void checkPage() {
        $(GROUPS_MENU).shouldBe(Condition.visible.because("Не отображается меню"));
    }

    public OfficialGroupsPage goToOfficialGroups() {
        $(OFFICIAL_GROUPS_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Официальные"))
                .click();
        return new OfficialGroupsPage();
    }

    public MyGroupsPage goToMyGroups() {
        $(MY_GROUPS_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Показать все"))
                .click();
        return new MyGroupsPage();
    }
}
