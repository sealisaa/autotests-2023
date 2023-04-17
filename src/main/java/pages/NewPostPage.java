package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class NewPostPage extends LoadablePage {
    private static final By POSTING_BLOCK = byXpath("//*[contains(@class, 'posting_block')]");
    private static final By POST_INPUT = byXpath("//*[@data-module='postingForm/mediaText']");
    private static final By ADD_MUSIC_BUTTON = byXpath("//*[contains(@class, 'posting') and contains(@data-l, 'button.music')]");
    private static final By ADD_BUTTON = byXpath("//a[contains(@class,'button') and text()='Добавить']");
    private static final By SUBMIT_POSTING = byXpath("//*[contains(@class,'posting_submit')]");
    private static final String MUSIC_TRACK = "//*[contains(@class, 'track') and contains(@data-json, '%s')]";

    @Override
    void checkPage() {
        $(POSTING_BLOCK).shouldBe(Condition.visible.because("Не отображается блок для создания поста"));
    }

    public MainPage createPostWithMusic(String music, String text) {
        enterPostText(text);
        addMusicToPost(music);
        submitPosting();
        return new MainPage();
    }

    public void enterPostText(String text) {
        $(POST_INPUT)
                .shouldBe(Condition.visible.because("Не отображается поле для ввода текста"))
                .setValue(text);
    }

    public void addMusicToPost(String music) {
        $(ADD_MUSIC_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка добавления музыки в пост"))
                .shouldBe(Condition.enabled.because("Кнопка добавления музыки в пост не активна"))
                .click();
        $(byXpath(String.format(MUSIC_TRACK, music)))
                .shouldBe(Condition.visible.because("Не отображается песня для поста"))
                .click();
        $(ADD_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Добавить"))
                .click();
    }

    public void submitPosting() {
        $(SUBMIT_POSTING)
                .shouldBe(Condition.visible.because("Не отображается кнопка Поделиться"))
                .click();
    }
}
