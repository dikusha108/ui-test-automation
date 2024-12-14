package test.catalog;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import model.catalog.PostCatalogProducts;
import model.catalog.PostCatalogProductsResponse;
import org.junit.jupiter.api.*;
import pageobjects.CatalogPage;
import test.BaseTest;

import java.util.List;

import static steps.BaseSteps.*;
import static config.Config.getCatalogCategoryUrl;
import static data.TestParams.*;
import static java.time.Duration.ofSeconds;
import static pagecomponents.CatalogPagination.getPaginationMoreButton;
import static pagecomponents.CatalogPagination.getPaginationPages;
import static pagecomponents.ProductList.*;
import static pagecomponents.ProductListItemsCardView.*;
import static pageobjects.CatalogPage.getLoaderSpinner;
import static steps.AuthorizationSteps.successAuthorization;
import static steps.BaseSteps.checkValue;
import static steps.BaseSteps.clickElement;
import static steps.CatalogApiSteps.getCommonCurrencyUsd;
import static steps.CatalogApiSteps.postCatalogProducts;
import static steps.CatalogSteps.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CatalogProductListBaseTest extends BaseTest {
    @BeforeEach
    public void setUp() {
        super.setUpBase();
    }

    @AfterAll
    public void tearDown () {
        super.tearDownBase();
    }

    @Test
    @DisplayName("Check the display of the 'Availability' column in the catalog table for an unauthorised user")
    public void checkRemainForUnauthorised() {
        open(getCatalogCategoryUrl(), CatalogPage.class);

        // Switch to table view
        switchCatalogGrid(true);

        // Check that there is a column with the text "Availability" in the table
        boolean isTransitColumnExists = false;
        for (SelenideElement item : getCatalogTableHeadElements()){
            if (item.has(Condition.text("Availability"))) {
                isTransitColumnExists = true;
                break;
            }
        }
        checkValue(isTransitColumnExists, true);

        // Switch to tile view
        switchCatalogGrid(false);

        // Check that there is a field "Availability"
        for (SelenideElement item : getCatalogRemainValues()) {
            item.shouldHave(Condition.text("In stock"));
        }
    }

    @Test
    @DisplayName("Check the display of the 'Availability' column in the catalog table for an authorised user")
    public void checkRemainForAuthorised() {
        successAuthorization(getLogin2(), getPassword());

        // Open the catalog page
        open(getCatalogCategoryUrl(), CatalogPage.class);

        // Switch to table view
        switchCatalogGrid(true);
        setRemainFilter(false);

        // Check that there is a column with the text "Availability" in the table
        boolean isTransitColumnExists = false;
        for (SelenideElement item : getCatalogTableHeadElements()){
            if (item.has(Condition.text("Availability"))) {
                isTransitColumnExists = true;
                break;
            }
        }
        checkValue(isTransitColumnExists, true);

        // Switch to tile view
        switchCatalogGrid(false);

        // Check that there is a field "Availability"
        checkValue(getCatalogRemainValues().size(), 25);

        // Get the list of products
        PostCatalogProductsResponse expectedProductsList = postCatalogProducts(
                new PostCatalogProducts(
                        getPriceListCategory(),
                        getMaterialCategory(),
                        0,
                        25,
                        1
                )
        );

        // Check the availability of products
        int i = 0;
        for (SelenideElement item : getCatalogRemainValues()) {
            item.shouldHave(Condition.text(expectedProductsList.getData().get(i).getRemain()));
            i++;
        }
    }
}
