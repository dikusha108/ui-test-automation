package model.warranty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SerialNumberInfo {
    private String serial_number;
    private int exists;
    private String way_bill_number;
    private String way_bill_data;
    private String warranty_date;
    private int warranty_active;

    public String getSerialNumber() {
        return serial_number;
    }

    public int getExists() {
        return exists;
    }

    public String getWayBillNumber() {
        return way_bill_number;
    }

    public String getWayBillData() throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date d = inputFormat.parse(way_bill_data);
        return outputFormat.format(d);
    }

    public String getWarrantyDate() {
        return warranty_date;
    }

    public String getWarrantyActive() {
        if (warranty_active == 1) {
            return "гарантийный";
        } else {
            return "гарантия истекла";
        }
    }

    @Override
    public String toString() {
        try {
            return getSerialNumber() +
                    "\t" + getWayBillData() +
                    "\t" + getWayBillNumber() +
                    "\t" + getWarrantyDate() +
                    "\t" + getWarrantyActive() + "\n";
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
