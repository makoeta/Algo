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



  static String blankLine;
  static boolean printMatrix = false;

  /**
   * Main method.
   *
   * @param args n
   */
  public static void main(String[] args) {

    if (args.length == 1) printMatrix = true; // bei einer Zahl wird die Matrix geprintet

    try {
      for (String s:
      args) {
        n = Integer.parseInt(s);
        if (n < 0) n = n * -1;
        foo();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void foo() {
    headNodes = new DLXNode[n * 5];

    for (int i = 0; i < headNodes.length; i++) { // generiert head nodes
      DLXNode headerNode = new DLXNode();
      headNodes[i] = headerNode;
      if (i > 0) {
        horizontalConnect(headNodes[i - 1], headerNode);
      }
    }
    horizontalConnect(headNodes[headNodes.length - 1], head);
    horizontalConnect(head, headNodes[0]);


    if (printMatrix) {
      // Kopfzeile der Tabelle
      StringBuilder headerPrint = new StringBuilder();
      for (int i = 1; i < 6; i++) {
        headerPrint.append("- ".repeat(n));
        headerPrint.append("|");
      }
      blankLine = headerPrint.delete(headerPrint.length() - 1, headerPrint.length()).toString();
      System.out.print(blankLine);
    }


    if (printMatrix) System.out.print(">> U");
    addFigureToMatrix(new int[]{0, 2, n, n + 1, n + 2}, 3, 2); // U
    if (printMatrix) System.out.print(">> Flipped U");
    addFigureToMatrix(new int[]{0, 1, 2, n, n + 2}, 3, 2); // flipped U
    if (printMatrix) System.out.print(">> U clockwise");
    addFigureToMatrix(new int[]{0, 1, n, n * 2, (n * 2) + 1}, 2, 3);
    if (printMatrix) System.out.print(">> U counterclockwise");
    addFigureToMatrix(new int[]{0, 1, n + 1, n * 2, (n * 2) + 1}, 2, 3);


    if (printMatrix) System.out.print(">> N");
    addFigureToMatrix(new int[]{0, n, n + 1, (n * 2) + 1, (n * 3) + 1}, 2, 4); // N
    if (printMatrix) System.out.print(">> Flipped N");
    addFigureToMatrix(new int[]{0, (n), n * 2, n * 2 + 1, (n * 3) + 1},
        2, 4); // mirrored & flipped N
    if (printMatrix) System.out.print(">> Mirrored N");
    addFigureToMatrix(new int[]{1, n, n + 1, n * 2, n * 3}, 2, 4); // mirrored N
    if (printMatrix) System.out.print(">> Mirrored & Flipped N");
    addFigureToMatrix(new int[]{1, n + 1, n * 2, (n * 2) + 1, n * 3},
        2, 4); //flipped N
    if (printMatrix) System.out.print(">> N clockwise");
    addFigureToMatrix(new int[]{2, 3, n, n + 1, n + 2}, 4, 2);
    if (printMatrix) System.out.print(">> N clockwise mirrored");
    addFigureToMatrix(new int[]{0, 1, n + 1, n + 2, n + 3}, 4, 2);
    if (printMatrix) System.out.print(">> N counterclockwise");
    addFigureToMatrix(new int[]{1, 2, 3, n, n + 1}, 4, 2);
    if (printMatrix) System.out.print(">> N counterclockwise mirrored");
    addFigureToMatrix(new int[]{0, 1, 2, n + 2, n + 3}, 4, 2);


    if (printMatrix) System.out.print(">> Z");
    addFigureToMatrix(new int[]{1, 2, n + 1, n * 2, (n * 2) + 1}, 3, 3); // Z
    if (printMatrix) System.out.print(">> Mirrored Z");
    addFigureToMatrix(new int[]{0, 1, n + 1, (n * 2) + 1, (n * 2) + 2}, 3, 3); // mirrored Z
    if (printMatrix) System.out.print(">> Z counterclockwise");
    addFigureToMatrix(new int[]{0, n, n + 1, n + 2, (n * 2) + 2}, 3, 3); // Z counterclockwise
    if (printMatrix) System.out.print(">> Z clockwise");
    addFigureToMatrix(new int[]{2, n, n + 1, n + 2, n * 2}, 3, 3); // Z clockwise

    System.out.print("\nCalculating");

    DLX.h = head;
    DLX.cnt = 0;
    DLX.search(0);

    System.out.println("\na(" + n + ") = " + DLX.cnt);
  }

  static DLXNode getLowestNode(DLXNode node) {
    return node.C.U; // eins über dem Head der Spalte
  }

  static void connectNodeWithLastInRow(DLXNode newLowestNode) {
    //unter die bisher letzte Node hängen
    verticalConnect(getLowestNode(newLowestNode.C), newLowestNode);
    verticalConnect(newLowestNode, newLowestNode.C); // "Überschuss" zu head
  }

  static void horizontalConnect(DLXNode left, DLXNode right) {
    left.R = right;
    right.L = left;
  }

  static void verticalConnect(DLXNode up, DLXNode down) {
    up.D = down;
    down.U = up;
  }

  static DLXNode[] makernodelist() {
    DLXNode[] out = new DLXNode[5];

    for (int i = 0; i < out.length; i++) {
      out[i] = new DLXNode();
    }

    horizontalConnect(out[0], out[1]);
    horizontalConnect(out[1], out[2]);
    horizontalConnect(out[2], out[3]);
    horizontalConnect(out[3], out[4]);
    horizontalConnect(out[4], out[0]);

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
        if (printMatrix) printMatrixRow(places);
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
    StringBuilder print = new StringBuilder(blankLine);

    for (int j : heads) {
      int indent = j / n; //Anzahl der |
      print.replace((j * 2) + indent, (j * 2) + indent + 1, "X");
    }

    System.out.print("\n" + print);
  }
}