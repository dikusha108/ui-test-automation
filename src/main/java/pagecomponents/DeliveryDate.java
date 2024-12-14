package pagecomponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DeliveryDate {
    // Дата доставки
    @FindBy(how = How.XPATH, using = ".//input[@name='delivery-date']")
    private static SelenideElement deliveryDateInput;

    // Календарь
    @FindBy(how = How.XPATH, using = ".//div[@class='dayContainer']")
    private static SelenideElement deliveryDateCalendar;

    // Недоступные даты в календаре
    @FindBy(how = How.XPATH, using = ".//div[@class='dayContainer']//span[@class='flatpickr-disabled']")
    private static ElementsCollection disabledDays;

    // Доступные даты в календаре
    @FindBy(how = How.XPATH, using = ".//div[@class='dayContainer']//span[not(contains(@class, 'flatpickr-disabled'))]")
    private static ElementsCollection enabledDays;

    // Сегодняшняя дата в календаре
    @FindBy(how = How.CLASS_NAME, using = "today")
    private static SelenideElement todayDate;

    public static SelenideElement getDeliveryDateInput() {
        return deliveryDateInput;
    }

    public static SelenideElement getDeliveryDateCalendar() {
        return deliveryDateCalendar;
    }

    public static ElementsCollection getDisabledDays() {
        return disabledDays;
    }

    public static ElementsCollection getEnabledDays() {
        return enabledDays;
    }

    public static SelenideElement getTodayDate() {
        return todayDate;
    }
}
