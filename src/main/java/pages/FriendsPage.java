package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.FriendCardWrapper;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FriendsPage extends LoadablePage {
    private static final By FRIEND_CARD = byXpath("//li[contains(@class, 'ugrid')]");
    private static final By FRIENDS_LIST = byXpath("//ul[contains(@class, 'ugrid')]");

    private final Map<String, FriendCardWrapper> userFriends = new HashMap<>();

    public FriendsPage() {
        checkPage();
    }

    @Override
    void checkPage() {
        $(FRIENDS_LIST)
                .shouldBe(Condition.visible.because("Не отображается список друзей"));
        ElementsCollection userFriendsCollection = $$(FRIEND_CARD);
        for (SelenideElement friend : userFriendsCollection) {
            friend.shouldBe(Condition.visible.because("Не отображается карточка друга"));
            FriendCardWrapper friendCard = new FriendCardWrapper(friend);
            userFriends.put(friendCard.getFriendName(), friendCard);
        }
    }

    public Map<String, FriendCardWrapper> getUserFriends() {
        return userFriends;
    }
}
