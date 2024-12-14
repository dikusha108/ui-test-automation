package clients;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.mapper.ObjectMapperType.GSON;

public class BaseHttpClient {
    private static final String AUTHORIZATION = "Authorization";
    private static final String CORR_INFO = "CorrInfo";
    private static final String UID = "uid";
    private static final String LOG_TRACKER = "Log-Tracker";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String JSON = "application/json";

    private static final RestAssuredConfig config = RestAssuredConfig.newConfig()
            .objectMapperConfig(new ObjectMapperConfig(GSON))
            .sslConfig(new SSLConfig().relaxedHTTPSValidation())
            .redirect(new RedirectConfig().followRedirects(true));

    public static Response doGetRequest(String token, String uri) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(AUTHORIZATION, token)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .get(uri);
    }

    public static Response doGetRequest(String token, Object corr_info, String uri) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(AUTHORIZATION, token)
                .header(CORR_INFO, corr_info)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .get(uri);
    }

    public static Response doGetRequest(String token, Integer uid, String uri) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(AUTHORIZATION, token)
                .header(UID, uid)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .get(uri);
    }

    public static Response doGetRequest(String token, String uri, HashMap<String, Object> queryParams) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(AUTHORIZATION, token)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .queryParams(queryParams)
                .get(uri);
    }

    public static Response doGetRequest(String token, Object corr_info, String uri, HashMap<String, Object> queryParams) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(AUTHORIZATION, token)
                .header(CORR_INFO, corr_info)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .queryParams(queryParams)
                .get(uri);
    }

    public static Response doGetRequest(String token, Integer uid, String uri, HashMap<String, Object> queryParams) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(AUTHORIZATION, token)
                .header(UID, uid)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .queryParams(queryParams)
                .get(uri);
    }

    public static Response doPostRequest(String token, Object corr_info, String uri) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(CONTENT_TYPE, JSON)
                .header(AUTHORIZATION, token)
                .header(CORR_INFO, corr_info)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .post(uri);
    }

    public static Response doPostRequest(String token, String uri, Object body) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(CONTENT_TYPE, JSON)
                .header(AUTHORIZATION, token)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .body(body)
                .post(uri);
    }

    public static Response doPostRequest(String token, Object corr_info, String uri, Object body) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(CONTENT_TYPE, JSON)
                .header(AUTHORIZATION, token)
                .header(CORR_INFO, corr_info)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .body(body)
                .post(uri);
    }

    public static Response doPostRequest(String token, Integer uid, String uri, Object body) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(CONTENT_TYPE, JSON)
                .header(AUTHORIZATION, token)
                .header(UID, uid)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .body(body)
                .post(uri);
    }

    public static Response doPostRequest(String token, String uri) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(CONTENT_TYPE, JSON)
                .header(AUTHORIZATION, token)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .post(uri);
    }

    public static Response doPatchRequest(String token, String uri, Object body) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(CONTENT_TYPE, JSON)
                .header(AUTHORIZATION, token)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .body(body)
                .patch(uri);
    }

    public static Response doPatchRequest(String token, Object corr_info, String uri, Object body) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(CONTENT_TYPE, JSON)
                .header(AUTHORIZATION, token)
                .header(CORR_INFO, corr_info)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .body(body)
                .patch(uri);
    }

    public static Response doPutRequest(String token, Object corr_info, String uri, Object body) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(CONTENT_TYPE, JSON)
                .header(AUTHORIZATION, token)
                .header(CORR_INFO, corr_info)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .body(body)
                .put(uri);
    }

    public static Response doDeleteRequest(String token, String uri) {
        return given().config(config)
                .filter(new AllureRestAssured())
                .header(CONTENT_TYPE, JSON)
                .header(AUTHORIZATION, token)
                .header(LOG_TRACKER, "autotest-" + RandomStringUtils.randomAlphabetic(30))
                .delete(uri);
    }
}