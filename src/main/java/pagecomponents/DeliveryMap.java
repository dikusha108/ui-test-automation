package pagecomponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DeliveryMap {
    // Панель с картой
    @FindBy(how = How.CLASS_NAME, using = "delivery-form__map-panel")
    private static SelenideElement deliveryMapPanel;

    // Заголовок панели с картой
    @FindBy(how = How.CLASS_NAME, using = "delivery-form__map-search-title")
    private static SelenideElement deliveryMapPanelTitle;

    // Текст панели с картой
    @FindBy(how = How.CLASS_NAME, using = "delivery-form__map-search-text")
    private static SelenideElement deliveryMapPanelText;

    // Поле ввода адреса
    @FindBy(how = How.ID, using = "delivery-map-search")
    private static SelenideElement inputAddress;

    // Список подсказок
    @FindBy(how = How.CLASS_NAME, using = "ymaps-2-1-79-search__suggest-item")
    private static ElementsCollection suggestedAddress;

    // Список терминалов
    @FindBy(how = How.CLASS_NAME, using = "delivery-form__map-baloon-address")
    private static ElementsCollection terminalAddresses;

    // Время работы терминала
    @FindBy(how = How.CLASS_NAME, using = "delivery-form__map-worktime")
    private static SelenideElement terminalWorkTime;

    public static SelenideElement getDeliveryMapPanel() {
        return deliveryMapPanel;
    }

    public static SelenideElement getDeliveryMapPanelTitle() {
        return deliveryMapPanelTitle;
    }

    public static SelenideElement getDeliveryMapPanelText() {
        return deliveryMapPanelText;
    }

    public static SelenideElement getInputAddress() {
        return inputAddress;
    }

    public static ElementsCollection getSuggestedAddress() {
        return suggestedAddress;
    }

    public static ElementsCollection getTerminalAddresses() {
        return terminalAddresses;
    }

    public static SelenideElement getTerminalWorkTime() {
        return terminalWorkTime;
    }
}
