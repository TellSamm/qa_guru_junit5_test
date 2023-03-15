package ru.SammTell;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class HomeWorkTest {


    @Test
    static void openSite() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        open("https://ru.wikipedia.org/");
        $(".main-top > .main-top-left").shouldHave(text("Добро пожаловать"));
    }




}
