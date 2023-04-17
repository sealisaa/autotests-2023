package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.wrappers.FriendCardWrapper;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FriendsPage extends LoadablePage {
    private static final By FRIEND_CARD = byXpath("//li[contains(@class, 'ugrid')]");
    private static final By FRIENDS_LIST = byXpath("//ul[contains(@class, 'ugrid')]");

    private final Map<String, FriendCardWrapper> userFriends = new HashMap<>();

    @Override
    void checkPage() {
        $(FRIENDS_LIST)
                .shouldBe(Condition.visible.because("Не отображается список друзей"));
    }

    public Map<String, FriendCardWrapper> getUserFriends() {
        ElementsCollection userFriendsCollection = $$(FRIEND_CARD);
        for (SelenideElement friend : userFriendsCollection) {
            friend.shouldBe(Condition.visible.because("Не отображается карточка друга"));
            FriendCardWrapper friendCard = new FriendCardWrapper(friend);
            userFriends.put(friendCard.getFriendName(), friendCard);
        }
        return userFriends;
    }
}
