package edu.max.aufgabe6;

public class DLXNode {

  DLXNode C;           // reference to column-header
  DLXNode L, R, U, D;  // left, right, up, down references
  DLXNode(){ C=L=R=U=D=this; }

}
