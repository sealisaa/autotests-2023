package okTests.music;

import com.codeborne.selenide.SelenideElement;
import okTests.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.User;
import utils.UserData;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayMusicTest extends BaseTest {
    private static final String MUSIC_TITLE = "Oshhh";
    private static LoginPage loginPage;
    private final User user = UserData.user;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
    }

    @Test
    void playMusicTest() {
        MainPage mainPage = loginPage.login(user);
        MusicPage musicPage = mainPage.goToMusicPage();
        SelenideElement music = musicPage.searchMusic(MUSIC_TITLE);
        musicPage.playMusic(music);
        assertThat(musicPage.getPlayingMusicTitle())
                .isEqualToIgnoringCase(MUSIC_TITLE);
    }
}
