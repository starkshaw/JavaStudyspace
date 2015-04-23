/**
 * Created by Zhenbang Xiao on 2015/4/23 0023.
 */

import java.io.*;

public class testHashKey {

    public static int size = 216569;

    public static void main(String[] args) {
        getHashKey("abbreviations");
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
            for (int i = 0; i < asciiSeq.length; i++) {
                tempHash[i] = (long) (asciiSeq[i] * Math.pow(26, i));
                System.out.println(asciiSeq[i] + " : " + tempHash[i]);
            }
            for (int i = 0; i < tempHash.length; i++) {
                tempSum = tempSum + tempHash[i];
                System.out.println("Sum: " + tempSum);
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
