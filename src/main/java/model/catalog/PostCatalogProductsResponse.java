package model.catalog;

import java.util.List;

public class PostCatalogProductsResponse {
    private Integer count;
    private Integer per_page;
    private Integer pages;
    private Integer page;
    private List<CatalogProduct> data;
    private Meta meta;

    public List<CatalogProduct> getData() {
        return data;
    }

    public Meta getMeta() {
        return meta;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getPages() {
        return pages;
    }
}