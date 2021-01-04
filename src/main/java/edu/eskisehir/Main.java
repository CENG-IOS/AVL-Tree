package edu.eskisehir;

public class Main {
    public static void main(String[] args) {
        AVL tree = new AVL();
        tree.Insert(5);
        tree.Insert(3);
        tree.Insert(20);
        tree.Insert(7);
        tree.Insert(6);

        System.out.println("root: "+tree.Root().key);
        System.out.println("root.left: "+tree.Root().left.key);
        System.out.println("root.right: "+tree.Root().right.key);
        System.out.println("root.right.left: "+tree.Root().right.left.key);
        System.out.println("root.right.right: "+tree.Root().right.right.key);

        System.out.println("rootun dengesi: "+tree.getBalance(tree.Root()));

















    }
}
