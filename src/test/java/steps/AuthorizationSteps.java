package steps;

import io.qameta.allure.Step;
import pagecomponents.MainPageHeader;
import pageobjects.AuthPage;
import pageobjects.BillListPage;
import pageobjects.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static config.Config.getBaseUrl;
import static data.TestParams.*;
import static java.time.Duration.ofSeconds;

public class AuthorizationSteps extends BaseSteps {
    @Step("Authorization")
    public static void authorization(String login, String password) {
        fillField(AuthPage.getLogin(), login);
        fillField(AuthPage.getPassword(), password);
        clickElement(AuthPage.getButtonLogIn());
    }

    @Step("Success authorization")
    public static void successAuthorization(String login, String password) {
        // Open the main page
        open(getBaseUrl(), MainPage.class);

        // Click on the "Log in to B2B" button
        MainPageHeader.getLogInB2B().shouldBe(visible, ofSeconds(10));
        clickElement(MainPageHeader.getLogInB2B());

        // Open the authorization page
        AuthPage authPage = page(AuthPage.class);

        // Wait for the form to load
        authPage.waitAuthorizationForm();

        // Authorization
        authorization(login, password);

        // Open the "Bill list" page
        page(BillListPage.class);
    }

    @Step("Activate offer user")
    public static void activateOfferUser() {
        executeQuery("UPDATE \"Sec_User\" s SET s.\"IsActive\" = 1 WHERE SEC_USER_ID = " + getSecUserId(), "firebird");
        executeQuery("UPDATE CONTRACT SET CNT_ISACTIVE = 1 WHERE CORR_ID = " + getCorrIdOffer(), "firebird");
    }
}