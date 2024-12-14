package pagecomponents;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductListItemsOld {
    // Все кнопки "+" в списке
    @FindBy(how = How.XPATH, using = ".//div[@class='input__quantity']/button[@class='input__quantity-btn input__quantity-btn--plus']")
    private static ElementsCollection catalogPlusButtons;

    // Все кнопки "-" в списке
    @FindBy(how = How.XPATH, using = ".//div[@class='input__quantity']/button[@class='input__quantity-btn input__quantity-btn--minus']")
    private static ElementsCollection catalogMinusButtons;

    // Количество у товаров в каталоге
    @FindBy(how = How.XPATH, using = ".//div[@class='input__quantity']/input")
    private static ElementsCollection catalogCountInputs;

    // Артикулы позиций в списке, которые есть в наличии
    @FindBy(how = How.XPATH, using = ".//div[@class='input__quantity']/button[@class='input__quantity-btn input__quantity-btn--plus']" +
            "/parent::div/parent::div/parent::td/parent::tr/td[@class='catalog-items__table-col catalog-items__table-col-1']")
    private static ElementsCollection catalogPositionArticles;

    // Наименования позиций в списке, которые есть в наличии
    @FindBy(how = How.XPATH, using = ".//div[@class='input__quantity']/button[@class='input__quantity-btn input__quantity-btn--plus']" +
            "/parent::div/parent::div/parent::td/parent::tr/td[@class='catalog-items__table-col catalog-items__table-col-4']/a/span[@class='catalog-items__item-title']")
    private static ElementsCollection catalogPositionNames;

    public static ElementsCollection getCatalogPlusButtons() {
        return catalogPlusButtons;
    }

    public static ElementsCollection getCatalogMinusButtons() {
        return catalogMinusButtons;
    }

    public static ElementsCollection getCatalogCountInputs() {
        return catalogCountInputs;
    }

    public static ElementsCollection getCatalogPositionArticles() {
        return catalogPositionArticles;
    }

    public static ElementsCollection getCatalogPositionNames() {
        return catalogPositionNames;
    }
}
