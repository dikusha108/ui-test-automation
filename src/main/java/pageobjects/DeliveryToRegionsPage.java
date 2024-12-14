package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pagecomponents.DeliveryDate;
import pagecomponents.DeliveryMap;

import static com.codeborne.selenide.Selenide.page;

public class DeliveryToRegionsPage extends ShipmentBasePage {
    // Карта
    private static final DeliveryMap deliveryMap = page(DeliveryMap.class);

    // Кнопка "Вперед"
    @FindBy(how = How.XPATH, using = "//button[@class='button-base button-base--center button-base--gray' and @data-delivery-btn='next' and @type='button']")
    private static SelenideElement deliveryButtonNext;

    // Кнопка "Назад"
    @FindBy(how = How.XPATH, using = ".//button[@data-delivery-btn='prev']")
    private static SelenideElement deliveryButtonPrev;

    // Выпадающее меню "Выберите адрес доставки"
    @FindBy(how = How.XPATH, using = "//div[@class='dropdown__el'][.//ul[@data-dropdown-list='delivery-address']]")
    private static SelenideElement selectDeliveryAddress;

    // Поп-ап с ошибкой выбора терминала
    @FindBy(how = How.CLASS_NAME, using = "delivery-formmap-warn")
    private static SelenideElement terminalsErrorText;

    // Поп-ап с ошибкой выбора терминала
    @FindBy(how = How.CLASS_NAME, using = "delivery-formmap-error")
    private static SelenideElement terminalsError;

    // Желаемая дата доставки
    private static final DeliveryDate deliveryDate = page(DeliveryDate.class);

    // Дополнительные услуги
    @FindBy(how = How.XPATH, using = "//label[@class='checkbox checkbox-black']/input[@type='checkbox']")
    private static ElementsCollection additionalServices;

    // Чекбокс "Согласен с условиями"
    @FindBy(how = How.XPATH, using = "//label[@class='checkbox checkbox-black conditions-agreed' and contains(text(), 'Согласен с условиями и стоимостью платной доставки')]")
    private static SelenideElement conditionsAgreed;

    public static SelenideElement getDeliveryButtonNext() {
        return deliveryButtonNext;
    }

    public static SelenideElement getDeliveryButtonPrev() {
        return deliveryButtonPrev;
    }

    public static SelenideElement getSelectDeliveryAddress() {
        return selectDeliveryAddress;
    }

    public static SelenideElement getTerminalsError() {
        return terminalsError;
    }

    public static SelenideElement getTerminalsErrorText() {
        return terminalsErrorText;
    }

    public static ElementsCollection getAdditionalServices() {
        return additionalServices;
    }

    public static SelenideElement getConditionsAgreed() {
        return conditionsAgreed;
    }
}
