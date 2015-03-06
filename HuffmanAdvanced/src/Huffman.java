/**
 * Created by Zhenbang Xiao on 2015/2/24 0024.
 */

import java.util.*;

public class Huffman {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your sentence: ");
        String sentence = in.nextLine();
        String encoded = "";
        String original = "";

        int[] counter = new int[sentence.length()]; // Each position is for the letter in the position in the word.

        for (char c : sentence.toCharArray()) {
            String ASCII_code = Integer.toBinaryString((int) c);
            if (ASCII_code.length() == 6) ASCII_code = "0" + ASCII_code; // Make to 7 chars long.

            original += ASCII_code + " ";
            counter[sentence.indexOf(c)]++; // Count the number of times this character appears.
        }

        System.out.println(original + "\n");

        PriorityQueue<Tree> PQ = new PriorityQueue<Tree>();  //make a priority queue to hold the forest of trees

        for (int i = 0; i < counter.length; i++) {

            if (counter[i] != 0) {
                // Print number of times each char appeared.
                System.out.println("'" + sentence.charAt(i) + "' appeared " + counter[i] + " time" + ((counter[i] > 1) ? "s" : ""));

                // Create a tree with one node and add to priority que.
                PQ.add(Tree.growTree(counter[i], sentence.charAt(i))); // Create a new tree with frequency and char.
            }

        }


        while (PQ.size() > 1) {             //while there are two or more Trees left in the forest

            //FILL THIS IN:

            //IMPLEMENT THE HUFFMAN ALGORITHM
            Tree firstTree = PQ.poll();
            Tree secondTree = PQ.poll();
            Tree comboTree = new Tree();

            comboTree.frequency = firstTree.frequency + secondTree.frequency;
            comboTree.root = new Node();
            Tree totalTree = Tree.growTree(comboTree.frequency, firstTree, secondTree);
            PQ.add(totalTree);
            //when you're making the new combined tree, don't forget to assign a default root node (or else you'll get a null pointer exception)
            //if you like, to check if everything is working so far, try printing out the letter of the roots of the two trees you're combining
        }

        Tree HuffmanTree = PQ.poll();   //now there's only one tree left - get its codes
        System.out.println();
        for (char c : sentence.toCharArray()) {
            encoded += HuffmanTree.getCode(c) + " ";
        }
        System.out.println(encoded);
        double original_bits = countBits(original);
        double encoded_bits = countBits(encoded);
        double compressed = Math.round((encoded_bits / original_bits) * 10000) / 100.00;
        System.out.println("Compressed size is " + (int) encoded_bits + " bits / " + (int) original_bits + " bits = " + compressed + "%\n");
    }

    public static double countBits(String sentence) {
        int count = sentence.length();
        for(char c : sentence.toCharArray()) {
            if(c == ' ') count--; // Decrement from string length count if space is found.
        }
        return (double) count;
        //FILL THIS IN:

        //get all the codes for the letters and print them out
        //call the getCode() method on the HuffmanTree Tree object for each letter in the sentence

        //print out all the info

    }
}

