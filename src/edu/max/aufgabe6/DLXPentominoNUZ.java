package edu.max.aufgabe6;

/**
 * Calculates the solutions for an 5xN field with the given pentimonos.
 *
 * @author Maximilian König
 */
public class DLXPentominoNUZ {

  static DLXNode head = new DLXNode();
  static int n;
  static DLXNode[] headNodes;

  /**
   * Main method.
   *
   * @param args args[0] = n
   */
  public static void main(String[] args) {

    n = 0;

    try {
      n = Integer.parseInt(args[0]);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    n = 7;

    headNodes = new DLXNode[n * 5];


    for (int i = 0; i < headNodes.length; i++) { // generiert head nodes
      DLXNode headerNode = new DLXNode();
      headNodes[i] = headerNode;
      if (i > 0) {
        verticalConnect(headNodes[i - 1], headerNode);
      }
    }
    verticalConnect(headNodes[headNodes.length - 1], head);
    verticalConnect(head, headNodes[0]);

    // Kopfzeile der Tabelle
    StringBuilder headerPrint = new StringBuilder();
    for (int i = 1; i < 6; i++) {
      for (int j = 1; j < n + 1; j++) {
        headerPrint.append("-" + i + Integer.toHexString(j));
      }
    }
    System.out.println(headerPrint.substring(1));


    //System.out.println("Mirrored & Flipped N");
    addFigureToMatrix(new int[]{1, n + 1, n * 2, (n * 2) + 1, n * 3},
        2, 4); //flipped N
    //System.out.println("Flipped N");
    addFigureToMatrix(new int[]{0, (n), n * 2, n * 2 + 1, (n * 3) + 1},
        2, 4); // mirrored & flipped N
    //System.out.println("N");
    addFigureToMatrix(new int[]{0, n, n + 1, (n * 2) + 1, (n * 3) + 1}, 2, 4); // N
    //System.out.println("Mirrored N");
    addFigureToMatrix(new int[]{1, n, n + 1, n * 2, n * 3}, 2, 4); // mirrored N
    //System.out.println("N clockwise");
    addFigureToMatrix(new int[]{2, 3, n, n + 1, n + 2}, 4, 2);
    //System.out.println("N clockwise mirrored");
    addFigureToMatrix(new int[]{0, 1, n + 1, n + 2, n + 3}, 4, 2);
    //System.out.println("N counterclockwise");
    addFigureToMatrix(new int[]{1, 2, 3, n, n + 1}, 4, 2);
    //System.out.println("N counterclockwise mirrored");
    addFigureToMatrix(new int[]{0, 1, 2, n + 2, n + 3}, 4, 2);

    //System.out.println("U");
    addFigureToMatrix(new int[]{0, 2, n, n + 1, n + 2}, 3, 2); // U
    //System.out.println("Flipped U");
    addFigureToMatrix(new int[]{0, 1, 2, n, n + 2}, 3, 2); // flipped U
    //System.out.println("U clockwise");
    addFigureToMatrix(new int[]{0, 1, n, n * 2, (n * 2) + 1}, 2, 3);
    //System.out.println("U counterclockwise");
    addFigureToMatrix(new int[]{0, 1, n + 1, n * 2, (n * 2) + 1}, 2, 3);

    //System.out.println("Z");
    addFigureToMatrix(new int[]{1, 2, n + 1, n * 2, (n * 2) + 1}, 3, 3); // Z
    //System.out.println("Mirrored Z");
    addFigureToMatrix(new int[]{0, 1, n + 1, (n * 2) + 1, (n * 2) + 2}, 3, 3); // mirrored Z
    //System.out.println("Z counterclockwise");
    addFigureToMatrix(new int[]{0, n, n + 1, n + 2, (n * 2) + 2}, 3, 3); // Z counterclockwise
    //System.out.println("Z clockwise");
    addFigureToMatrix(new int[]{2, n, n + 1, n + 2, n * 2}, 3, 3); // Z clockwise

    DLX.h = head;
    DLX.search(0);
    System.out.println("\nSol.: " + DLX.sol);
  }

  static DLXNode getLowestNode(DLXNode node) {
    return node.C.U; // eins über dem Head der Spalte
  }

  static void connectNodeWithLastInRow(DLXNode newLowestNode) {
    //unter die bisher letzte Node hängen
    horizontalConnect(getLowestNode(newLowestNode.C), newLowestNode);
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

  static DLXNode[] makernodelist() {
    DLXNode[] out = new DLXNode[5];

    for (int i = 0; i < out.length; i++) {
      out[i] = new DLXNode();
    }

    verticalConnect(out[0], out[1]);
    verticalConnect(out[1], out[2]);
    verticalConnect(out[2], out[3]);
    verticalConnect(out[3], out[4]);
    verticalConnect(out[4], out[0]);

    return out;
  }

  static void addFigureToMatrix(int[] places, int width, int height) {

    for (int i = 0; i < (5 - height) + 1; i++) {
      for (int k = 0; k < n - width + 1; k++) {
        if (k != 0) {
          for (int j = 0; j < places.length; j++) { //Plätze verschieben
            places[j]++;
          }
        }
        printMatrixRow(places);
        // Nodes in matrix
        DLXNode[] nodes = makernodelist();
        for (int x = 0; x < nodes.length; x++) {
          nodes[x].C = headNodes[places[x]]; // Connect to head
          connectNodeWithLastInRow(nodes[x]); // Vertical connect
        }
      }
      for (int j = 0; j < places.length; j++) { //Plätze verschieben hier um drei: neue Zeile
        places[j] = places[j] + width;
      }

    }
  }

  static void printMatrixRow(int[] heads) {
    StringBuilder print = new StringBuilder();
    for (int i = 0; i < (n * 5); i++) {
      print.append("---");
    }
    print = print.delete(print.length() - 1, print.length());


    for (int i = 0; i < heads.length; i++) {
      print.replace(((heads[i])  * 3), ((heads[i])  * 3) + 1, "XX");
    }

    System.out.println(print.substring(0, print.length() - 5));
  }

}
