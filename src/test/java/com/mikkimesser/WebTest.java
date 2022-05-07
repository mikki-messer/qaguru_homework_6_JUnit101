package com.mikkimesser;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DisplayName("Веб-тесты с параметрами")
public class WebTest {
    @DisplayName("Установка параметров")
    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1280x720";
    }

    @DisplayName("Тест словаря Multitran")
    @Test
    void testMultitranTranslation(){
        SelenideElement wordInput = $("#s");
        SelenideElement languageFromDropdown = $("#l1");
        SelenideElement languageToDropdown = $("#l2");
        SelenideElement searchButton = $("input[type='submit']");
        ElementsCollection resultCells = $$(".trans");

        Selenide.open("https://multitran.ru");

        wordInput.setValue("engine");
        languageFromDropdown.selectOption("English");
        languageToDropdown.selectOption("Russian");
        searchButton.click();
        resultCells.find(Condition.text("локомотив")).shouldBe(visible);
    }
}
