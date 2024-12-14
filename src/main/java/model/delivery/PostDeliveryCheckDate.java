package model.delivery;

import model.bill.Product;

import java.util.List;

public class PostDeliveryCheckDate {
    private Integer delivery_point_id;
    private List<Product> products;

    public PostDeliveryCheckDate(Integer delivery_point_id, List<Product> products) {
        this.delivery_point_id = delivery_point_id;
        this.products = products;
    }
}