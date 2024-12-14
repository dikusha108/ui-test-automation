package model.catalog;

import java.util.List;

public class FilteredBy {
    private Object products;
    private Object pricelist_category;
    private Object material_categories;
    private Object brands;
    private Object only_in_stock;
    private Object min_price;
    private Object max_price;
    private List<CategoryFilter> category_filters;
    private String search_string;

    public FilteredBy(
            Object products,
            Object pricelist_category,
            Object material_categories,
            Object brands,
            Object only_in_stock,
            Object min_price,
            Object max_price,
            List<CategoryFilter> category_filters,
            String search_string
    ) {
        this.products = products;
        this.pricelist_category = pricelist_category;
        this.material_categories = material_categories;
        this.brands = brands;
        this.only_in_stock = only_in_stock;
        this.min_price = min_price;
        this.max_price = max_price;
        this.category_filters = category_filters;
        this.search_string = search_string;
    }

    public String getSearchString() {
        return search_string;
    }
}