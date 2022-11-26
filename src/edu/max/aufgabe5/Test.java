package edu.max.aufgabe5;

import java.util.Arrays;

public class Test {

  public static void main(String[] args) {
    int n = 7;
    Perm p = new Perm(n);

    int count = 0;
    int[] a = p.getNext();

    while (a != null) {
      System.out.println(Arrays.toString(a));
      count++;
      a = p.getNext();
    }

    System.out.println("Aufrufe von perm() = " + p.count);
    System.out.println("Anzahl der Permutationen = " + count);

    System.out.println("perm() / Permutationen = " + ((double) p.count / (double)count));
  }

}
