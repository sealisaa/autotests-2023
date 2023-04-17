package okTests.music;

import com.codeborne.selenide.SelenideElement;
import okTests.BaseTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.Post;
import utils.wrappers.MusicWrapper;
import utils.User;
import utils.UserData;
import utils.wrappers.PostWrapper;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatePostWithMusicTest extends BaseTest {
    private final User user = UserData.user;
    private static MainPage mainPage;
    private static MusicPage musicPage;
    private static final String musicTitle = "Oshhh";
    private static final String text = "Текст для теста";

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        mainPage = loginPage.login(user);
        musicPage = mainPage.goToMusicPage();
        musicPage.goToMyMusic().deleteAllMyMusic();
        SelenideElement searchResult = musicPage.getMusicFromSearch(musicTitle);
        MusicWrapper musicTrack = new MusicWrapper(searchResult);
        musicTrack.addToMyMusic();
        mainPage = musicPage.close();
    }

    @Test
    void createPostWithMusicTest() {
        mainPage = mainPage.createNewPost().createPostWithMusic(musicTitle, text);
        Post expectedPost = new Post.PostBuilder()
                .setAuthor(user.getName())
                .setText(text)
                .setMusic(musicTitle)
                .build();
        PostWrapper postWithText = mainPage.getPostByText(text);
        assertThat(postWithText).isNotNull();
        Post createdPost = new Post.PostBuilder()
                .setAuthor(postWithText.getAuthor())
                .setText(postWithText.getText())
                .setMusic(postWithText.getMusicTitle())
                .build();
        assertThat(expectedPost)
                .usingRecursiveComparison()
                .isEqualTo(createdPost);
    }

    @AfterAll
    static void setDown() {
        PostWrapper post = mainPage.getPostByText(text);
        post.delete();
        musicPage = mainPage.goToMusicPage();
        musicPage.deleteAllMyMusic();
    }
}
