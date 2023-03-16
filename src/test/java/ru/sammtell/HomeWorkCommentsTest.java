package ru.sammtell;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class HomeWorkCommentsTest {

    @Disabled("Причина по которой мы задизейблили тест! " +
            "Например можно поставить номер задачи из Jira " +
            "потом найти быстро задизейблинный тест комбинацией клавищ ctrl + shift + L")
    @Test
    @DisplayName("Название нашего теста №1")
    void openSite() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        open("https://ru.wikipedia.org/");
        $(".main-top > .main-top-left").shouldHave(text("Добро пожаловать"));
    }


    @ValueSource(strings = {
            "JAVA", "Python", "C++"
    })
    @ParameterizedTest(name = "Проверка числа результатов поиска по тексту на сайте YAHOO.com {0}")
    // в параметрезированных тестах это убираем @DisplayName("Проверка числа результатов поиска по тексту на сайте YAHOO.com {0}") // {0} порядковый номер из метода searchResultsYahoo "String testData"
    @Tag("HIGH")
    void searchResultsYahoo(String testData) { // значения из @ValueSource(strings = подставятся по одному в метод поле String testData
        $("#ybar-search-box-container").click();
        $("input[type='text']:nth-of-type(1)").setValue(testData);
        $("#ybar-search").click();
        $$("div.dd.algo").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(5));
    }


    @Test
    @DisplayName("Проверка выдачи резульатов кол-ва мобильных приложений при клике на кнопку Mobile Apps в разделе MORE")
    @Tag("HIGH")
    void SubjectMobileApps() {
        open("https://www.yahoo.com/");
        $("div[role='toolbar']").shouldHave(text("Sign in"));
        $("#root_9").click();
        $("div#YDC-Col1 a[href^='https://mobile']").click();
        $$(".app-inner").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(7));

    }
}

// Java в процессе работы программы имеет возможность получить информацию об аннотациях расставленных в вашем коде
// и как работает junit на самом деле, что бы запустить любую java программу нужен метод main так вот
// в junit в нем есть этот метод майн который наход все классы с аннотацией @Test и запускает их, тоесть в java есть
// способ запустить метод через @Test

//в junit есть assert-шены мы их практически не используем в обучении, но например AssertEquals проверяет ожидаемый и актуальный результаты
//и еще есть сообщение о падении теста, можно указывать можно не указывать !!assert-шены!! - это важная составляющая junit-a
//@Test - можно аннотации проставлять везде почти кроме локальных переменных типа var
//@DisplayName - указываем название нашего теста (проверка чего то ) например
//@DisplayName - над классом то это будет в  Allure как тест сьют а если @DisplayName над под @Test то запись будет имено какой тест кейс прошёл
//@Disabled- нужна тогда и только тогда когда возникает момент когда тест нужно задизейблить, он написал но мы не хотим его запускать
//либо этот тест нужно исправить и пока времени нет можно поставить @Disabled, закоментировать код не надо, надо поставить @Disabled потому что закоментированнй код это плохая практика
//@Disabled- можно поставить и над всем КЛАССОМ, когда это случается? и почему это полезно?
//что бы исправить все тесты в классе например!
//@Tag - можно указать критичность выполнения бага, насколько он важен @Tag("HIGH")
//@Tags - можно указать и критичность и допустим web или браузер как пишется синтаксис:
//@Tags({ - массив из тегов
//        @Tag("HIGH"),
//        @Tag("WEB"),
//        @Tags("CHROME")
//})
// Почему нужно использовать для паралельных тестов BeforeEach вместо BeforeAll в доладе инфа - https://www.youtube.com/watch?v=LuLBL-DHTAQ
//@ParameterizedTest - это тоже самое что и @Test но и + это метка что этот тест параметризованный, а если он
// параметризованный то мы должны определить ДАТА_ПРОВАЙДЕР - можно определить аннотацией (@ValueSource)
//@ValueSource - принимает в себя все виды примитивов
//@CsvSource - такой формат описания данных где значения разделяются запятой и это выглядит как таблица
//@CsvSource({
//            "JAVA,   Java | Oracle",
//            "Python,  Welcome to Python.org"
//    }) - чувствителен к запятым, и если есть лишние в тексте то нужно ввести


//@CsvSource({
//            "JAVA,   Java | Oracle",
//            "Python,  Welcome to Python.org"
//    },
// delimiter = '|'
//)
//ЛИБО вот так можно @CsvFileSource(путь до файла с данными например @CsvFileSource(resources = "/testdata/searchResultsYahoo2.csv")
//resources = означает и обязывает чтобы файлы обязаны быть в папке resources !!!