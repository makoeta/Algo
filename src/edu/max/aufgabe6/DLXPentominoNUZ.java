package edu.max.aufgabe6;


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

    headNodes = new DLXNode[3 + n * 5];

    /**
     * Matrix-Spalten für Formen:
     * N U Z
     */
    for (int i = 0; i < headNodes.length; i++) { // 3 Spalten für Formen + 8 für Breitde des Spielfeldes
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
    addFigureToMatrix(new int[]{1, 3, 8, 9, 14, 19}, n - 1); // N
    addFigureToMatrix(new int[]{1, 4, 8, 9, 13, 18}, n - 1); // mirrored N
    addFigureToMatrix(new int[]{1, 4, 9, 13, 14, 18}, n - 1); // flipped N
    addFigureToMatrix(new int[]{1, 3, 8, 13, 14, 19}, n - 1); // mirrored & flipped N

    addFigureToMatrix(new int[]{0, 3, 5, 8, 9, 10}, n - 1); // U
    addFigureToMatrix(new int[]{0, 3, 4, 5, 8, 10}, n - 1); // flipped U



    addFigureToMatrix(new int[]{2, 4, 5, 9, 13, 14}, n - 2); // Z
    addFigureToMatrix(new int[]{2, 3, 4, 9, 14, 15}, n - 2); // mirrored Z

    DLX.h = head;
    DLX.search(0);
    System.out.println("Sol.: " + DLX.cnt);
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

  static DLXNode[] makeRNodeList() {
    DLXNode[] out = new DLXNode[6];

    for (int i = 0; i < out.length; i++) {
      out[i] = new DLXNode();
    }

    verticalConnect(out[0], out[1]);
    verticalConnect(out[1], out[2]);
    verticalConnect(out[2], out[3]);
    verticalConnect(out[3], out[4]);
    verticalConnect(out[4], out[5]);
    verticalConnect(out[5], out[0]);

    return out;
  }

  static void addFigureToMatrix(int[] places, int epl) {

    for (int i = 0; i < epl; i++) { // Zeilen Iteration -> drei Möglichen pro Doppelspalte
      for (int k = 0; k < 3; k++) { //immer 3 pro Zeile
        if (k != 0) {
          for (int j = 1; j < places.length; j++) { //Plätze verschieben
            places[j]++;
          }
        }
        printMatrixRow(places);
        //System.out.println(Arrays.toString(places));
        // Nodes in matrix
        DLXNode[] nodes = makeRNodeList();
        for (int x = 0; x < nodes.length; x++) {
          nodes[x].C = headNodes[places[x]]; // Connect to head
          connectNodeWithLastInRow(nodes[x]); // Vertical connect
        }
      }
      for (int j = 1; j < places.length; j++) { //Plätze verschieben hier um drei: neue Zeile
        places[j] = places[j] + 3;
      }

    }
  }

  static void printMatrixRow(int[] heads) {
    StringBuilder print = new StringBuilder("");
    for (int i = 0; i < (n * 5); i++) {
      print.append("---");
    }
    print = print.delete(print.length() - 1, print.length());

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
      //print = print.delete(80, print.length());
    }
    System.out.println(print);
   }

}
