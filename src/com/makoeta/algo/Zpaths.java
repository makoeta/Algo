package com.makoeta.algo;

import java.math.BigInteger;

public class Zpaths {

    public static void main(String[] args) {

        algoAb(15);
        /*while(true) {
            int input;
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Eingabe: ");
                input = sc.nextInt();
                if(input < 0) throw new Exception();
            } catch (Exception e) {
                System.out.println("Bitte eine positive Ganzzahl eingeben.");
                continue;
            }
            algoA(input);
        }*/
    }


    /**
     * Algorithmus für zPaths
     * @param n
     */
    static void algoA(int n) {

        /*
        Anzahl der Generationen errechnen. Jede Generation ist im Bereich von g(x)= -x + Generation

        Bsp.:
            1. Generation
            g(x) = -x + 1

            ...

            14. Generation
            g(x) = -x + 14

         Erklärung:
         Die Steigung von m = -1 ist nötig, da sonst nicht alle Punkte für n im berechneten Bereich liegen.

         Der Faktor von 2/3 ist nötig, da dieser (abgerundet) die niedrigste Anzahl an Generationen in
         Verhältnis zu n wieder gibt. 2/3 ist das Verhältnis, welches sich aus der Gleichung n = 2i + j ergibt.
         */
        float anzahlGen = (int) ((2f/3f)*n);

        BigInteger[][] gitterPfade = new BigInteger[(int) anzahlGen + 1][(int) anzahlGen + 1];

        for (int i = 0; i < anzahlGen+1; i++) {
            for(int j = 0; j < anzahlGen+1; j++) {
                gitterPfade[i][j] = BigInteger.ZERO;
            }
        }

        gitterPfade[0][0] = BigInteger.ONE;

        /*
        Rechnen der Generationen
         */
        for (int i = 0; i < anzahlGen; i++) {
            int x = i;
            int y = 0;
            // Startpunkt immer bei Nullstelle

            while(y<=i) { //Negative Steigung ablaufen, solange bis x = y Geraden
                //Erster Schritt -> Nach rechts
                gitterPfade[x+1][y] = gitterPfade[x+1][y].add(gitterPfade[x][y]);

                //Zweiter Schritt -> nach oben, möglich?
                if(y+1 <= x) {
                    gitterPfade[x][y+1] = gitterPfade[x][y+1].add(gitterPfade[x][y]);
                }
                x--;
                y++;
            }
        }

        BigInteger gesamtPfade = BigInteger.ZERO;
        int j = 0;
        if (n%2 != 0) { // Berechnung des Punktes der am nächsten zur x-Achse liegt
            j ++;
        }
        for (int i = n/2; i >= j; i--,j += 2) {
            System.out.println("Punkt [" + i + "/" + j + "]: " + gitterPfade[i][j]);
            gesamtPfade = gesamtPfade.add(gitterPfade[i][j]);
        }
        //System.out.println("Für n = " + n + "\nGesamt: " + gesamtPfade + " Pfade");

        System.out.println("a(" + n + ") = " + gesamtPfade);
    }

    static void algoAb(int n) {
        float anzahlGen = (int) ((2f/3f)*n);

        BigInteger[] gitterPfade = {BigInteger.ONE};
        BigInteger[] temp;

        for (int i = 1; i < anzahlGen; i++) {
            temp = new BigInteger[gitterPfade.length];
            if(i % 2 == 0) { // Wenn Generation gerade, dann vergrößern
                temp = new BigInteger[gitterPfade.length + 1];
            }

            for(int j = 0; j < temp.length; j++) {
                if(j-1 < 0) temp[j] = gitterPfade[j]; // unten
                else if(j == gitterPfade.length) temp[j] = gitterPfade[gitterPfade.length - 1]; //oben
                else {
                    temp[j] = gitterPfade[j].add(gitterPfade[j-1]); // mitte
                }
            }
            gitterPfade = temp.clone();

            for (BigInteger b:
                    gitterPfade) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }
}