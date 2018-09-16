package com.infoshareacademy.webapp;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        Integer sum = 0;

        for (int i=0; i < 5; i++) {
            sum += i;
        }

        int[] ints = {1,2,3};

        Double average = Double.valueOf(sum / 3);

        System.out.println("Suma: " + sum);
        System.out.println("Średnia z Double: " + average);

        BigDecimal bigDecimal = new BigDecimal(sum);
        BigDecimal bigDecimalav = new BigDecimal(ints.length);
        BigDecimal result = bigDecimal.divide(bigDecimalav,5, BigDecimal.ROUND_HALF_DOWN);

        System.out.println();
        System.out.println("BigDecimal Suma: " + bigDecimal);
        System.out.println("BigDecimal Średnia: " + result);

    }

}
