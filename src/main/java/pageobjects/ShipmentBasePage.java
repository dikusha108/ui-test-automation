package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ShipmentBasePage {
    // Заголовок страницы
    @FindBy(how = How.XPATH, using = "//h1[@class='delivery-form__title']")
    private static SelenideElement deliveryFormTitle;

    // Выпадающее меню "Выберите тип отгрузки"
    @FindBy(how = How.XPATH, using = "//div[@class='dropdown__el'][.//ul[@data-dropdown-list='delivery-type']]")
    private static SelenideElement selectDeliveryType;

    // Всплывающая подсказка
    @FindBy(how = How.XPATH, using = "//div[@class='tippy-content']")
    private static SelenideElement tip;

    // Список типов отгрузки
    @FindBy(how = How.XPATH, using = "//ul[@data-dropdown-list='delivery-type']//a")
    private static ElementsCollection deliveryTypeOptions;

    // Выпадающее меню "Представитель клиента"
    @FindBy(how = How.XPATH, using = "//div[@class='dropdown__el'][.//ul[@data-dropdown-list='delivery-client']]")
    private static SelenideElement selectDeliveryClient;

    // Список представителей клиента
    @FindBy(how = How.XPATH, using = ".//ul[@data-dropdown-list='delivery-client']//li")
    private static ElementsCollection deliveryClientOptions;

    // Кнопка "Отправить"
    @FindBy(how = How.XPATH, using = "//button[text()='отправить']")
    private static SelenideElement buttonSubmit;

    // Всплывающее окно
    @FindBy(how = How.CLASS_NAME, using = "tippy-box")
    private static SelenideElement popUp;

    // Всплывающее окно
    @FindBy(how = How.CLASS_NAME, using = "tippy-content")
    private static SelenideElement popUpContent;

    // Модальное окно создания накладных
    @FindBy(how = How.XPATH, using = "//div[@class='base-popup delivery-form__popup' and @data-popup='popup-success']")
    private static SelenideElement deliveryFormPopUpSuccess;
    @FindBy(how = How.XPATH, using = "//div[@class='base-popup delivery-form__popup']")
    private static SelenideElement deliveryFormPopUp;

    @FindBy(how = How.XPATH, using = "//div[@class='base-popup delivery-form__popup' and @data-popup='popup-error']")
    private static SelenideElement deliveryFormPopUpError;

    // Модальное окно создания накладных
    @FindBy(how = How.XPATH, using = "//div[@class='base-popup delivery-form__popup' and @data-popup='popup-success']//div[@class='delivery-form__popup-title']")
    private static SelenideElement deliveryFormPopUpHeader;

    // Модальное окно создания накладных
    @FindBy(how = How.XPATH, using = "//div[@class='base-popup delivery-form__popup' and @data-popup='popup-success']//div[@class='delivery-form__popup-content']//div[@class='delivery-form__popup-text']")
    private static SelenideElement deliveryFormPopUpText;

    // Кнопка закрытия модального окна
    @FindBy(how = How.XPATH, using = "//button[@class='delivery-form__popup__close' and @data-popup-close='popup-success']")
    private static SelenideElement buttonDeliveryFormPopUpClose;
    @FindBy(how = How.XPATH, using = "//button[@class='delivery-form__popup__close' and @data-popup-close='popup-error']")
    private static SelenideElement buttonDeliveryFormPopUpCloseError;


    public static SelenideElement getSelectDeliveryType() {
        return selectDeliveryType;
    }

    public static SelenideElement getSelectDeliveryClient() {
        return selectDeliveryClient;
    }

    public static SelenideElement getButtonSubmit() {
        return buttonSubmit;
    }

    public static ElementsCollection getDeliveryTypeOptions() {
        return deliveryTypeOptions;
    }

    public static ElementsCollection getDeliveryClientOptions() {
        return deliveryClientOptions;
    }

    public static SelenideElement getTip() {
        return tip;
    }

    public static SelenideElement getDeliveryFormPopUpSuccess() {
        return deliveryFormPopUpSuccess;
    }

    public static SelenideElement getButtonDeliveryFormPopUpClose() {
        return buttonDeliveryFormPopUpClose;
    }

    public static SelenideElement getPopUp() {
        return popUp;
    }

    public static SelenideElement getPopUpContent() {
        return popUpContent;
    }

    public static SelenideElement getDeliveryFormPopUpHeader() {
        return deliveryFormPopUpHeader;
    }

    public static SelenideElement getDeliveryFormPopUpText() {
        return deliveryFormPopUpText;
    }

    public static SelenideElement getDeliveryFormTitle() {
        return deliveryFormTitle;
    }

    public static SelenideElement getDeliveryFormPopUpError() {
        return deliveryFormPopUpError;
    }

    public static SelenideElement getButtonDeliveryFormPopUpCloseError() {
        return buttonDeliveryFormPopUpCloseError;
    }

    public static SelenideElement getDeliveryFormPopUp() {
        return deliveryFormPopUp;
    }
}
