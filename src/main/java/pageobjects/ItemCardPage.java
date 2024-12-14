package pageobjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pagecomponents.Cart;
import pagecomponents.PersonalAccountHeader;

import static com.codeborne.selenide.Selenide.page;

public class ItemCardPage {
    // Заголовок товара
    @FindBy(how = How.CLASS_NAME, using = "heading--page")
    private static SelenideElement itemCardHeading;

    // Кнопка "+"
    @FindBy(how = How.XPATH, using = ".//div[@id='quantity_input']/button[@id='quantity_plus']")
    private static SelenideElement itemCardPlusButton;

    // Кнопка "-"
    @FindBy(how = How.ID, using = "quantity_minus")
    private static SelenideElement itemCardMinusButton;

    // Количество товара
    @FindBy(how = How.ID, using = "quantity")
    private static SelenideElement itemCardCountInput;

    // Компонент "Шапка"
    private static PersonalAccountHeader personalAccountHeader = page(PersonalAccountHeader.class);

    // Компонент "Корзина"
    private static Cart cart = page(Cart.class);

    public static SelenideElement getItemCardHeading() {
        return itemCardHeading;
    }

    public static Cart getCart() {
        return cart;
    }

    public static PersonalAccountHeader getHeader() {
        return personalAccountHeader;
    }

    public static SelenideElement getItemCardPlusButton() {
        return itemCardPlusButton;
    }

    public static SelenideElement getItemCardMinusButton() {
        return itemCardMinusButton;
    }

    public static SelenideElement getItemCardCountInput() {
        return itemCardCountInput;
    }
}