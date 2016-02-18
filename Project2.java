/*
 * File:    
 * Author:  Brandon Pearce
 * Date:    04/xx/2015
 * Purpose: 
 */
package project2;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Angmar
 */
public class Project2 {

    static Scanner scanner;
    static int choice;
    static BinTreeUMUC<String> binTree;
    static boolean quit = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner fileScan = new Scanner(System.in);
        scanner = new Scanner(System.in);
        
        try {
            File myFile = new File("F:\\Documents\\binarytree.txt");
            fileScan = new Scanner(myFile);
        } catch (java.io.FileNotFoundException io) {
            System.out.println(io);
        }
        binTree = new BinTreeUMUC<>(fileScan);
        
        System.out.println("-+:::::: Welcome to the Binary Tree Demo ::::::+-");
        System.out.println();
        displayMenu();
        
        while (!quit) {
            System.out.println();
            System.out.println("Please submit your query");
            System.out.print("> ");
            choices();
        }
                
    }
    
    public static void displayMenu() {
        System.out.println("*****************  Main Menu  *******************");
        System.out.println("*************************************************");
        System.out.println("[1] String toPreOrderString()");
        System.out.println("[2] String toInOrderString()");
        System.out.println("[3] String toPostOrderString()");
        System.out.println("[4] String toLevelOrderString()");
        System.out.println("[5] int countNodes()");
        System.out.println("[6] int countLeadfs()");
        System.out.println("[7] int countLeft()");
        System.out.println("[8] int countRight");
        System.out.println("[9]");
        System.out.println("[11] quit demo");
    }
    
    public static void choices() {
            try {
                choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("[1]");
                    binTree.preOrderTraverse();
                }
                if (choice == 2) {
                    System.out.println("[2]");
                    binTree.inOrderTraverse();
                }
                if (choice == 3) {
                    System.out.println("[3]");
                    binTree.postOrderTraverse();
                }
                if (choice == 4) {
                    System.out.println("[4]");
                    binTree.toLevelOrderString(binTree.root);
                }
                if (choice == 5) {
                    System.out.println("[5]");
                    System.out.println(binTree.countNodes(binTree.root));
                }
                if (choice == 6) {
                    System.out.println("[6]");
                    System.out.println(binTree.countLeafs(binTree.root));
                }
                if (choice == 7) {
                    System.out.println("[7]");
                    System.out.println(binTree.countLeft(binTree.root));
                }
                if (choice == 8) {
                    System.out.println("[8]");
                    System.out.println(binTree.countRight(binTree.root));
                }
                else if (choice == 11) {
                    System.out.println("Exiting demo.  Goodbye!");
                    quit = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Only options 1-11 are allowed!");
                System.out.println(e);
                scanner.nextLine();
            }
    }
    
    
}
