package model.catalog;

import java.util.List;

public class CategoryFilter {
    private Object filter_id;
    private Object value;
    private List<Object> values;
    private Object min_value;
    private Object max_value;

    public CategoryFilter(Object filter_id, Object value) {
        this.filter_id = filter_id;
        this.value = value;
    }

    public CategoryFilter(Object filter_id, List<Object> values) {
        this.filter_id = filter_id;
        this.values = values;
    }

    public CategoryFilter(Object filter_id, Object min_value, Object max_value) {
        this.filter_id = filter_id;
        this.min_value = min_value;
        this.max_value = max_value;
    }
}
