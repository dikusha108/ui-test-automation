package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    @BeforeAll
    public void setUpBase() {
        Selenide.clearBrowserCookies();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().includeSelenideSteps(false).savePageSource(false));
        Configuration.headless = true;
        Configuration.savePageSource = false;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 40000;
        Configuration.pageLoadTimeout =  60000;
        System.setProperty("chromeoptions.args", "--remote-allow-origins=*");
    }

    @AfterAll
    public void tearDownBase() {
        Selenide.closeWebDriver();
    }
}
