package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.model.POSConfigSetup;

public class DiscountControl {

    public static double getDouble(double db) {
        if (POSConfigSetup.Bean().getP_DiscRound().equals("F")) {
            return NumberControl.UP_DOWN_25(db);
        } else {
            return db;
        }
    }

}
