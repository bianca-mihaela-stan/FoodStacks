package Validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Audit.Audit;

public class DataValidator {
    final static String DATE_FORMAT = "yyyy-MM-dd";

    public static Date convertToValidDate(String date) {
        Audit audit = Audit.getInstance("../Data/Audit.csv");
        audit.writeToFile();
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            return df.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    public static String convertDateToString(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    public static String escapeString(String string)
    {
        return "\""+string+"\"";
    }
    public static String formatDateToString(Date date)
    {
        return escapeString(convertDateToString(date));
    }

}
