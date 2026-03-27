package com.ics.pdatakeorder.util;

import java.awt.Component;

public class MSG {

    private MSG() {
        throw new AssertionError();
    }

    public static void ERR(String error) {
        System.err.println(error);
    }

    public static void WAR(String warning) {
        System.err.println(warning);
    }

    public static void NOTICE(String notice) {
        System.err.println(notice);
    }

    public static boolean CONF(String confirm) {
        boolean success = false;
        return success;
    }

    public static void ERR_MSG(Component com, String error) {
        System.err.println(error);
    }

    public static void WAR_MSG(Component com, String warning) {
    }

    public static void NOTICE_MSG(Component com, String notice) {
    }

    public static boolean CONF_MSG(Component com, String confirm) {
        boolean success = false;
        return success;
    }
    
    public static void ERR(Component com, String error) {
        System.err.println(error);
    }

    public static void WAR(Component com, String warning) {
    }

    public static void NOTICE(Component com, String notice) {
    }

    public static boolean CONF(Component com, String confirm) {
        boolean success = false;
        return success;
    }

}
