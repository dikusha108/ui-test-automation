package data;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestParams {
    private static Connection connectToFirebirdDb() {
        try {
            return DriverManager.getConnection(firebirdUrl, firebirdUsername, firebirdPassword);
        } catch (Exception e) {
            throw new AssertionError("Connection to firebird failed:\n" + e.getMessage());
        }
    }

    private static Connection connectToCatalogDb() {
        try {
            return DriverManager.getConnection(getPostgresqlCatalogUrl(), getPostgresqlCatalogUsername(), getPostgresqlCatalogPassword());
        } catch (Exception e) {
            throw new AssertionError("Connection to postgresql_catalog failed:\n" + e.getMessage());
        }
    }

    private static String getParam(SqlQueryEnum sqe) {
        return getParam(sqe.getParam(), sqe.getQuery(), sqe.getDb());
    }

    private static String getParam(String param, String query, String db) {
        Connection conn = connectToFirebirdDb();
        if (db.equals("catalog")) {
            conn = connectToCatalogDb();
        }

        try {
            ResultSet rs = conn.createStatement().executeQuery(query);
            rs.next();
            return rs.getString(param);
        } catch (Exception e){
            throw new AssertionError(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static Dotenv dotenv = Dotenv.load();
    private static final String login = dotenv.get("LOGIN");
    private static final String login2 = dotenv.get("LOGIN2");
    private static final Integer uid = Integer.parseInt(dotenv.get("UID"));
    private static final boolean trirdPartyDistributorsAllow = dotenv.get("TRIRD_PARTY_DISTRIBUTORS_ALLOW").equals("true");
    private static final Integer uid2 = Integer.parseInt(SqlQueryEnum.uid2.getParam());
    private static final Integer uid_offer = Integer.parseInt(SqlQueryEnum.uid_offer.getParam());
    private static final String blockedLogin = dotenv.get("BLOCKED_LOGIN");
    private static final String loginWithDisabledForm = dotenv.get("DISABLED_LOGIN");
    private static final String loginWithNewForm = dotenv.get("NEW_LOGIN");
    private static final String loginOffer = dotenv.get("OFERTA_LOGIN");
    private static final String password = dotenv.get("PASSWORD");
    private static final String commonPassword = dotenv.get("COMMON_PASSWORD");
    private static final String firebirdUrl = dotenv.get("FIREBIRD_URL");
    private static final String firebirdUsername = dotenv.get("FIREBIRD_USERNAME");
    private static final String firebirdPassword = dotenv.get("FIREBIRD_PASSWORD");
    private static final String postgresql_catalog_url = dotenv.get("POSTGRESQL_CATALOG_URL");
    private static final String postgresql_catalog_username = dotenv.get("POSTGRESQL_CATALOG_USERNAME");
    private static final String postgresql_catalog_password = dotenv.get("POSTGRESQL_CATALOG_PASSWORD");
    private static final String subCategoryMotherboardsLink = dotenv.get("subCategoryMotherboardsLink");
    private static final String subCategoryVideoCardsLink = dotenv.get("subCategoryVideoCardsLink");
    private static final String subCategoryProcessorsLink = dotenv.get("subCategoryProcessorsLink");
    private static final String subCategory1Link = dotenv.get("subCategory1Link");
    private static final String subCategory2Link = dotenv.get("subCategory2Link");
    private static final String subCategory3Link = dotenv.get("subCategory3Link");
    private static final Integer minDeliverySum = Integer.parseInt(dotenv.get("MIN_DELIVERY_SUM"));
    private static final Integer maxAttempts = Integer.parseInt(dotenv.get("MAX_ATTEMPTS"));
    private static final String bill_token = "Bearer " + dotenv.get("BILL_API_TOKEN");
    private static final String cart_token = "Bearer " + dotenv.get("CART_API_TOKEN");
    private static final String catalog_token = "Bearer " + dotenv.get("CATALOG_API_TOKEN");
    private static final String b2b_token = "Bearer " + dotenv.get("B2B_API_TOKEN");
    private static final String delivery_token = "Bearer " + dotenv.get("DELIVERY_API_TOKEN");
    private static final String corr_info = "dlCyJXAZ/O2ik4uTBBAc9Pqcq5m48h1V3CY0DmWJLo0=";
    private static final String corr_info2 = "oa2BjlXG4+mKmYWXIbw4Jhf0SlRSiS8ANeS4ZRAWVIE=";
    private static final String corr_info_oferta = "KYKuJ6fcRcw20zdJM7y5+f57GW47Nd4HfMTzaC7/zIw=";
    private static final String fullName = "Васкецов Александр Александрович";
    private static final String items = "55735 1\n95817 1\n55718 1";
    private static final String saintPetersburgAddress = "Санкт-Петербург Псковская ул. 10-12";
    private static final String kaliningradAddress = "ул. Житомирская, 22, Калининград";
    private static final String regionsAddress = "г. Владимир, ул. Горького, д. 50";
    private static final String incorrectAddress = "Москвоаоаоао";
    private static final String moscowAddress = "Московская область г. Люберцы ул. Красная д.1 Лит.Ф";
    private static final Integer priceListCategory = 5521;
    private static final List<Integer> materialCategory = List.of(979293, 979382, 979446);
    private static final Integer corr_id = Integer.parseInt(getParam(SqlQueryEnum.corr_id));
    private static final Integer corr_id2 = Integer.parseInt(getParam(SqlQueryEnum.corr_id2));
    private static final Integer corr_id_offer = Integer.parseInt(getParam(SqlQueryEnum.corr_id_offer));
    private static final Integer po_index = Integer.parseInt(getParam(SqlQueryEnum.po_index));
    private static final Integer po_index2 = Integer.parseInt(getParam(SqlQueryEnum.po_index2));
    private static final Integer material_id_1 = Integer.parseInt(getParam(SqlQueryEnum.material_id_1));
    private static final Integer material_id_2 = Integer.parseInt(getParam(SqlQueryEnum.material_id_2));
    private static final Integer material_id_3 = Integer.parseInt(getParam(SqlQueryEnum.material_id_3));
    private static final Integer material_id_4 = Integer.parseInt(getParam(SqlQueryEnum.material_id_4));
    private static final Integer material_id_5 = Integer.parseInt(getParam(SqlQueryEnum.material_id_5));
    private static final Integer material_id_ocs = trirdPartyDistributorsAllow ? Integer.parseInt(getParam(SqlQueryEnum.material_id_ocs)) : null;
    private static final Integer cnt_id = Integer.parseInt(
            getParam(SqlQueryEnum.cnt_id.getParam(), String.format(SqlQueryEnum.cnt_id.getQuery(), getCorrId()), "firebird")
    );
    private static final Integer us_id = Integer.parseInt(getParam(SqlQueryEnum.us_id.getParam(), String.format(SqlQueryEnum.us_id.getQuery(), getCorrId()), "firebird"));
    private static final Integer sec_user_id = Integer.parseInt(getParam(SqlQueryEnum.sec_user_id.getParam(), String.format(SqlQueryEnum.sec_user_id.getQuery(), getUidOffer()), "firebird"));

    private static final List<String> validSerialNumbers = List.of("F8M0XB024804","E063DA892657","18187179000014101570","18257164000017690882",
            "MPX6001ACABW1180801640","J6D0AP056536","MPY7501AFAAG1183400577");
    private static final List<String> validWayBills = List.of("12","13","14");
    public static String getLogin() {
        return login;
    }
    public static String getLogin2() {
        return login2;
    }
    public static Integer getUid() {
        return uid;
    }
    public static Integer getUidOffer() {
        return uid_offer;
    }
    public static Integer getUid2() {
        return uid2;
    }
    public static Integer getSecUserId() {
        return sec_user_id;
    }
    public static String getPassword() {
        return password;
    }
    public static String getFullName() {
        return fullName;
    }
    public static String getSaintPetersburgAddress() {
        return saintPetersburgAddress;
    }
    public static String getKaliningradAddress() { return kaliningradAddress;}
    public static String getRegionsAddress() {return regionsAddress;}
    public static String getIncorrectAddress() {return incorrectAddress;}
    public static String getMoscowAddress() {return moscowAddress;}
    public static String getBlockedLogin() {
        return blockedLogin;
    }
    public static String getCommonPassword() {return commonPassword;}
    public static String getSubCategoryMotherboardsLink() {return subCategoryMotherboardsLink;}
    public static String getSubCategory1Link() {return subCategory1Link;}

    public static String getLoginWithDisabledForm() {
        return loginWithDisabledForm;
    }
    public static String getLoginWithNewForm() {
        return loginWithNewForm;
    }
    public static String getLoginOffer() {
        return loginOffer;
    }
    public static String getItems() {
        return material_id_1 + " 1\n" + material_id_2 + " 1\n" + material_id_3 + " 1";
    }
    public static String getFirebirdUrl() { return firebirdUrl; }
    public static String getFirebirdUsername() { return firebirdUsername; }
    public static String getFirebirdPassword() { return firebirdPassword; }
    public static String getPostgresqlCatalogUrl() {
        return postgresql_catalog_url;
    }

    public static String getPostgresqlCatalogUsername() {
        return postgresql_catalog_username;
    }

    public static String getPostgresqlCatalogPassword() {
        return postgresql_catalog_password;
    }
    public static Integer getCorrId() {
        return corr_id;
    }
    public static Integer getCorrId2() {
        return corr_id2;
    }
    public static Integer getCorrIdOffer() {
        return corr_id_offer;
    }
    public static Integer getPoIndex() {
        return po_index;
    }
    public static Integer getPoIndex2() {
        return po_index2;
    }
    public static String getBillToken() {return bill_token;}
    public static String getCartToken() {return cart_token;}
    public static String getCatalogToken() {return catalog_token;}
    public static String getB2bToken() {return b2b_token;}
    public static String getCorrInfo() {return corr_info;}
    public static String getCorrInfo(Integer corr_id) {
        String corr_info = getCorrInfo();
        if (corr_id == getCorrId2()) corr_info = getCorrInfo2();
        if (corr_id == getCorrIdOffer()) corr_info = getCorrInfoOffer();
        return corr_info;
    }
    public static String getCorrInfo2() {return corr_info2;}
    public static String getCorrInfoOffer() {return corr_info_oferta;}

    public static Integer getMaterialId1() {
        return material_id_1;
    }

    public static Integer getMaterialId2() {
        return material_id_2;
    }

    public static Integer getMaterialId3() {
        return material_id_3;
    }
    public static Integer getMaterialId4() {
        return material_id_4;
    }
    public static Integer getMaterialId5() {
        return material_id_5;
    }
    public static Integer getMaterialIdOcs() {
        return material_id_ocs;
    }
    public static Integer getContractId()  { return  cnt_id; }
    public static Integer getContractIdDollar(Integer corr_id)  {
        return Integer.parseInt(getParam(SqlQueryEnum.cnt_id_dollar.getParam(), String.format(SqlQueryEnum.cnt_id_dollar.getQuery(), corr_id), "firebird"));
    }
    public static Integer getUsId()  { return  us_id; }
    public static Integer getUsId(Integer corr_id)  {
        return Integer.parseInt(getParam(SqlQueryEnum.us_id.getParam(), String.format(SqlQueryEnum.us_id.getQuery(), corr_id), "firebird"));
    }
    public static Integer getContactPersonId(Integer corr_id)  {
        return Integer.parseInt(getParam(SqlQueryEnum.contact_person.getParam(), String.format(SqlQueryEnum.contact_person.getQuery(), corr_id), "firebird"));
    }
    public static Integer getContractId(Integer corr_id)  {
        return Integer.parseInt(getParam(SqlQueryEnum.cnt_id.getParam(), String.format(SqlQueryEnum.cnt_id.getQuery(), corr_id), "firebird"));
    }
    public static Integer getOfferContractId(Integer corr_id)  {
        return Integer.parseInt(getParam(SqlQueryEnum.cnt_id_offer.getParam(), String.format(SqlQueryEnum.cnt_id_offer.getQuery(), corr_id), "firebird"));
    }
    public static Integer getCorrId(Integer id_bitrix)  {
        return Integer.parseInt(getParam(SqlQueryEnum.corr_id.getParam(), String.format(SqlQueryEnum.corr_id.getQuery(), id_bitrix), "firebird"));
    }

    public static Integer getMinDeliverySum() {
        return minDeliverySum;
    }
    public static Integer getMaxAttempts() {
        return maxAttempts;
    }
    public static String getDeliveryToken() {return delivery_token;}
    public static List<String> getValidSerialNumbers() {
        return validSerialNumbers;
    }
    public static List<String> getValidWayBills() {
        return validWayBills;
    }

    public static boolean checkThirdPartyDistributorsAllow() {
        return trirdPartyDistributorsAllow;
    }

    public static Integer getPriceListCategory() {
        return priceListCategory;
    }

    public static List<Integer> getMaterialCategory() {
        return materialCategory;
    }
}
