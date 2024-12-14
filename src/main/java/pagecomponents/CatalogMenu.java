package pagecomponents;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static data.TestParams.*;

public class CatalogMenu {
    // Категория "Комплектующие для ПК"
    @FindBy(how = How.XPATH, using = ".//div[@class='catalog__menu-container']//ul[@class='menu']/li/span[text()='Кабели, разъёмы, переходники']")
    private static SelenideElement categoryPcComponents;

    // Подкатегория "Материнские платы"
    private static SelenideElement subCategoryMotherboards;
    public static void initializeSubCategoryMotherboards() {
        String subCategoryMotherboardsLink = getSubCategoryMotherboardsLink();
        subCategoryMotherboards = Selenide.$(By.xpath(subCategoryMotherboardsLink));
    }

    public static SelenideElement getCategoryPcComponents() {
        return categoryPcComponents;
    }

    public static SelenideElement getSubCategoryMotherboards() {
        return subCategoryMotherboards;
    }
}