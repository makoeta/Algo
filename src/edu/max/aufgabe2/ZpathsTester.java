package edu.max.aufgabe2;

import java.math.*;

public class ZpathsTester {

    public static void main(String[] args) {
        //new ZpathsTester(1000);
        new ZpathsTester(9000);
    }

    public ZpathsTester(int n) {

        for(int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            BigInteger erg = Zpaths.algoA(n);
            long endTime = System.currentTimeMillis();
            System.out.println("Algorithmus AB: a(" + n + ") = " + erg + " in " + (endTime - startTime) + " Milli.");

            startTime = System.currentTimeMillis();
            erg = Zpaths.algoAb(n);
            endTime = System.currentTimeMillis();
            System.out.println("Algorithmus AB: a(" + n + ") = " + erg + " in " + (endTime - startTime) + " Milli.");

            System.out.println();
        }


    }

}
