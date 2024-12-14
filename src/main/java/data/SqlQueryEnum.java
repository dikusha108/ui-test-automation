package data;

public enum SqlQueryEnum {
    uid("3549", "", "firebird"),
    uid2("1752", "", "firebird"),
    uid_offer("12852", "", "firebird"),
    foreign_uid("7495", "", "firebird"),
    corr_id("CORR_ID", "EXECUTE PROCEDURE L3_CAPI_B2B_USERINFO(" + uid.getParam() + ", null, null)", "firebird"),
    corr_id2("CORR_ID", "EXECUTE PROCEDURE L3_CAPI_B2B_USERINFO(" + uid2.getParam() + ", null, null)", "firebird"),
    corr_id_offer("CORR_ID", "EXECUTE PROCEDURE L3_CAPI_B2B_USERINFO(" + uid_offer.getParam() + ", null, null)", "firebird"),
    po_index("po_index", "EXECUTE PROCEDURE L3_CAPI_B2B_USERINFO(" + uid.getParam() + ", null, null)", "firebird"),
    po_index2("po_index", "EXECUTE PROCEDURE L3_CAPI_B2B_USERINFO(" + uid2.getParam() + ", null, null)", "firebird"),
    us_id("US_ID_RESPONSE", "SELECT US_ID_RESPONSE  FROM CORR WHERE CORR_ID = %s;", "firebird"),
    cnt_id("MAX", "SELECT MAX(CNT_ID) FROM CONTRACT c WHERE CORR_ID = %s AND (CNTT_ID = 1 OR CNTT_ID = 12) AND CNT_ISACTIVE = 1", "firebird"),
    cnt_id_offer("MAX", "SELECT MAX(CNT_ID) FROM CONTRACT c WHERE CORR_ID = %s AND CNTT_ID = 12 AND CNT_ISACTIVE = 1", "firebird"),
    cnt_id_dollar("MAX", "SELECT MAX(CNT_ID) FROM CONTRACT c WHERE CORR_ID = %s AND CNTT_ID = 2 AND CNT_ISACTIVE = 1", "firebird"),
    contract_name("CNT_NAME", "SELECT CNT_NAME FROM CONTRACT c WHERE CORR_ID = %s AND CNTT_ID = 1 AND CNT_ISACTIVE = 1", "firebird"),
    contract_name_by_id("CNT_NAME", "SELECT CNT_NAME FROM CONTRACT c WHERE CNT_ID = %s", "firebird"),
    contract_date("CNT_DATE", "SELECT CNT_DATE FROM CONTRACT c WHERE CORR_ID = %s AND CNTT_ID = 1 AND CNT_ISACTIVE = 1", "firebird"),
    incorrect_cnt_id("MAX", "SELECT MAX(CNT_ID) FROM CONTRACT c WHERE CORR_ID = %s AND CNTT_ID != 1 AND CNTT_ID != 2 AND CNT_ISACTIVE = 1", "firebird"),
    foreign_corr_id("CORR_ID", "EXECUTE PROCEDURE L3_CAPI_B2B_USERINFO(" + foreign_uid.getParam() + ", null, null)", "firebird"),
    foreign_cnt_id("MAX", "SELECT MAX(CNT_ID) FROM CONTRACT c WHERE CORR_ID = %s AND CNTT_ID = 1 AND CNT_ISACTIVE = 1", "firebird"),
    foreign_cnt_id_dollar("MAX", "SELECT MAX(CNT_ID) FROM CONTRACT c WHERE CORR_ID = %s AND CNTT_ID = 2 AND CNT_ISACTIVE = 1", "firebird"),
    contact_person("MAX", "select MAX(CORR_ID_OUT) from L3_CAPI_CORR_PERSONS(%s)", "firebird"),
    foreign_contact_person("MAX", "select MAX(CORR_ID_OUT) from L3_CAPI_CORR_PERSONS(%s)", "firebird"),
    sec_user_id("SEC_USER_ID", "SELECT FIRST 1 SEC_USER_ID FROM \"Sec_User\" s " +
            "JOIN SITE_APP_FORM saf ON saf.SEC_USER_CONTACT=s.SEC_USER_ID " +
            "WHERE ID_BITRIX = %s;", "firebird"),
    material_id_1("rs_mat_id", "select mn.rs_mat_id\n" +
            "from material_new mn \n" +
            "join pricelist_pim pp on pp.pim_mat_id = mn.pim_mat_id            \n" +
            "join pricelist_prices pp2 on pp2.rs_mat_id = mn.rs_mat_id\n" +
            "left join pricelist_stocks ps on ps.rs_mat_id = mn.rs_mat_id\n" +
            "join price_category_new pcn on pcn.pim_pc_id = pp.pc_pim_id \n" +
            "join material_category_new mcn on mcn.pim_mct_id = mn.pim_mct_id \n" +
            "join material_brand mb on mb.pim_mat_id  = mn.pim_mat_id\n" +
            "join brand b on b.pim_brand_id = mb.pim_brand_id\n" +
            "where ps.qty>0 and pp2.price_rrc > 10 " +
            //"and ps.distributor_type = '3logic' and pp2.distributor_type = '3logic' and \n" +
            "order by ps.qty desc limit 1;", "catalog"),
    title("MAT_NAME", "SELECT MAT_NAME FROM MATERIAL m2 WHERE MAT_ID = %s", "firebird"),
    material_id_2("rs_mat_id", "select mn.rs_mat_id\n" +
            "from material_new mn \n" +
            "join pricelist_pim pp on pp.pim_mat_id = mn.pim_mat_id            \n" +
            "join pricelist_prices pp2 on pp2.rs_mat_id = mn.rs_mat_id\n" +
            "left join pricelist_stocks ps on ps.rs_mat_id = mn.rs_mat_id\n" +
            "join price_category_new pcn on pcn.pim_pc_id = pp.pc_pim_id \n" +
            "join material_category_new mcn on mcn.pim_mct_id = mn.pim_mct_id \n" +
            "join material_brand mb on mb.pim_mat_id  = mn.pim_mat_id\n" +
            "join brand b on b.pim_brand_id = mb.pim_brand_id\n" +
            "where ps.qty>0 and pp2.price_rrc > 10 " +
            //"and ps.distributor_type = '3logic' and pp2.distributor_type = '3logic' and \n" +
            "order by ps.qty desc limit 1 offset 1;", "catalog"),
    material_id_3("rs_mat_id", "select mn.rs_mat_id\n" +
            "from material_new mn \n" +
            "join pricelist_pim pp on pp.pim_mat_id = mn.pim_mat_id            \n" +
            "join pricelist_prices pp2 on pp2.rs_mat_id = mn.rs_mat_id\n" +
            "left join pricelist_stocks ps on ps.rs_mat_id = mn.rs_mat_id\n" +
            "join price_category_new pcn on pcn.pim_pc_id = pp.pc_pim_id \n" +
            "join material_category_new mcn on mcn.pim_mct_id = mn.pim_mct_id \n" +
            "join material_brand mb on mb.pim_mat_id  = mn.pim_mat_id\n" +
            "join brand b on b.pim_brand_id = mb.pim_brand_id\n" +
            "where ps.qty>0 and pp2.price_rrc > 10 " +
            //"and ps.distributor_type = '3logic' and pp2.distributor_type = '3logic' and \n" +
            "order by ps.qty desc limit 1 offset 2;", "catalog"),
    material_id_4("rs_mat_id", "select mn.rs_mat_id\n" +
            "from material_new mn \n" +
            "join pricelist_pim pp on pp.pim_mat_id = mn.pim_mat_id            \n" +
            "join pricelist_prices pp2 on pp2.rs_mat_id = mn.rs_mat_id\n" +
            "left join pricelist_stocks ps on ps.rs_mat_id = mn.rs_mat_id\n" +
            "join price_category_new pcn on pcn.pim_pc_id = pp.pc_pim_id \n" +
            "join material_category_new mcn on mcn.pim_mct_id = mn.pim_mct_id \n" +
            "join material_brand mb on mb.pim_mat_id  = mn.pim_mat_id\n" +
            "join brand b on b.pim_brand_id = mb.pim_brand_id\n" +
            "where ps.qty=0 " +
            //"and ps.distributor_type = '3logic' and pp2.distributor_type = '3logic' and \n" +
            " limit 1;", "catalog"),
    material_id_5("rs_mat_id", "select mn.rs_mat_id\n" +
            "from material_new mn \n" +
            "join pricelist_pim pp on pp.pim_mat_id = mn.pim_mat_id            \n" +
            "join pricelist_prices pp2 on pp2.rs_mat_id = mn.rs_mat_id\n" +
            "left join pricelist_stocks ps on ps.rs_mat_id = mn.rs_mat_id\n" +
            "join price_category_new pcn on pcn.pim_pc_id = pp.pc_pim_id \n" +
            "join material_category_new mcn on mcn.pim_mct_id = mn.pim_mct_id \n" +
            "join material_brand mb on mb.pim_mat_id  = mn.pim_mat_id\n" +
            "join brand b on b.pim_brand_id = mb.pim_brand_id\n" +
            "where ps.qty=4 " +
            //"and ps.distributor_type = '3logic' and pp2.distributor_type = '3logic' and \n" +
            "order by random() limit 1;", "catalog"),
    material_id_ocs("rs_mat_id", "select mn.rs_mat_id, ps.distributor_type, \n" +
            "pp2.organization_uid, ps.days_for_transfer, ps.qty, pp2.price_rrc \n" +
            "from material_new mn \n" +
            "join pricelist_pim pp on pp.pim_mat_id = mn.pim_mat_id            \n" +
            "join pricelist_prices pp2 on pp2.rs_mat_id = mn.rs_mat_id\n" +
            "left join pricelist_stocks ps on ps.rs_mat_id = mn.rs_mat_id\n" +
            "join price_category_new pcn on pcn.pim_pc_id = pp.pc_pim_id \n" +
            "join material_category_new mcn on mcn.pim_mct_id = mn.pim_mct_id \n" +
            "join material_brand mb on mb.pim_mat_id  = mn.pim_mat_id\n" +
            "join brand b on b.pim_brand_id = mb.pim_brand_id\n" +
            "where ps.distributor_type = 'distributors_v1' and pp2.distributor_type = 'distributors_v1' and ps.qty_in_reserve = 0 and ps.qty>0\n" +
            "order by ps.qty desc limit 1;", "catalog"),
    correspondent_name("CORR_NAME2", "SELECT CORR_NAME2 FROM CORR WHERE CORR_ID = %s", "firebird"),
    correspondent_name_abbr("CORR_NAME2", "SELECT CORR_NAME2 FROM CORR WHERE CORR_ID = %s", "firebird"),
    email("APP_FORM_EMAIL", "EXECUTE PROCEDURE L3_CAPI_B2B_USERINFO(" + uid.getParam() + ", null, null)", "firebird"),
    foreign_transport_company_id("TC_ID_OUT", "SELECT FIRST 1 TC_ID_OUT from L3_CAPI_CORR_TRANS_COMPANIES(%s)", "firebird"),
    foreign_delivery_address("CRM_ADDR_ID_RET", "SELECT FIRST 1 CRM_ADDR_ID_RET from L3_CAPI_CORR_ADDR_LIST(%s)", "firebird"),
    transport_company_id("TC_ID_OUT", "select FIRST 1 TC_ID_OUT from L3_CAPI_CORR_TRANS_COMPANIES(%s)", "firebird"),
    trans_company_address_id("CRM_ADDR_ID_RET", "select FIRST 1 CRM_ADDR_ID_RET from L3_CAPI_CORR_ADDR_LIST(%s);", "firebird"),
    foreign_trans_company_address_id("CRM_ADDR_ID_RET", "select FIRST 1 CRM_ADDR_ID_RET from L3_CAPI_CORR_ADDR_LIST(%s);", "firebird"),
    delivery_address("CRM_ADDR_ID_RET", "select FIRST 1 CRM_ADDR_ID_RET from L3_CAPI_CORR_ADDR_LIST(%s);", "firebird");

    private final String param;
    private final String query;
    private final String db;

    SqlQueryEnum(String param, String query, String db) {
        this.param = param;
        this.query = query;
        this.db = db;
    }

    public String getParam() {
        return param;
    }

    public String getQuery() {
        return query;
    }
    public String getDb() {
        return db;
    }
}