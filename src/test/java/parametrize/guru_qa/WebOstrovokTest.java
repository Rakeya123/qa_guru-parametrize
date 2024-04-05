package parametrize.guru_qa;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeEach;
import parametrize.data.LanguageOstrovok;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import parametrize.pageobjects.ChoiceLanguage;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebOstrovokTest {

    ChoiceLanguage choiceLanguage = new ChoiceLanguage();

    @BeforeEach
    void setUp() {
        Configuration.pageLoadStrategy = "eager";
        open("https://ostrovok.ru");
    }


    @EnumSource(LanguageOstrovok.class)
    @ParameterizedTest(name = "Правильный выбор языка")
    void сorrectTextDisplay(LanguageOstrovok language) {

        choiceLanguage.languageTabOpen();
        choiceLanguage.chooseLanguageClick(language.name());
        choiceLanguage.titleCheckMustHave(language);

    }

    @CsvSource(value = {
            "Italiano,Cerca",
            "English,Search"
    })

    @ParameterizedTest(name = "Для выбора страны {0} название кнопки поиска {1}")
    void buttonName(String language, String expectedButtonName) {

        choiceLanguage.languageTabOpen();
        choiceLanguage.chooseLanguageClick(language);
        choiceLanguage.searchButtonLanguage(expectedButtonName);


    }


    static Stream<Arguments> selenideCorrectFilters() {

        return Stream.of(
                Arguments.of(LanguageOstrovok.English, List.of("Destination", "Check-in", "Check-out", "1 room for")),
                Arguments.of(LanguageOstrovok.Italiano, List.of("Città, hotel o aeroporto", "Check-in", "Check-out", ",1 camera per"))
        );

    }

    @MethodSource
    @EnumSource(LanguageOstrovok.class)
    @ParameterizedTest (name = "Названия фильтров на нужном языке")
    void selenideCorrectFilters(LanguageOstrovok language, List<String> expectedFilters){
        choiceLanguage.languageTabOpen();
        choiceLanguage.chooseLanguageClick(language.name());
        choiceLanguage.checkingFilters(expectedFilters);

    }
}
