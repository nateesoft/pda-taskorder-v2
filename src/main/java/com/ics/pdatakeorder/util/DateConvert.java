package com.ics.pdatakeorder.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Dell-Softpos
 */
public class DateConvert {

    public String dateGetToShow(String date) {
        date = date.replace("-", "");
        String yyyy = date.substring(0, 4);
        String mm = date.substring(4, 6);
        String dd = date.substring(6, 8);
        date = dd + "/" + mm + "/" + yyyy;
        return date;
    }

    public String GetCurrentDate() {
        SimpleDateFormat GetLocalDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String DateOrder = "";
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, +2);
        Calendar current1 = Calendar.getInstance();
        current1.add(Calendar.DATE, +0);
        DateOrder += GetLocalDate.format(current1.getTime());
        
        return DateOrder;
    }

    public String GetCurrentTime() {
        SimpleDateFormat ShowDatefmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        String TimeString = "";
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, 0);
        TimeString += ShowDatefmt.format(current.getTime());
        
        return TimeString;
    }

}
