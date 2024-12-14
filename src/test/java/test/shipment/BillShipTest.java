package test.shipment;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pagecomponents.DeliveryMap;
import pageobjects.BillElementPage;
import pageobjects.DeliveryToRegionsPage;
import test.BaseTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.HoverOptions.withOffset;
import static com.codeborne.selenide.Selenide.page;
import static data.TestParams.*;
import static java.time.Duration.ofSeconds;
import static pageobjects.BillElementPage.*;
import static pageobjects.DeliveryToRegionsPage.getDeliveryFormTitle;
import static pageobjects.DeliveryToRegionsPage.*;
import static pageobjects.PickUpPage.getDeliveryClientOptions;
import static pageobjects.PickUpPage.getDeliveryTypeOptions;
import static pageobjects.PickUpPage.getSelectDeliveryClient;
import static pageobjects.PickUpPage.getSelectDeliveryType;
import static steps.AuthorizationSteps.successAuthorization;
import static steps.BaseSteps.*;
import static steps.BillSteps.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BillShipTest extends BaseTest {
    @BeforeAll
    public void setUp() {
        super.setUpBase();
        successAuthorization(getLogin(), getPassword());
    }

    @AfterAll
    public void tearDown () {
        super.tearDownBase();
    }

    @Test
    @DisplayName("Bill shipment method \"Delivery\" from the bill card")
    public void billShippedDeliveryToRegionsFromBillCard() throws InterruptedException {
        // Create a bill and pay
        Integer bill_id = createAndReserveBill(getCorrId());
        Integer way_bill = payBill(bill_id, getUsId(), getCorrId());

        // Get the bill number and click on it
        String billNumber = openBillById(bill_id);

        // Check that the "Cancel" button is not active
        getButtonShipped()
                .scrollIntoView(false)
                .shouldBe(visible, ofSeconds(10))
                .shouldHave(attribute("disabled"), ofSeconds(10));
        getButtonShipped().hover(withOffset(10, 10));

        // If a hint appears, click on it
        if (getPopUpSmallButton().has(visible)){
            clickElement(getPopUpSmallButton());
        }

        // Check that the "Send for shipment" button is active
        getButtonShipped()
                .shouldBe(visible, ofSeconds(10))
                .shouldNotHave(attribute("disabled"), ofSeconds(10))
                .click(usingJavaScript());

        // Open the shipment page
        page(DeliveryToRegionsPage.class);

        // Select the type of shipment
        getDeliveryFormTitle().shouldHave(text("Shipment number: " + billNumber));

        // Select the type of shipment
        fillDeliveryToRegionsForm("Free delivery to the terminal");

        // Submit the form
        submitDeliveryToRegionsForm();

        // Check the modal window
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String tomorrowDate = getNextWorkingDay(false);
        checkDeliveryModalWindow(
                "Shipment number: " + billNumber + " from " + todayDate,
                        "Shipment is created successfully. Shipment date is " + tomorrowDate + "."
        );

        // Open the bill page
        page(BillElementPage.class);

        // Check the button "Cancel" i nots active
        getButtonShipped().shouldBe(visible, ofSeconds(10)).shouldHave(attribute("disabled"), ofSeconds(10));

        // Check waybills
        checkWayBills(null);
        
        // Cancel the bill
        deleteWaybill(bill_id, getUsId());
        deletePayment(way_bill, bill_id);
        postBillCancel("correct_token", getCorrInfo(), bill_id, 200);
    }

    private static Stream<Arguments> provideParamsForTestsWithIncorrectAddress() {
        return Stream.of(
                Arguments.of(
                        "attempt to select a terminal in Moscow",
                        "Moscow"
                ),
                Arguments.of(
                        "attempt to select a terminal in St. Petersburg",
                        "St. Petersburg"
                ),
                Arguments.of(
                        "attempt to select a terminal in Kaliningrad",
                        "Kaliningrad"
                ),
                Arguments.of(
                        "attempt to select a terminal in Kazakhstan",
                        "Kazakhstan"
                )
        );
    }
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParamsForTestsWithIncorrectAddress")
    @DisplayName("Bill shipment method \"Delivery\" to the region with an incorrect address")
    public void billShippedDeliveryToRegionWithIncorrectAddress(String description, String address) {
        // Create a bill and pay
        Integer bill_id = createAndReserveBill(getCorrId());
        Integer way_bill = payBill(bill_id, getUsId(), getCorrId());

        // Open the bill page
        openBillById(bill_id);

        // Check that the "Cancel" button is not active
        getButtonShipped()
                .scrollIntoView(false)
                .shouldBe(visible, ofSeconds(10))
                .shouldHave(attribute("disabled"), ofSeconds(10));
        getButtonShipped().hover(withOffset(10, 10));

        // If a hint appears, click on it
        if (getPopUpSmallButton().has(visible)){
            clickElement(getPopUpSmallButton());
        }

        // Check that the "Send for shipment" button is active
        getButtonShipped()
                .shouldBe(visible, ofSeconds(10))
                .shouldNotHave(attribute("disabled"), ofSeconds(10))
                .click(usingJavaScript());

        // Open the shipment page
        page(DeliveryToRegionsPage.class);

        // Select the type of shipment
        getSelectDeliveryType().shouldBe(visible, ofSeconds(10));
        clickElement(getSelectDeliveryType());
        clickElement(getDeliveryTypeOptions().get(3));

        // Select the delivery client
        getSelectDeliveryClient().scrollIntoView(false).shouldBe(visible, ofSeconds(10));
        clickElement(getSelectDeliveryClient());
        clickElement(getDeliveryClientOptions().get(0));

        // Click "Next"
        getDeliveryButtonNext().scrollIntoView(false);
        clickElement(getDeliveryButtonNext());

        // Wait for the map to load
        DeliveryMap.getDeliveryMapPanel().shouldBe(visible, ofSeconds(20));
        DeliveryMap.getDeliveryMapPanelTitle().shouldHave(text("Select the terminal for delivery"));
        DeliveryMap.getDeliveryMapPanelText().shouldHave(text("Delivery to the terminal is not available in the following cities: Moscow, St. Petersburg, Kaliningrad, outside of Russia."));

        // Enter the address
        fillField(DeliveryMap.getInputAddress(), address);

        // Select the first address from the list
        DeliveryMap.getSuggestedAddress().get(0).shouldBe(visible, ofSeconds(20)).scrollIntoView(false);
        clickElement(DeliveryMap.getSuggestedAddress().get(0));

        // Check that the terminal is not available
        DeliveryMap.getTerminalAddresses().get(0).shouldNotBe(visible, ofSeconds(20));

        // Cancel the bill
        deleteWaybill(bill_id, getUsId());
        deletePayment(way_bill, bill_id);
        postBillCancel("correct_token", getCorrInfo(), bill_id, 200);
    }
}
