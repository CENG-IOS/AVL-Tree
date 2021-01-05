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
public class AVL {
    private AVLNode root;      /* Pointer to the root of the tree */
    private int noOfNodes;     /* No of nodes in the tree */

    /*******************************************************
     * Constructor: Initializes the AVL
     *******************************************************/
    public AVL() {
        root = null;
        noOfNodes = 0;
    }

    /*******************************************************
     * Returns a pointer to the root of the tree
     *******************************************************/
    public AVLNode Root() {
        return root;
    }

    /*******************************************************
     * Returns the number of nodes in the tree
     *******************************************************/
    public int NoOfNodes() {
        return noOfNodes;
    }

    /*******************************************************
     * Inserts the key into the AVL. Returns a pointer to
     * the inserted node
     *******************************************************/
    public AVLNode Insert(int key) {
        root = helpInsert(root, key);
        return null;
    } //end-Insert

    /*******************************************************
     * Deletes the key from the tree (if found). Returns
     * 0 if deletion succeeds, -1 if it fails
     *******************************************************/
    public int Delete(int key) {
        AVLNode copy = root;
        AVLNode temp = helpDeletion(copy, key);
        if (temp == null)
            return -1;
        else {
            noOfNodes--;
            return 0;
        }
    } //end-Delete

    /*******************************************************
     * Searches the AVL for a key. Returns a pointer to the
     * node that contains the key (if found) or NULL if unsuccessful
     *******************************************************/
    public AVLNode Find(int key) {
        AVLNode copy = root;
        return helpFind(copy, key);
    } //end-Find

    /*******************************************************
     * Returns a pointer to the node that contains the minimum key
     *******************************************************/
    public AVLNode Min() {
        if (root == null) {
            return null;
        }
        AVLNode min = root;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    } //end-Min

    /*******************************************************
     * Returns a pointer to the node that contains the maximum key
     *******************************************************/
    public AVLNode Max() {
        AVLNode max = root;
        while (max.right != null) {
            max = max.right;
        }
        return max;
    } //end-Max

    /*******************************************************
     * Returns the depth of tree. Depth of a tree is defined as
     * the depth of the deepest leaf node. Root is at depth 0
     *******************************************************/
    public int Depth() {
        return height(root) - 1;
    } //end-Depth

    /*******************************************************
     * Performs an inorder traversal of the tree and prints [key, count]
     * pairs in sorted order
     *******************************************************/
    public void Print() {
        inOrder(root);
        System.out.println();
    } //end-Print

    private void inOrder(AVLNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    private int height(AVLNode node) {
        if (node == null)
            return 0;
        else {

            int lDepth = height(node.left);
            int rDepth = height(node.right);

            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    public int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private AVLNode helpFind(AVLNode copy, int key) {
        if (copy == null) {

            return null;

        } else if (key < copy.key) {

            return helpFind(copy.left, key);

        } else if (key > copy.key) {

            return helpFind(copy.right, key);

        } else {

            return copy;
        }
    }

    private AVLNode helpDeletion(AVLNode root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = helpDeletion(root.left, key);

        else if (key > root.key)
            root.right = helpDeletion(root.right, key);

        else {

            if ((root.left == null) || (root.right == null)) {
                AVLNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;

            } else {
                AVLNode temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = helpDeletion(root.right, temp.key);
            }
        }


        if (root == null)
            return root;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rotateRight(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return rotateLeft(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

    private AVLNode helpInsert(AVLNode node, int key) {

        if (node == null) {
            noOfNodes++;
            node = new AVLNode(key);
        } else if (key < node.key) {
            node.left = helpInsert(node.left, key);
            if (height(node.left) - height(node.right) == 2)
                if (key < node.left.key)
                    node = rotateLeft(node);
                else
                    node = doubleRotateLeft(node);
        } else {
            AVLNode temp = Find(key);
            if (temp != null){
                node.count++;

            }


            node.right = helpInsert(node.right, key);
            if (height(node.right) - height(node.left) == 2)
                if (key > node.right.key)
                    node = rotateRight(node);
                else
                    node = doubleRotateRight(node);
        }

        return node;
    }

    private AVLNode rotateLeft(AVLNode k2) {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        return k1;
    }

    private AVLNode rotateRight(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        return k2;
    }

    private AVLNode doubleRotateLeft(AVLNode k3) {
        k3.left = rotateRight(k3.left);
        return rotateLeft(k3);
    }

    private AVLNode doubleRotateRight(AVLNode k1) {
        k1.right = rotateLeft(k1.right);
        return rotateRight(k1);
    }
}
