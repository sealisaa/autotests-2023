package okTests;

import org.junit.jupiter.api.BeforeAll;
import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {

    private static final String OK_URL = "http://ok.ru";

    @BeforeAll
    static void start() {
        open(OK_URL);
    }
}