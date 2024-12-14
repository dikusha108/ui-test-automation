package pagecomponents;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductListItemsCardView {
    // Все кнопки "+" в списке (новая версия)
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'products-main-wrapper')]//ul[contains(@class, 'products__list')]//li[contains(@class, 'products__item')]//div[contains(@class, 'product')]//div[contains(@class, 'add-to-cart')]//div[contains(@class, 'cart-count')]//button[contains(@class, 'cart-count__plus') and @type='button']")
    private static ElementsCollection catalogPlusButtons;

    // Все кнопки "-" в списке (новая версия)
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'products-main-wrapper')]//ul[contains(@class, 'products__list')]//li[contains(@class, 'products__item')]//div[contains(@class, 'product')]//div[contains(@class, 'add-to-cart')]//div[contains(@class, 'cart-count')]//button[contains(@class, 'cart-count__minus') and contains(@class, 'input__quantity-btn--minus') and @type='button']")
    private static ElementsCollection catalogMinusButtons;

    // Количество у товаров в каталоге (новая версия)
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'products-main-wrapper')]//ul[contains(@class, 'products__list')]//li[contains(@class, 'products__item')]//div[contains(@class, 'product')]//div[contains(@class, 'add-to-cart')]//div[contains(@class, 'cart-count')]//input")
    private static ElementsCollection catalogCountInputs;

    // Артикулы позиций в списке, которые есть в наличии
    @FindBy(how = How.XPATH, using = "//div[@class='product']//ul[@class='feature__list']/li[span[@class='feature__title' and contains(text(),'Артикул:')]]/span[@class='feature__value']")
    private static ElementsCollection catalogPositionArticles;

    // Наименования позиций в списке, которые есть в наличии
    @FindBy(how = How.CLASS_NAME, using = "product__title")
    private static ElementsCollection catalogPositionNames;
    
    // Ссылка "В корзину" у товаров в каталоге
    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'add-to-cart__btn')]//span")
    private static ElementsCollection addToCartPrimary;

    // Ссылка "В корзину" у товаров в каталоге
    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'add-to-cart__btn')]//span[contains(@class, 'add-to-cart__secondary-title')]")
    private static ElementsCollection addToCartSecondary;

    // Кнопка "Удалить" у товаров в корзине
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'products-main-wrapper')]//ul[contains(@class, 'products__list')]//li[contains(@class, 'products__item')]//div[contains(@class, 'product')]//div[contains(@class, 'add-to-cart')]//div[contains(@class, 'add-to-cart__buttons')]//button[contains(@class, 'delete-cart')]")
    private static ElementsCollection deleteFromCartButton;

    // Значения "Транзит" в каталоге
    @FindBy(how = How.XPATH, using = "//li[span[@class='feature__title' and text()='Транзит: ']]/span[@class='feature__value']")
    private static ElementsCollection catalogTransitValues;

    // Значения "Наличие" в каталоге
    @FindBy(how = How.XPATH, using = "//li[span[@class='feature__title' and text()='Мск: ']]/span[@class='feature__value']")
    private static ElementsCollection catalogRemainValues;

    // Цены в каталоге
    @FindBy(how = How.XPATH, using = "//div[@class='price-wrapper']//p[@class='secondary-price']")
    private static ElementsCollection catalogSecondaryPrices;

    // Цены в каталоге
    @FindBy(how = How.XPATH, using = "//div[@class='price-wrapper']//p[@class='primary-price']")
    private static ElementsCollection catalogPrimaryPrices;

    // Бейджики у товаров в каталоге
    @FindBy(how = How.XPATH, using = "//div[@class='product__bages']")
    private static ElementsCollection catalogProductBages;

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

    public static ElementsCollection getAddToCartPrimary() {
        return addToCartPrimary;
    }

    public static ElementsCollection getAddToCartSecondary() {
        return addToCartSecondary;
    }

    public static ElementsCollection getDeleteFromCartButton() {
        return deleteFromCartButton;
    }

    public static ElementsCollection getCatalogTransitValues() {
        return catalogTransitValues;
    }

    public static ElementsCollection getCatalogRemainValues() {
        return catalogRemainValues;
    }

    public static ElementsCollection getCatalogSecondaryPrices() {
        return catalogSecondaryPrices;
    }

    public static ElementsCollection getCatalogPrimaryPrices() {
        return catalogPrimaryPrices;
    }

    public static ElementsCollection getCatalogProductBages() {
        return catalogProductBages;
    }
}
