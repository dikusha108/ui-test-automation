package test.authorization;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pagecomponents.MainPageHeader;
import pageobjects.AuthPage;
import pageobjects.MainPage;
import test.BaseTest;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static config.Config.getBaseUrl;
import static data.TestParams.*;
import static java.time.Duration.ofSeconds;
import static steps.AuthorizationSteps.*;
import static steps.BaseSteps.clickElement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthorizationTest extends BaseTest {
    @BeforeEach
    public void setUp() {
        super.setUpBase();

        // Activate user
        activateOfferUser();
    }

    @AfterEach
    public void tearDown () {
        super.tearDownBase();
    }

    @Test
    @DisplayName("Authorization with valid credentials")
    public void authorizationWithValidCredentials()  {
        successAuthorization(getLogin(), getPassword());
    }

    @Test
    @DisplayName("Authorization with offer agreement user")
    public void authorizationWithOfferUser()  {
        successAuthorization(getLoginOffer(), getPassword());
    }

    @Test
    @DisplayName("Authorization with blocked user")
    public void authorizationWithBlockedUser() {
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
        authorization(getBlockedLogin(), getCommonPassword());

        // Check that the error message appeared
        AuthPage.getMessageError().shouldBe(visible, ofSeconds(10));
        AuthPage.getMessageError().shouldHave(text("Your account is blocked"));
    }

    private static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of(
                        "with incorrect login",
                        "qwe",
                        getPassword()
                ),
                Arguments.of(
                        "with incorrect password",
                        getLogin(),
                        "qwe"
                ),
                Arguments.of(
                        "with incorrect login and password",
                        "qwe",
                        "qwe"
                ),
                Arguments.of(
                        "with empty login",
                        "",
                        getPassword()
                ),
                Arguments.of(
                        "with empty password",
                        getLogin(),
                        ""
                ),
                Arguments.of(
                        "with empty login and password",
                        "",
                        ""
                )
        );
    }
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParamsForTests")
    @DisplayName("Authorization with invalid credentials")
    public void authorizationWithInvalidCredentials(String description, String login, String password) {
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

        // Check that the error message appeared
        AuthPage.getMessageError().shouldBe(visible, ofSeconds(10));
        AuthPage.getMessageError().shouldHave(text("Incorrect login or password."));
    }
}