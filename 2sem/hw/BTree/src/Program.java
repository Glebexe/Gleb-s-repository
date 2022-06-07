public class Program {

    public static void main(String[] args) {
        B1();
        B2();
        B3();
    }

    private static void B1() {
        BTree tree = new BTree(2);
        tree.insert(51);
        tree.insert(12);
        tree.insert(45);
        tree.insert(36);
        tree.insert(89);
        tree.insert(82);
        tree.insert(46);
        tree.insert(20);
        tree.insert(28);
        tree.insert(37);
        tree.insert(17);
        tree.insert(32);
        tree.print();
    }

    private static void B2() {
        BTree tree = new BTree(3);
        tree.insert(47);
        tree.insert(64);
        tree.insert(33);
        tree.insert(56);
        tree.insert(76);
        tree.insert(14);
        tree.insert(7);
        tree.insert(5);
        tree.insert(7);
        tree.insert(63);
        tree.insert(1);
        tree.insert(87);
        tree.insert(32);
        tree.insert(51);
        tree.insert(87);
        tree.remove(7);
        tree.remove(5);
        tree.remove(56);
        tree.remove(64);
        tree.remove(51);
        tree.print();
    }

    private static void B3() {
        BTree tree = new BTree(4);
        tree.insert( 30);
        tree.insert( 24);
        tree.insert( 50);
        tree.insert( 27);
        tree.insert( 84);
        tree.insert( 89);
        tree.insert( 92);
        tree.insert( 96);
        tree.insert( 46);
        tree.insert( 13);
        tree.insert( 69);
        tree.insert( 19);
        tree.remove( 96);
        tree.remove( 50);
        tree.remove( 24);
        tree.insert( 97);
        tree.insert( 69);
        tree.insert( 9);
        tree.remove( 97);
        tree.remove( 27);
        tree.insert( 97);
        tree.insert( 55);
        tree.insert( 86);
        tree.remove( 97);
        tree.remove( 55);
        tree.remove( 13);
        tree.remove( 19);
        tree.remove( 69);
        tree.print();
    }
}
