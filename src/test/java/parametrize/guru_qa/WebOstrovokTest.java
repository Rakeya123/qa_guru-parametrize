package parametrize.guru_qa;

import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import parametrize.data.LanguagOstrovok;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import parametrize.pages.MainPage;

import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.Selenide.*;

public class WebOstrovokTest {

    MainPage mainPage = new MainPage();

    @BeforeEach
    void setUp() {
        Configuration.pageLoadStrategy = "eager";
        open("https://ostrovok.ru");
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    @EnumSource(LanguagOstrovok.class)
    @ParameterizedTest(name = "Правильное оттображение {0} языка")
    void сorrectTextDisplay(LanguagOstrovok language) {

        mainPage.languageTabOpen();
        mainPage.selectLanguage(language.name());
        mainPage.chekTitleLanguage(language);

    }

    @CsvSource(value = {
            "Italiano,Cerca",
            "English,Search"
    })

    @ParameterizedTest(name = "Для выборнной страны {0} название кнопки поиска {1}")
    void buttonName(String language, String expectedButtonName) {

        mainPage.languageTabOpen();
        mainPage.selectLanguage(language);
        mainPage.checkSearchButtonLanguage(expectedButtonName);

    }

    static Stream<Arguments> selenideCorrectFilters() {

        return Stream.of(
                Arguments.of(LanguagOstrovok.English, List.of("Leisure Business")),
                Arguments.of(LanguagOstrovok.Italiano, List.of("Piacere Lavoro"))
        );

    }

    //@Disabled
    @MethodSource
    @ParameterizedTest(name = "Названия фильтров на {0}  языке")
    void selenideCorrectFilters(LanguagOstrovok language, List<String> expectedFilters) {
        mainPage.languageTabOpen();
        mainPage.selectLanguage(language.name());
        mainPage.checkRadioButtons(expectedFilters);

    }
}
