package pagecomponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class CatalogPagination {
    @FindBy(className = "pagination__more-btn")
    private static SelenideElement paginationMoreButton;

    @FindBy(xpath = "//ul[@class='pagination__list']//li//a | //ul[@class='pagination__list']//li//span")
    private static ElementsCollection paginationPages;

    public static SelenideElement getPaginationMoreButton() {
        return paginationMoreButton;
    }

    public static ElementsCollection getPaginationPages() {
        return paginationPages;
    }
}
