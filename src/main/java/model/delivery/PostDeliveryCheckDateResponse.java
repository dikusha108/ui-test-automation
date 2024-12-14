package model.delivery;

public class PostDeliveryCheckDateResponse {
    private String min_delivery_date;

    public PostDeliveryCheckDateResponse(String min_delivery_date) {
        this.min_delivery_date = min_delivery_date;
    }

    public String getMinDeliveryDate() {
        return min_delivery_date;
    }
}
