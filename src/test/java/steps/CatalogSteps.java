package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pageobjects.CatalogCategoriesPage;
import pageobjects.CatalogPage;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static java.time.Duration.ofSeconds;
import static pagecomponents.CatalogFilters.*;
import static pagecomponents.CatalogFilters.getFilterAvailable;
import static pagecomponents.HeaderSearch.getSearchButton;
import static pagecomponents.HeaderSearch.getSearchInput;
import static pagecomponents.MainPageHeader.getButtonCatalog;
import static pagecomponents.MainPageHeader.getOpenSearch;
import static pagecomponents.PersonalAccountHeader.getButtonHeaderCatalog;
import static pageobjects.CatalogCategoriesPage.getCatalogCategoryNew;
import static pageobjects.CatalogPage.*;
import static pageobjects.CatalogPage.getCatalogSwitchViewToGridButton;

public class CatalogSteps extends BaseSteps {
    @Step("Switch the catalog view")
    public static void switchCatalogGrid(boolean isTableView) {
        // Open the catalog page
        page(CatalogPage.class);
        getCatalogSwitchViewToGridButton().shouldBe(visible, ofSeconds(30));

        // Switch the catalog view
        boolean isTableViewCurrent = !getCatalogSwitchViewToGridButton().has(cssClass("products-view__btn--active"));
        if (isTableView) {
            if (isTableViewCurrent) {
            } else {
                clickElement(getCatalogSwitchViewToTabletButton());
                getCatalogSwitchViewToTabletButton().shouldHave(cssClass("products-view__btn--active"), ofSeconds(30));
            }
        } else {
            if (isTableViewCurrent) {
                clickElement(getCatalogSwitchViewToGridButton());
                getCatalogSwitchViewToGridButton().shouldHave(cssClass("products-view__btn--active"), ofSeconds(30));
            }
        }
    }

    @Step("Set the remaining filter")
    public static void setRemainFilter(boolean isRemainOn) {
        if (isRemainOn) {
            if (getFilterAvailable().has(cssClass("quik-filter__item--active"))) {
                return;
            }
            else {
                getFilterAvailable().scrollIntoView(false);
                clickElement(getFilterAvailable());
                getLoaderSpinner().shouldNotBe(visible, ofSeconds(30));
                getFilterAvailable().shouldHave(Condition.cssClass("quik-filter__item--active"), ofSeconds(30));
            }
        } else {
            if (getFilterAvailable().has(cssClass("quik-filter__item--active"))) {
                getFilterAvailable().scrollIntoView(false);
                clickElement(getFilterAvailable());
                getLoaderSpinner().shouldNotBe(visible, ofSeconds(30));
                getFilterAvailable().shouldNotHave(Condition.cssClass("quik-filter__item--active"), ofSeconds(30));
            }
            else {
                return;
            }
        }
    }

    @Step("Set the currency")
    public static void setCurrency(String value) {
        if (value.equals("RUB")) {
            if (getCurrencyRuble().has(attribute("selected"))) {
                return;
            }
            else {
                clickElement(getCurrencies());
                getCurrencyRuble().shouldBe(visible, ofSeconds(10));
                clickElement(getCurrencyRuble());
                getCurrencyRuble().shouldHave(attribute("selected"), ofSeconds(30));
            }
        } else if (value.equals("USD")) {
            if (getCurrencyDollar().has(attribute("selected"))) {
                return;
            }
            else {
                clickElement(getCurrencies());
                clickElement(getCurrencyDollar());
                getCurrencyDollar().shouldBe(visible, ofSeconds(10));
                getCurrencyDollar().shouldHave(attribute("selected"), ofSeconds(30));
            }

        }
    }
}