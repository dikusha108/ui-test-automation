package pagecomponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.attribute;

public class CatalogFilters {
    // Фильтры "В наличии", "Некондиция", "Акция"
    @FindBy(how = How.XPATH, using = "//ul[@class='quik-filter']//li")
    private static ElementsCollection quickFilters;

    // Кнопки "Сбросить" и "Применить"
    @FindBy(how = How.XPATH, using = "//button[@data-form-reset='']")
    private static SelenideElement resetButton;

    @FindBy(how = How.XPATH, using = "//button[@data-form-submit='']")
    private static SelenideElement applyButton;

    // Фильтр "Производитель"
    @FindBy(how = How.XPATH, using = "//a[@data-name-group='filter-group-brands']")
    private static SelenideElement filterBrands;

    @FindBy(how = How.XPATH, using = "//div[@id='filter-group-brands']//ul//li//label")
    private static ElementsCollection filterBrandsOptions;

    @FindBy(how = How.XPATH, using = "//div[@id='filter-group-brands']//ul//li//label//input")
    private static ElementsCollection filterBrandsOptionsId;

    @FindBy(how = How.XPATH, using = "//a[@data-filter-id='filter-brands']//span[@class='more-btn__main-title']")
    private static SelenideElement filterBrandsMoreBtn;

    @FindBy(how = How.XPATH, using = "//a[@data-filter-id='filter-brands']//span[@class='more-btn__reset-title']")
    private static SelenideElement filterBrandsHideBtn;

    @FindBy(how = How.XPATH, using = "//a[@class='filter-group__select-all']")
    private static SelenideElement filterBrandsSelectAll;

    @FindBy(how = How.XPATH, using = "//ul[@id='filter-brands']//li//input[@type='text' and @class='filters__search']")
    private static SelenideElement filterBrandsSearch;

    // Фильтр "Цена"
    @FindBy(how = How.XPATH, using = "//a[@data-name-group='filter-group-0']")
    private static SelenideElement filterPrices;

    @FindBy(how = How.XPATH, using = "//input[@name='min-price']")
    private static SelenideElement filterPriceMin;

    @FindBy(how = How.XPATH, using = "//input[@name='max-price']")
    private static SelenideElement filterPriceMax;

    @FindBy(how = How.XPATH, using = "//span[@class='maxmax-price-reset']")
    private static SelenideElement filterPriceMinReset;

    @FindBy(how = How.XPATH, using = "//span[@class='maxmax-price-reset']")
    private static SelenideElement filterPriceMaxReset;

    @FindBy(how = How.XPATH, using = "//select[@id='currencies']")
    private static SelenideElement currencies;

    @FindBy(how = How.XPATH, using = "//select[@id='currencies']//option")
    private static ElementsCollection currenciesOptions;

    public static SelenideElement getFilterAvailable() {
        return quickFilters.get(0);
    }

    public static SelenideElement getFilterBadCondition() {
        return quickFilters.get(1);
    }

    public static ElementsCollection getQuickFilters() {
        return quickFilters;
    }

    public static SelenideElement getFilterBrands() {
        return filterBrands;
    }

    public static ElementsCollection getFilterBrandsOptions() {
        return filterBrandsOptions;
    }

    public static SelenideElement getFilterBrandsMoreBtn() {
        return filterBrandsMoreBtn;
    }

    public static SelenideElement getFilterBrandsHideBtn() {
        return filterBrandsHideBtn;
    }

    public static SelenideElement getFilterBrandsSelectAll() {
        return filterBrandsSelectAll;
    }

    public static SelenideElement getFilterBrandsSearch() {
        return filterBrandsSearch;
    }

    public static ElementsCollection getFilterBrandsOptionsId() {
        return filterBrandsOptionsId;
    }

    public static SelenideElement getResetButton() {
        return resetButton;
    }

    public static SelenideElement getApplyButton() {
        return applyButton;
    }

    public static SelenideElement getFilterPrices() {
        return filterPrices;
    }

    public static SelenideElement getFilterPriceMin() {
        return filterPriceMin;
    }

    public static SelenideElement getFilterPriceMax() {
        return filterPriceMax;
    }

    public static SelenideElement getFilterPriceMinReset() {
        return filterPriceMinReset;
    }

    public static SelenideElement getFilterPriceMaxReset() {
        return filterPriceMaxReset;
    }

    public static SelenideElement getCurrencies() {
        return currencies;
    }

    public static ElementsCollection getCurrenciesOptions() {
        return currenciesOptions;
    }

    public static SelenideElement getCurrencyRuble() {
        return currenciesOptions.filterBy(attribute("value", "RUB")).first();
    }

    public static SelenideElement getCurrencyDollar() {
        return currenciesOptions.filterBy(attribute("value", "USD")).first();
    }
}
