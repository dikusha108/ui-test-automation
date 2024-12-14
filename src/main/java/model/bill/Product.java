package model.bill;

public class Product {
    private Object bill_item_id;
    private Object product_id;
    private Object product_number;
    private Object brand_name;
    private Object product_name;
    private Object quantity;
    private Object price;
    private Object currency_abbr;
    private Object product_type;

    public Product(Object product_id, Object quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }
    public Product(Object product_id, Object quantity, String product_type) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.product_type = product_type;
    }

    public Product(Object bill_item_id, Object product_id, Object quantity) {
        this.bill_item_id = bill_item_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public Product(Object product_id, Object product_number, Object brand_name, Object product_name, Object quantity, Object price, Object currency_abbr) {
        this.product_id = product_id;
        this.product_number = product_number;
        this.brand_name = brand_name;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = price;
        this.currency_abbr = currency_abbr;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }
}