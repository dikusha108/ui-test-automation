package pageobjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pagecomponents.PersonalMenu;
import pagecomponents.PersonalSubMenu;

import static com.codeborne.selenide.Selenide.page;

public class WarrantyPage {
    // Компонент меню
    private static final PersonalMenu personalMenu = page(PersonalMenu.class);

    // Компонент меню
    private static final PersonalSubMenu personalSubMenu = page(PersonalSubMenu.class);

    // Текстовое поле "Проверка серийных номеров"
    @FindBy(how = How.XPATH, using = "//div[@class='warranty-row']/textarea[@name='serial']")
    private static SelenideElement textareaSerials;

    // Кнопка "Проверить"
    @FindBy(how = How.XPATH, using = "//button[@id='check-serial']")
    private static SelenideElement buttonCheckSerials;

    // Найденные серийные номера
    @FindBy(how = How.XPATH, using = "//div[@id='warranty']/div[1]")
    private static SelenideElement foundSerials;

    // Не найденные серийные номера
    @FindBy(how = How.XPATH, using = "//div[@id='warranty']/div[2]")
    private static SelenideElement notFoundSerials;

    // Ссылка "Скачать результаты"
    @FindBy(how = How.XPATH, using = "//div[@id='warranty']/a")
    private static SelenideElement downloadReport;

    // Заголовок "Проверка серийных номеров"
    @FindBy(how = How.XPATH, using = "//div[@id='warranty']/h3")
    private static SelenideElement serialsHeader;

    public static SelenideElement getButtonCheckSerials() {
        return buttonCheckSerials;
    }

    public static SelenideElement getTextareaSerials() {
        return textareaSerials;
    }

    public static PersonalMenu getPersonalMenu() {
        return personalMenu;
    }

    public static PersonalSubMenu getPersonalSubMenu() {
        return personalSubMenu;
    }

    public static SelenideElement getFoundSerials() {
        return foundSerials;
    }

    public static SelenideElement getNotFoundSerials() {
        return notFoundSerials;
    }

    public static SelenideElement getDownloadReport() {
        return downloadReport;
    }

    public static SelenideElement getSerialsHeader() {
        return serialsHeader;
    }
}
