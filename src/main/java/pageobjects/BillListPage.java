package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pagecomponents.ModalWindow;
import pagecomponents.PersonalBillMenu;
import pagecomponents.PersonalMenu;
import pagecomponents.PersonalSubMenu;

import static com.codeborne.selenide.Selenide.page;

public class BillListPage {
    // Компонент меню
    private static final PersonalMenu personalMenu = page(PersonalMenu.class);
    // Компонент меню
    private static final PersonalBillMenu personalBillMenu = page(PersonalBillMenu.class);

    // Компонент меню
    private static final PersonalSubMenu personalSubMenu = page(PersonalSubMenu.class);

    // Модальное окно
    private static final ModalWindow modalWindow = page(ModalWindow.class);

    // Кнопка "Отменить"
    @FindBy(how = How.XPATH, using = "//button[@id='btnCancelOrdersInGrid']")
    private static SelenideElement buttonCancel;

    // Кнопка "Объединить"
    @FindBy(how = How.XPATH, using = "//button[@id='btnMergeOrdersInGrid']")
    private static SelenideElement buttonMerge;

    // Кнопка "Отправить на отгрузку"
    @FindBy(how = How.XPATH, using = "//button[@class='js-btnShipped']")
    private static SelenideElement buttonShippedInList;

    // Кнопка "Отправить на отгрузку"
    @FindBy(how = How.XPATH, using = "//button[@class='js-btnShipped disable']")
    private static SelenideElement buttonShippedInListDisabled;

    // Счета
    @FindBy(how = How.XPATH, using = "//table[@id='LLL_ORDERS_LIST_GRID_ID_table']/tbody/tr")
    private static ElementsCollection bills;

    // Счета со статусом "Оплачен"
    @FindBy(how = How.XPATH, using = "//table[@id='LLL_ORDERS_LIST_GRID_ID_table']/tbody/tr[@data-shipped='1']")
    private static ElementsCollection paidBills;

    // Счета со статусом "Не оплачен"
    @FindBy(how = How.XPATH, using = "//table[@id='LLL_ORDERS_LIST_GRID_ID_table']/tbody/tr[@data-shipped='0']")
    private static ElementsCollection notPaidBills;

    // Номера счетов (ссылка на страницу счета)
    @FindBy(how = How.XPATH, using = ".//span[@class='main-grid-cell-content']/a[starts-with(@href, \"/personal/orders/\")]")
    private static ElementsCollection billNumbers;

    // Номера счетов (ссылка на страницу счета)
    @FindBy(how = How.XPATH, using = "//table[@id='LLL_ORDERS_LIST_GRID_ID_table']/tbody/tr[@data-shipped='0']/td/div/span/a[starts-with(@href, '/personal/orders/item/')]")
    private static ElementsCollection notPaidBillNumbers;

    // Номера счетов (ссылка на страницу счета)
    @FindBy(how = How.XPATH, using = "//table[@id='LLL_ORDERS_LIST_GRID_ID_table']/tbody/tr[@data-shipped='1']/td/div/span/a[starts-with(@href, '/personal/orders/item/')]")
    private static ElementsCollection paidBillNumbers;

    public static ElementsCollection getBills() {
        return bills;
    }

    public static PersonalBillMenu getPersonalBillMenu() {
        return personalBillMenu;
    }

    public static SelenideElement getButtonCancelInBillList() {
        return buttonCancel;
    }

    public static ElementsCollection getBillNumbers() {
        return billNumbers;
    }

    public static ModalWindow getModalWindow() {
        return modalWindow;
    }

    public static SelenideElement getButtonShippedInList() {
        return buttonShippedInList;
    }

    public static SelenideElement getButtonShippedInListDisabled() {
        return buttonShippedInListDisabled;
    }

    public static ElementsCollection getPaidBills() {
        return paidBills;
    }

    public static ElementsCollection getNotPaidBills() {
        return notPaidBills;
    }

    public static ElementsCollection getNotPaidBillNumbers() {
        return notPaidBillNumbers;
    }

    public static ElementsCollection getPaidBillNumbers() {
        return paidBillNumbers;
    }

    public static SelenideElement getButtonMerge() {
        return buttonMerge;
    }

    public static PersonalMenu getPersonalMenu() {
        return personalMenu;
    }

    public static PersonalSubMenu getPersonalSubMenu() {
        return personalSubMenu;
    }
}