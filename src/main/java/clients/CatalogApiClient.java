package clients;


import io.restassured.response.Response;
import model.bill.PostBillCreateAndReserve;
import model.catalog.PostCatalogProducts;

import java.util.HashMap;

import static clients.BaseHttpClient.doGetRequest;
import static clients.BaseHttpClient.doPostRequest;
import static data.TestParams.*;

public class CatalogApiClient {
    private static final String BASE_URL = dotenv.get("CATALOG_API_URL");

    private static String getToken(String token) {
        if (token.equals("correct_token")) {
            return getCatalogToken();
        } else {
            return null;
        }
    }
    public static Response getCommonCurrencyUsd(String token) {
        return doGetRequest(getToken(token), BASE_URL + "/api/common/currency/usd");
    }
    public static Response postCatalogProducts(String token, PostCatalogProducts body) {
        return doPostRequest(getToken(token), BASE_URL + "/api/catalog/products", body);
    }
}