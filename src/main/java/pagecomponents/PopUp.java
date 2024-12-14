package pagecomponents;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PopUp {
    // Текст в модальном окне
    @FindBy(how = How.XPATH, using = "//div[@class='requests-base-popup__message']")
    private static SelenideElement popUpTexts;

    // Кнопка в модальном окне
    @FindBy(how = How.XPATH, using = "//button[@class='button-white']")
    private static SelenideElement buttonPopUp;

    public static SelenideElement getPopUpTexts() {
        return popUpTexts;
    }

    public static SelenideElement getButtonPopUp() {
        return buttonPopUp;
    }
}
