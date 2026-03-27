/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ics.pdatakeorder.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author Dell-Softpos
 */
public class DateConvert {

    public String dateDatabase(String date) {
        date = date.replace("/", "");
        String dd = date.substring(0, 2);
        String mm = date.substring(2, 4);
        String yyyy = date.substring(4, 8);
        date = yyyy + "-" + mm + "-" + dd;
        System.out.println(date);
        return date;
        
    }

    public String dateForErp(String date) {
        date = date.trim().replace("-", "");
        String dd = date.substring(0, 2);
        String mm = date.substring(2, 4);
        String yyyy = date.substring(4, 8);
        date = dd + mm + yyyy;
        System.out.println(date);
        return date;
    }

    public String dateGetToShow(String date) {
        date = date.replace("-", "");
        String yyyy = date.substring(0, 4);
        String mm = date.substring(4, 6);
        String dd = date.substring(6, 8);
        date = dd + "/" + mm + "/" + yyyy;
//        System.out.println(date);
        return date;
    }

    public String GetCurrentDate() {
        SimpleDateFormat GetLocalDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar c = new GregorianCalendar();
        int MM = c.get(Calendar.MONTH);
        int yyyy = c.get(Calendar.YEAR);
        int dd = c.get(Calendar.DATE);
        String dateString = "";
        String DateOrder = "";
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, +2);
        dateString += ShowDatefmt.format(current.getTime());
        Calendar current1 = Calendar.getInstance();
        current1.add(Calendar.DATE, +0);
        DateOrder += GetLocalDate.format(current1.getTime());
        System.out.println(DateOrder);
        //txtDate1.setText(dateString);
        return DateOrder;
    }
    public String dateForErpInsertDate() {
        SimpleDateFormat GetLocalDate = new SimpleDateFormat("ddMMyyyy", Locale.ENGLISH);
        SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar c = new GregorianCalendar();
        int MM = c.get(Calendar.MONTH);
        int yyyy = c.get(Calendar.YEAR);
        int dd = c.get(Calendar.DATE);
        String dateString = "";
        String DateOrder = "";
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, +2);
        dateString += ShowDatefmt.format(current.getTime());
        Calendar current1 = Calendar.getInstance();
        current1.add(Calendar.DATE, +0);
        DateOrder += GetLocalDate.format(current1.getTime());
        System.out.println(DateOrder);
        //txtDate1.setText(dateString);
        return DateOrder.replace("/", "");
    }

    public String GetCurrentTime() {
        SimpleDateFormat ShowDatefmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        Calendar c = new GregorianCalendar();
        String TimeString = "";
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, 0);
        TimeString += ShowDatefmt.format(current.getTime());
        System.out.println(TimeString);
        //txtDate1.setText(dateString);
        return TimeString;
    }

    public String GetCurrentDateEE() {
        SimpleDateFormat GetLocalDate = new SimpleDateFormat("EE", Locale.ENGLISH);
        Calendar c = new GregorianCalendar();
        int MM = c.get(Calendar.MONTH);
        int yyyy = c.get(Calendar.YEAR);
        int dd = c.get(Calendar.DATE);
        String dateString = "";
        String DateOrder = "";
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, +2);
        Calendar current1 = Calendar.getInstance();
        current1.add(Calendar.DATE, +0);
        DateOrder += GetLocalDate.format(current1.getTime());
        System.out.println(dateString);
        //txtDate1.setText(dateString);
        return DateOrder;
    }

    public String minusDate(String dateInput, int i) {
        String[] dateStr = dateInput.split("-");//2016-12-31
        int yyyy = Integer.parseInt(dateStr[0]);//2016
        int MM = Integer.parseInt(dateStr[1]);//12
        int dd = Integer.parseInt(dateStr[2]);//31
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.set(yyyy, MM - 1, dd - 1);//set back date
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String dateUse = s.format(c.getTime());//use date time (format: yyyy-MM-dd);
        System.out.println(dateUse);
        return dateUse;
    }

    public String minusDate1(String dateInput, int i) {
        String[] dateStr = dateInput.split("-");//2016-12-31
        int yyyy = Integer.parseInt(dateStr[0]);//2016
        int MM = Integer.parseInt(dateStr[1]);//12
        int dd = Integer.parseInt(dateStr[2]);//31
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.set(yyyy, MM - 1, dd - i);//set back date
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String dateUse = s.format(c.getTime());//use date time (format: yyyy-MM-dd);
        System.out.println(dateUse);
        return dateUse;
    }

    public String GetDateEE(String dateInput) {
        String[] dateStr = dateInput.split("-");//2016-12-31
        int yyyy = Integer.parseInt(dateStr[0]);//2016
        int MM = Integer.parseInt(dateStr[1]);//12
        int dd = Integer.parseInt(dateStr[2]);//31
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.set(yyyy, MM - 1, dd);//set back date
        SimpleDateFormat s = new SimpleDateFormat("EE", Locale.ENGLISH);
        String dateUse = s.format(c.getTime());//use date time (format: yyyy-MM-dd);
//        System.out.println(dateUse);
        return dateUse;
    }

    public String minusDateFC(String dateInput, int i) {
        String[] dateStr = dateInput.split("-");//2016-12-31
        int yyyy = Integer.parseInt(dateStr[0]);//2016
        int MM = Integer.parseInt(dateStr[1]);//12
        int dd = Integer.parseInt(dateStr[2]);//31
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.set(yyyy, MM - 1, dd - 7);//set back date
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd-EE", Locale.ENGLISH);
        String dateUse = s.format(c.getTime());//use date time (format: yyyy-MM-dd);
//        System.out.println(dateUse);
        return dateUse;
    }

    public static void main(String[] args) {
        DateConvert dc = new DateConvert();
//        dc.dateGetToShow(dc.GetCurrentDate());
//        dc.dateForErp(dc.GetCurrentDate());
//        dc.dateForErpInsertDate();
        dc.GetCurrentDate();

    }
}
