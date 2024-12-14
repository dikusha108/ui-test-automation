package model.catalog;

public class TransitData {
    private Integer mat_id;
    private Double transit_quantity;
    private Integer transit_quantity_addinf;
    private String arrival;

    public TransitData(Integer mat_id, Double transit_quantity, Integer transit_quantity_addinf, String arrival) {
        this.mat_id = mat_id;
        this.transit_quantity = transit_quantity;
        this.transit_quantity_addinf = transit_quantity_addinf;
        this.arrival = arrival;
    }

    public String getArrival() {
        if (arrival == null) {
            return "Нет";
        } else return arrival;
    }
}
