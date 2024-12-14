package steps;

import clients.CatalogApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.catalog.GetCommonCurrencyUsd;
import model.catalog.PostCatalogProducts;
import model.catalog.PostCatalogProductsResponse;

import static steps.BaseSteps.checkStatusCode;

public class CatalogApiSteps {
    @Step("Get currency rate from Catalog API")
    public static Double getCommonCurrencyUsd() {
        Response response = CatalogApiClient.getCommonCurrencyUsd("correct_token");
        checkStatusCode(response, 200);
        return response.body().as(GetCommonCurrencyUsd.class).getRate();
    }

    @Step("Get product list from Catalog API")
    public static PostCatalogProductsResponse postCatalogProducts(PostCatalogProducts body) {
        Response response = CatalogApiClient.postCatalogProducts("correct_token", body);
        checkStatusCode(response, 200);
        return response.body().as(PostCatalogProductsResponse.class);
    }
}
