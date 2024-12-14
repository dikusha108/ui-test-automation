package steps;

import clients.BillApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.bill.PostBillCreateAndReserve;
import model.bill.PostBillCreateAndReserveResponse;
import model.warranty.PostCheckSerials;
import model.warranty.PostCheckWayBills;
import model.warranty.SerialNumberInfo;
import model.warranty.VisitsInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static data.TestParams.getCorrInfo;
import static steps.BaseSteps.*;

public class WarrantySteps {
    @Step("Get warranty status")
    public static List<VisitsInfo> postCheckWayBills(String token, Object corr_info, PostCheckWayBills body, Integer statusCode) {
        Response response = BillApiClient.postCheckWayBills(token, corr_info, body);
        checkStatusCode(response, statusCode);
        return  Arrays.asList(response.body().as(VisitsInfo[].class));
    }

    @Step("Create check repair text")
    public static String createCheckRepairText(List<String> serials) throws IOException {
        // Get warranty status
        List<VisitsInfo> serialsResponse = postCheckWayBills("correct_token", getCorrInfo(), new PostCheckWayBills(serials), 200);

        // Create a string with the necessary information
        StringBuilder repairInfo = new StringBuilder();
        for (VisitsInfo item : serialsResponse) {
            repairInfo.append(item.toString());
        }

        // Fill in the template
        String text = Files.readString(Path.of("./src/test/resources/check_repair_example.txt"));
        text = text.replace("serial_numbers", repairInfo);
        return text;
    }
}
