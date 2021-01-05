package edu.eskisehir;

public class Main {
    public static void main(String[] args) {
        AVL avl = new AVL();
        /*tree.Insert(5);
        tree.Insert(3);
        tree.Insert(20);
        tree.Insert(7);
        tree.Insert(6);
        tree.Insert(5);*/
        avl.Insert(5);
        avl.Insert(5);
        avl.Insert(10);
        avl.Insert(10);
        avl.Insert(1);
        avl.Insert(3);
        avl.Insert(2);
        avl.Insert(4);
        avl.Insert(20);
        avl.Insert(15);
        avl.Insert(30);
        avl.Insert(40);
        avl.Print();
        System.out.println("5in sayısı: "+avl.Find(5).count);
        System.out.println("10in sayısı: "+avl.Find(10).count);

        /*System.out.println("root: " + tree.Root().key);
        System.out.println("root.left: " + tree.Root().left.key);
        System.out.println("root.right: " + tree.Root().right.key);
        System.out.println("root.right.left: " + tree.Root().right.left.key);
        System.out.println("root.right.right: " + tree.Root().right.right.key);
        System.out.println("rootun dengesi: " + tree.getBalance(tree.Root()));
        System.out.println("depth: " + tree.Depth());
        System.out.println("max: " + tree.Max().key);
        System.out.println("min: "+tree.Min().key);
        tree.Print();
        System.out.println(tree.Find(55));
        //System.out.println(5+" silindi. " + tree.Delete(5));
        tree.Print();
        System.out.println("-------------------------------------");
        System.out.println("5in sayısı: "+tree.Find(5).count);
        System.out.println("node sayısı: "+tree.NoOfNodes());*/
    }
}
