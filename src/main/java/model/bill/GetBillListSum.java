package model.bill;

public class GetBillListSum {
    private Integer id;
    private String res;
    private Double total_sum;

    public GetBillListSum(Integer id, String res, Double total_sum) {
        this.id = id;
        this.res = res;
        this.total_sum = total_sum;
    }

    public Double getTotalSum() {
        return total_sum;
    }
}