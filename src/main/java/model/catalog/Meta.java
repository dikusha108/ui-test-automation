package model.catalog;

import java.util.List;

public class Meta {
    private Double min_price;
    private Double max_price;
    private List<Category> material_categories;
    private List<Category> pricelist_categories;
    private List<Category> brands;
    private Object sorted_by;
    private FilteredBy filtered_by;
    private String new_search_string;

    public Meta(
            Double min_price,
            Double max_price,
            List<Category> material_categories,
            List<Category> pricelist_categories,
            List<Category> brands,
            Object sorted_by,
            FilteredBy filtered_by,
            String new_search_string
    ) {
        this.min_price = min_price;
        this.max_price = max_price;
        this.material_categories = material_categories;
        this.pricelist_categories = pricelist_categories;
        this.brands = brands;
        this.sorted_by = sorted_by;
        this.filtered_by = filtered_by;
        this.new_search_string = new_search_string;
    }

    public Meta(Double min_price, Double max_price, Object sorted_by, FilteredBy filtered_by) {
        this.min_price = min_price;
        this.max_price = max_price;
        this.sorted_by = sorted_by;
        this.filtered_by = filtered_by;
    }

    public Meta(Object sorted_by, FilteredBy filtered_by) {
        this.sorted_by = sorted_by;
        this.filtered_by = filtered_by;
    }

    public FilteredBy getFilteredBy() {
        return filtered_by;
    }

    public List<Category> getBrands() {
        return brands;
    }

    public Double getMinPrice() {
        return min_price;
    }

    public Double getMaxPrice() {
        return max_price;
    }
}
