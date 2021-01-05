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
    private AVLNode deleted;

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
        helpDeletion(copy, key);
        if (deleted == null) {
            return -1;
        } else {
            deleted = null;
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

    private AVLNode helpDeletion(AVLNode node, int key) {

        if (node == null)
            return node;

        if (key < node.key)
            node.left = helpDeletion(node.left, key);

        else if (key > node.key)
            node.right = helpDeletion(node.right, key);

        else {
            if (node.count > 1) {
                node.count--;
                return null;
            }

            if ((node.left == null) || (node.right == null)) {
                AVLNode temp = node.left != null ? node.left : node.right;

                if (temp == null) {
                    temp = node;
                    deleted = temp;
                    node = null;
                } else{
                    deleted = temp;
                    node = temp;
                }

            } else {
                AVLNode temp = minValueNode(node.right);

                node.key = temp.key;
                node.count = temp.count;
                temp.count = 1;
                node.right = helpDeletion(node.right, temp.key);
            }


        }

        if (node == null)
            return node;

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0)
            return rotateRight(node);

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0)
            return rotateLeft(node);


        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
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

            node = new AVLNode(key);
            AVLNode temp = Find(key);
            if (temp != null && temp.key == key) {
                node.count++;
            } else noOfNodes++;
        } else if (key < node.key) {
            node.left = helpInsert(node.left, key);
            if (height(node.left) - height(node.right) == 2)
                if (key < node.left.key)
                    node = rotateLeft(node);
                else
                    node = doubleRotateLeft(node);
        } else {
            if (node.key == key) {
                node.count++;
            }
            node.right = helpInsert(node.right, key);
            if (height(node.right) - height(node.left) == 2)
                if (key >= node.right.key)
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
