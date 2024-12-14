package clients;


import model.bill.PostBillCreateAndReserve;
import io.restassured.response.Response;
import model.warranty.PostCheckSerials;
import model.warranty.PostCheckWayBills;

import java.util.HashMap;

import static clients.BaseHttpClient.doGetRequest;
import static clients.BaseHttpClient.doPostRequest;
import static data.TestParams.dotenv;
import static data.TestParams.getBillToken;

public class BillApiClient {
    private static final String BASE_URL = dotenv.get("BILL_API_URL");

    private static String getToken(String token) {
        if (token.equals("correct_token")) {
            return getBillToken();
        } else {
            return null;
        }
    }

    public static Response postBillCreateAndReserve(String token, Object corr_info, PostBillCreateAndReserve body) {
        return doPostRequest(getToken(token), corr_info, BASE_URL + "/api/bill/create_and_reserve", body);
    }
    public static Response postBillCreateAndReserveApplication(String token, Object corr_info, PostBillCreateAndReserve body) {
        return doPostRequest(getToken(token), corr_info, BASE_URL + "/api/bill/create_and_reserve/application", body);
    }

    public static Response getBillListSum(String token, Object corr_info, HashMap<String, Object> queryParams) {
        return doGetRequest(getToken(token), corr_info, BASE_URL + "/api/bill/list_sum", queryParams);
    }

    public static Response postBillCancel(String token, Object corr_info, Object bill_id) {
        return doPostRequest(getToken(token), corr_info, BASE_URL + "/api/bill/" + bill_id + "/cancel");
    }

    public static Response postCheckSerials(String token, Object corr_info, PostCheckSerials body) {
        return doPostRequest(getToken(token), corr_info, BASE_URL + "/api/way_bill/warranty/check_serials", body);
    }

    public static Response postCheckWayBills(String token, Object corr_info, PostCheckWayBills body) {
        return doPostRequest(getToken(token), corr_info, BASE_URL + "/api/way_bill/warranty/check_waybills", body);
    }
}