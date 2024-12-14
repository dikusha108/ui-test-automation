package model.warranty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisitsInfo {
    private int visit_id;
    private int document_id;
    private String document_date;
    private String serial_num;
    private String repair_status;

    @Override
    public String toString() {
        return document_id + ".0" +
                "\t" + document_date +
                "\t" + serial_num +
                "\t" + repair_status + "\n";
    }
}
