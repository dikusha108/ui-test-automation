package config;

import static data.TestParams.dotenv;

public class Config {
    private static final String baseUrl = dotenv.get("BASE_URL");
    private static final String baseOrdersUrl = dotenv.get("BASE_URL") + "/personal/orders/";
    private static final String personalOrdersItemUrl = dotenv.get("BASE_URL") + "/personal/orders/item/";
    private static final String catalogCategoryUrl = dotenv.get("BASE_URL") + "/catalog/kabeli-razyemy-perekhodniki-5515/kabeli-dlya-lokalnoy-seti-5521/";

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getBaseOrdersUrl() {
        return baseOrdersUrl;
    }
    public static String getPersonalOrdersItemUrl(Integer bill_id) {
        return personalOrdersItemUrl + bill_id + "/";
    }
    public static String getCatalogCategoryUrl() {
        return catalogCategoryUrl;
    }
}
