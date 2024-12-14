package pagecomponents;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PersonalAccountHeader {
    // Иконка/кнопка пользователя
    @FindBy(how = How.CLASS_NAME, using = "header__user-name")
    private static SelenideElement buttonHeaderUserName;

    // ФИО
    @FindBy(how = How.XPATH, using = ".//ul[@class='header__user-submenu']/li/a")
    private static SelenideElement linkFullName;

    // Информационное сообщение
    @FindBy(how = How.XPATH, using = ".//ul[@class='header__user-submenu']/li/span")
    private static SelenideElement spanTextMessage;

    // Иконка/кнопка корзины
    @FindBy(how = How.CLASS_NAME, using = "header__cart")
    private static SelenideElement buttonHeaderCartPersonal;

    // Иконка/кнопка корзины
    @FindBy(how = How.XPATH, using = "//a[@class='button button--icon button--light-grey button--small header__cart-btn header__mobile-visible-btn' and @href='/personal/basket/']")
    private static SelenideElement buttonHeaderCartPublic;

    // Кнопка "Каталог"
    @FindBy(how = How.XPATH, using = ".//a[@href='/catalog/']")
    private static SelenideElement buttonHeaderCatalog;

    public static SelenideElement getButtonHeaderUserName() {
        return buttonHeaderUserName;
    }

    public static SelenideElement getLinkFullName() {
        return linkFullName;
    }

    public static SelenideElement getButtonHeaderCart() {
        return buttonHeaderCartPersonal;
    }

    public static SelenideElement getButtonHeaderCartPublic() {
        return buttonHeaderCartPublic;
    }

    public static SelenideElement getButtonHeaderCatalog() {
        return buttonHeaderCatalog;
    }

    public static SelenideElement getSpanTextMessage() {
        return spanTextMessage;
    }
}