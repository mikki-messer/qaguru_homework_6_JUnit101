package com.mikkimesser.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DictionaryMainPage {
    //URL
    String pageURL = "https://multitran.ru";
    //locators
    SelenideElement wordInput = $("#s");
    SelenideElement languageFromDropdown = $("#l1");
    SelenideElement languageToDropdown = $("#l2");
    SelenideElement searchButton = $("input[type='submit']");
    ElementsCollection resultCells = $$(".trans");
    SelenideElement resultsTable = $(".trans").parent().parent();
    //actions
    public void openPage(){
        open(pageURL);
    }

    public void setWord(String _word){
        wordInput.setValue(_word);
    }

    public void setLanguageFrom(String _language){
        languageFromDropdown.selectOption(_language);
    }

    public void setLanguageTo(String _language){
        languageToDropdown.selectOption(_language);
    }

    public void clickSearch(){
        searchButton.click();
    }

    public void translate(String _word, String _languageFrom, String _languageTo){
        setWord(_word);
        setLanguageFrom(_languageFrom);
        setLanguageTo(_languageTo);
        clickSearch();
    }

    public void checkTranslationResult(String _expectedResult){
        resultsTable.shouldHave(text(_expectedResult));
    }
}
