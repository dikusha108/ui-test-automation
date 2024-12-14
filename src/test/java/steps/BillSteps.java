package steps;

import clients.BillApiClient;
import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.bill.GetBillListSum;
import model.bill.PostBillCreateAndReserve;
import model.bill.PostBillCreateAndReserveResponse;
import model.bill.Product;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pagecomponents.*;
import pageobjects.BasketPage;
import pageobjects.BillElementPage;
import pageobjects.BillListPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static steps.BaseSteps.*;
import static com.codeborne.selenide.Selenide.page;
import static config.Config.getBaseOrdersUrl;
import static config.Config.getPersonalOrdersItemUrl;
import static data.TestParams.*;
import static java.time.Duration.ofSeconds;
import static pagecomponents.Cart.getCartButtonCreateBill;
import static pagecomponents.CreateBillTable.*;
import static pagecomponents.DeliveryDate.getEnabledDays;
import static pagecomponents.ModalWindow.getModalWindowContent;
import static pagecomponents.ModalWindow.getModalWindowOKBill;
import static pagecomponents.ModalWindowCreateBill.*;
import static pagecomponents.PersonalAccountHeader.getButtonHeaderCart;
import static pagecomponents.PersonalAccountHeader.getButtonHeaderCartPublic;
import static pageobjects.BasketPage.*;
import static pageobjects.BasketPage.getOptionsCorrespondent;
import static pageobjects.BillElementPage.*;
import static pageobjects.BillElementPage.getButtonCancelBill;
import static pageobjects.BillListPage.*;
import static pageobjects.DeliveryMoscowPage.*;
import static pageobjects.DeliveryMoscowPage.getDeliveryComment;
import static pageobjects.DeliveryTkPage.getCommentForWarehouse;
import static pageobjects.DeliveryToRegionsPage.getDeliveryButtonNext;
import static pageobjects.DeliveryToRegionsPage.getDeliveryButtonPrev;
import static pageobjects.DeliveryToRegionsPage.getConditionsAgreed;
import static pageobjects.PickUpPage.getRadiobuttonKholmogory;
import static pageobjects.ShipmentBasePage.getButtonDeliveryFormPopUpClose;
import static pageobjects.ShipmentBasePage.getButtonSubmit;
import static pageobjects.ShipmentBasePage.getDeliveryFormPopUpSuccess;
import static steps.BaseSteps.checkStatusCode;

public class BillSteps {
    @Step("Check calender and choose date")
    public static void checkCalendarAndChooseDate() {
        // Scroll to the delivery date input
        DeliveryDate.getDeliveryDateInput().scrollIntoView(false);
        DeliveryDate.getDeliveryDateInput().shouldBe(visible, ofSeconds(10));

        // Click on the delivery date input
        clickElement(DeliveryDate.getDeliveryDateInput());
        DeliveryDate.getDeliveryDateCalendar().shouldBe(visible, ofSeconds(10));

        // Check that the current date is disabled
        DeliveryDate.getTodayDate().shouldHave(cssClass("flatpickr-disabled"), ofSeconds(10));

        // Choose the first enabled day
        clickElement(getEnabledDays().get(0));
    }

    @Step("Check delivery modal window")
    public static void checkDeliveryModalWindow(String header, String content) {
        // Check that the modal window is displayed
        getDeliveryFormPopUpSuccess().shouldBe(visible, ofSeconds(20));
        getDeliveryFormPopUpHeader().shouldHave(text(header), ofSeconds(20));

        // Check that the modal window contains the specified text
        getDeliveryFormPopUpText().shouldHave(text(content), ofSeconds(20));

        // Close the modal window
        clickElement(getButtonDeliveryFormPopUpClose());
    }

    private static void fillMaskedInputField(SelenideElement element, String value) {
        ((JavascriptExecutor) WebDriverRunner.getWebDriver()).
                executeScript("arguments[0].value = arguments[1]", element, value);

        ((JavascriptExecutor) WebDriverRunner.getWebDriver()).
                executeScript("var event = new Event('input', { bubbles: true }); arguments[0].dispatchEvent(event);", element);
    }

    @Step("Create a bill")
    public static PostBillCreateAndReserveResponse postBillCreateAndReserve(String token, Object corr_info, PostBillCreateAndReserve body, Integer statusCode, int maxRetries) {
        int attrmpts = 0;
        while (attrmpts < maxRetries) {
            Response response = BillApiClient.postBillCreateAndReserve(token, corr_info, body);
            if (response.statusCode() == statusCode) {
                return response.body().as(PostBillCreateAndReserveResponse.class);
            }
            attrmpts++;
        }
        throw new AssertionError("Couldn't create a bill after " + maxRetries + " attempts");
    }

    @Step("Create a bill")
    public static PostBillCreateAndReserveResponse postBillCreateAndReserveApplication(String token, Object corr_info, PostBillCreateAndReserve body, Integer statusCode) {
        Response response = BillApiClient.postBillCreateAndReserveApplication(token, corr_info, body);
        checkStatusCode(response, statusCode);
        return response.body().as(PostBillCreateAndReserveResponse.class);
    }

    @Step("Get the sum of the bill list")
    public static GetBillListSum getBillListSum(String token, Object corr_info, HashMap<String, Object> queryParams, Integer statusCode) {
        Response response = BillApiClient.getBillListSum(token, corr_info, queryParams);
        checkStatusCode(response, statusCode);
        return response.body().as(GetBillListSum.class);
    }

    @Step("Cancel the bill")
    public static void postBillCancel(String token, Object corr_info, Integer document_id, Integer statusCode) {
        Response response = BillApiClient.postBillCancel(token, corr_info, document_id);
        checkStatusCode(response, statusCode);
    }

    @Step("Create a bill with products")
    public static Integer createAndReserveBill(Integer corr_id) {
        String corr_info = getCorrInfo(corr_id);
        return postBillCreateAndReserve(
                "correct_token",
                corr_info,
                new PostBillCreateAndReserve(corr_id, getContractId(corr_id), List.of(new Product(getMaterialId1(), 100), new Product(getMaterialId2(), 50))),
                200,
                getMaxAttempts()
        ).getId();
    }

    @Step("Pay the bill {bill_id}")
    public static Integer payBill(Integer bill_id, Integer us_id, Integer corr_id) {
        Integer DA_ID = null;
        HashMap<String, Object> queryParamsListSum = new HashMap<>();
        queryParamsListSum.put("bill_id_list", bill_id.toString());

        // Get the sum of the bill list
        String corr_info = getCorrInfo(corr_id);
        double pay_sum = getBillListSum(
                "correct_token",
                corr_info,
                queryParamsListSum,
                200
        ).getTotalSum() + 1000;

        // Create a payment
        // here should be a script to create a payment

        return DA_ID;
    }

    @Step("Delete a waybill")
    public static void deleteWaybill(Integer dd_id, Integer us_id) {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");

            Connection conn = DriverManager.getConnection(getFirebirdUrl(), getFirebirdUsername(), getFirebirdPassword());

            if (conn == null) {
                System.err.println("Could not connect to database");
            } else {
                Statement stmt = conn.createStatement();
                stmt.execute("DELETE FROM WAYBILLS WHERE BILL_ID =" + dd_id + " AND US_ID = " + us_id + ";");
                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            throw new AssertionError("Connection failed:\n" + e.getMessage());
        }
    }

    @Step("Delete a payment")
    public static void deletePayment(Integer da_id, Integer dd_id) {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");

            Connection conn = DriverManager.getConnection(getFirebirdUrl(), getFirebirdUsername(), getFirebirdPassword());

            if (conn == null) {
                System.err.println("Could not connect to database");
            } else {
                Statement stmt = conn.createStatement();

                stmt.executeUpdate("DELETE FROM PAYMENTS WHERE DA_ID = " + da_id + ";");

                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            throw new AssertionError("Connection failed:\n" + e.getMessage());
        }
    }

    @Step("Get next working date in a format dd.MM.yyyy")
    public static String getNextWorkingDay(boolean isForSpbDelivery) {
        String nextWorkingDay = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY) {
            nextWorkingDay = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } if (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) {
            nextWorkingDay = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } if (LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
            nextWorkingDay = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        if (isForSpbDelivery) {
            if (LocalDate.now().getDayOfWeek() == DayOfWeek.TUESDAY) {
                nextWorkingDay = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } else if (LocalDate.now().getDayOfWeek() == DayOfWeek.THURSDAY) {
                nextWorkingDay = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } else if (LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY) {
                nextWorkingDay = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } else if (LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY || LocalDate.now().getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                nextWorkingDay = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
        }
        return nextWorkingDay;
    }

    @Step("Get the next day in a format dd.MM.yyyy")
    public static String getNextDay() {
        String nextWorkingDay = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        return nextWorkingDay;
    }

    @Step("Fill the delivery form")
    public static void fillDeliveryToRegionsForm(String deliveryType) throws InterruptedException {
        // Select the delivery type
        getSelectDeliveryType().shouldBe(visible, ofSeconds(10));
        clickElement(getSelectDeliveryType());
        clickElement(getDeliveryTypeOptions().filter(text(deliveryType)).first());

        // Select the client representative
        getSelectDeliveryClient().scrollIntoView(false).shouldBe(visible, ofSeconds(10));
        clickElement(getSelectDeliveryClient());
        clickElement(getDeliveryClientOptions().get(0));

        // Click on the "Next" button
        getDeliveryButtonNext().scrollIntoView(false);
        clickElement(getDeliveryButtonNext());

        // Wait for the map to load
        DeliveryMap.getDeliveryMapPanel().shouldBe(visible, ofSeconds(20));
        DeliveryMap.getDeliveryMapPanelTitle().shouldHave(text("Укажите адрес доставки"));
        DeliveryMap.getDeliveryMapPanelText().shouldHave(text("Доставка до терминала ТК доступна в пределах России"));

        // Fill in the address
        fillField(DeliveryMap.getInputAddress(), "Тверь");

        // Select the address from the suggestions
        DeliveryMap.getSuggestedAddress().get(0).shouldBe(visible, ofSeconds(20)).scrollIntoView(false);
        clickElement(DeliveryMap.getSuggestedAddress().get(0));

        // Select the terminal
        DeliveryMap.getTerminalAddresses().get(0).shouldBe(exist, ofSeconds(20)).scrollIntoView(false).shouldBe(visible, ofSeconds(20));
        clickElement(DeliveryMap.getTerminalAddresses().get(0));

        // Check that the terminal working hours have appeared
        DeliveryMap.getTerminalWorkTime().scrollIntoView(false).shouldBe(visible, ofSeconds(10));

        // Click on the "Next" button
        getDeliveryButtonPrev().scrollIntoView(false);
        Selenide.executeJavaScript("arguments[0].click();", getDeliveryButtonNext());

        // Select the delivery date
        checkCalendarAndChooseDate();
    }

    @Step("Check the waybills")
    public static void checkWayBills(String modalWindowContent){
        // Open the list of waybills
        getButtonShowWayBills().shouldNotHave(attribute("disabled"), ofSeconds(10));
        clickElement(getButtonShowWayBills());

        // Check that the modal window is displayed
        getModalWindowContent().shouldHave(text("Waybill list"), ofSeconds(30));
        if (modalWindowContent != null) {
            getModalWindowContent().shouldHave(text(modalWindowContent), ofSeconds(30));
        }

        // Close the modal window
        clickElement(getModalWindowOKBill());
    }

    @Step("Open the bill by ID")
    public static String openBillById(Integer bill_id){
        open(getPersonalOrdersItemUrl(bill_id), BillElementPage.class);
        page(BillElementPage.class);
        disableAnimation();
        String billNumber = getBreadcrumbsItems().get(getBreadcrumbsItems().size() - 1).getText();

        return billNumber;
    }

    @Step("Submit the delivery form")
    public static void submitDeliveryToRegionsForm() {
        // because of deadlocks in the system, we need to try to submit the form several times
        final int MAX_ATTEMPTS = getMaxAttempts();
        int attempt = 0;
        boolean isSuccess = false;

        while (attempt < MAX_ATTEMPTS && !isSuccess) {
            attempt++;

            // Click on the "Submit" button
            getButtonSubmit().scrollIntoView(false);
            getButtonSubmit().shouldBe(visible, Duration.ofSeconds(10));
            clickElement(getButtonSubmit());

            // Check the appearance of the message about the successful creation of the waybill
            if (getDeliveryFormPopUpSuccess().is(visible)) {
                isSuccess = true;
            }

            // If an error message appears, close the error message
            if (!isSuccess && attempt < MAX_ATTEMPTS) {
                getButtonDeliveryFormPopUpCloseError().shouldBe(visible, Duration.ofSeconds(30));
                clickElement(getButtonDeliveryFormPopUpCloseError());

                // Click on the "Next" button
                getDeliveryButtonNext().scrollIntoView(false);
                clickElement(getDeliveryButtonNext());

                // Wait for the map to load
                DeliveryMap.getDeliveryMapPanel().shouldBe(visible, ofSeconds(20));
                DeliveryMap.getDeliveryMapPanelTitle().shouldHave(text("Укажите адрес доставки"));
                DeliveryMap.getDeliveryMapPanelText().shouldHave(text("Доставка до терминала ТК доступна в пределах России"));

                // Fill in the address
                fillField(DeliveryMap.getInputAddress(), "Тверь");

                // Select the address from the suggestions
                DeliveryMap.getSuggestedAddress().get(0).shouldBe(visible, ofSeconds(20)).scrollIntoView(false);
                clickElement(DeliveryMap.getSuggestedAddress().get(0));

                // Click on the terminal
                DeliveryMap.getTerminalAddresses().get(0).shouldBe(exist, ofSeconds(20)).scrollIntoView(false).shouldBe(visible, ofSeconds(20));
                clickElement(DeliveryMap.getTerminalAddresses().get(0));

                // Check that the terminal working hours have appeared
                DeliveryMap.getTerminalWorkTime().scrollIntoView(false).shouldBe(visible, ofSeconds(10));

                // Click on the "Next" button
                getDeliveryButtonPrev().scrollIntoView(false);
                Selenide.executeJavaScript("arguments[0].click();", getDeliveryButtonNext());

                // Choose the delivery date
                checkCalendarAndChooseDate();
            }
        }

        if (!isSuccess) {
            throw new RuntimeException("Couldn't create a shipment after " + MAX_ATTEMPTS + " attempts.");
        }
    }

    @Step("Select bill by number {billNumber}")
    public static void selectBillByNumber(
            String billNumber,
            ElementsCollection billListNumbers,
            ElementsCollection billListItems,
            String className
    ) {
        // Wait for the list of bills to load
        billListNumbers.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));

        // Check that the number of bills and items is the same
        if (billListNumbers.size() != billListItems.size()) {
            throw new RuntimeException("Sizes are different: billListNumbers.size=" +
                    billListNumbers.size() + ", billListItems.size=" + billListItems.size());
        }

        // Get the list of bill numbers
        List<String> billsSnapshot = billListNumbers.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());

        // Find the bill by number
        for (int i = 0; i < billsSnapshot.size(); i++) {
            if (billsSnapshot.get(i).equals(billNumber)) {
                billListItems.get(i).scrollIntoView(false);
                clickElement(billListItems.get(i));
                billListItems.get(i).shouldHave(cssClass(className), Duration.ofSeconds(10));
                return;
            }
        }

        // If the bill is not found, throw an exception
        throw new RuntimeException("Bill " + billNumber + " was not found in the list: " + billsSnapshot);
    }
}