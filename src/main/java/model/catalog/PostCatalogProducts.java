package model.catalog;

import java.util.List;

public class PostCatalogProducts {
    private Integer corr_poindex = 7;
    private String search_string;
    private List<String> quality;
    private Boolean strict_search = false;
    private List<Integer> products;
    private Integer pricelist_category;
    private Integer price_category_strict;
    private List<Integer> material_categories;
    private List<String> brands;
    private Integer only_in_stock;
    private Double min_price;
    private Double max_price;
    private Integer page = 1;
    private Integer per_page = 50;
    private Integer return_products;
    private Integer return_price_boundaries;
    private Integer return_material_categories;
    private Integer return_price_categories;
    private Integer return_brands;
    private Integer return_dynamic_filters;
    private boolean return_transit_info = true;
    private boolean return_attributes = false;

    public PostCatalogProducts(Integer pricelist_category, List<Integer> material_categories, Integer only_in_stock, Integer per_page, Integer page) {
        this.pricelist_category = pricelist_category;
        this.material_categories = material_categories;
        this.only_in_stock = only_in_stock;
        this.per_page = per_page;
        this.page = page;
    }

    public PostCatalogProducts(Integer corr_poindex, Integer pricelist_category, List<Integer> material_categories, Integer only_in_stock, Integer per_page, Integer page) {
        this.pricelist_category = pricelist_category;
        this.material_categories = material_categories;
        this.only_in_stock = only_in_stock;
        this.per_page = per_page;
        this.page = page;
        this.corr_poindex = corr_poindex;
    }

    public PostCatalogProducts(Integer corr_poindex, Integer pricelist_category, List<Integer> material_categories, List<String> quality, Integer per_page, Integer page) {
        this.pricelist_category = pricelist_category;
        this.material_categories = material_categories;
        this.quality = quality;
        this.per_page = per_page;
        this.page = page;
        this.corr_poindex = corr_poindex;
    }

    public PostCatalogProducts(Integer corr_poindex, String search_string, Boolean strict_search, Integer page, Integer per_page, Integer only_in_stock) {
        this.corr_poindex = corr_poindex;
        this.search_string = search_string;
        this.strict_search = strict_search;
        this.page = page;
        this.per_page = per_page;
        this.only_in_stock = only_in_stock;
    }

    public PostCatalogProducts(Integer corr_poindex, String search_string, Boolean strict_search, Integer page, Integer per_page, List<String> quality) {
        this.corr_poindex = corr_poindex;
        this.search_string = search_string;
        this.strict_search = strict_search;
        this.page = page;
        this.per_page = per_page;
        this.quality = quality;
    }

    public PostCatalogProducts(Integer corr_poindex, String search_string, Integer pricelist_category, List<Integer> material_categories, List<String> brands, Integer only_in_stock, Integer per_page) {
        this.corr_poindex = corr_poindex;
        this.search_string = search_string;
        this.pricelist_category = pricelist_category;
        this.material_categories = material_categories;
        this.brands = brands;
        this.only_in_stock = only_in_stock;
        this.per_page = per_page;
    }

    public PostCatalogProducts(Integer corr_poindex, String search_string, Integer pricelist_category, List<Integer> material_categories, Integer only_in_stock, Double min_price, Double max_price, Integer per_page) {
        this.corr_poindex = corr_poindex;
        this.search_string = search_string;
        this.pricelist_category = pricelist_category;
        this.material_categories = material_categories;
        this.only_in_stock = only_in_stock;
        this.min_price = min_price;
        this.max_price = max_price;
        this.per_page = per_page;
    }
}