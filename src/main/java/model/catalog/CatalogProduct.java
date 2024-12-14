package model.catalog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class CatalogProduct {
    private Integer id;
    private Integer rs_id;
    private Integer pim_id;
    private String pim_uid;
    private String partnumber;
    private String material_name;
    private String model;
    private Double length;
    private Double width;
    private Double height;
    private Double package_weight;
    private Double package_quantity;
    private Double package_volume;
    private Integer warranty;
    private String barcode;
    private Double price;
    private Double price_retail;
    private String currency;
    private Double remain;
    private Double remain_addinf;
    private Integer on_order;
    private Integer pricelist_category_id;
    private Integer material_category_id;
    private Integer brand_id;
    private String brand_name;
    private String retail_name;
    private String html_description;
    private TransitData transit_data;
    private String change_date;
    private Double trcd;
    private Boolean is_call_price;
    private List<String> product_type;
    private Integer days_for_transfer;
    private String days_for_transfer_text;
    private String quality;


    public Object getId() {
        return id;
    }

    public Integer getRsId() {
        return rs_id;
    }

    public String getBrandName() {
        return brand_name;
    }

    public String getPartnumber() {
        return partnumber;
    }

    public String getMaterialName() {
        return material_name;
    }

    public Double getPrice() {
        return price;
    }

    public String getRemain() {
        if (remain_addinf == 2) {
            return ">" + remain.intValue();
        }
        if (remain_addinf == 0) {
            Integer remainInt = remain.intValue();
            return remainInt.toString();
        }
        if (remain_addinf == 3) {
            return "резерв";
        }
        return remain.toString();
    }

    public String getRemainForUnauthorised() {
        if (remain_addinf == 3) {
            return "резерв";
        }
        if (remain > 0) {
            return "есть";
        }
        else {
            return "нет";
        }
    }

    public Integer getBrandId() {
        return brand_id;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public Double getTrcd() {
        return trcd;
    }

    public Integer getPricelistCategoryId() {
        return pricelist_category_id;
    }


    public LocalDateTime getChangeDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        return LocalDateTime.parse(change_date, formatter);
    }

    public TransitData getTransitData() {
        if (transit_data == null) {
            return new TransitData(0, 0.0, 0, null);
        }
        return transit_data;
    }

    public String getQuality() {
        if (quality.equals("Бэдпак (3L)")) return "Некондиция";
        return "";
    }
}