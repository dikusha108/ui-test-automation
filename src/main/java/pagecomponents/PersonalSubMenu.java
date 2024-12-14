package pagecomponents;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PersonalSubMenu {

    // Пункты меню
    @FindBy(how = How.CLASS_NAME, using = "personalMenuSubmenuItem")
    private static ElementsCollection personalMenuItem;

    public static ElementsCollection getPersonalMenuItem() {
        return personalMenuItem;
    }
}
