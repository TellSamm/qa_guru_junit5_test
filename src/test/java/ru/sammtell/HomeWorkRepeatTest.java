package ru.sammtell;
import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Повторение пройденного материала на примерах с других ресурсов!")
public class HomeWorkRepeatTest {

    @Test
    @DisplayName("Первый тест steampowered в разделе ФАЙТИНГИ И БОЕВЫЕ ИСКУССТВА")
    @Tag("TEST-TEST")
    void simpleTest(){
        open("https://store.steampowered.com/");
        $(byText("Категории")).hover();
        $("a[href*='fighting_martial_arts/']").click();
        $("#SaleSection_56339").shouldHave(text("Файтинги и боевые искусства"));
        $("#store_nav_search_term").setValue("java").pressEnter();
        $("#search_results_filtered_warning_persistent").shouldHave(text("Результатов по вашему запросу: 16. Некоторые продукты (67) скрыты согласно вашим настройкам."));
        $$("a[href*='store.steampowered.com/app']").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(16));
        $(byText("Скрыть бесплатные игры")).click();
        $("#search_results_filtered_warning_persistent").shouldHave(text("Результатов по вашему запросу: 14. Некоторые продукты (61) скрыты согласно вашим настройкам."));
        $$("a[href*='store.steampowered.com/app']").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(14));
    }


}























