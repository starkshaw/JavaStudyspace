/**
 * Created by Zhenbang Xiao on 2015/2/24 0024.
 */

import java.util.*;

public class Huffman {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter your sentence: ");
        String sentence = in.nextLine();
        String binaryString = "";      //this stores the string of binary code


        for (int i = 0; i < sentence.length(); i++) {        //go through the sentence
            int decimalValue = (int) sentence.charAt(i);      //convert to decimal
            String binaryValue = Integer.toBinaryString(decimalValue);      //convert to binary
            for (int j = 7; j > binaryValue.length(); j--) {
                binaryString += "0";           //this loop adds in those pesky leading zeroes
            }
            binaryString += binaryValue + " "; //add to the string of binary
        }
        System.out.println(binaryString);    //print out the binary


        int[] array = new int[256];      //an array to store all the frequencies

        for (int i = 0; i < sentence.length(); i++) {    //go through the sentence
            array[(int) sentence.charAt(i)]++;    //increment the appropriate frequencies

        }


        PriorityQueue<Tree> PQ = new PriorityQueue<Tree>();  //make a priority queue to hold the forest of trees


        for (int i = 0; i < array.length; i++) { //go through frequency array
            if (array[i] > 0) { //print out non-zero frequencies - cast to a char
                System.out.println("'" + (char) i + "' appeared " + array[i] + ((array[i] == 1) ? " time" : " times"));

                //FILL THIS IN:

                //MAKE THE FOREST OF TREES AND ADD THEM TO THE PQ
                Tree myTree = new Tree();
                myTree.frequency = array[i];
                myTree.root = new Node();
                myTree.root.letter = (char) i;
                PQ.add(myTree);
                //create a new Tree
                //set the cumulative frequency of that Tree
                //insert the letter as the root node
                //add the Tree into the PQ

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

            //when you're making the new combined tree, don't forget to assign a default root node (or else you'll get a null pointer exception)
            //if you like, to check if everything is working so far, try printing out the letter of the roots of the two trees you're combining
        }

        Tree HuffmanTree = PQ.poll();   //now there's only one tree left - get its codes


        //FILL THIS IN:

        //get all the codes for the letters and print them out
        //call the getCode() method on the HuffmanTree Tree object for each letter in the sentence

        //print out all the info

    }
}

