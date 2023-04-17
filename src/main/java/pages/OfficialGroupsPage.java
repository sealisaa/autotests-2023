package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class OfficialGroupsPage extends LoadablePage {
    private static final By GROUPS_CATALOG = byXpath("//*[contains(@id, 'UserGroupsCatalogContent')]");
    private static final By JOIN_GROUP_BUTTON = byXpath("//a[contains(@class, 'group-join_btn')]");
    private static final By MY_GROUPS_BUTTON = byXpath("//*[contains(@id,'MyGroupsTopBlock')]//a[contains(@hrefattrs, 'GroupsSubMenu_User_MyGroupsNav_All')]");

    @Override
    void checkPage() {
        $(GROUPS_CATALOG).shouldBe(Condition.visible.because("Не отображаются рекомендуемые группы"));
    }

    public OfficialGroupsPage joinGroup() {
        $(JOIN_GROUP_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка вступить"))
                .click();
        return this;
    }

    public MyGroupsPage goToMyGroups() {
        $(MY_GROUPS_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Показать все"))
                .click();
        return new MyGroupsPage();
    }
}
