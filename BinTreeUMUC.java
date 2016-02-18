/*
 * File:    BinTreeUMUC.java
 * Author:  Brandon Pearce
 * Date:    09/09/2015
 * Purpose: Generic Binary Tree Class with internal Node Class
 */
package project2;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Angmar
 */
public class BinTreeUMUC <T extends Comparable<T>> {
    NodeBinTree<T> root = new NodeBinTree<>(null,null,null, 0);
    Scanner scanner = new Scanner(System.in);
    
    private class NodeBinTree<T extends Comparable<T>> {
        T x;
        NodeBinTree leftChild;
        NodeBinTree rightChild;
        int level;
        
        public NodeBinTree (T x, NodeBinTree<T> leftChild, NodeBinTree<T> rightChild, int level) {
            this.x = x;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.level = level;
        }
    }
    
    public BinTreeUMUC() {
    }
    
    public BinTreeUMUC(Scanner scanner) {
        T obj;
        int level = 0;
        while (scanner.hasNext()) { //while there is a next object
            String s = scanner.next(); // string of the whole line
            String[] sReg = s.split("\\s*,\\s*"); //regex delimiter
            for (int i = 0;i<sReg.length;i++) {
                if (sReg[i].length() == 2) {
                    String intStr = sReg[i].substring(0,1);
                    level = Integer.parseInt(intStr); //parse level
                    s = sReg[i].substring(1); //substring of object
                    obj = (T) s; //cast T to s
                    System.out.println("Object: " + obj + " ; Level: " + level);
                } else {
                    String intStr = sReg[i].substring(0, 2);
                    level = Integer.parseInt(intStr);
                    s = sReg[i].substring(2);
                    obj = (T) s;
                    System.out.println("Object: " + obj + " ; Level: " + level);
                } 
                addElem(obj, level); // add the object to the tree with its level
                // addElem then calls insertNode()
            }
             
        }
    }
    
    private NodeBinTree<T> insertNode(NodeBinTree<T> node, NodeBinTree<T> newNode, int level) {
        System.out.println("@insertNode: Obj " + node.x + " comparing to newObj: " + newNode.x );
        
        if (node.x == null) {
            node.x = newNode.x;
            System.out.println("@insertNode: added: " + node.x);
        } else if (node.x.compareTo(newNode.x) < 0 ) {  // if new obj is < than comapred obj 
            if (node.leftChild != null)
                insertNode(node.leftChild, newNode, level); // if there already node, recurse
            else {// will be added to the left
                node.leftChild = newNode;
                System.out.println("@insertNode: added: " + node.leftChild.x + " to right of " + node.x);
            }    
        } else {  // compare to rightChild
            if (node.rightChild != null) // if node exists, recurse
                insertNode(node.rightChild, newNode, level);
            else {  //will be added to right 
                node.rightChild = newNode;
                System.out.println("@insertNode: added: " + node.rightChild.x + " to right of " + node.x);
            }
        }    
        return node;
    }
    
    public void addElem (T element, int level) {
        NodeBinTree<T> node = new NodeBinTree<>(element, null, null, level);
        System.out.println("@addElem: adding: " + node.x);
        insertNode(root, node, level);
    }
    
    protected void visit(NodeBinTree<T> p) {
        System.out.print(p.x + ", ");
    }
    
    public String toPreOrderString(NodeBinTree<T> p) {
        String s = null;
        String c = null;
        visit(p);
        if (p.leftChild != null)
            toPreOrderString(p);
        if (p.rightChild != null)
            toPreOrderString(p);
        return s;
    }
    
    public String toInOrderString(NodeBinTree<T> p) {
        String s = null;
        if (p.leftChild != null) 
            toInOrderString(p);
        visit (p);
        if (p.rightChild != null)
            toInOrderString(p);
        return s;
    }
    
    public String toPostOrderString(NodeBinTree<T> p) {
        String s = null;
        if (p.leftChild != null)
            toPostOrderString(p);
        if (p.rightChild != null)
            toPostOrderString(p);
        visit (p);
        return s;
    }
    
    public String toLevelOrderString(NodeBinTree<T> root) {
        String s = null;
        Queue<NodeBinTree<T>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            NodeBinTree<T> node = q.poll();
            visit (node);
            if (node.leftChild != null)
                q.add(node.leftChild);
            if (node.rightChild != null)
                q.add(node.rightChild);
        }
        return s;
    }
    
    public int countNodes(NodeBinTree<T> p) {
        if (p == null)
            return 0;
        else {
            if (p.leftChild == null && p.rightChild == null)
                return 1;
            else
                return (1 + (countNodes(p.leftChild) + countNodes(p.rightChild)));
        }    
    }
    
    public int countLeafs(NodeBinTree<T> p) {
        if (p == null)
            return 0;
        if (p.leftChild == null && p.rightChild == null)
            return 1;
        else
            return countLeafs(p.leftChild) + countLeafs(p.rightChild);
    }
    
    public int countLeft(NodeBinTree<T> p) {
        int n = 0;
        if (p.leftChild != null)
            n += 1 + countLeft(p.leftChild);
        if (p.rightChild != null)
            n += countLeft(p.rightChild);
        return n;
    }
    
    public int countRight(NodeBinTree<T> p) {
        int n = 0;
        if (p.rightChild != null)
            n += 1 + countRight(p.rightChild);
        if (p.leftChild != null)
            n += countRight(p.leftChild);
        return n;
    }
    
    public void preOrderTraverse() {
        preOrderH(root);
    }
    public void inOrderTraverse() {
        inOrderH(root);
    }
    public void postOrderTraverse() {
        postOrderH(root);
    }
    public void levelOrderTraverse() {
        levelOrderH(root);
    }
    private void preOrderH(NodeBinTree<T> p) {
        try {
            if (p.x != null) {
                System.out.print(p.x + ", ");
                preOrderH(p.leftChild);
                preOrderH(p.rightChild);
            }    
        } catch (NullPointerException np) {
                //System.out.println(np);
        }
    }
    private void inOrderH(NodeBinTree<T> p) {
        try {
            if (p.x != null) {
                inOrderH(p.leftChild);
                System.out.print(p.x + ", ");
                inOrderH(p.rightChild);
            }
        } catch (NullPointerException np) {
            
        }
    }
    private void postOrderH(NodeBinTree<T> p) {
        try {
            if (p.x != null) {
                postOrderH(p.leftChild);
                postOrderH(p.rightChild);
                System.out.println(p.x + ", ");
            }
        } catch (NullPointerException np) {
            
        }
    }
    private void levelOrderH(NodeBinTree<T> p) {
        
    }
    
}
