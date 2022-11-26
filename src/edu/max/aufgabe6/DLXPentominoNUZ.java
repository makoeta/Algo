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
    System.out.println("U-N-Z-1-2-3-4-5");


    addUtoMatrix();

    System.out.println("Break");

  }

  static DLXNode getLowestNode(DLXNode node) {
    while (node.D != null && node.D != node) {
      node = node.D;
    }
    return node;
  }

  static void connectNodeWithLastInRow(DLXNode newLowestNode) {
    horizontalConnect(getLowestNode(newLowestNode.C), newLowestNode);
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

  /**
   * U id in matrix: 1 0 0
   *
   *
   */
  static void addUtoMatrix() {

    for (int i = 0; i < n; i++) { // Zeilen Iteration -> drei Möglichen pro Doppelspalte
      for (int j = 0; j <= 3; j++) {
        DLXNode rowBeginn = makeNewLineStart(headNodes[0]);

        System.out.println("1-0-0");

        /**
         * Links oben
         */
        rowBeginn.R = new DLXNode();
        verticalConnect(rowBeginn, rowBeginn.R);
        rowBeginn.R.C = headNodes[(i*5) - 1 < 0 ? 0 : (i*5) - 1];
        connectNodeWithLastInRow(rowBeginn.R);

        /**
         * Rechts oben
         */
        rowBeginn.R.R = new DLXNode();
        verticalConnect(rowBeginn.R, rowBeginn.R.R);
        rowBeginn.R.R.C = headNodes[((i*5) - 1 < 0 ? 0 : (i*5) - 1) + 2];
        connectNodeWithLastInRow(rowBeginn.R.R);

        /**
         * 3er Balken
         */
        rowBeginn.R.R.R = new DLXNode();
        verticalConnect(rowBeginn.R.R, rowBeginn.R.R.R);
        rowBeginn.R.R.R.C = headNodes[((i*5) - 1 < 0 ? 0 : (i*5) - 1) + 5];
        connectNodeWithLastInRow(rowBeginn.R.R.R);

        rowBeginn.R.R.R.R = new DLXNode();
        verticalConnect(rowBeginn.R.R.R, rowBeginn.R.R.R.R);
        rowBeginn.R.R.R.R.C = headNodes[((i*5) - 1 < 0 ? 0 : (i*5) - 1) + 6];
        connectNodeWithLastInRow(rowBeginn.R.R.R.R);

        rowBeginn.R.R.R.R.R = new DLXNode();
        verticalConnect(rowBeginn.R.R.R.R, rowBeginn.R.R.R.R.R);
        rowBeginn.R.R.R.R.R.C = headNodes[((i*5) - 1 < 0 ? 0 : (i*5) - 1) + 7];
        connectNodeWithLastInRow(rowBeginn.R.R.R.R.R);


      }

    }


  }

}
