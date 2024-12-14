package pageobjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static data.TestParams.*;

public class CatalogCategoriesPage {

    // Категория "Кабели, разъёмы, переходники"
    @FindBy(how = How.XPATH, using = ".//ul[@class='categories__list']/li/a[@href='/catalog/kabeli-razyemy-perekhodniki-5515/']")
    private static SelenideElement catalogCategoryNew;

    // Подкатегория 1
    @FindBy(how = How.XPATH, using = ".//div[@class='category-content']/div[@class='scroll-container']/div[@class='category-content__col']/div/a[@href='/catalog/kabeli-razyemy-perekhodniki-5515/kabeli-dlya-lokalnoy-seti-5521/']")
    private static SelenideElement subCategory1;
    public static void initializeSubCategory1() {
        subCategory1 = Selenide.$(By.xpath(getSubCategory1Link()));
    }

    public static SelenideElement getCatalogCategoryNew() {
        return catalogCategoryNew;
    }
}