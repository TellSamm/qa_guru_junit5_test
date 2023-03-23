package ru.sammtell;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.sammtell.data.Locale2;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;


public class WorkLocaleRepeatTest {

    @BeforeEach
    static void openPracticeForm() {
        Configuration.browserSize = "1920x1080";
        //Configuration.browser ="firefox";
        Selenide.open("https://selenide.org/");

    }
    //Stream - Возвращает поток, состоящий из элементов этого потока, соответствующих заданному предикату.
    //Arguments- Получите аргументы, используемые для вызова метода @ParameterizedTest.
    static Stream<Arguments>dataProvider(){ //====> каркас для запуска тестов

        return Stream.of(
            Arguments.of(Locale2.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes") ),
            Arguments.of(Locale2.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы") )
        );
    }


    @MethodSource("dataProvider") //@ Источник метода -- это аннотация которая позволяет описать специальный статический метод,
    //который может вернуть любые java объекты вообще которые существуют в вашей программе
    @ParameterizedTest(name = "Для локали {0} на сайте selenide.org должен отображаться список кнопок {1}")
    @Tag("Smoke")
    void buttonsLocale(Locale2 vyborYazika, List<String> ozhidayemiyRezultat) {
        //вместо String используем наш enum он же Locale2, а в качестве второго
        // (ожидаемого рез-та)
        // будем использовать List как список String-ов
        //Selenide.open("https://selenide.org/");
        $$("#languages a").find(Condition.text(vyborYazika.name())).click(); //---> ищем две кнопки с помощью #languages a
        //далее находим по тексту, и используем встроенный метод enum ->
        // .name который перебирает в классе Locale2(emum) нужную локаль указанную там!
        $$(".main-menu-pages a").filter(visible).shouldHave(texts(ozhidayemiyRezultat));

    }
}
