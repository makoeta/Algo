package edu.max.aufgabe3;

/**
 * @author Alois Heinz
 */
public class Perm extends Thread {
    private int[] a;        // a Arbeitsarrayprivate
    int max;        // maximaler Indexprivate
    boolean mayread = false; // Kontrolle


    public Perm(int n) {        // Konstruktor
        a = new int[n];     // Indices: 0 .. n-1
        max = n - 1;          // Maximaler Index
        for (int i = 0; i <= max; ) {
            a[i] = i++ + 1; // a fuellen
        }
        this.start();      // run-Methode beginnt zu laufen
    }

    public void run() {// die Arbeits-Methode
        perm(0);        // a[0] bleibt fest; permutiere ab 1
        a = null;        // Anzeige, dass fertig
        put();          // ausliefern
    } // end run

    private void perm(int i) { // permutiere ab Index i
        if (i >= max) put();  // eine Permutation fertig
        else {
            for (int j = i; j <= max; j++) { // jedes nach Vorne
                swap(i, j);               // vertauschen
                perm(i + 1);               // und Rekursion
            }
            int h = a[i];                 // restauriere
            System.arraycopy(a, i + 1, a, i, max - i); // shift links
            a[max] = h;
        }
    } // end perm

    private void swap(int i, int j) {   // tausche a[i] <-> a[j]
        if (i != j) {
            int h = a[i];
            a[i] = a[j];
            a[j] = h;
        }
    } // end swap

    public synchronized int[] getNext() { // liefert naechste Permutation
        mayread = false;                // a darf geaendert werden
        notify();                      // wecke anderen Thread
        try {
            while (!mayread) wait();  // non busy waiting
        } catch (InterruptedException ignore) {
        }
        return a;                  // liefere Permutationsarray
    } // end getNext

    private synchronized void put() {   // uebergebe array
        mayread = true;                 // a wird gelesen
        notify();                      // wecke anderen Thread
        try {
            if (a != null)
                while (mayread) wait();    // non busy waiting
        } catch (InterruptedException ignore) {
        }
    } // end put
}