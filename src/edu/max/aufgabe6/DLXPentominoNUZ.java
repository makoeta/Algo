package edu.max.aufgabe6;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DLXPentominoNUZ {

  static DLXNode head = new DLXNode();
  static int n;
  static DLXNode[] headNodes = new DLXNode[8];

  public static void main(String[] args) {

    n = 0;

    try {
      n = Integer.parseInt(args[0]);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    n = 5;

    headNodes = new DLXNode[8];

    /**
     * Matrix-Spalten für Formen:
     * N U Z
     */
    for (int i = 0; i < 8; i++) { // 3 Spalten für Formen + 8 für Breitde des Spielfeldes
      DLXNode headerNode = new DLXNode();
      headNodes[i] = headerNode;
      if (i > 0) {
        verticalConnect(headNodes[i -1], headerNode);
      }
    }
    verticalConnect(head, headNodes[0]);
    verticalConnect(headNodes[7], head); // Kreis
    System.out.print("U-N-Z");

    for (int i = 1; i < n + 1; i++) {
      System.out.print("-" + i + "1-" + i + "2-" + i + "3-" + i + "4-" + i + "5");
    }
    System.out.println();


    addUtoMatrix();
    addFlippedUtoMatrix();
    addZtoMatrix();
    addMirroredZtoMatrix();


  }

  static DLXNode getLowestNode(DLXNode node) {
    return node.C.U; // eins über dem Head der Spalte
  }

  static void connectNodeWithLastInRow(DLXNode newLowestNode) {
    horizontalConnect(getLowestNode(newLowestNode.C), newLowestNode); //unter die bisher letzte Node hängen
    horizontalConnect(newLowestNode, newLowestNode.C); // "Überschuss" zu head
  }

  static void verticalConnect(DLXNode left, DLXNode right) {
    left.R = right;
    right.L = left;
  }

  static void horizontalConnect(DLXNode up, DLXNode down) {
    up.D = down;
    down.U = up;
  }

  static DLXNode makeNewLineStart(DLXNode headNode) {

    DLXNode rowBeginn = getLowestNode(headNode);
    rowBeginn.D = new DLXNode(); // Neue Node für Zeilenbeginn
    rowBeginn = rowBeginn.D;
    rowBeginn.C = headNode; //Mit head verbinden
    connectNodeWithLastInRow(rowBeginn);

    return rowBeginn.D;
  }

  static DLXNode[] makeRNodeList(int length) {
    DLXNode[] out = new DLXNode[length];

    for (int i = 0; i < length; i++) {
      out[i] = new DLXNode();
    }

    for (int i = 0; i < length; i++) {
      verticalConnect(out[ i - 1 < 0 ? length - 1 : i], out[i]);
    }

    return out;
  }



  static void addUtoMatrix() {

    int[] places = {0, 3, 5, 8, 9, 10};

    for (int i = 0; i < n - 1; i++) { // Zeilen Iteration -> drei Möglichen pro Doppelspalte
      for (int k = 0; k < 3; k++) { //immer 3 pro Zeile
        if (k != 0) {
          for (int j = 1; j < places.length; j++) { //Plätze verschieben
            places[j]++;
          }
        }
        printMatrixRow(places);
        //System.out.println(Arrays.toString(places));
        // Nodes in matrix
        DLXNode[] nodes = makeRNodeList(6);
        for (int x = 0; x < nodes.length; x++) {
          nodes[x].C = headNodes[x]; // Connect to head
          connectNodeWithLastInRow(nodes[x]); // Vertical connect
        }
      }
      for (int j = 1; j < places.length; j++) { //Plätze verschieben hier um drei: neue Zeile
        places[j] = places[j] + 3;
      }

    }
  }


  static void addFlippedUtoMatrix() {

    int[] places = {0, 3, 4, 5, 8, 10};

    for (int i = 0; i < n - 1; i++) { // Zeilen Iteration -> drei Möglichen pro Doppelspalte
      for (int k = 0; k < 3; k++) { //immer 3 pro Zeile
        if (k != 0) {
          for (int j = 1; j < places.length; j++) { //Plätze verschieben
            places[j]++;
          }
        }
        printMatrixRow(places);
        //System.out.println(Arrays.toString(places));
        // Nodes in matrix
        DLXNode[] nodes = makeRNodeList(6);
        for (int x = 0; x < nodes.length; x++) {
          nodes[x].C = headNodes[x]; // Connect to head
          connectNodeWithLastInRow(nodes[x]); // Vertical connect
        }
      }
      for (int j = 1; j < places.length; j++) { //Plätze verschieben hier um drei: neue Zeile
        places[j] = places[j] + 3;
      }

    }
  }


  static void addZtoMatrix() {

    int[] places = {2, 4, 5, 9, 13, 14};

    for (int i = 0; i < n - 2; i++) { // Zeilen Iteration -> drei Möglichen pro Doppelspalte
      for (int k = 0; k < 3; k++) { //immer 3 pro Zeile
        if (k != 0) {
          for (int j = 1; j < places.length; j++) { //Plätze verschieben
            places[j]++;
          }
        }
        printMatrixRow(places);
        //System.out.println(Arrays.toString(places));
        // Nodes in matrix
        DLXNode[] nodes = makeRNodeList(6);
        for (int x = 0; x < nodes.length; x++) {
          nodes[x].C = headNodes[x]; // Connect to head
          connectNodeWithLastInRow(nodes[x]); // Vertical connect
        }
      }
      for (int j = 1; j < places.length; j++) { //Plätze verschieben hier um drei: neue Zeile
        places[j] = places[j] + 3;
      }

    }
  }

  static void addMirroredZtoMatrix() {

    int[] places = {2, 3, 4, 9, 9, 14, 15};

    for (int i = 0; i < n - 2; i++) { // Zeilen Iteration -> drei Möglichen pro Doppelspalte
      for (int k = 0; k < 3; k++) { //immer 3 pro Zeile
        if (k != 0) {
          for (int j = 1; j < places.length; j++) { //Plätze verschieben
            places[j]++;
          }
        }
        printMatrixRow(places);
        //System.out.println(Arrays.toString(places));
        // Nodes in matrix
        DLXNode[] nodes = makeRNodeList(6);
        for (int x = 0; x < nodes.length; x++) {
          nodes[x].C = headNodes[x]; // Connect to head
          connectNodeWithLastInRow(nodes[x]); // Vertical connect
        }
      }
      for (int j = 1; j < places.length; j++) { //Plätze verschieben hier um drei: neue Zeile
        places[j] = places[j] + 3;
      }

    }
  }





  static void printMatrixRow(int[] heads) {
    StringBuilder print = new StringBuilder("----");
    for (int i = 0; i < (n*14); i++) {
      print.append("-");
    }

    switch (heads[0]) {
      case 0:
        print.replace(0, 0, "X");
        break;
      case 1:
        print.replace(2, 2, "X");
        break;
      case 2:
        print.replace(4, 4,"X");
        break;
    }


    for (int i = 1; i < heads.length; i++) {
      print.replace(((heads[i])  * 3) - 3, ((heads[i])  * 3) - 2, "XX");
    }


    if (print.length() > 80) {
      print = print.delete(80, print.length());
    }
    System.out.println(print);
   }

}
