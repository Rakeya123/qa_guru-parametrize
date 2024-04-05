package parametrize.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import parametrize.data.LanguageOstrovok;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ChoiceLanguage {
    private final SelenideElement languageTab = $(".LanguageWidget-module__control--2X-Kl"),
     titleCheck = $(".homepage-howdy-title");

    private final ElementsCollection chooseLanguage = $$(".LanguageWidget-module__item--1yC8C");
    private final ElementsCollection   titleFilters = $$(".Tabs-module__content--2AOXp");




    public ChoiceLanguage languageTabOpen () {
        languageTab.click();
        return this;

    }
    public ChoiceLanguage chooseLanguageClick (String value) {
        chooseLanguage.findBy(text(value)).click();
        return this;
    }

    public ChoiceLanguage checkingFilters(List<String> expectedFilters) {
        titleFilters.filter(visible).shouldHave(texts(expectedFilters));

        return this;
    }
    public ChoiceLanguage titleCheckMustHave (LanguageOstrovok language) {
        titleCheck.shouldHave(text(language.description));
        return this;
    }





//    public RegistrationPage firstNameEmpty() {
//        firstNameInput.shouldHave((cssValue("border-color", "rgb(220, 53, 69)")));
//        firstNameInput.shouldBe((empty));
//
//        return this;
//    }
}