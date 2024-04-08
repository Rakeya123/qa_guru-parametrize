package parametrize.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import parametrize.data.LanguagOstrovok;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement languageTab = $(".LanguageWidget-module__control--2X-Kl"),
     titleCheck = $(".homepage-howdy-title"),
     searchButton= $(".Button-module__content--2FF16");

    private final ElementsCollection selectLanguageClick = $$(".LanguageWidget-module__item--1yC8C");
    private final ElementsCollection   radioGroup = $$(".RadioGroup-module__group--Gbhnu");




    public MainPage languageTabOpen () {
        languageTab.click();
        return this;

    }
    public MainPage selectLanguage(String value) {
        selectLanguageClick.findBy(text(value)).click();
        return this;
    }

    public MainPage checkRadioButtons(List<String> value) {
        radioGroup.filter(visible).shouldHave(texts(value));

        return this;
    }
    public MainPage chekTitleLanguage (LanguagOstrovok language) {

        titleCheck.shouldHave(text(language.getDescription()));
        return this;
    }

public MainPage checkSearchButtonLanguage(String value){
        searchButton.shouldHave(text(value));
        return this;
}

}