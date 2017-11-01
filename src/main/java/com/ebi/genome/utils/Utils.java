package com.ebi.genome.utils;

import java.util.Date;

public class Utils {

    private Utils() {

    }

    public static Long getCurrentTimestamp() {
        return new Date().getTime() / Constants.MILISECOND;
    }
}
