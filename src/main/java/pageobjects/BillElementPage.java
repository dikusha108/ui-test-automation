package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pagecomponents.ModalWindow;
import pagecomponents.PopUp;

import static com.codeborne.selenide.Selenide.page;

public class BillElementPage {
    // Модальное окно
    private static final ModalWindow modalWindow = page(ModalWindow.class);

    // Модальное окно (новое)
    private static final PopUp popup = page(PopUp.class);

    // Прогресс бар
    @FindBy(how = How.CLASS_NAME, using = "ui-progressbar")
    private static SelenideElement progressBar;

    // Кнопка "Отменить счет"
    @FindBy(how = How.CLASS_NAME, using = "js-btnCancelBill")
    private static SelenideElement buttonCancelBill;

    // Кнопка "Отправить на отгрузку"
    @FindBy(how = How.CLASS_NAME, using = "js-btnShipped")
    private static SelenideElement buttonShipped;

    // Кнопка "Накладные"
    @FindBy(how = How.CLASS_NAME, using = "js-btnShowWayBills")
    private static SelenideElement buttonShowWayBills;

    // Кнопка "Да" при отмене счета
    @FindBy(how = How.XPATH, using = ".//button/span[text()='Да']")
    private static SelenideElement buttonConfirm;

    // Статус отмены счета
    @FindBy(how = How.CLASS_NAME, using = "popup-window-content")
    private static SelenideElement resultText;

    // Кнопка "Ок" после отмены счета
    @FindBy(how = How.XPATH, using = ".//button/span[text()='OK']")
    private static SelenideElement buttonOk;

    // Чекбокс "Выбрать все товары"
    @FindBy(how = How.ID, using = "LLL_ORDER_ITEM_GRID_ID_check_all")
    private static SelenideElement inputChooseAllItems;

    // Чекбоксы у каждой позиции (нужно учесть, что первый чекбокс скрыт)
    @FindBy(how = How.XPATH, using = "//td[@class='main-grid-cell main-grid-cell-checkbox']/span")
    private static ElementsCollection inputChooseItems;

    // Кнопка "Удалить выбранные позиции"
    @FindBy(how = How.XPATH, using = ".//button[text()='Удалить выбранные позиции']")
    private static SelenideElement buttonDeleteItems;

    // Текст "Нет данных"
    @FindBy(how = How.XPATH, using = ".//div[text()='Нет данных']")
    private static SelenideElement textEmptyBill;

    // Поле для редактирования комментария к счету
    @FindBy(how = How.CLASS_NAME, using = "js-addComment")
    private static SelenideElement textareaBillComment;

    // Кнопка "Сохранить изменения" при редактировании комментария
    @FindBy(how = How.CLASS_NAME, using = "js-btnEditBill")
    private static SelenideElement buttonSaveComment;

    // Кнопка "Сохранить изменения" при редактировании позиций
    @FindBy(how = How.CLASS_NAME, using = "js-btnSaveItems")
    private static SelenideElement buttonSaveItems;

    // Строки таблицы позиций счета
    @FindBy(how = How.XPATH, using = "//table[@class='main-grid-table']/tbody/tr[not(@hide='true')]")
    private static ElementsCollection itemsTableRowElements;

    // Кнопка увеличения кол-ва позиции
    @FindBy(how = How.CLASS_NAME, using = "increaseCountGrid")
    private static ElementsCollection buttonsIncreaseItemCount;

    // Кнопка уменьшения кол-ва позиции
    @FindBy(how = How.CLASS_NAME, using = "decreaseCountGrid")
    private static ElementsCollection buttonsDecreaseItemCount;

    // Кол-ва позиции
    @FindBy(how = How.CLASS_NAME, using = "quantity_grid")
    private static ElementsCollection itemQuantities;

    // Элементы хлебных крошек
    @FindBy(how = How.CLASS_NAME, using = "breadcrumbs__item")
    private static ElementsCollection breadcrumbsItems;

    // Кнопка "Понятно" в подсказке
    @FindBy(how = How.CLASS_NAME, using = ".//span[text()='понятно']")
    private static SelenideElement popUpSmallButton;

    // Кнопка "Удалить позиции без резерва"
    @FindBy(how = How.CLASS_NAME, using = "js-btnRemoveUnReserveItems")
    private static SelenideElement buttonRemoveUnReserveItems;

    // Кол-ва позиции в резерве
    @FindBy(how = How.XPATH, using = "//tbody/tr//td[9]//span[@class='main-grid-cell-content' and @data-prevent-default='true']")
    private static ElementsCollection itemQuantitiesInReserve;

    // Дни в пути
    @FindBy(how = How.XPATH, using = "//tbody/tr//td[10]//span[@class='main-grid-cell-content' and @data-prevent-default='true']")
    private static ElementsCollection daysInTransit;

    // Кол-ва позиции без резерва
    @FindBy(how = How.XPATH, using = "//tbody/tr//td[11]//span[@class='main-grid-cell-content' and @data-prevent-default='true']")
    private static ElementsCollection itemQuantitiesWithoutReserve;

    public static SelenideElement getProgressBar() {
        return progressBar;
    }

    public static SelenideElement getButtonCancelBill() {
        return buttonCancelBill;
    }

    public static SelenideElement getButtonConfirm() {
        return buttonConfirm;
    }

    public static SelenideElement getButtonOk() {
        return buttonOk;
    }

    public static SelenideElement getResultText() {
        return resultText;
    }

    public static SelenideElement getInputChooseAllItems() {
        return inputChooseAllItems;
    }

    public static SelenideElement getButtonDeleteItems() {
        return buttonDeleteItems;
    }

    public static SelenideElement getTextEmptyBill() {
        return textEmptyBill;
    }

    public static SelenideElement getTextareaBillComment() {
        return textareaBillComment;
    }

    public static SelenideElement getButtonSaveComment() {
        return buttonSaveComment;
    }

    public static ModalWindow getModalWindow() {
        return modalWindow;
    }

    public static ElementsCollection getInputChooseItems() {
        return inputChooseItems;
    }

    public static ElementsCollection getItemsTableRowElements() {
        return itemsTableRowElements;
    }

    public static ElementsCollection getButtonsIncreaseItemCount() {
        return buttonsIncreaseItemCount;
    }

    public static ElementsCollection getButtonsDecreaseItemCount() {
        return buttonsDecreaseItemCount;
    }

    public static ElementsCollection getItemQuantities() {
        return itemQuantities;
    }

    public static SelenideElement getButtonSaveItems() {
        return buttonSaveItems;
    }

    public static SelenideElement getButtonShipped() {
        return buttonShipped;
    }

    public static SelenideElement getPopUpSmallButton() {
        return popUpSmallButton;
    }

    public static SelenideElement getButtonShowWayBills() {
        return buttonShowWayBills;
    }

    public static ElementsCollection getBreadcrumbsItems() {
        return breadcrumbsItems;
    }

    public static SelenideElement getButtonRemoveUnReserveItems() {
        return buttonRemoveUnReserveItems;
    }

    public static PopUp getPopup() {
        return popup;
    }

    public static ElementsCollection getItemQuantitiesInReserve() {
        return itemQuantitiesInReserve;
    }

    public static ElementsCollection getDaysInTransit() {
        return daysInTransit;
    }

    public static ElementsCollection getItemQuantitiesWithoutReserve() {
        return itemQuantitiesWithoutReserve;
    }
}