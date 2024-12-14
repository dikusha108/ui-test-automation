package model.bill;

import java.util.List;

public class PostBillCreateAndReserve {
    private Object corr_id;
    private Object contract_id;
    private List<Product> products;
    private boolean allow_side_distributors = true;

    public PostBillCreateAndReserve(Object corr_id, Object contract_id, List<Product> products) {
        this.corr_id = corr_id;
        this.contract_id = contract_id;
        this.products = products;
    }
}