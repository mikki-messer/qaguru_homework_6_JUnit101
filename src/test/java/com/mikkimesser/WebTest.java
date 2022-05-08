package com.mikkimesser;

import com.codeborne.selenide.*;
import com.google.common.base.Strings;
import com.mikkimesser.domain.Language;
import com.mikkimesser.pages.DictionaryMainPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

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
        DictionaryMainPage dictionaryMainPage = new DictionaryMainPage();
        String word2translate = "engine";
        String expectedResult = "локомотив";
        String languageFrom = "English";
        String languageTo = "Russian";

        dictionaryMainPage.openPage();
        dictionaryMainPage.translate(word2translate, languageFrom, languageTo);
        dictionaryMainPage.checkTranslationResult(expectedResult);
    }

    @ValueSource(strings = {
            "engine",
            "car"
    })
    @ParameterizedTest(name = "Проверка перевода в мультитране с английского на русский слова {0}")
    void testMultitranTranslationParametrizedSimple(String testData){
        DictionaryMainPage dictionaryMainPage = new DictionaryMainPage();
        String languageFrom = "English";
        String languageTo = "Russian";

        dictionaryMainPage.openPage();
        dictionaryMainPage.translate(testData, languageFrom, languageTo);
        dictionaryMainPage.checkTranslationResult(testData);
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
        DictionaryMainPage dictionaryMainPage = new DictionaryMainPage();
        String languageFrom = "English";
        String languageTo = "Russian";

        dictionaryMainPage.openPage();
        dictionaryMainPage.translate(testData, languageFrom, languageTo);
        dictionaryMainPage.checkTranslationResult(expectedResult);
    }

    static Stream<Arguments> testMethodSourceExampleProvider() {
        return Stream.of(
                    Arguments.of(" ", true),
                    Arguments.of("", true),
                    Arguments.of("foo", false)
                );
    }

    @MethodSource("testMethodSourceExampleProvider")
    @ParameterizedTest(name = "Простой тест с аннотацией Method Source, вход:\"{0}\", ожидаем {1}")
    void testMethodSourceExample(String input, boolean expectedResult){
        Assertions.assertEquals(expectedResult, Strings.isNullOrEmpty(input.trim()));
    }

    @EnumSource(Language.class)
    @ParameterizedTest(name = "Проверка наличия языка в дропдаунах на главной странице")
    void testMultitranTranslationParametrizedEnum(Language language){
        DictionaryMainPage dictionaryMainPage = new DictionaryMainPage();

        dictionaryMainPage.openPage();
        System.out.println(language.name());
        dictionaryMainPage.setLanguageFrom(language.name());
        dictionaryMainPage.setLanguageTo(language.name());
    }
}
