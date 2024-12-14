package pagecomponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ProductList {

    private static ProductListItemsOld productListItemsOld = page(ProductListItemsOld.class);
    private static ProductListItemsCardView productListItemsCardView = page(ProductListItemsCardView.class);

    // Заголовки таблицы каталога
    @FindBy(how = How.XPATH, using = "//table[@class='catalog-products__table products-table']//thead//tr//th")
    private static ElementsCollection catalogTableHeadElements;

    // Сообщение "Ничего не найдено"
    @FindBy(how = How.XPATH, using = "//div[@class='not-found']//span[@class='not-found__title']")
    private static SelenideElement catalogNotFound;


    public static ElementsCollection getCatalogTableHeadElements() {
        return catalogTableHeadElements;
    }

    public static SelenideElement getCatalogNotFound() {
        return catalogNotFound;
    }
}