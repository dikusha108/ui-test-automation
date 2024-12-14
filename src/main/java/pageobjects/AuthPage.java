package pageobjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pagecomponents.PersonalAccountHeader;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static java.time.Duration.ofSeconds;

public class AuthPage {
    private final PersonalAccountHeader personalAccountHeader = page(PersonalAccountHeader.class);

    // Поле "Логин"
    @FindBy(how = How.XPATH, using = ".//form[@name='system_auth_form']/input[@name='USER_LOGIN']")
    private static SelenideElement login;

    // Поле "Пароль"
    @FindBy(how = How.XPATH, using = ".//form[@name='system_auth_form']/input[@name='USER_PASSWORD']")
    private static SelenideElement password;

    // Кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//form[@name='system_auth_form']/button[@class='btn2 form__submit']")
    private static SelenideElement buttonLogIn;

    // Кнопка "Принять куки"
    @FindBy(how = How.XPATH, using = ".//div[@class='cookie-tooltip__buttons']/button[@class='ok']")
    private static SelenideElement buttonAcceptCookie;

    // Сообщение об ошибке
    @FindBy(how = How.XPATH, using = ".//div[@class='siteForm__message siteForm__message-error']")
    private static SelenideElement messageError;

    // Сообщение
    @FindBy(how = How.CLASS_NAME, using = "heading--form")
    private static SelenideElement headingForm;

    public PersonalAccountHeader getPersonalAccountHeader() {
        return personalAccountHeader;
    }

    public static SelenideElement getLogin() {
        return login;
    }

    public static SelenideElement getPassword() {
        return password;
    }

    public static SelenideElement getButtonLogIn() {
        return buttonLogIn;
    }

    public static SelenideElement getMessageError() {
        return messageError;
    }

    public void waitAuthorizationForm() {
        getLogin().shouldBe(visible, ofSeconds(10));
        getPassword().shouldBe(visible, ofSeconds(10));
        getButtonLogIn().shouldBe(visible, ofSeconds(10));
    }

    public static SelenideElement getHeadingForm() {
        return headingForm;
    }

    public static SelenideElement getButtonAcceptCookie() {
        return buttonAcceptCookie;
    }
}