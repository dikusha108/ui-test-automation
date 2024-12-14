package pagecomponents;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPageHeader {
    // Кнопка "Каталог"
    @FindBy(how = How.XPATH, using = ".//a[@href='/catalog/']")
    private static SelenideElement buttonCatalog;

    // Кнопка "Войти в B2B"
    @FindBy(how = How.XPATH, using = ".//a[@href='/auth/']")
    private static SelenideElement logInB2B;

    // Кнопка "Перейти в B2B"
    @FindBy(how = How.CLASS_NAME, using = "header-link-partnership")
    private static SelenideElement goToB2B;

    // Кнопка поиска
    @FindBy(how = How.XPATH, using = ".//button[@data-search-opener='data-search-opener']")
    private static SelenideElement openSearch;

    private static HeaderSearch headerSearch = page(HeaderSearch.class);

    public static SelenideElement getLogInB2B() {
        return logInB2B;
    }

    public static SelenideElement getButtonCatalog() {
        return buttonCatalog;
    }

    public static SelenideElement getGoToB2B() {
        return goToB2B;
    }

    public static SelenideElement getOpenSearch() {
        return openSearch;
    }
}