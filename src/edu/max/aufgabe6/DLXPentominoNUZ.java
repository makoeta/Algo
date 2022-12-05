package edu.max.aufgabe6;

/**
 * Calculates the solutions for an 5xN field with the given pentimonos.
 *
 * @author Maximilian König, Jonas Gierlich, Tobias Bergtold
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

  /**
   * Generates the matrix with all figures and then let the dlx algorithm calculate the solutions.
   */
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
      for (int i = 0; i < n; i++) {
        headerPrint.append("- ".repeat(5));
        headerPrint.append("|");
      }
      blankLine = headerPrint.delete(headerPrint.length() - 1, headerPrint.length()).toString();
      System.out.print(blankLine);
    }


    if (printMatrix) System.out.print(">> U");
    addFigureToMatrix(new int[]{0, 2, 5, 6, 7}, 3, 2); // U
    if (printMatrix) System.out.print(">> Flipped U");
    addFigureToMatrix(new int[]{0, 1, 2, 5, 7}, 3, 2); // flipped U
    if (printMatrix) System.out.print(">> U clockwise");
    addFigureToMatrix(new int[]{0, 1, 5, 10, 11}, 2, 3);
    if (printMatrix) System.out.print(">> U counterclockwise");
    addFigureToMatrix(new int[]{0, 1, 6, 10, 11}, 2, 3);


    if (printMatrix) System.out.print(">> N");
    addFigureToMatrix(new int[]{0, 5, 6, 11, 16}, 2, 4); // N
    if (printMatrix) System.out.print(">> Flipped N");
    addFigureToMatrix(new int[]{0, 5, 10, 11, 16},
        2, 4); // mirrored & flipped N
    if (printMatrix) System.out.print(">> Mirrored N");
    addFigureToMatrix(new int[]{1, 5, 6, 10, 15}, 2, 4); // mirrored N
    if (printMatrix) System.out.print(">> Mirrored & Flipped N");
    addFigureToMatrix(new int[]{1, 6, 10, 11, 15},
        2, 4); //flipped N
    if (printMatrix) System.out.print(">> N clockwise");
    addFigureToMatrix(new int[]{2, 3, 5, 6, 7}, 4, 2);
    if (printMatrix) System.out.print(">> N clockwise mirrored");
    addFigureToMatrix(new int[]{0, 1, 6, 7, 8}, 4, 2);
    if (printMatrix) System.out.print(">> N counterclockwise");
    addFigureToMatrix(new int[]{1, 2, 3, 5, 6}, 4, 2);
    if (printMatrix) System.out.print(">> N counterclockwise mirrored");
    addFigureToMatrix(new int[]{0, 1, 2, 7, 8}, 4, 2);


    if (printMatrix) System.out.print(">> Z");
    addFigureToMatrix(new int[]{1, 2, 6, 10, 11}, 3, 3); // Z
    if (printMatrix) System.out.print(">> Mirrored Z");
    addFigureToMatrix(new int[]{0, 1, 6, 11, 12}, 3, 3); // mirrored Z
    if (printMatrix) System.out.print(">> Z counterclockwise");
    addFigureToMatrix(new int[]{0, 5, 6, 7, 12}, 3, 3); // Z counterclockwise
    if (printMatrix) System.out.print(">> Z clockwise");
    addFigureToMatrix(new int[]{2, 5, 6, 7, 10}, 3, 3); // Z clockwise


    System.out.print("\na(" + n + ") = ");

    DLX.h = head;
    DLX.cnt = 0;
    DLX.search(0);

    System.out.print(DLX.cnt);
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

  /**
   * Adds all possibilities of a figure to the matrix.
   *
   * @param rows first possibility to put the figure in the top left of the matrix.
   * @param width width of the figure
   * @param height height of the figure
   */
  static void addFigureToMatrix(int[] rows, int width, int height) {

    for (int i = 0; i < (n - height) + 1; i++) {
      //System.out.println("Hier");
      for (int k = 0; k < (5 - width) + 1; k++) { //reihe
        if (k != 0) {
          for (int j = 0; j < rows.length; j++) { //Plätze verschieben
            rows[j]++;
          }
        }
        if (printMatrix) printMatrixRow(rows);
        // Nodes in matrix
        DLXNode[] nodes = makernodelist();
        for (int x = 0; x < nodes.length; x++) {
          nodes[x].C = headNodes[rows[x]]; // Connect to head
          connectNodeWithLastInRow(nodes[x]); // Vertical connect
        }
      }
      for (int j = 0; j < rows.length; j++) { //Plätze verschieben hier um drei: neue Zeile
        rows[j] = rows[j] + width;
      }
    }
  }

  /**
   * Prints a row with the given nodes in the console.
   *
   * @param rows where nodes exist
   */
  static void printMatrixRow(int[] rows) {
    StringBuilder print = new StringBuilder(blankLine);

    for (int j : rows) {
      int indent = j / 5; //Anzahl der |
      print.replace((j * 2) + indent, (j * 2) + indent + 1, "X");
    }

    System.out.print("\n" + print);
  }
}