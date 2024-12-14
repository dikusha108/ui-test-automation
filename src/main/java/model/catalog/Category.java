package model.catalog;

public class Category {
    private Integer id;
    private String name;
    private Integer product_count;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getProductCount() {
        return product_count;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", product_count=" + product_count +
                '}';
    }
}
