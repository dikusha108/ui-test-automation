package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pagecomponents.*;

import static com.codeborne.selenide.Selenide.page;

public class CatalogPage {
    // Компонент "Шапка"
    private static PersonalAccountHeader personalAccountHeader = page(PersonalAccountHeader.class);
    private static MainPageHeader mainPageHeader = page(MainPageHeader.class);

    // Компонент "Меню каталога"
    private static CatalogMenu catalogMenu = page(CatalogMenu.class);

    // Компонент "Корзина"
    private static Cart cart = page(Cart.class);

    // Компонент "Список товаров"
    private static ProductList productList = page(ProductList.class);

    // Компонент "Фильтры каталога"
    private static CatalogFilters catalogFilters = page(CatalogFilters.class);

    // Компонент "Пагинация каталога"
    private static CatalogPagination catalogPagination = page(CatalogPagination.class);

    // Модальное окно со счетами
    private static CreateBillTable createBillTable = page(CreateBillTable.class);

    // Кнопка переключения версии каталога
    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'view-toggle__btn')]")
    private static SelenideElement catalogSwitchVersionButton;

    // Курс валюты
    @FindBy(how = How.XPATH, using = "//span[@class='currency-rate__value']")
    private static SelenideElement currencyRatesValue;

    // Кнопка переключения версии каталога
    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'products-view__btn--tablet')]")
    private static SelenideElement catalogSwitchViewToTabletButton;

    // Кнопка переключения версии каталога
    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'products-view__btn--grid')]")
    private static SelenideElement catalogSwitchViewToGridButton;

    // Спиннер загрузки
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'loader__spinner')]")
    private static SelenideElement loaderSpinner;


    public static PersonalAccountHeader getHeader() {
        return personalAccountHeader;
    }

    public static Cart getCart() {
        return cart;
    }

    public static SelenideElement getCatalogSwitchVersionButton() {
        return catalogSwitchVersionButton;
    }

    public static SelenideElement getCurrencyRatesValue() {
        return currencyRatesValue;
    }

    public static SelenideElement getCatalogSwitchViewToTabletButton() {
        return catalogSwitchViewToTabletButton;
    }

    public static SelenideElement getCatalogSwitchViewToGridButton() {
        return catalogSwitchViewToGridButton;
    }

    public static SelenideElement getLoaderSpinner() {
        return loaderSpinner;
    }
}