package pagecomponents;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ModalWindow {
    // Модальное окно на странице счета
    @FindBy(how = How.XPATH, using = "//div[@class='popup-window-content']/div[@class='info']/p")
    private static SelenideElement modalWindowBillTexts;

    // Кнопка "Ок" в модальном окне
    @FindBy(how = How.XPATH, using = "//button[@class='ui-btn ui-btn-md ui-btn-primary']")
    private static SelenideElement modalWindowOKBill;

    // Модальное окно на странице счета
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'popup-window-with-titlebar')]//div[@class='popup-window-content']")
    private static SelenideElement modalWindowContent;

    // Заголовок модального окна
    @FindBy(how = How.XPATH, using = "//div[@class='popup-window-titlebar-text']")
    private static SelenideElement modalWindowTitle;

    public static SelenideElement getModalWindowBillTexts() {
        return modalWindowBillTexts;
    }

    public static SelenideElement getModalWindowOKBill() {
        return modalWindowOKBill;
    }

    public static SelenideElement getModalWindowContent() {
        return modalWindowContent;
    }

    public static SelenideElement getModalWindowTitle() {
        return modalWindowTitle;
    }
}
