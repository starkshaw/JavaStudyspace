/**
 * Created by Zhenbang Xiao on 2015/2/24 0024.
 */

import java.io.*;
import java.util.*;

public class Tree implements Comparable<Tree> {
    public Node root;
    public int frequency = 0;

    public Tree() {
        root = null;
    }

    public int compareTo(Tree obj) {
        if (frequency - obj.frequency > 0) {
            return 1;
        } else if (frequency - obj.frequency < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    String path = "error";

    public String getCode(char letter) {
        path = "";      // Initialize
        if(findPath(this.root, letter)==true){
            return path;
        }else{
            return "error";
        }
    }

    public boolean findPath(Node root, char letter) {
        if(root.letter == letter){
            return true; // Found the letter
        }
        if(root.leftChild != null && findPath(root.leftChild, letter)) {
            path = 0 + path;
            return true; // Pass true value back to the top!
        }
        if(root.rightChild != null && findPath(root.rightChild, letter)) {
            path = 1 + path;
            return true;
        }
        return false;
    }

    public static Tree growTree(int frequency, char letter) {
        Tree newTree = new Tree();
        newTree.frequency = frequency;
        newTree.root = new Node();
        newTree.root.letter = letter;
        return newTree;
    }

    public static Tree growTree(int frequency, Tree one, Tree two) {
        Tree newTree = new Tree();
        newTree.frequency = frequency;
        newTree.root = new Node();
        // Check which node should go where.
        if(one.frequency > two.frequency) {
            newTree.root.leftChild = one.root;
            newTree.root.rightChild = two.root;
        } else {
            newTree.root.leftChild = two.root;
            newTree.root.rightChild = one.root;
        }
        return newTree;
    }
}
