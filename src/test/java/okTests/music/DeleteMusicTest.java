package okTests.music;

import com.codeborne.selenide.SelenideElement;
import okTests.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.wrappers.MusicWrapper;
import utils.User;
import utils.UserData;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteMusicTest extends BaseTest {

    private final User user = UserData.user;
    private static MusicPage musicPage;
    private static final String MUSIC_TITLE = "Oshhh";

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.login(user);
        musicPage = mainPage.goToMusicPage();
        musicPage.deleteAllMyMusic();
        SelenideElement searchResult = musicPage.searchMusic(MUSIC_TITLE);
        MusicWrapper musicTrack = new MusicWrapper(searchResult);
        musicTrack.addToMyMusic();
    }

    @Test
    void deleteMusicTest() {
        musicPage.deleteMusic(MUSIC_TITLE);
        Map<String, MusicWrapper> myMusic = musicPage.getMyMusic();
        assertThat(myMusic.keySet()).doesNotContain(MUSIC_TITLE);
    }
}