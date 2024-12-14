package test.warranty;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import pagecomponents.ModalWindow;
import pagecomponents.PersonalMenu;
import pagecomponents.PersonalSubMenu;
import pageobjects.RepairPage;
import test.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static data.TestParams.*;
import static pageobjects.RepairPage.getButtonCheckRepair;
import static steps.AuthorizationSteps.successAuthorization;
import static steps.BaseSteps.*;
import static steps.WarrantySteps.createCheckRepairText;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckRepairTest extends BaseTest {
    @BeforeAll
    public void setUp() {
        Configuration.downloadsFolder = "src/test/resources"; // Path to download files
        Configuration.fileDownload = FileDownloadMode.FOLDER; // Download files to the specified folder

        successAuthorization(getLogin(), getPassword());
    }

    @AfterAll
    public void setUpA() {
        super.tearDownBase();
    }

    @Test
    @DisplayName("Check one valid waybill")
    public void checkOneValidWayBill() throws IOException, InterruptedException {
        // Path to save the file
        String path = "./src/test/resources/";
        String fileName = "repair_report_1.xlsx";

        // Open the "Warranty" page
        clickElement(PersonalMenu.getPersonalMenuItems().filter(text("Warranty")).first());
        PersonalSubMenu.getPersonalMenuItem().filter(text("Check serial numbers")).first()
                .shouldHave(attribute("class", "personalMenuSubmenuItem personalMenuSubmenuItem-active"), Duration.ofSeconds(30));

        // Open the page
        clickElement(PersonalSubMenu.getPersonalMenuItem().filter(text("Warranty Status")).first());
        page(RepairPage.class);

        // Check that the element is active
        PersonalSubMenu.getPersonalMenuItem().filter(text("Warranty Status")).first()
                .shouldHave(
                    attribute(
                        "class",
                        "personalMenuSubmenuItem personalMenuSubmenuItem-active"),
                    Duration.ofSeconds(30)
                );

        // Fill in the "Serial numbers" field
        fillField(RepairPage.getTextarea(), getValidWayBills().get(0));

        // Find the button and click on it to download the file
        SelenideElement downloadButton = getButtonCheckRepair().shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Проверить"));
        downloadFile(downloadButton, path, fileName);

        // Check the contents of the file
        checkValue(
                convertExcelToString(new File(path + fileName)).replace("\t", ""),
                createCheckRepairText(List.of(getValidWayBills().get(0))).replace("\t", "")
        );
    }

    @Test
    @DisplayName("Check warranty with non-existent number")
    public void checkWayBillWithNonexistentNumber() {
        // Open the "Warranty" page
        clickElement(PersonalMenu.getPersonalMenuItems().filter(text("Warranty")).first());
        PersonalSubMenu.getPersonalMenuItem().filter(text("Check Serial Number")).first()
                .shouldHave(attribute("class", "personalMenuSubmenuItem personalMenuSubmenuItem-active"), Duration.ofSeconds(30));
        clickElement(PersonalSubMenu.getPersonalMenuItem().filter(text("Warranty Status")).first());

        // Open the page
        page(RepairPage.class);

        // Check that the element is active
        PersonalSubMenu.getPersonalMenuItem().filter(text("Warranty Status")).first()
                .shouldHave(
                        attribute(
                                "class",
                                "personalMenuSubmenuItem personalMenuSubmenuItem-active"),
                        Duration.ofSeconds(30)
                );

        // Fill in the "Serial numbers" field
        fillField(RepairPage.getTextarea(), "qwe123");

        // Click on the "Check" button
        clickElement(getButtonCheckRepair());

        // Check that the serial number is not found
        ModalWindow.getModalWindowContent().shouldHave(text("Number is not found: qwe123"));
        clickElement(ModalWindow.getModalWindowOKBill());
    }

    @Test
    @DisplayName("Check warranty with 33 numbers")
    public void checkWayBillWith33Numbers() {
        // Open the "Warranty" page
        clickElement(PersonalMenu.getPersonalMenuItems().filter(text("Warranty")).first());
        PersonalSubMenu.getPersonalMenuItem().filter(text("Check Serial Number")).first()
                .shouldHave(attribute("class", "personalMenuSubmenuItem personalMenuSubmenuItem-active"), Duration.ofSeconds(30));

        // Open the page
        clickElement(PersonalSubMenu.getPersonalMenuItem().filter(text("Warranty Status")).first());
        page(RepairPage.class);

        // Check that the element is active
        PersonalSubMenu.getPersonalMenuItem().filter(text("Warranty Status")).first()
                .shouldHave(
                        attribute(
                                "class",
                                "personalMenuSubmenuItem personalMenuSubmenuItem-active"),
                        Duration.ofSeconds(30)
                );

        // Create a string with 33 serial numbers
        StringBuilder serials = new StringBuilder();
        for (int i = 0; i < 33; i++) {
            serials.append(RandomStringUtils.randomAlphabetic(10)).append("\n");
        }

        // Fill in the "Serial numbers" field
        fillField(RepairPage.getTextarea(), serials.toString());

        // Click on the "Check" button
        clickElement(getButtonCheckRepair());

        // Check that the number of waybills is too large
        ModalWindow.getModalWindowContent().shouldHave(text("Too many numbers: 33"));
        clickElement(ModalWindow.getModalWindowOKBill());
    }
}
