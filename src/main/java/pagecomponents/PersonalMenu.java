package pagecomponents;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PersonalMenu {
    // Пункты меню
    @FindBy(how = How.CLASS_NAME, using = "personalMenuItem")
    private static ElementsCollection personalMenuItems;

    public static ElementsCollection getPersonalMenuItems() {
        return personalMenuItems;
    }
}
