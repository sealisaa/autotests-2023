package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;

public class FriendCardWrapper {
    private final SelenideElement friendCard;
    private static final By FRIEND_NAME = byXpath(".//*[@class='ellip']/a");

    public FriendCardWrapper(SelenideElement friendCard) {
        friendCard.shouldBe(Condition.visible.because("Не отображается карточка друга"));
        this.friendCard = friendCard;
    }

    public String getFriendName() {
        return friendCard.$(FRIEND_NAME)
                .shouldBe(Condition.visible.because("Не отображается имя друга"))
                .text();
    }
}
