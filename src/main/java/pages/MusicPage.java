package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.wrappers.MusicWrapper;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MusicPage extends LoadablePage {
    private static final By FOR_YOU_BUTTON = byXpath("//a[@data-tsid='showcase']");
    private static final By MY_MUSIC_BUTTON = byXpath("//a[@data-tsid='library']");
    private static final By RADIO_BUTTON = byXpath("//a[@data-tsid='radio']");
    private static final By COLLECTIONS_BUTTON = byXpath("//a[@data-tsid='collections']");
    private static final By MUSIC_TRACK = byXpath(".//wm-track[@data-tsid='track']");
    private static final By PLAYING_MUSIC_TITLE = byXpath("//wm-player-track//span[@class='name']");
    private static final By PLAYING_MUSIC_ARTIST = byXpath("//wm-player-track//span[@class='artist']");
    private static final By MUSIC_SEARCH_INPUT = byXpath("//header//wm-search-input/input");
    private static final By CLOSE_BUTTON = byXpath("//*[@id='music_layer_holder']/*[contains(@class, 'toolbar-layer_close')]");
    private static final By MY_MUSIC_HEADER = byXpath("//h1[text()='Моя музыка']");
    private static final By SEARCH_RESULTS = byXpath("//*[@data-tsid='search_results']");

    private final Map<String, MusicWrapper> myMusic = new HashMap<>();

    @Override
    void checkPage() {
        $(FOR_YOU_BUTTON).shouldBe(Condition.visible.because("Не отображается кнопка Для вас"));
        $(MY_MUSIC_BUTTON).shouldBe(Condition.visible.because("Не отображается кнопка Моя музыка"));
        $(RADIO_BUTTON).shouldBe(Condition.visible.because("Не отображается кнопка Радио"));
        $(COLLECTIONS_BUTTON).shouldBe(Condition.visible.because("Не отображается кнопка Коллекции"));
    }

    public MusicPage goToMyMusic() {
        if ($(MY_MUSIC_HEADER).is(Condition.visible)) {
            refresh();
        } else {
            $(MY_MUSIC_BUTTON)
                    .shouldBe(Condition.visible.because("Не отображается кнопка Моя музыка"))
                    .click();
        }
        return this;
    }

    public SelenideElement getMusicFromSearch(String music) {
        searchMusic(music);
        $(SEARCH_RESULTS)
                .shouldBe(Condition.visible.because("Не отображаются результаты поиска"));
        return $$(MUSIC_TRACK)
                .shouldBe(CollectionCondition.sizeNotEqual(0).because("Ни одного трека не найдено"))
                .get(0)
                .shouldBe(Condition.text(music).because("Результат не соответствует введенной строке"));
    }

    public void searchMusic(String music) {
        $(MUSIC_SEARCH_INPUT)
                .shouldBe(Condition.visible.because("Не отображается поле для поиска музыки"))
                .setValue(music)
                .pressEnter();
    }

    public void playMusic(SelenideElement music) {
        music.click();
    }

    public String getPlayingMusicTitle() {
        return $(PLAYING_MUSIC_TITLE)
                .shouldBe(Condition.visible.because("Не отображается название трека"))
                .text();
    }

    public String getPlayingMusicArtist() {
        return $(PLAYING_MUSIC_ARTIST)
                .shouldBe(Condition.visible.because("Не отображается исполнитель"))
                .text();
    }

    public Map<String, MusicWrapper> getMyMusic() {
        ElementsCollection myMusicCollection = $$(MUSIC_TRACK);
        for (SelenideElement music : myMusicCollection) {
            music
                    .shouldBe(Condition.visible.because("Не отображается музыка"));
            MusicWrapper musicTrack = new MusicWrapper(music);
            myMusic.put(musicTrack.getMusicTitle(), musicTrack);
        }
        return myMusic;
    }

    public void deleteMusic(String musicTitle) {
        myMusic.get(musicTitle).deleteFromMyMusic();
    }

    public void deleteAllMyMusic() {
        for (MusicWrapper music : myMusic.values()) {
            music.deleteFromMyMusic();
        }
    }

    public MainPage close() {
        $(CLOSE_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Закрыть"))
                .click();
        return new MainPage();
    }
}
