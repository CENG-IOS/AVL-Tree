package edu.eskisehir;
/******************************************************
 * AVL ADT.
 * Supported operations:
 * Insert
 * Delete
 * Find
 * Min
 * Max
 * Depth
 * Print
 ******************************************************/
public class AVL{
  private AVLNode root;      /* Pointer to the root of the tree */
  private int noOfNodes;     /* No of nodes in the tree */

  /*******************************************************
   * Constructor: Initializes the AVL
   *******************************************************/
  public AVL(){root=null; noOfNodes=0;}

  /*******************************************************
   * Returns a pointer to the root of the tree
   *******************************************************/
  public AVLNode Root(){return root;}

  /*******************************************************
   * Returns the number of nodes in the tree
   *******************************************************/
  public int NoOfNodes(){return noOfNodes;}

  /*******************************************************
   * Inserts the key into the AVL. Returns a pointer to
   * the inserted node
   *******************************************************/
  public AVLNode Insert(int key){
    // Fill this in
    return null;
  } //end-Insert

  /*******************************************************
   * Deletes the key from the tree (if found). Returns
   * 0 if deletion succeeds, -1 if it fails
   *******************************************************/
  public int Delete(int key){
    // Fill this in
    return 0;
  } //end-Delete

  /*******************************************************
   * Searches the AVL for a key. Returns a pointer to the
   * node that contains the key (if found) or NULL if unsuccessful
   *******************************************************/
  public AVLNode Find(int key){
    // Fill this in
    return null;
  } //end-Find

  /*******************************************************
   * Returns a pointer to the node that contains the minimum key
   *******************************************************/
  public AVLNode Min(){
    // Fill this in
    return null;
  } //end-Min

  /*******************************************************
   * Returns a pointer to the node that contains the maximum key
   *******************************************************/
  public AVLNode Max(){
    // Fill this in
    return null;
  } //end-Max

  /*******************************************************
   * Returns the depth of tree. Depth of a tree is defined as
   * the depth of the deepest leaf node. Root is at depth 0 
   *******************************************************/
  public int Depth(){
    // Fill this in
    return -1;
  } //end-Depth

  /*******************************************************
   * Performs an inorder traversal of the tree and prints [key, count] 
   * pairs in sorted order
   *******************************************************/
  public void Print(){
    // Fill this in
  } //end-Print
};
