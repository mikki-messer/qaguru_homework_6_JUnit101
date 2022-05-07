package com.mikkimesser;

import com.codeborne.selenide.*;
import com.google.common.base.Strings;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

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

    @Disabled("Disabled Annotation Example")
    @DisplayName("Простой тест словаря Multitran")
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

    @ValueSource(strings = {
            "engine",
            "car"
    })
    @ParameterizedTest(name = "Проверка перевода в мультитране с английского на русский слова {0}")
    void testMultitranTranslationParametrizedSimple(String testData){
        SelenideElement wordInput = $("#s");
        SelenideElement languageFromDropdown = $("#l1");
        SelenideElement languageToDropdown = $("#l2");
        SelenideElement searchButton = $("input[type='submit']");
        SelenideElement resultCell = $(".trans");

        Selenide.open("https://multitran.ru");

        wordInput.setValue(testData);
        languageFromDropdown.selectOption("English");
        languageToDropdown.selectOption("Russian");
        searchButton.click();
        resultCell.parent().parent().shouldHave(text(testData));
    }

    @CsvSource(value = {
            "engine | локомотив",
            "car | автомобиль (A four-wheeled motor vehicle used for land transport, usually propelled by a gasoline or diesel internal combustion engine"
    },
    delimiter = '|'
    )
    @ParameterizedTest(name = "Перевод в мультитране с английского на русский. Вход {0}, ожидаем {1}")
    void testMultitranTranslationParametrizedComplex(String testData,
                                                     String expectedResult){
        SelenideElement wordInput = $("#s");
        SelenideElement languageFromDropdown = $("#l1");
        SelenideElement languageToDropdown = $("#l2");
        SelenideElement searchButton = $("input[type='submit']");
        ElementsCollection resultCells = $$(".trans");

        Selenide.open("https://multitran.ru");

        wordInput.setValue(testData);
        languageFromDropdown.selectOption("English");
        languageToDropdown.selectOption("Russian");
        searchButton.click();
        resultCells.find(Condition.text(expectedResult)).shouldBe(visible);
    }
    static Stream<Arguments> testMethodSourceExampleProvider() {
        return Stream.of(
                    Arguments.of(" ", true),
                    Arguments.of("", true),
                    Arguments.of("foo", false)
                );
    }

    @MethodSource("testMethodSourceExampleProvider")
    @ParameterizedTest(name = "Простой тест с аннотацией Method Source")
    void testMethodSourceExample(String input, boolean expectedResult){
        Assertions.assertEquals(expectedResult, Strings.isNullOrEmpty(input.trim()));
    }
}
