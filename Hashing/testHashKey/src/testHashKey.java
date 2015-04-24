/**
 * Created by Zhenbang Xiao on 2015/4/23 0023.
 */

import java.io.*;

public class testHashKey {

    public static int size = 195010037;

    public static void main(String[] args) {
        getHashKey("seasicknesses");
    }

    public static int getHashKey(String word) {
//this is the primary hash key function - it should return a number which is a slot in the hash table
//for words, a good strategy is to raise each character to successive powers of the alphabet size
//assume that the alphabet is ASCII characters - a total of 256 possibilities
//each successive character value should be raised to successive powers of 256
//the whole thing is added together and then moduloed to create a hash table index
        long tempMod;
        long tempSum = 0l;
        try {
            byte[] asciiSeq = word.getBytes("US-ASCII");
            long[] tempHash = new long[word.length()];
            //System.out.println(asciiSeq.length);
            if (asciiSeq.length <= 12) {
                for (int i = 0; i < asciiSeq.length; i++) {
                    tempHash[i] = (long) ((asciiSeq[i] - 96) * Math.pow(27, i));
                    //System.out.println(asciiSeq[i] + ", " + (asciiSeq[i] - 96) + " : " + tempHash[i]);
                }
            } else {
                byte[] tmpSeq = new byte[12];
                int count = 0;
                while (count <= tmpSeq.length-1) {
                    tmpSeq[count] = asciiSeq[asciiSeq.length - 1 - count];
                    System.out.println(count + " : " + tmpSeq[count]);
                    count++;
                }
                for (int i = 0; i < tmpSeq.length; i++) {
                    tempHash[i] = (long) ((tmpSeq[i] - 96) * Math.pow(27, i));
                    System.out.println(asciiSeq[i] + ", " + (asciiSeq[i] - 96) + " : " + tempHash[i]);
                }
            }
            for (int i = 0; i < tempHash.length; i++) {
                tempSum = tempSum + tempHash[i];
                //System.out.println("Sum: " + tempSum);
            }
            System.out.println("The hash code for " + word + " is " + tempSum);
            //return (int) word.charAt(word.length() - 1);
            tempMod = tempSum % size;
            return (int) (tempMod);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported character set");
            return -1;
        }
    }
}
