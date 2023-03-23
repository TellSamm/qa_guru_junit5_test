package ru.sammtell;
import com.beust.jcommander.Strings;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("***Проверки работы поисковых систем***")
public class HomeWorkRepeatTest {


    @Deprecated
    @ValueSource(strings = { //ValueSourse можем использовать если нам нужен 1 пареметр в методе типа (String testData)
            "Java", "Junit","Battlefield 2","Postman"
    })
    @ParameterizedTest(name = "ЯндексПоиск тексу -> {0}")
//    @DisplayName("ЯндексПоиск тексу -> {0}") - при параметризированных тестах нужно текст с значением переводить на  @ParameterizedTest(name = "ЯндексПоиск тексу -> {0}")
    @Tag("Smoke")
    void testSearchResults(String testData) {
        open("https://ya.ru/");
        $("#text").setValue(testData).pressEnter();
        $$("li.serp-item").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(10));
    }



    //@CsvSource принимает массив значений, разделенных запятыми, и каждая запись массива соответствует строке в CSV-файле.
    //По умолчанию запятая является разделителем столбцов, но мы можем настроить ее с помощью атрибута -разделителя :
    @CsvSource(
        value = {"Battlefield,  — отмеченный наградами шутер от EA и DICE...",
                "Skyrim, The Elder Scrolls | Skyrim"}
    )
    @ParameterizedTest(name = "При вводе названия игры {0} выводимый текс в поисковой системе {1}")
    @Tags({ //@Аннатоция ТЭГС пишется именно так)
            @Tag("SMOKE"),
            @Tag("GAMES")
    })
    void testSearchGamesAndExpectedResults1(String testDataGames, String expectedText) {
        open("https://ya.ru/");
        $("#text").setValue(testDataGames).pressEnter();
        $$("li.serp-item").first().shouldHave(Condition.text(expectedText));
    }


    //@CsvFileSource - указываем в файле значения ввод и ожидаемый результат, прописываем путь до файла с данными как на примере)
    @Deprecated
    @CsvFileSource (resources = "/testdata/searchGames.csv")
    @ParameterizedTest(name = "При вводе названия игры {0} выводимый текс в поисковой системе {1}")
    @Tags({ //@Аннатоция ТЭГС пишется именно так)
            @Tag("SMOKE"),
            @Tag("GAMES")
    })
    void testSearchGamesAndExpectedResults2(String testDataGames, String expectedText) {
        open("https://ya.ru/");
        $("#text").setValue(testDataGames).pressEnter();
        $$("li.serp-item").first().shouldHave(Condition.text(expectedText));
    }
}























