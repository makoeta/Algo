package edu.max.aufgabe3;

/**
 * @author Maximilian König
 */
public class PermParity {

  public static void main(String[] args) {

    for (String s:
    args) {
      try {
        System.out.println("n = " + s);
        foo(Integer.parseInt(s));
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

  }

  static void foo(int n) {
    Perm p = new Perm(n);
    int[] buffer = p.getNext();

    while(buffer != null) {

      boolean print = true;

      for (int i = 1; i < buffer.length; i++) {

        if (buffer[i] % 2 == 0 && buffer[i] > buffer[i-1]) {
          continue;
        }

        if (buffer[i] % 2 == 1 && buffer[i] < buffer[i-1]) {
          continue;
        }

        print = false;
        break;
      }

      if (print) printArr(buffer);
      buffer = p.getNext();
    }
  }

  static void printArr(int[] arr) {
    for (int i:
        arr) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

}
