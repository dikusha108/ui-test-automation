package pagecomponents;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderSearch {
    // Поле ввода поискового запроса
    @FindBy(how = How.ID, using = "edSearchF")
    private static SelenideElement searchInput;

    // Кнопка поиска
    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'search__start') and @type='submit']")
    private static SelenideElement searchButton;

    public static SelenideElement getSearchInput() {
        return searchInput;
    }

    public static SelenideElement getSearchButton() {
        return searchButton;
    }
}
