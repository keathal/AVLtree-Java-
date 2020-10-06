package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Tree myTree = new Tree(50);
        myTree.add(25, myTree);
        myTree.add(59, myTree);
        myTree.add(29, myTree);
        myTree.add(15, myTree);
        myTree.add(54, myTree);
        myTree.add(67, myTree);
        myTree.add(20, myTree);
        myTree.add(27, myTree);
        myTree.add(35, myTree);
        myTree.add(26, myTree);
        myTree.add(28, myTree);
//        myTree.delete(myTree, 25);
        //Tree foundTree = myTree.getTree(25, myTree);
//        System.out.println(myTree);
//        System.out.println("ordered by levels: ");
//        myTree.traverseLevelOrder(myTree);
//        System.out.println("element n 3: ");
//        System.out.println(myTree.getTreeByIndex(myTree, 3));
//        myTree.deleteByIndex(myTree, 1);
//        System.out.println(myTree);

        Random rnd = new Random();
        Tree testTree = new Tree(rnd.nextInt(100));
        for (int i=0; i<30; i++) {
            testTree.add(rnd.nextInt(300), testTree);
//            testTree = testTree.balance(testTree);
        }
//        TreeGUI gui = new TreeGUI(testTree);
//        System.out.println(testTree);
        Tree avlTree = testTree.balance(testTree);
        TreeGUI gui2 = new TreeGUI(avlTree);
//        System.out.println("\n" + avlTree);

    }

}
