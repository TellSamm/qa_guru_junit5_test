package ru.sammtell;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.sammtell.data.Locale;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Проверка работы выбора локали на сайте www.rzd.ru")
public class WorkLokaleTest {

    static Stream<Arguments> buttonsInDifferentLanguages(){
        return Stream.of(
         Arguments.of(Locale.Eng, List.of("Passengers",
                 "Freight",
                 "The Company",
                 "Investor Relations",
                 "Contacts",
                 "Аccessibility version")),
         Arguments.of(Locale.Rus, List.of("Пассажирам",
                 "Грузовые перевозки",
                 "Компания",
                 "Работа в РЖД",
                 "Контакты",
                 "Версия для слабовидящих"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Для локали {0} на сайте www.rzd.ru должен отображаться список кнопок {1}}")
    @Tag("Smoke")
    void buttonsInDifferentLanguages(Locale locale, List<String> expectedButtons) {
        open("https://www.rzd.ru/");
        $("a[class^='locale-switch']").click();
        $$("a[href*='/en/']").find(text(locale.name())).click();
        $$("div[class$='header-menu_wrap']").filter(visible).shouldHave(texts(expectedButtons));
    }




}
